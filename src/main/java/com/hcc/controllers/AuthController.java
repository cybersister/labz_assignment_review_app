package com.hcc.controllers;

import com.hcc.dto.AuthCredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid
                                   AuthCredentialsRequest authCredentialsRequest) {
        System.out.println("Received login request: " + authCredentialsRequest);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(
                        authCredentialsRequest.getUsername(),
                        authCredentialsRequest.getPassword());

        Authentication authentication = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
        // performs the authentication

        String token = tokenService.gener

    }

    /*
        try {

            // Generate a JWT token upon successful authentication
            String token = tokenService.generateToken(authentication);

            // Return the token in a JSON response
            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException e) {
            // Return an error if authentication fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
     */



}
