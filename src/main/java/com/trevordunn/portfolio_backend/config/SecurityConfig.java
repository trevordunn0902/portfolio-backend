package com.trevordunn.portfolio_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // disable CSRF for API POST requests
            .authorizeHttpRequests()
                .requestMatchers("/api/contact/**").permitAll() // allow contact POST without auth
                .anyRequest().authenticated()
            .and()
            .httpBasic(); // enable basic auth for other endpoints
        return http.build();
    }
}
