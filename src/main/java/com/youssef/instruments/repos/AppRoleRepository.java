package com.youssef.instruments.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.youssef.instruments.model.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByRoleName(String roleName);
}
