package com.deep_coding15.GesStockApi.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;

public interface StockMouvementRepository extends JpaRepository<StockMouvement, Long> {
    
}
