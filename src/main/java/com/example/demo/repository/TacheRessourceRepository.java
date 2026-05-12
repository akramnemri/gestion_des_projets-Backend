package com.example.demo.repository;

import com.example.demo.entity.TacheRessource;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TacheRessourceRepository extends JpaRepository<TacheRessource, Long> {
    List<TacheRessource> findByTacheId(Long tacheId);
}