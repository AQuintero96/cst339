package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures form authentication using an in-memory demonstration account.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Provides password hashing and comparison.
     * @return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates the account required by Part 1.
     * @param encoder password encoder
     * @return in-memory account store
     */
    @Bean
    public InMemoryUserDetailsManager users(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("test")
                        .password(encoder.encode("test"))
                        .roles("USER")
                        .build());
    }

    /**
     * Defines public resources, protected pages, login, and logout.
     * @param http security configuration builder
     * @return configured security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.authorizeRequests(authorize -> authorize
                .antMatchers(
                        "/", "/login", "/error",
                        "/css/**", "/js/**", "/images/**",
                        "/favicon.ico", "/service/**")
                .permitAll()
                .anyRequest().authenticated());

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/orders/display", true)
                .failureUrl("/login?error")
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll());

        // CSRF protection stays enabled. Thymeleaf POST forms include the token.
        return http.build();
    }
}