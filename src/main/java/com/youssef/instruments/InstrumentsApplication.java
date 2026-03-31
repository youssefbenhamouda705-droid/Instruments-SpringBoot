package com.youssef.instruments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.youssef.instruments.model.AppRole;
import com.youssef.instruments.model.AppUser;
import com.youssef.instruments.model.GenreMusical;
import com.youssef.instruments.model.instruments;
import com.youssef.instruments.repos.AppRoleRepository;
import com.youssef.instruments.repos.AppUserRepository;
import com.youssef.instruments.repos.GenreMusicalRepository;
import com.youssef.instruments.service.instrumentsService;

@SpringBootApplication
public class InstrumentsApplication implements CommandLineRunner {

    @Autowired
    instrumentsService instrumentService;

    @Autowired
    GenreMusicalRepository genreMusicalRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(InstrumentsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // ===== Initialisation des instruments (si BDD vide) =====
        if (genreMusicalRepository.count() == 0) {

            GenreMusical rock = genreMusicalRepository.save(
                new GenreMusical(null, "Rock", "Musique Rock", null));
            GenreMusical jazz = genreMusicalRepository.save(
                new GenreMusical(null, "Jazz", "Musique Jazz", null));
            GenreMusical classique = genreMusicalRepository.save(
                new GenreMusical(null, "Classique", "Musique Classique", null));

            instrumentService.saveInstrument(
                new instruments(null, "Guitare", 1200.0, rock));
            instrumentService.saveInstrument(
                new instruments(null, "Piano", 5000.0, classique));
            instrumentService.saveInstrument(
                new instruments(null, "Violon", 3000.0, classique));
            instrumentService.saveInstrument(
                new instruments(null, "Batterie", 2500.0, jazz));
            instrumentService.saveInstrument(
                new instruments(null, "Saxophone", 1800.0, jazz));
        }

        // ===== Initialisation des rôles et utilisateurs (Atelier 08) =====
        if (appRoleRepository.count() == 0) {

            // Création des rôles
            AppRole roleAdmin = appRoleRepository.save(new AppRole("ADMIN"));
            AppRole roleUser = appRoleRepository.save(new AppRole("USER"));

            // Création de l'utilisateur admin (mot de passe haché avec BCrypt)
            AppUser admin = new AppUser("admin", passwordEncoder.encode("123"));
            admin.getRoles().add(roleAdmin);
            admin.getRoles().add(roleUser);
            appUserRepository.save(admin);

            // Création de l'utilisateur standard
            AppUser user1 = new AppUser("user1", passwordEncoder.encode("123"));
            user1.getRoles().add(roleUser);
            appUserRepository.save(user1);

            System.out.println("=== Utilisateurs initialisés ===");
            System.out.println("admin / 123  => rôles: ADMIN + USER");
            System.out.println("user1 / 123  => rôle: USER");
        }
    }
}