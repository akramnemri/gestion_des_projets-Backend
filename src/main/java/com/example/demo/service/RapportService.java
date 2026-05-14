package com.example.demo.service;

import com.example.demo.dto.RapportDTO;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import com.example.demo.entity.TacheRessource;
import com.example.demo.repository.ProjetRepository;
import com.example.demo.repository.TacheRepository;
import com.example.demo.repository.TacheRessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RapportService {

    @Autowired
    private ProjetRepository projetRepo;
    @Autowired
    private TacheRepository tacheRepo;
    @Autowired
    private TacheRessourceRepository allocationRepo;

    public RapportDTO genererRapport(Long projetId) {
        Projet projet = projetRepo.findById(projetId).orElse(null);
        if (projet == null) return null;

        RapportDTO rapport = new RapportDTO();
        rapport.setProjetId(projet.getId());
        rapport.setProjetNom(projet.getNom());
        rapport.setBudgetTotal(projet.getBudget());

        double coutTotal = 0.0;

        // Find all taches for this projet
        List<Tache> taches = tacheRepo.findAll(); // we'll filter manually for simplicity
        for (Tache t : taches) {
            if (t.getProjet() != null && t.getProjet().getId().equals(projetId)) {
                List<TacheRessource> allocations = allocationRepo.findByTacheId(t.getId());
                for (TacheRessource a : allocations) {
                    if (a.getRessource() != null && a.getQuantite() != null) {
                        coutTotal += a.getRessource().getCout() * a.getQuantite();
                    }
                }
            }
        }

        rapport.setCoutTotal(coutTotal);
        rapport.setBudgetRestant(projet.getBudget() - coutTotal);

        return rapport;
    }
}