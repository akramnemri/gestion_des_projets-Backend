package com.example.demo.service;

import com.example.demo.dto.ProjetDTO;
import com.example.demo.entity.Projet;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.mapper.ProjetMapper;
import com.example.demo.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository repo;

    public List<ProjetDTO> getAll() {
        List<ProjetDTO> result = new ArrayList<>();
        for (Projet p : repo.findAll()) {
            result.add(ProjetMapper.toDTO(p));
        }
        return result;
    }

    public ProjetDTO getById(Long id) {
        return repo.findById(id).map(ProjetMapper::toDTO).orElse(null);
    }

    public ProjetDTO save(ProjetDTO dto) {
        Projet entity = ProjetMapper.toEntity(dto);
        entity.setId(null);
        validateBusinessRules(entity);
        Projet saved = repo.save(entity);
        return ProjetMapper.toDTO(saved);
    }

    public ProjetDTO update(Long id, ProjetDTO dto) {
        if (!repo.existsById(id)) return null;
        Projet p = ProjetMapper.toEntity(dto);
        p.setId(id);
        validateBusinessRules(p);
        return ProjetMapper.toDTO(repo.save(p));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private void validateBusinessRules(Projet p) {
        Map<String, String> errors = new HashMap<>();

        // Budget > 0
        if (p.getBudget() == null || p.getBudget() <= 0) {
            errors.put("budget", "Le budget doit etre positif");
        }

        // dateFin > dateDebut
        if (p.getDateDebut() != null && p.getDateFin() != null) {
            if (!p.getDateFin().isAfter(p.getDateDebut())) {
                errors.put("dateFin", "La date de fin doit etre apres la date de debut");
            }
        }

        if (!errors.isEmpty()) {
            throw new BusinessValidationException(errors);
        }
    }
}
