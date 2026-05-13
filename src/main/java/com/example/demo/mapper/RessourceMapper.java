package com.example.demo.mapper;

import com.example.demo.dto.RessourceDTO;
import com.example.demo.entity.Ressource;
import com.example.demo.enums.DisponibiliteRessource;

public class RessourceMapper {

    public static RessourceDTO toDTO(Ressource r) {
        RessourceDTO dto = new RessourceDTO();
        dto.setId(r.getId());
        dto.setNom(r.getNom());
        dto.setType(r.getType());
        dto.setCout(r.getCout());
        // Convert enum → String for JSON/frontend
        dto.setDisponibilite(r.getDisponibilite() != null ? r.getDisponibilite().name() : null);
        return dto;
    }

    public static Ressource toEntity(RessourceDTO dto) {
        Ressource r = new Ressource();
        r.setId(dto.getId());
        r.setNom(dto.getNom());
        r.setType(dto.getType());
        r.setCout(dto.getCout());
        // Convert String → enum from frontend/JSON
        if (dto.getDisponibilite() != null && !dto.getDisponibilite().isBlank()) {
            try {
                r.setDisponibilite(DisponibiliteRessource.valueOf(dto.getDisponibilite().trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                r.setDisponibilite(null);
            }
        }
        return r;
    }
}
