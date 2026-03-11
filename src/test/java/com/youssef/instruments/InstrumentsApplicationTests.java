package com.youssef.instruments;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.youssef.instruments.model.GenreMusical;
import com.youssef.instruments.model.instruments;
import com.youssef.instruments.repos.GenreMusicalRepository;
import com.youssef.instruments.repos.instrumentsRepository;

@SpringBootTest
class InstrumentsApplicationTests {

    @Autowired
    private instrumentsRepository instrumentRepository;

    @Autowired
    private GenreMusicalRepository genreMusicalRepository;

    @Test
    public void testCreateGenre() {
        genreMusicalRepository.save(new GenreMusical(null, "Rock", "Musique Rock", null));
        genreMusicalRepository.save(new GenreMusical(null, "Jazz", "Musique Jazz", null));
        genreMusicalRepository.save(new GenreMusical(null, "Classique", "Musique Classique", null));
    }

    // ✅ Créer des instruments avec genres
    @Test
    public void testCreateInstrument() {
        GenreMusical rock = genreMusicalRepository.findById(1L).get();
        instruments inst = new instruments(null, "Guitare", 1200.0, rock);
        instrumentRepository.save(inst);
    }

    // ✅ Recherche par nom exact
    @Test
    public void testFindByNomInstrument() {
        List<instruments> liste = instrumentRepository.findByNomInstrument("Guitare");
        for (instruments i : liste)
            System.out.println(i);
    }

    // ✅ Recherche par nom contient
    @Test
    public void testFindByNomInstrumentContains() {
        List<instruments> liste = instrumentRepository.findByNomInstrumentContains("tar");
        for (instruments i : liste)
            System.out.println(i);
    }

    // ✅ Recherche par nom et prix
    @Test
    public void testFindByNomPrix() {
        List<instruments> liste = instrumentRepository.findByNomPrix("Guitare", 1000.0);
        for (instruments i : liste)
            System.out.println(i);
    }

    @Test
    public void testFindByGenreMusical() {
        GenreMusical genre = new GenreMusical();
        genre.setIdGenre(1L);
        List<instruments> liste = instrumentRepository.findByGenreMusical(genre);
        for (instruments i : liste)
            System.out.println(i);
    }

    @Test
    public void testFindByGenreMusicalIdGenre() {
        List<instruments> liste = instrumentRepository.findByGenreMusicalIdGenre(1L);
        for (instruments i : liste)
            System.out.println(i);
    }

    @Test
    public void testFindByOrderByNomInstrumentAsc() {
        List<instruments> liste = instrumentRepository.findByOrderByNomInstrumentAsc();
        for (instruments i : liste)
            System.out.println(i);
    }

    // ✅ Tri par nom et prix
    @Test
    public void testTrierInstrumentsNomsPrix() {
        List<instruments> liste = instrumentRepository.trierInstrumentsNomsPrix();
        for (instruments i : liste)
            System.out.println(i);
    }
}