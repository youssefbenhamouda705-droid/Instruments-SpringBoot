package com.youssef.instruments.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.youssef.instruments.model.AppUser;

/**
 * Repository JPA pour la gestion des utilisateurs.
 * Fournit findByUsername pour l'authentification Spring Security.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * Utilisé par MyUserDetailsService lors de l'authentification.
     */
    AppUser findByUsername(String username);
}
