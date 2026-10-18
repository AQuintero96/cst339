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
 * Configures form authentication with MongoDB accounts and BCrypt hashes.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Provides BCrypt password comparison.
     * @return password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Connects database account lookup with password verification.
     * @param service database-backed user details
     * @param encoder password verification implementation
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
     * Protects web pages while retaining public REST access for Part 2.
     * @param http security builder
     * @param provider database authentication provider
     * @return security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http, DaoAuthenticationProvider provider)
            throws Exception {

        http.authenticationProvider(provider);

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

        // CSRF remains enabled for the Thymeleaf POST forms.
        return http.build();
    }
}