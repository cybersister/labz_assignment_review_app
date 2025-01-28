package com.hcc.services;

import com.hcc.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TokenServiceTest {

    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateToken_validAuthentication_returnsToken() {
        // give
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("testToken");

        // when
        String token = tokenService.generateToken(authentication);

        // then
        assertEquals("testToken", token);

        verify(authentication, times(1)) .getPrincipal();
        verify(jwtUtil, times(1)).generateToken(userDetails);
    }
    // this method is called when the authentication process has already been verified

    @Test
    public void validateTokenAndGetUsername_validToken_returnsUsername() {
        // given
        String token = "validToken";

        when(jwtUtil.isTokenExpired(token)).thenReturn(false);
        when(jwtUtil.getUsernameFromToken(token)).thenReturn("testUser");

        // when
        String username = tokenService.validateTokenAndGetUsername(token);

        // then
        assertEquals("testUser", username);

        verify(jwtUtil, times(1)).isTokenExpired(token);
        verify(jwtUtil, times(1)).getUsernameFromToken(token);
    }

    @Test
    public void validateTokenAndGetUsername_expiredToken_throwsRuntimeException() {
        // given
        String token = "expiredToken";

        when(jwtUtil.isTokenExpired(token)).thenReturn(true);

        // when + then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            tokenService.validateTokenAndGetUsername(token);
        });

        assertEquals("Token is expired.", runtimeException.getMessage());

        verify(jwtUtil, times(1)).isTokenExpired(token);
        verify(jwtUtil, times(0)).getUsernameFromToken(token);
    }

    @Test
    public void validateTokenAndGetUsername_invalidToken_throwsRuntimeException() {
        // given
        String token = "invalidToken";

        when(jwtUtil.isTokenExpired(token)).thenReturn(false);
        when(jwtUtil.getUsernameFromToken(token)).thenThrow(
                new RuntimeException("Invalid token"));

        // when + then
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            tokenService.validateTokenAndGetUsername(token);
        });

        assertEquals("Invalid token", runtimeException.getMessage());

        verify(jwtUtil, times(1)).isTokenExpired(token);
        verify(jwtUtil, times(1)).getUsernameFromToken(token);
    }

}
