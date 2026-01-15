package com.deep_coding15.GesStockApi.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
    <Optional> Categorie findByCode(String code);
    
    boolean existsByCode(String code);
}
