package com.example.busreservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/users/register", "/users/login", "/users/health" , "api/bookings/book" , "api/buses/search" , "api/payments/process").permitAll() // Allow these endpoints without authentication
                .anyRequest().authenticated() // All other requests need to be authenticated
            );
        return http.build();
    }
}
