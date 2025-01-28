package com.hcc.services;

import com.hcc.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    private final JwtUtil jwtUtil;

    public TokenService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    public String validateTokenAndGetUsername(String token) {
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Token is expired.");
        }
        return jwtUtil.getUsernameFromToken(token);
    }

}
