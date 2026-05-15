package com.example.demo.controller;

import com.example.demo.dto.RapportDTO;
import com.example.demo.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rapports")
@CrossOrigin(origins = "http://localhost:4200")
public class RapportController {

    @Autowired
    private RapportService service;

    @GetMapping("/projet/{projetId}")
    public RapportDTO getRapport(@PathVariable Long projetId) {
        return service.genererRapport(projetId);
    }
}