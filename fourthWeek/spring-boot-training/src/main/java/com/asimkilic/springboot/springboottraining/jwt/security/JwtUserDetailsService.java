package com.asimkilic.springboot.springboottraining.jwt.security;

import com.asimkilic.springboot.springboottraining.entity.User;
import com.asimkilic.springboot.springboottraining.service.entityservice.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntityService.findByUsername(username);
        return JwtUserDetails.create(user);

    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        User user = userEntityService.findById(id);
        return JwtUserDetails.create(user);

    }
}
