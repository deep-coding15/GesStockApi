package com.deep_coding15.GesStockApi.stock.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.stock.dto.stock_mouvement.StockMouvementResponseDTO;
import com.deep_coding15.GesStockApi.stock.mapper.StockMouvementMapper;
import com.deep_coding15.GesStockApi.stock.service.StockMouvementService;

@RestController
@RequestMapping("/api/v1/stock-mouvements")
public class StockMouvementController {
    private final StockMouvementService stockMouvementService;
    private final StockMouvementMapper stockMouvementMapper;

    public StockMouvementController(
            StockMouvementService service,
            StockMouvementMapper mapper) {
        this.stockMouvementService = service;
        this.stockMouvementMapper = mapper;
    }

    @GetMapping("/stock/{stockId}")
    public List<StockMouvementResponseDTO> byStock(
            @PathVariable Long stockId) {
        return stockMouvementService.getAllMouvementsByStock(stockId)
                      .stream()
                      .map(stockMouvementMapper::toResponse)
                      .toList();
    }

    @GetMapping("/produit/{produitId}")
    public List<StockMouvementResponseDTO> byProduit(
            @PathVariable Long produitId) {
        return stockMouvementService.getAllMouvementsByProduit(produitId)
                      .stream()
                      .map(stockMouvementMapper::toResponse)
                      .toList();
    }
    
    @GetMapping("/utilisateur/{utilisateurId}")
    public List<StockMouvementResponseDTO> byUtilisateur(
            @PathVariable Long utilisateurId) {
        return stockMouvementService.getAllMouvementsByUtilisateur(utilisateurId)
                      .stream()
                      .map(stockMouvementMapper::toResponse)
                      .toList();
    }
}
