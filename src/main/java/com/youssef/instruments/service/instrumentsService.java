package com.youssef.instruments.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.youssef.instruments.dto.InstrumentDTO;
import com.youssef.instruments.model.GenreMusical;
import com.youssef.instruments.model.instruments;

public interface instrumentsService {

    instruments saveInstrument(instruments i);
    instruments updateInstrument(instruments i);
    void deleteInstrument(instruments i);
    void deleteInstrumentById(Long id);
    instruments getInstrument(Long id);
    List<instruments> getAllInstruments();
    Page<instruments> getAllInstrumentsParPage(int page, int size);

    List<instruments> findByNomInstrument(String nom);
    List<instruments> findByNomInstrumentContains(String nom);
    List<instruments> findByNomPrix(String nom, Double prix);
    List<instruments> findByGenreMusical(GenreMusical g);
    List<instruments> findByGenreMusicalIdGenre(Long id);
    List<instruments> findByOrderByNomInstrumentAsc();
    List<instruments> trierInstrumentsNomsPrix();
    List<GenreMusical> getAllGenres();

    // ===== Méthodes DTO (Atelier DTO) =====
    InstrumentDTO convertEntityToDto(instruments instrument);
    instruments convertDtoToEntity(InstrumentDTO dto);
    InstrumentDTO saveInstrumentDTO(InstrumentDTO dto);
    InstrumentDTO getInstrumentDTO(Long id);
}