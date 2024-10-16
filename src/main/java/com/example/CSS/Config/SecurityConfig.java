package com.example.CSS.Config;

import com.example.CSS.Security.JwtTokenFilter;
import com.example.CSS.Security.JwtUtil;
import com.example.CSS.Services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HttpSecurity
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",
                                "/api/supplydocument/getAll",
                                "/api/supplydocument",
                                "/api/supplydocument/{id}",
                                "/api/warehouses/getAll",
                                "/api/warehouses",
                                "/api/warehouses/{id}"
                        ).permitAll()
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .addFilterBefore(jwtTokenFilter(http.getSharedObject(AuthenticationManager.class), jwtUtil()),
                        UsernamePasswordAuthenticationFilter.class); // Add custom JWT filter

        return http.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        return new JwtTokenFilter(authenticationManager, userDetailsService, jwtUtil); // Pass the AuthenticationManager, UserDetailsService, and JwtUtil
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(); // Create a JwtUtil bean
    }
}