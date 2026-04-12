package com.skillverse.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())//  Disable CSRF (REST API)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )  // Stateless session (JWT)
                //  Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",        // login/register (handled by Auth-Service ideally)
                                "/public/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/courses/**").permitAll() //Public read
                        .anyRequest().authenticated() //  Everything else secured
                )
                .authenticationProvider(authenticationProvider) // 🔹 Authentication provider
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // 🔹 JWT filter

        return http.build();
    }
}