package com.example.demo.dto;

public class RapportDTO {
    private Long projetId;
    private String projetNom;
    private Double budgetTotal;
    private Double coutTotal;
    private Double budgetRestant;

    public Long getProjetId() { return projetId; }
    public void setProjetId(Long projetId) { this.projetId = projetId; }

    public String getProjetNom() { return projetNom; }
    public void setProjetNom(String projetNom) { this.projetNom = projetNom; }

    public Double getBudgetTotal() { return budgetTotal; }
    public void setBudgetTotal(Double budgetTotal) { this.budgetTotal = budgetTotal; }

    public Double getCoutTotal() { return coutTotal; }
    public void setCoutTotal(Double coutTotal) { this.coutTotal = coutTotal; }

    public Double getBudgetRestant() { return budgetRestant; }
    public void setBudgetRestant(Double budgetRestant) { this.budgetRestant = budgetRestant; }
}