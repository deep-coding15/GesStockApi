package com.deep_coding15.GesStockApi.stock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.stock.entity.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {
    
    Optional<Stock> findByProduitId(Long produitId);
    boolean existsByProduitId(Long id);
    List<Stock> findAllMouvementsStocksByProduitId(Long produitId);

}
