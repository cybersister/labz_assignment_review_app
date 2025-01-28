package com.hcc.controllers;

import com.hcc.dto.AuthCredentialsRequest;
import com.hcc.services.TokenService;
import com.sun.net.httpserver.HttpsServer;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenService tokenService;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void login_successfulAuthentication_returnsToken() {
        // given
        AuthCredentialsRequest authCredentialsRequest =
                new AuthCredentialsRequest("testUser", "password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager
                .authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.generateToken(authentication)).thenReturn("testToken");

        // when
        ResponseEntity<?> response = authController.login(authCredentialsRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals(true, responseBody.get("success"));
        assertEquals("Login successful", responseBody.get("message"));

        Map<String, String> data = (Map<String, String>) responseBody.get("data");
        assertEquals("testToken", data.get("token"));

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService, times(1))
                .generateToken(authentication);
    }

    @Test
    public void login_failedAuthentication_returnsUnauthorized() {
        // given
        AuthCredentialsRequest authCredentialsRequest =
                new AuthCredentialsRequest("testUser", "wrongPassword");

        when(authenticationManager
                .authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password."));

        // when
        ResponseEntity<?> response = authController.login(authCredentialsRequest);

        // then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals(false, responseBody.get("success"));

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService, times(0))
                .generateToken(any(Authentication.class));
    }

    @Test
    public void validate_validToken_returnsUsername() {
        // given
        String token = "validToken";

        when(tokenService.validateTokenAndGetUsername(token)).thenReturn("testUser");

        // when
        ResponseEntity<?> response = authController.validate(Map.of("token", token));

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals("testUser", responseBody.get("username"));
        assertEquals(true, responseBody.get("valid"));

        verify(tokenService, times(1))
                .validateTokenAndGetUsername(token);
    }

    @Test
    public void validate_missingToken_returnsBadRequest() {
        // given
        // nothing ...

        // when
        ResponseEntity<?> response = authController.validate(Map.of());

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals(false, responseBody.get("success"));
        assertEquals("Token validation failed", responseBody.get("message"));
        assertEquals("Token is required or cannot be empty",
                responseBody.get("error"));

        verify(tokenService, times(0))
                .validateTokenAndGetUsername(anyString());
    }

    @Test
    public void validate_invalidToken_returnsUnauthorized() {
        // given
        String token = "invalidToken";

        when(tokenService.validateTokenAndGetUsername(token))
                .thenThrow(new RuntimeException("Invalid token"));

        // when
        ResponseEntity<?> response = authController.validate(Map.of("token", token));

        // then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals(false, responseBody.get("success"));
        assertEquals("Token validation failed", responseBody.get("message"));
        assertEquals("Invalid token", responseBody.get("error"));

        verify(tokenService, times(1))
                .validateTokenAndGetUsername(token);
    }

}
