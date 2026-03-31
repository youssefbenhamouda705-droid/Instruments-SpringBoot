package com.youssef.instruments.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.youssef.instruments.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}
