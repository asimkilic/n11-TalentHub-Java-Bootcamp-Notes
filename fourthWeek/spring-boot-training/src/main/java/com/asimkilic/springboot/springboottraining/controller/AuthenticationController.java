package com.asimkilic.springboot.springboottraining.controller;

import com.asimkilic.springboot.springboottraining.dto.UserRequestDto;
import com.asimkilic.springboot.springboottraining.entity.User;
import com.asimkilic.springboot.springboottraining.jwt.security.JwtTokenGenerator;
import com.asimkilic.springboot.springboottraining.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {


        return authenticationService.login(userRequestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto) {

        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        try {
            authenticationService.validateUserRequest(username);

        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        User user = authenticationService.register(username, password);
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);

    }
}

