package com.example.demo.service;

import com.example.demo.dto.RessourceDTO;
import com.example.demo.entity.Ressource;
import com.example.demo.mapper.RessourceMapper;
import com.example.demo.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RessourceService {

    @Autowired
    private RessourceRepository repo;

    public List<RessourceDTO> getAll() {
        List<RessourceDTO> list = new ArrayList<>();
        for (Ressource r : repo.findAll()) {
            list.add(RessourceMapper.toDTO(r));
        }
        return list;
    }

    public RessourceDTO getById(Long id) {
        return repo.findById(id).map(RessourceMapper::toDTO).orElse(null);
    }

    public RessourceDTO save(RessourceDTO dto) {
        Ressource entity = RessourceMapper.toEntity(dto);
        entity.setId(null);         
        Ressource saved = repo.save(entity);
        return RessourceMapper.toDTO(saved);
    }

    public RessourceDTO update(Long id, RessourceDTO dto) {
        if (!repo.existsById(id)) return null;
        Ressource r = RessourceMapper.toEntity(dto);
        r.setId(id);
        return RessourceMapper.toDTO(repo.save(r));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}