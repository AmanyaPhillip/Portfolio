package com.example.bankapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.bankapp.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    AccountService accountService;

    // Define the password encoder bean using BCrypt for secure password hashing
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configure the security filter chain for HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection (for simplicity; enable in production)
            .csrf(csrf -> csrf.disable())
            // Allow unauthenticated access to the registration page, require authentication for all other requests
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated())
            // Configure form-based login
            .formLogin(form -> form
                .loginPage("/login") // Custom login page
                .loginProcessingUrl("/login") // URL to submit login credentials
                .defaultSuccessUrl("/dashboard", true) // Redirect after successful login
                .permitAll())
            // Configure logout behavior
            .logout(logout -> logout
                .invalidateHttpSession(true) // Invalidate session on logout
                .clearAuthentication(true) // Clear authentication data
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Logout URL
                .logoutSuccessUrl("/login?logout") // Redirect after logout
                .permitAll())
            // Allow H2 console or other frames from same origin (if needed)
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            );
            
        return http.build();
    }

    // Configure authentication manager to use AccountService and password encoder
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

}
