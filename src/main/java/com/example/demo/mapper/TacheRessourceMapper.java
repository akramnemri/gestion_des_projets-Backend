package com.example.demo.mapper;

import com.example.demo.dto.TacheRessourceDTO;
import com.example.demo.entity.TacheRessource;

public class TacheRessourceMapper {
    public static TacheRessourceDTO toDTO(TacheRessource tr) {
        TacheRessourceDTO dto = new TacheRessourceDTO();
        dto.setId(tr.getId());
        if (tr.getTache() != null) dto.setTacheId(tr.getTache().getId());
        if (tr.getRessource() != null) dto.setRessourceId(tr.getRessource().getId());
        dto.setQuantite(tr.getQuantite());
        return dto;
    }

    public static TacheRessource toEntity(TacheRessourceDTO dto) {
        TacheRessource tr = new TacheRessource();
        tr.setId(dto.getId());
        tr.setQuantite(dto.getQuantite());
        return tr;
    }
}