package com.youssef.instruments.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.youssef.instruments.model.GenreMusical;

public interface GenreMusicalRepository extends JpaRepository<GenreMusical, Long> {
}