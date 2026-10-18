package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Protects application endpoints using GitHub OAuth2 login.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures public resources and the OAuth2 login flow.
     * @param http security configuration builder
     * @return configured security chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.authorizeRequests(authorize -> authorize
                .antMatchers(
                        "/", "/error", "/login", "/favicon.ico",
                        "/oauth2/**", "/login/oauth2/**")
                .permitAll()
                .anyRequest().authenticated());

        http.oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/service/test", true));

        // Retains the framework's CSRF and session protections.
        return http.build();
    }
}