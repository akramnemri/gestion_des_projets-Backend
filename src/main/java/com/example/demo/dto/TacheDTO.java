package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TacheDTO {
    private Long id;

    private Long projetId;
    private Long responsableId;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    private String etat;
    private String priorite;

    @NotNull(message = "La deadline est obligatoire")
    private LocalDate deadline;
    
    
    
    

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProjetId() { return projetId; }
    public void setProjetId(Long projetId) { this.projetId = projetId; }

    public Long getResponsableId() { return responsableId; }
    public void setResponsableId(Long responsableId) { this.responsableId = responsableId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}