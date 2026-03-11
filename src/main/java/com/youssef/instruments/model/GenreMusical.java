package com.youssef.instruments.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class GenreMusical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGenre;
    private String nomGenre;
    private String descriptionGenre;

    @JsonIgnore
    @OneToMany(mappedBy = "genreMusical")
    private List<instruments> instrumentsList;

    public GenreMusical() {}

    public GenreMusical(Long idGenre, String nomGenre,
                        String descriptionGenre, List<instruments> instrumentsList) {
        this.idGenre = idGenre;
        this.nomGenre = nomGenre;
        this.descriptionGenre = descriptionGenre;
        this.instrumentsList = instrumentsList;
    }

    public Long getIdGenre() { return idGenre; }
    public void setIdGenre(Long idGenre) { this.idGenre = idGenre; }

    public String getNomGenre() { return nomGenre; }
    public void setNomGenre(String nomGenre) { this.nomGenre = nomGenre; }

    public String getDescriptionGenre() { return descriptionGenre; }
    public void setDescriptionGenre(String descriptionGenre) {
        this.descriptionGenre = descriptionGenre;
    }

    public List<instruments> getInstrumentsList() { return instrumentsList; }
    public void setInstrumentsList(List<instruments> instrumentsList) {
        this.instrumentsList = instrumentsList;
    }

    @Override
    public String toString() {
        return "GenreMusical [id=" + idGenre
                + ", nom=" + nomGenre
                + ", description=" + descriptionGenre
                + "]";
    }
}