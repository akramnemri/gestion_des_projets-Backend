package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tache_ressource")
public class TacheRessource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    @ManyToOne
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    private Integer quantite;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Tache getTache() { return tache; }
    public void setTache(Tache tache) { this.tache = tache; }

    public Ressource getRessource() { return ressource; }
    public void setRessource(Ressource ressource) { this.ressource = ressource; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
}