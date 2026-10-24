package com.example.towatch.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/types", "/api/types/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/types").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/types/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/types/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/statuses", "/api/statuses/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/statuses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/statuses/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/statuses/{id}").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/auth/login", "/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)));

                return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
