package com.asimkilic.springboot.springboottraining.jwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenGenerator {
    @Value("${com.asimkilic.springboot.springboottraining.jwt.security.app.key}")
    private String APP_KEY;
    @Value("${com.asimkilic.springboot.springboottraining.jwt.security.expire.time}")
    private Long EXPIRE_TIME;


    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date((new Date()).getTime() + EXPIRE_TIME);
        String token = Jwts.builder()
                .setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_KEY)
                .compact();

        return token;
    }

    public Long findUserIdByToken(String token) {
        String userIdStr = parseToken(token)
                .getBody()
                .getSubject();
        return Long.parseLong(userIdStr);
    }

    public boolean validateToken(String token) {
        boolean isValid;
        try {
            parseToken(token);
            isValid = !isTokenExpired(token);

        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isTokenExpired(String token) {
        Jws<Claims> claimsJws = parseToken(token);
        Date expirationDate = claimsJws.getBody().getExpiration();
        return expirationDate.before(new Date());
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(APP_KEY)
                .parseClaimsJws(token);
    }

}
