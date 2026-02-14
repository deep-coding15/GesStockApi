package com.deep_coding15.GesStockApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // API REST → pas de CSRF
                .csrf(csrf -> csrf.disable())
                // Autorisations par endpoint
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC
                        .requestMatchers(
                            "auth/login",
                                "/api/health/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**")
                        .permitAll() // Autoriser les endpoints d'authentification

                        // ADMIN
                        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/roles/**").hasRole("ADMIN")

                        // GERANT + ADMIN
                        .requestMatchers(
                                "/api/v1/categories/**",
                                "/api/v1/produits/**",
                                "/api/v1/stocks/**",
                                "/api/v1/stock-mouvements/**")
                        .hasAnyRole("ADMIN", "GERANT")

                        // CAISSIER + GERANT + ADMIN
                        .requestMatchers(
                                "/api/v1/ventes/**",
                                "/api/v1/vente-lignes/**")
                        .hasAnyRole("ADMIN", "GERANT", "CAISSIER")

                        // TOUT LE RESTE
                        .anyRequest().authenticated() // Exiger une authentification pour les autres endpoints
                )
                .httpBasic(Customizer.withDefaults()) // Utiliser l'authentification HTTP Basic
                // Apres authentification, si exception
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}