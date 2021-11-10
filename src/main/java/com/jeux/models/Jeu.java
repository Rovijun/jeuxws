package com.jeux.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jeu")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    @NotNull(message = "Le champ nom ne peut être null")
    @NotBlank(message = "Le champ nom ne peut être vide")
    private String nom;

    @Column(name = "description")
    @NotNull(message = "Le champ description ne peut être null")
    @NotBlank(message = "Le champ description ne peut être vide")
    private String description;

    @Column(name = "dateSortie")
    @NotNull(message = "Le champ date de sortie ne peut être null")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateSortie;

    @OneToMany(mappedBy="jeu", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Avis> avisList;

    public Jeu () {

    }

    public Jeu(String nom, String description, Date dateSortie, List<Avis> avisList) {
        this.nom = nom;
        this.description = description;
        this.dateSortie = dateSortie;
        this.avisList = avisList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<Avis> getAvisList() {
        return avisList;
    }

    public void setAvisList(List<Avis> avisList) {
        this.avisList = avisList;
    }
}
