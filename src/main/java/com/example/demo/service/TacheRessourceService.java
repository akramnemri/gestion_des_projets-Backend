package com.example.demo.service;

import com.example.demo.dto.TacheRessourceDTO;
import com.example.demo.entity.TacheRessource;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.mapper.TacheRessourceMapper;
import com.example.demo.repository.RessourceRepository;
import com.example.demo.repository.TacheRepository;
import com.example.demo.repository.TacheRessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TacheRessourceService {

    @Autowired
    private TacheRessourceRepository repo;
    @Autowired
    private TacheRepository tacheRepo;
    @Autowired
    private RessourceRepository ressourceRepo;

    public List<TacheRessourceDTO> getAll() {
        List<TacheRessourceDTO> list = new ArrayList<>();
        for (TacheRessource tr : repo.findAll()) {
            list.add(TacheRessourceMapper.toDTO(tr));
        }
        return list;
    }

    public List<TacheRessourceDTO> getByTacheId(Long tacheId) {
        List<TacheRessourceDTO> list = new ArrayList<>();
        for (TacheRessource tr : repo.findByTacheId(tacheId)) {
            list.add(TacheRessourceMapper.toDTO(tr));
        }
        return list;
    }

    public TacheRessourceDTO save(TacheRessourceDTO dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getQuantite() == null || dto.getQuantite() <= 0) {
            errors.put("quantite", "La quantite doit etre superieure a 0");
        }

        if (!errors.isEmpty()) {
            throw new BusinessValidationException(errors);
        }

        TacheRessource tr = TacheRessourceMapper.toEntity(dto);
        if (dto.getTacheId() != null) {
            tr.setTache(tacheRepo.findById(dto.getTacheId()).orElse(null));
        }
        if (dto.getRessourceId() != null) {
            tr.setRessource(ressourceRepo.findById(dto.getRessourceId()).orElse(null));
        }
        return TacheRessourceMapper.toDTO(repo.save(tr));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
