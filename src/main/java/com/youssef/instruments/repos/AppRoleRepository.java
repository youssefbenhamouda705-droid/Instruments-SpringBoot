package com.youssef.instruments.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.youssef.instruments.model.AppRole;

/**
 * Repository JPA pour la gestion des rôles.
 */
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    /**
     * Recherche un rôle par son nom (ex: "ADMIN", "USER").
     */
    AppRole findByRoleName(String roleName);
}
