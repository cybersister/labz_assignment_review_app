package com.hcc.controllers;

import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

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
        try {
            System.out.println("Received login request: " + authCredentialsRequest);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                    UsernamePasswordAuthenticationToken(
                    authCredentialsRequest.getUsername(),
                    authCredentialsRequest.getPassword());
            // creates an authentication token with the username and password provided from
            //  the AuthCredentialsRequest object

            Authentication authentication = authenticationManager
                    .authenticate(usernamePasswordAuthenticationToken);
            // the authentication token is passed to the AuthenticationManager to perform
            //  the authentication
            // if the authentication is successful, it returns an Authentication object
            //  containing user details and roles

            String token = tokenService.generateToken(authentication);
            // generates a jwt token after a successful authentication

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Login successful",
                    "data", Map.of("token", token)
            ));
            // returns the token in a json response
            // the client receives the jwt which it can store and use for future use
        } catch (BadCredentialsException e) {
            // catches an exception thrown by authenticationManager.authenticate() when
            //  the credentials are invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "success", false,
                            "message", "Invalid username or password",
                            "error", e.getMessage()
                    ));
            // returns an error if the authentication fails
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        if (token == null || token.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "message", "Token validation failed",
                            "error", "Token is required or cannot be empty"
                    ));
        }

        try {
            String username = tokenService.validateTokenAndGetUsername(token);

            return ResponseEntity.ok(Map.of("username", username, "valid", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "success", false,
                            "message", "Token validation failed",
                            "error", e.getMessage()));
        }
    }
    // responsible for checking if a provided jwt is valid

}
