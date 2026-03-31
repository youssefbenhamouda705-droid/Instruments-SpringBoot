package com.youssef.instruments.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.youssef.instruments.service.MyUserDetailsService;

/**
 * Configuration Spring Security (Ateliers 07 + 08).
 * Utilise SecurityFilterChain (moderne, compatible Spring Boot 3+/4+).
 * Gère : login, logout, rôles, BCrypt, page 403.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * Bean BCryptPasswordEncoder pour le hashage des mots de passe.
     * Atelier 07 — Section BCrypt.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provider d'authentification.
     * Spring Security 7 : DaoAuthenticationProvider(UserDetailsService) prend
     * le UserDetailsService en argument, puis setPasswordEncoder séparément.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configuration principale de la sécurité HTTP.
     * Atelier 07 — Protection des URLs par rôle.
     * Atelier 08 — Authentification via BDD.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth

                // Ressources statiques et webjars accessibles sans authentification
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()

                // Page de connexion accessible à tous
                .requestMatchers("/login", "/login/**").permitAll()

                // Actions réservées aux ADMIN (supprimer, modifier, créer)
                .requestMatchers("/supprimerInstrument", "/modifierInstrument",
                                 "/showCreate", "/saveInstrument").hasRole("ADMIN")

                // Toutes les autres pages nécessitent une authentification
                .anyRequest().authenticated()
            )

            // Configuration de la page de login personnalisée (Atelier 07)
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/ListeInstruments", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )

            // Configuration de la déconnexion (Atelier 07)
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // Page d'accès refusé (403) — Atelier 07
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/403")
            );

        return http.build();
    }
}
