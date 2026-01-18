package com.deep_coding15.GesStockApi.vente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.vente.entity.Vente;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    
    <Optional> Vente findByReference(String reference);

    boolean existsByReference(String reference);
}
