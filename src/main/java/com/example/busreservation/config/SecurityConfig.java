package com.example.busreservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and() // Enable CORS
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/register", "/users/login", "/users/health", "api/bookings/book",
                                "api/buses/search", "api/buses", "api/payments/process")
                        .permitAll() // Allow these endpoints without authentication
                        .anyRequest().authenticated() // All other requests need to be authenticated
                );
        return http.build();
    }

    // CORS Configuration to allow requests from localhost:3000
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow specific origins
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization")); // Allowed headers
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed methods

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
