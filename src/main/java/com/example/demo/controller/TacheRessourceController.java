package com.example.demo.controller;

import com.example.demo.dto.TacheRessourceDTO;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.service.TacheRessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@CrossOrigin(origins = "http://localhost:4200")
public class TacheRessourceController {

    @Autowired
    private TacheRessourceService service;

    @GetMapping
    public List<TacheRessourceDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/tache/{tacheId}")
    public List<TacheRessourceDTO> getByTache(@PathVariable Long tacheId) {
        return service.getByTacheId(tacheId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TacheRessourceDTO dto) {
        try {
            return ResponseEntity.ok(service.save(dto));
        } catch (BusinessValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getErrors());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
