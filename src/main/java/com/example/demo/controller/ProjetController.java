package com.example.demo.controller;

import com.example.demo.dto.ProjetDTO;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.service.ProjetService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {

    @Autowired
    private ProjetService service;

    @GetMapping
    public List<ProjetDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProjetDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProjetDTO dto) {
        try {
            return ResponseEntity.ok(service.save(dto));
        } catch (BusinessValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getErrors());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProjetDTO dto) {
        try {
            ProjetDTO result = service.update(id, dto);
            if (result == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result);
        } catch (BusinessValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getErrors());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
