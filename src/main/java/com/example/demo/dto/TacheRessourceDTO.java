package com.example.demo.dto;

public class TacheRessourceDTO {
    private Long id;
    private Long tacheId;
    private Long ressourceId;
    private Integer quantite;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTacheId() { return tacheId; }
    public void setTacheId(Long tacheId) { this.tacheId = tacheId; }

    public Long getRessourceId() { return ressourceId; }
    public void setRessourceId(Long ressourceId) { this.ressourceId = ressourceId; }

    public Integer getQuantite() { return quantite; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
}