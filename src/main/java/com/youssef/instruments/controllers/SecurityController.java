package com.youssef.instruments.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur gérant les pages de sécurité :
 * - Page de connexion (/login)
 * - Page d'accès refusé (/403)
 */
@Controller
public class SecurityController {

    /**
     * Affiche la page de connexion personnalisée.
     * Atelier 07 — Login Form.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Affiche la page d'accès interdit (erreur 403).
     * Atelier 07 — Gestion des droits.
     */
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
