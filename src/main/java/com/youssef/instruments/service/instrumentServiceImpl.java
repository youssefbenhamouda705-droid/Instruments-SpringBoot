package com.youssef.instruments.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.youssef.instruments.dto.InstrumentDTO;
import com.youssef.instruments.model.GenreMusical;
import com.youssef.instruments.model.instruments;
import com.youssef.instruments.repos.GenreMusicalRepository;
import com.youssef.instruments.repos.instrumentsRepository;

@Service
public class instrumentServiceImpl implements instrumentsService {

    @Autowired
    private instrumentsRepository instrumentRepository;

    @Autowired
    private GenreMusicalRepository genreMusicalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public instruments saveInstrument(instruments i) {
        return instrumentRepository.save(i);
    }

    @Override
    public instruments updateInstrument(instruments i) {
        return instrumentRepository.save(i);
    }

    @Override
    public void deleteInstrument(instruments i) {
        instrumentRepository.delete(i);
    }

    @Override
    public void deleteInstrumentById(Long id) {
        instrumentRepository.deleteById(id);
    }

    @Override
    public instruments getInstrument(Long id) {
        return instrumentRepository.findById(id).get();
    }

    @Override
    public List<instruments> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @Override
    public Page<instruments> getAllInstrumentsParPage(int page, int size) {
        return instrumentRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<instruments> findByNomInstrument(String nom) {
        return instrumentRepository.findByNomInstrument(nom);
    }

    @Override
    public List<instruments> findByNomInstrumentContains(String nom) {
        return instrumentRepository.findByNomInstrumentContains(nom);
    }

    @Override
    public List<instruments> findByNomPrix(String nom, Double prix) {
        return instrumentRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<instruments> findByGenreMusical(GenreMusical g) {
        return instrumentRepository.findByGenreMusical(g);
    }

    @Override
    public List<instruments> findByGenreMusicalIdGenre(Long id) {
        return instrumentRepository.findByGenreMusicalIdGenre(id);
    }

    @Override
    public List<instruments> findByOrderByNomInstrumentAsc() {
        return instrumentRepository.findByOrderByNomInstrumentAsc();
    }

    @Override
    public List<instruments> trierInstrumentsNomsPrix() {
        return instrumentRepository.trierInstrumentsNomsPrix();
    }

    @Override
    public List<GenreMusical> getAllGenres() {
        return genreMusicalRepository.findAll();
    }

    @Override
    public InstrumentDTO convertEntityToDto(instruments instrument) {
        InstrumentDTO dto = modelMapper.map(instrument, InstrumentDTO.class);
        if (instrument.getGenreMusical() != null) {
            dto.setGenreMusicalId(instrument.getGenreMusical().getIdGenre());
            dto.setNomGenreMusical(instrument.getGenreMusical().getNomGenre());
        }
        return dto;
    }

    @Override
    public instruments convertDtoToEntity(InstrumentDTO dto) {
        instruments instrument = modelMapper.map(dto, instruments.class);
        if (dto.getGenreMusicalId() != null) {
            GenreMusical genre = genreMusicalRepository.findById(dto.getGenreMusicalId()).orElse(null);
            instrument.setGenreMusical(genre);
        }
        return instrument;
    }

    @Override
    public InstrumentDTO saveInstrumentDTO(InstrumentDTO dto) {
        instruments instrument = convertDtoToEntity(dto);
        instruments saved = instrumentRepository.save(instrument);
        return convertEntityToDto(saved);
    }

    @Override
    public InstrumentDTO getInstrumentDTO(Long id) {
        instruments instrument = instrumentRepository.findById(id).get();
        return convertEntityToDto(instrument);
    }
}