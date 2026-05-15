package com.example.demo.controller;

import com.example.demo.dto.RessourceDTO;
import com.example.demo.service.RessourceService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ressources")
@CrossOrigin(origins = "http://localhost:4200")
public class RessourceController {

    @Autowired
    private RessourceService service;

    @GetMapping
    public List<RessourceDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RessourceDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public RessourceDTO create(@Valid @RequestBody RessourceDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public RessourceDTO update(@PathVariable Long id,@Valid @RequestBody RessourceDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}