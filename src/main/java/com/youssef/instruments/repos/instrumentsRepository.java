package com.youssef.instruments.repos;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.youssef.instruments.model.GenreMusical;
import com.youssef.instruments.model.instruments;

public interface instrumentsRepository extends JpaRepository<instruments, Long> {

    
    List<instruments> findByNomInstrument(String nom);

    List<instruments> findByNomInstrumentContains(String nom);

    @Query("select i from instruments i where i.nomInstrument like %?1 and i.prixInstrument > ?2")
    List<instruments> findByNomPrix(String nom, Double prix);

    @Query("select i from instruments i where i.nomInstrument like %:nom and i.prixInstrument > :prix")
    List<instruments> findByNomPrixParam(@Param("nom") String nom, @Param("prix") Double prix);

    @Query("select i from instruments i where i.genreMusical = ?1")
    List<instruments> findByGenreMusical(GenreMusical genreMusical);

    List<instruments> findByGenreMusicalIdGenre(Long id);

    List<instruments> findByOrderByNomInstrumentAsc();

    @Query("select i from instruments i order by i.nomInstrument ASC, i.prixInstrument DESC")
    List<instruments> trierInstrumentsNomsPrix();

    Page<instruments> findAll(Pageable pageable);
}