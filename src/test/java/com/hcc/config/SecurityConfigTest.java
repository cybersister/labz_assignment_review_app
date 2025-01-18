package com.hcc.config;

import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;

import static org.mockito.Mockito.*;

public class SecurityConfigTest {

    @Mock
    UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    CustomPasswordEncoder customPasswordEncoder;

    @InjectMocks
    SecurityConfig securityConfig;

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

    @Test
    public void testHttpsSecurityConfigurations() throws Exception {
        // given


        // when


        // then

    }

}
