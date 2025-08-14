// src/main/java/com/example/portfolio/config/SecurityConfig.java

package com.trevordunn.portfolio_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${ADMIN_USER:trevor}") // default to 'trevor' if missing
    private String adminUser;

    @Value("${ADMIN_PASSWORD:mysecurepassword123}") // default if missing
    private String adminPassword;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/contact").permitAll()
                .requestMatchers("/api/skills").permitAll()
                .requestMatchers("/api/skills/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
            .username(adminUser)
            .password(encoder.encode(adminPassword))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
