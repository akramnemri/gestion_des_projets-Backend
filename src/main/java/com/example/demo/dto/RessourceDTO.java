package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RessourceDTO {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String type;

    @NotNull(message = "Le cout est obligatoire")
    @Positive(message = "Le cout doit etre positif")
    private Double cout;

    private String disponibilite;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }

    public String getDisponibilite() { return disponibilite; }
    public void setDisponibilite(String disponibilite) { this.disponibilite = disponibilite; }
}