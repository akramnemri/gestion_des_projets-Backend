package com.example.demo.mapper;

import com.example.demo.dto.ProjetDTO;
import com.example.demo.entity.Projet;
import com.example.demo.enums.StatutProjet;

public class ProjetMapper {

    public static ProjetDTO toDTO(Projet p) {
        ProjetDTO dto = new ProjetDTO();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setDateDebut(p.getDateDebut());
        dto.setDateFin(p.getDateFin());
        dto.setBudget(p.getBudget());
        // Convert enum → String for JSON/frontend
        dto.setStatut(p.getStatut() != null ? p.getStatut().name() : null);
        return dto;
    }

    public static Projet toEntity(ProjetDTO dto) {
        Projet p = new Projet();
        p.setId(dto.getId());
        p.setNom(dto.getNom());
        p.setDateDebut(dto.getDateDebut());
        p.setDateFin(dto.getDateFin());
        p.setBudget(dto.getBudget());
        // Convert String → enum from frontend/JSON
        if (dto.getStatut() != null && !dto.getStatut().isBlank()) {
            try {
                p.setStatut(StatutProjet.valueOf(dto.getStatut().trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // leave null if invalid value sent — GlobalExceptionHandler will catch validation errors
                p.setStatut(null);
            }
        }
        return p;
    }
}
