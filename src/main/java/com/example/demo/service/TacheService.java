package com.example.demo.service;

import com.example.demo.dto.TacheDTO;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.mapper.TacheMapper;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.ProjetRepository;
import com.example.demo.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TacheService {

    @Autowired
    private TacheRepository repo;
    @Autowired
    private ProjetRepository projetRepo;
    @Autowired
    private EmployeRepository employeRepo;

    public List<TacheDTO> getAll() {
        List<TacheDTO> list = new ArrayList<>();
        for (Tache t : repo.findAll()) {
            list.add(TacheMapper.toDTO(t));
        }
        return list;
    }

    public TacheDTO getById(Long id) {
        return repo.findById(id).map(TacheMapper::toDTO).orElse(null);
    }

    public TacheDTO save(TacheDTO dto) {
        Tache t = TacheMapper.toEntity(dto);
        t.setId(null);
        if (dto.getProjetId() != null) {
            t.setProjet(projetRepo.findById(dto.getProjetId()).orElse(null));
        }
        if (dto.getResponsableId() != null) {
            t.setResponsable(employeRepo.findById(dto.getResponsableId()).orElse(null));
        }
        validateBusinessRules(t, true);
        return TacheMapper.toDTO(repo.save(t));
    }

    public TacheDTO update(Long id, TacheDTO dto) {
        if (!repo.existsById(id)) return null;
        Tache t = TacheMapper.toEntity(dto);
        t.setId(id);
        if (dto.getProjetId() != null) {
            t.setProjet(projetRepo.findById(dto.getProjetId()).orElse(null));
        }
        if (dto.getResponsableId() != null) {
            t.setResponsable(employeRepo.findById(dto.getResponsableId()).orElse(null));
        }
        validateBusinessRules(t, false);
        return TacheMapper.toDTO(repo.save(t));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private void validateBusinessRules(Tache t, boolean isCreate) {
        Map<String, String> errors = new HashMap<>();

        // deadline >= dateDebut du projet
        if (t.getDeadline() != null && t.getProjet() != null) {
            Projet projet = t.getProjet();
            if (projet.getDateDebut() != null && t.getDeadline().isBefore(projet.getDateDebut())) {
                errors.put("deadline", "La deadline doit etre apres la date de debut du projet");
            }
        }

        // deadline >= today (only on create)
        if (isCreate && t.getDeadline() != null) {
            if (t.getDeadline().isBefore(LocalDate.now())) {
                errors.put("deadline", "La deadline ne peut pas etre dans le passe");
            }
        }

        if (!errors.isEmpty()) {
            throw new BusinessValidationException(errors);
        }
    }
}
