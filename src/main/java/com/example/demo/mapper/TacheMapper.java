package com.example.demo.mapper;

import com.example.demo.dto.TacheDTO;
import com.example.demo.entity.Tache;
import com.example.demo.enums.EtatTache;
import com.example.demo.enums.PrioriteTache;

public class TacheMapper {

    public static TacheDTO toDTO(Tache t) {
        TacheDTO dto = new TacheDTO();
        dto.setId(t.getId());
        if (t.getProjet() != null) dto.setProjetId(t.getProjet().getId());
        if (t.getResponsable() != null) dto.setResponsableId(t.getResponsable().getId());
        dto.setDescription(t.getDescription());
        // Convert enums → String for JSON/frontend
        dto.setEtat(t.getEtat() != null ? t.getEtat().name() : null);
        dto.setPriorite(t.getPriorite() != null ? t.getPriorite().name() : null);
        dto.setDeadline(t.getDeadline());
        return dto;
    }

    public static Tache toEntity(TacheDTO dto) {
        Tache t = new Tache();
        t.setId(dto.getId());
        t.setDescription(dto.getDescription());
        // Convert String → enum from frontend/JSON
        if (dto.getEtat() != null && !dto.getEtat().isBlank()) {
            try {
                t.setEtat(EtatTache.valueOf(dto.getEtat().trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                t.setEtat(null);
            }
        }
        if (dto.getPriorite() != null && !dto.getPriorite().isBlank()) {
            try {
                t.setPriorite(PrioriteTache.valueOf(dto.getPriorite().trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                t.setPriorite(null);
            }
        }
        t.setDeadline(dto.getDeadline());
        return t;
    }
}
