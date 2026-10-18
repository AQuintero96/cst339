package com.gcu.security;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures database-backed form login and protects application routes.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Supplies the encoder compatible with existing inventory passwords.
     *
     * @return inventory password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new InventoryPasswordEncoder();
    }

    /**
     * Connects database account retrieval with password verification.
     *
     * @param userDetailsService database account lookup service
     * @param passwordEncoder password verification implementation
     * @return configured authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            InventoryUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    /**
     * Defines public resources, protected pages, login, and logout.
     *
     * @param http Spring Security configuration builder
     * @param authenticationProvider database authentication provider
     * @return configured security filter chain
     * @throws Exception if security configuration cannot be built
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider)
            throws Exception {

        http.authenticationProvider(authenticationProvider);

        http.authorizeRequests(authorize -> authorize
                // Allows internal error rendering without a redirect loop.
                .requestMatchers(request ->
                        request.getDispatcherType() == DispatcherType.ERROR)
                .permitAll()
                .antMatchers(
                        "/login",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/webjars/**",
                        "/favicon.ico")
                .permitAll()
                // Includes the home page and every inventory operation.
                .anyRequest().authenticated());

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/inventory", true)
                .failureUrl("/login?error")
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID"));

        // Retains CSRF protection and changes the session ID after login.
        http.sessionManagement(session -> session
                .sessionFixation().changeSessionId());

        return http.build();
    }
}