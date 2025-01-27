package com.hcc.config;

import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Responsible for configuring authentication. It defines how user are authenticated,
 * specifies the password encoding mechanism, and sets up the security rules for this
 * application.
 *
 * This class does not directly verify passwords but ensures that the necessary services
 * and encoders are properly wired into the authentication manager.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailServiceImpl userDetailServiceImpl;

    private CustomPasswordEncoder customPasswordEncoder;

    public SecurityConfig(UserDetailServiceImpl userDetailServiceImpl, CustomPasswordEncoder customPasswordEncoder) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    /**
     * Configures the {@link AuthenticationManagerBuilder} to use the custom user details
     * service and password encoder for authentication. It sets up the authentication
     * manager to fetch user-specific data using the {@link UserDetailServiceImpl} and to
     * validate passwords using the custom password encoder provided in the
     * {@link CustomPasswordEncoder}
     *
     * @param auth the {@link AuthenticationManagerBuilder} used to configure the
     *             authentication manager
     * @throws Exception thrown if an error occurs while setting up the authentication
     *                   manager
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl)
                .passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }

    /**
     * Configures the HTTP security settings for the API endpoints of this application.
     * This method defines the security rules for handling requests to various API
     * endpoints and specifies access control rules for different endpoints based on their
     * authentication requirements.
     * @param http the {@link HttpSecurity} object to configure the security settings
     * @throws Exception thrown if an error occurs while configuring the security settings
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            // disabled csrf because we're using tokens here
            .authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                // allows anyone who is authenticated or not to access this endpoint
                .antMatchers("/api/auth/validate").authenticated()
                // requires validated authentication to access (via a token) ...
                .antMatchers("/api/assignments", "/api/assignments/**")
                    .authenticated()
                .anyRequest().authenticated();
                // protects all other endpoints ... must be authenticated
    }
    // todo -> test this method

}
