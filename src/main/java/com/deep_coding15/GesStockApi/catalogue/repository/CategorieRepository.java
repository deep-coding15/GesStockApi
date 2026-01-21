package com.deep_coding15.GesStockApi.catalogue.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
    Optional<Categorie> findByCode(String code);
    Optional<Categorie> findByLibelle(String libelle);
    
    boolean existsByCode(String code);
}
