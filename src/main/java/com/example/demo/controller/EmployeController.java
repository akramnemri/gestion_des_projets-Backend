package com.example.demo.controller;

import com.example.demo.dto.EmployeDTO;
import com.example.demo.service.EmployeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeController {

    @Autowired
    private EmployeService service;

    @GetMapping
    public List<EmployeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EmployeDTO create(@Valid @RequestBody EmployeDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id,@Valid @RequestBody EmployeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}