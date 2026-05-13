package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProjetDTO {
	private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "La date de debut est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    @NotNull(message = "Le budget est obligatoire")
    @Positive(message = "Le budget doit etre positif")
    private Double budget;

    @NotBlank(message = "Le statut est obligatoire")
    private String statut;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}