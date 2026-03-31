package com.youssef.instruments.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.youssef.instruments.model.AppRole;
import com.youssef.instruments.model.AppUser;
import com.youssef.instruments.repos.AppUserRepository;

/**
 * Service personnalisé d'authentification Spring Security.
 * Charge les utilisateurs depuis la base de données via JPA.
 * Implémente UserDetailsService (Atelier 08).
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Recherche de l'utilisateur dans la BDD
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable : " + username);
        }

        // Conversion des rôles en GrantedAuthority pour Spring Security
        List<GrantedAuthority> authorities = appUser.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toList());

        // Construction de l'objet UserDetails utilisé par Spring Security
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
