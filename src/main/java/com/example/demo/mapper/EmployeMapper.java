package com.example.demo.mapper;

import com.example.demo.dto.EmployeDTO;
import com.example.demo.entity.Employe;
import com.example.demo.enums.RoleEmploye;

public class EmployeMapper {

    public static EmployeDTO toDTO(Employe e) {
        EmployeDTO dto = new EmployeDTO();
        dto.setId(e.getId());
        dto.setNom(e.getNom());
        dto.setEmail(e.getEmail());
        // Convert enum → String for JSON/frontend
        dto.setRole(e.getRole() != null ? e.getRole().name() : null);
        dto.setEquipe(e.getEquipe());
        return dto;
    }

    public static Employe toEntity(EmployeDTO dto) {
        Employe e = new Employe();
        e.setId(dto.getId());
        e.setNom(dto.getNom());
        e.setEmail(dto.getEmail());
        // Convert String → enum from frontend/JSON
        if (dto.getRole() != null && !dto.getRole().isBlank()) {
            try {
                e.setRole(RoleEmploye.valueOf(dto.getRole().trim().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                e.setRole(null);
            }
        }
        e.setEquipe(dto.getEquipe());
        return e;
    }
}
