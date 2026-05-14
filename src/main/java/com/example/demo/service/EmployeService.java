package com.example.demo.service;

import com.example.demo.dto.EmployeDTO;
import com.example.demo.entity.Employe;
import com.example.demo.mapper.EmployeMapper;
import com.example.demo.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository repo;

    public List<EmployeDTO> getAll() {
        List<EmployeDTO> list = new ArrayList<>();
        for (Employe e : repo.findAll()) {
            list.add(EmployeMapper.toDTO(e));
        }
        return list;
    }

    public EmployeDTO getById(Long id) {
        return repo.findById(id).map(EmployeMapper::toDTO).orElse(null);
    }

    public EmployeDTO save(EmployeDTO dto) {
        Employe entity = EmployeMapper.toEntity(dto);
        entity.setId(null);    
        Employe saved = repo.save(entity);
        return EmployeMapper.toDTO(saved);
    }

    public EmployeDTO update(Long id, EmployeDTO dto) {
        if (!repo.existsById(id)) return null;
        Employe e = EmployeMapper.toEntity(dto);
        e.setId(id);
        return EmployeMapper.toDTO(repo.save(e));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}