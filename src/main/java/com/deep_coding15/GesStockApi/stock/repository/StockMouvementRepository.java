package com.deep_coding15.GesStockApi.stock.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStockEnum;

public interface StockMouvementRepository extends JpaRepository<StockMouvement, Long> {

    List<StockMouvement> findAllByStockProduitId(Long id);
    
    List<StockMouvement> findAllByStockId(Long id);

    List<StockMouvement> findAllByUtilisateurId(Long utilisateurId);

    List<StockMouvement> findAllByTypeMouvement(TypeMouvementStockEnum type);

    List<StockMouvement> findAllByDateMouvementBetween(
            LocalDateTime debut,
            LocalDateTime fin);
}
