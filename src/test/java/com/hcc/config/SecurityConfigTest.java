package com.hcc.config;

import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

public class SecurityConfigTest {

    @Mock
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SecurityConfig securityConfig;

    public SecurityConfigTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticationBuilder() throws Exception {
        // given
        AuthenticationManagerBuilder authBuilder = mock(AuthenticationManagerBuilder.class);
        DaoAuthenticationConfigurer daoAuthConfig = mock(DaoAuthenticationConfigurer.class);

        when(authBuilder.userDetailsService(userDetailServiceImpl)).thenReturn(daoAuthConfig);
        when(daoAuthConfig.passwordEncoder(customPasswordEncoder.getPasswordEncoder()))
                .thenReturn(daoAuthConfig);

        // when
        securityConfig.configure(authBuilder);

        // then
        verify(authBuilder).userDetailsService(userDetailServiceImpl);
        verify(daoAuthConfig).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }

}