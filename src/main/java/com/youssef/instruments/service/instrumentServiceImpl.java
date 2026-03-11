package com.youssef.instruments.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    public List<GenreMusical> getAllGenres() {
        return genreMusicalRepository.findAll();
    }
}