package com.youssef.instruments.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.youssef.instruments.model.GenreMusical;
import com.youssef.instruments.model.instruments;
import com.youssef.instruments.repos.GenreMusicalRepository;
import com.youssef.instruments.service.instrumentsService;
import jakarta.validation.Valid;

@Controller
public class InstrumentController {

    @Autowired
    instrumentsService instrumentService;

    @Autowired
    GenreMusicalRepository genreMusicalRepository;

    @RequestMapping("/myView")
    public String afficherAccueil() {
        return "myView";
    }

    @GetMapping("/ListeInstruments")
    public String listeInstruments(ModelMap modelMap,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<instruments> liste = instrumentService.getAllInstrumentsParPage(page, size);

        modelMap.addAttribute("instruments", liste);
        modelMap.addAttribute("pages", new int[liste.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        return "listeInstruments";
    }

    @GetMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<GenreMusical> genres = instrumentService.getAllGenres();
        modelMap.addAttribute("instrument", new instruments());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("genres", genres);
        modelMap.addAttribute("page", 0);
        modelMap.addAttribute("size", 5);
        return "formInstrument";
    }

    @PostMapping("/saveInstrument")
    public String saveInstrument(
        @Valid @ModelAttribute("instrument") instruments instrument,
        BindingResult bindingResult,
        ModelMap modelMap,
        @RequestParam(name = "genreMusicalId", required = false) Long genreMusicalId,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size) {

        if (genreMusicalId != null) {
            GenreMusical genre = genreMusicalRepository.findById(genreMusicalId).orElse(null);
            instrument.setGenreMusical(genre);
        }

        if (bindingResult.hasErrors()) {
            List<GenreMusical> genres = instrumentService.getAllGenres();
            modelMap.addAttribute("genres", genres);
            modelMap.addAttribute("mode", instrument.getIdInstrument() == null ? "new" : "edit");
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("size", size);
            return "formInstrument";
        }

        boolean isNew = (instrument.getIdInstrument() == null);
        instrumentService.saveInstrument(instrument);

        int currentPage;
        if (isNew) {
            Page<instruments> prods = instrumentService.getAllInstrumentsParPage(0, size);
            currentPage = prods.getTotalPages() > 0 ? prods.getTotalPages() - 1 : 0;
        } else {
            currentPage = page;
        }

        return "redirect:/ListeInstruments?page=" + currentPage + "&size=" + size;
    }

    @GetMapping("/supprimerInstrument")
    public String supprimerInstrument(
        @RequestParam("id") Long id,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size) {

        instrumentService.deleteInstrumentById(id);

        Page<instruments> remaining = instrumentService.getAllInstrumentsParPage(page, size);
        if (page > 0 && remaining.isEmpty()) {
            page = page - 1;
        }

        return "redirect:/ListeInstruments?page=" + page + "&size=" + size;
    }

    @GetMapping("/modifierInstrument")
    public String editerInstrument(
        @RequestParam("id") Long id,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "5") int size,
        ModelMap modelMap) {

        instruments i = instrumentService.getInstrument(id);
        List<GenreMusical> genres = instrumentService.getAllGenres();

        modelMap.addAttribute("instrument", i);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("genres", genres);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("size", size);

        return "formInstrument";
    }
}