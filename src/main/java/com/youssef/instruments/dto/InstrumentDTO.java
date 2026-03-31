package com.youssef.instruments.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InstrumentDTO {

    private Long idInstrument;

    @NotNull(message = "Le nom est obligatoire")
    @Size(min = 3, max = 20, message = "Le nom doit être entre 3 et 20 caractères")
    private String nomInstrument;

    @NotNull(message = "Le prix est obligatoire")
    @Min(value = 100, message = "Le prix doit être supérieur ou égal à 100")
    @Max(value = 50000, message = "Le prix doit être inférieur ou égal à 50000")
    private Double prixInstrument;

    private Long genreMusicalId;

    private String nomGenreMusical;

    public InstrumentDTO() {}

    public InstrumentDTO(Long idInstrument, String nomInstrument,
                         Double prixInstrument, Long genreMusicalId, String nomGenreMusical) {
        this.idInstrument = idInstrument;
        this.nomInstrument = nomInstrument;
        this.prixInstrument = prixInstrument;
        this.genreMusicalId = genreMusicalId;
        this.nomGenreMusical = nomGenreMusical;
    }

    public Long getIdInstrument() { return idInstrument; }
    public void setIdInstrument(Long idInstrument) { this.idInstrument = idInstrument; }

    public String getNomInstrument() { return nomInstrument; }
    public void setNomInstrument(String nomInstrument) { this.nomInstrument = nomInstrument; }

    public Double getPrixInstrument() { return prixInstrument; }
    public void setPrixInstrument(Double prixInstrument) { this.prixInstrument = prixInstrument; }

    public Long getGenreMusicalId() { return genreMusicalId; }
    public void setGenreMusicalId(Long genreMusicalId) { this.genreMusicalId = genreMusicalId; }

    public String getNomGenreMusical() { return nomGenreMusical; }
    public void setNomGenreMusical(String nomGenreMusical) { this.nomGenreMusical = nomGenreMusical; }

    @Override
    public String toString() {
        return "InstrumentDTO [id=" + idInstrument
                + ", nom=" + nomInstrument
                + ", prix=" + prixInstrument
                + ", genreId=" + genreMusicalId
                + ", nomGenre=" + nomGenreMusical + "]";
    }
}
