package com.example.demo.entity;

import com.example.demo.enums.EtatTache;
import com.example.demo.enums.PrioriteTache;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Employe responsable;

    private String description;

    @Enumerated(EnumType.STRING)
    private EtatTache etat;

    @Enumerated(EnumType.STRING)
    private PrioriteTache priorite;

    private LocalDate deadline;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public Employe getResponsable() { return responsable; }
    public void setResponsable(Employe responsable) { this.responsable = responsable; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public EtatTache getEtat() { return etat; }
    public void setEtat(EtatTache etat) { this.etat = etat; }

    public PrioriteTache getPriorite() { return priorite; }
    public void setPriorite(PrioriteTache priorite) { this.priorite = priorite; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}
