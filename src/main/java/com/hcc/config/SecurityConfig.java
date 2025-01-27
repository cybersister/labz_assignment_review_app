package com.hcc.config;

import com.hcc.filters.JwtFilter;
import com.hcc.services.UserDetailServiceImpl;
import com.hcc.utils.CustomPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private UserDetailServiceImpl userDetailServiceImpl;
    private CustomPasswordEncoder customPasswordEncoder;
    private JwtFilter jwtFilter;

    public SecurityConfig(UserDetailServiceImpl userDetailServiceImpl,
                          CustomPasswordEncoder customPasswordEncoder,
                          JwtFilter jwtFilter) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.customPasswordEncoder = customPasswordEncoder;
        this.jwtFilter = jwtFilter;
    }

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl)
                .passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        // do not disable this lot here just for now!!

        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        http = http.exceptionHandling()
                .authenticationEntryPoint((request, response, exception) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            exception.getMessage());
                }).and();

        http.authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
