package com.hcc.config;

import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Used to load user-specific data during authentication.
     */
    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    /**
     * A custom password encoder that is used to encode and verify passwords.
     */
    @Autowired
    CustomPasswordEncoder customPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl)
                .passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll() // Allow public access to certain endpoints
                .anyRequest().authenticated() // Require authentication for all other requests
                .and()
            .formLogin()
                .loginPage("/login") // Custom login page
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
    */

    // proper wiring of HttpSecurity is what is missing from this implementation ...
    // currently, Spring's default security behavior applies, in other words, all
    //  endpoints require authentication
    // this will need to be uncommented and customized to match the application's needs

    // todo
    // - uncomment and customize HttpSecurity
    // - ensure that there is password hashing in the registration portion
    //   - when saving a new user to the database, hash their password before saving
    //     using the <BCryptPasswordEncoder>
    // - ensure all stored passwords are hashed using <BCryptPasswordEncoder>

}
