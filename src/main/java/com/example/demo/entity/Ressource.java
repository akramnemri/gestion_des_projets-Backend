package com.example.demo.entity;

import com.example.demo.enums.DisponibiliteRessource;
import jakarta.persistence.*;

@Entity
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private Double cout;

    @Enumerated(EnumType.STRING)
    private DisponibiliteRessource disponibilite;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }

    public DisponibiliteRessource getDisponibilite() { return disponibilite; }
    public void setDisponibilite(DisponibiliteRessource disponibilite) { this.disponibilite = disponibilite; }
}
