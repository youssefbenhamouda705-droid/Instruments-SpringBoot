package com.youssef.instruments.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class instruments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstrument;

    @NotNull(message = "Le nom est obligatoire")
    @Size(min = 3, max = 20, message = "Le nom doit être entre 3 et 20 caractères")
    private String nomInstrument;

    @NotNull(message = "Le prix est obligatoire")
    @Min(value = 100, message = "Le prix doit être supérieur ou égal à 100")
    @Max(value = 50000, message = "Le prix doit être inférieur ou égal à 50000")
    private Double prixInstrument;

    @ManyToOne
    private GenreMusical genreMusical;

    public instruments() {}

    public instruments(Long idInstrument, String nomInstrument,
                       Double prixInstrument, GenreMusical genreMusical) {
        this.idInstrument = idInstrument;
        this.nomInstrument = nomInstrument;
        this.prixInstrument = prixInstrument;
        this.genreMusical = genreMusical;
    }

    public instruments(String nomInstrument,
                       Double prixInstrument, GenreMusical genreMusical) {
        this.nomInstrument = nomInstrument;
        this.prixInstrument = prixInstrument;
        this.genreMusical = genreMusical;
    }

    public Long getIdInstrument() { return idInstrument; }
    public void setIdInstrument(Long idInstrument) { this.idInstrument = idInstrument; }

    public String getNomInstrument() { return nomInstrument; }
    public void setNomInstrument(String nomInstrument) { this.nomInstrument = nomInstrument; }

    public Double getPrixInstrument() { return prixInstrument; }
    public void setPrixInstrument(Double prixInstrument) { this.prixInstrument = prixInstrument; }

    public GenreMusical getGenreMusical() { return genreMusical; }
    public void setGenreMusical(GenreMusical genreMusical) { this.genreMusical = genreMusical; }

    @Override
    public String toString() {
        return "Instrument [id=" + idInstrument
                + ", nom=" + nomInstrument
                + ", prix=" + prixInstrument
                + ", genre=" + (genreMusical != null ?
                    genreMusical.getNomGenre() : "null")
                + "]";
    }
}