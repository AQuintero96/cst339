package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gcu.business.UserBusinessService;

/**
 * Secures web pages and REST endpoints using MongoDB accounts.
 * Supports both form login and HTTP Basic authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Provides BCrypt password verification.
     * @return password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Connects stored accounts with BCrypt password verification.
     * @param service database account lookup
     * @param encoder password encoder
     * @return authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserBusinessService service, PasswordEncoder encoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    /**
     * Requires authentication for orders and REST endpoints.
     * @param http security configuration builder
     * @param provider database authentication provider
     * @return configured security chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, DaoAuthenticationProvider provider)
            throws Exception {

        http.authenticationProvider(provider);

        // REST endpoints are no longer included in the public paths.
        http.authorizeRequests(authorize -> authorize
                .antMatchers(
                        "/", "/login", "/error",
                        "/css/**", "/js/**", "/images/**", "/favicon.ico")
                .permitAll()
                .anyRequest().authenticated());

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/orders/display", true)
                .failureUrl("/login?error")
                .permitAll());

        // API clients send credentials through the Authorization header.
        http.httpBasic(basic -> basic.realmName("CST339"));

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll());

        // CSRF remains enabled. The activity's API requests use GET.
        return http.build();
    }
}