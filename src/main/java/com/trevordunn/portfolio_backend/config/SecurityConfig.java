// src/main/java/com/trevordunn/portfolio_backend/config/SecurityConfig.java

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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Value("${admin.user:trevor}")
    private String adminUser;

    @Value("${admin.password:mysecurepassword123}")
    private String adminPassword;

    @Value("${spring.security.user.name:user}")
    private String defaultUser;

    @Value("${spring.security.user.password:password}")
    private String defaultPassword;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/contact").permitAll()
                .requestMatchers("/api/skills").permitAll()
                .requestMatchers("/api/skills/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // ✅ protect admin check
                .anyRequest().permitAll()
            )
            .httpBasic()
            .and()
            .cors(); // ✅ enable CORS globally
        return http.build();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
            .username(adminUser)
            .password(encoder.encode(adminPassword))
            .roles("ADMIN")
            .build();

        UserDetails normalUser = User.builder()
            .username(defaultUser)
            .password(encoder.encode(defaultPassword))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, normalUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Allow frontend dev server for all endpoints
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
