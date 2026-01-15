package com.deep_coding15.GesStockApi.vente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.vente.entity.VenteLigne;

public interface VenteLigneRepository extends JpaRepository<VenteLigne, Long> {
    
}
