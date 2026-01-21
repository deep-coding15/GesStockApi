package com.deep_coding15.GesStockApi.catalogue.repository;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByCategorieId(Long categorieId);
    List<Produit> findByCategorieCode(String code);
    
    Optional<Produit> findByDescription(String description);
    Optional<Produit> findByNom(String nom);
    Optional<Produit> findByReference(String reference);

    boolean existsByReference(String reference);
}
