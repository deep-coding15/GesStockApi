package com.deep_coding15.GesStockApi.stock.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.stock.dto.stock_mouvement.StockMouvementResponseDTO;
import com.deep_coding15.GesStockApi.stock.mapper.StockMouvementMapper;
import com.deep_coding15.GesStockApi.stock.service.StockMouvementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Stock Mouvement", description = "Suivi et consultation des mouvements de stock (entrées, sorties, réajustements)")
@RestController
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Requête invalide ou paramètres manquants"),
        @ApiResponse(responseCode = "401", description = "Non autorisé - authentification requise"),
        @ApiResponse(responseCode = "403", description = "Interdit - permissions insuffisantes"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
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

    /**
     * GET /api/v1/stock-mouvements/
     * Récupère tous les mouvements d'un stock
     * 
     * @param stockId L'identifiant unique du stock
     * @return Liste de tous les mouvements du stock
     */
    @GetMapping("/")
    @Operation(summary = "GET: Lister tous les mouvements de stock", description = "Récupère l'historique complet des mouvements associés à un stock spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mouvements récupérés avec succès"),
    })
    public List<StockMouvementResponseDTO> getAllStockMouvements() {
        return stockMouvementService.getAllMouvements()
                .stream()
                .map(stockMouvementMapper::toResponse)
                .toList();
    }
    
    /**
     * GET /api/v1/stock-mouvements/stock/{stockId}
     * Récupère tous les mouvements d'un stock
     * 
     * @param stockId L'identifiant unique du stock
     * @return Liste de tous les mouvements du stock
     */
    @GetMapping("/stock/{stockId}")
    @Operation(summary = "GET: Lister les mouvements d'un stock", description = "Récupère l'historique complet des mouvements associés à un stock spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mouvements récupérés avec succès"),
    })
    public List<StockMouvementResponseDTO> byStock(
            @PathVariable Long stockId) {
        return stockMouvementService.getAllMouvementsByStock(stockId)
                .stream()
                .map(stockMouvementMapper::toResponse)
                .toList();
    }

    /**
     * GET /api/v1/stock-mouvements/produit/{produitId}
     * Récupère tous les mouvements associés à un produit
     * 
     * @param produitId L'identifiant unique du produit
     * @return Liste de tous les mouvements du produit
     */
    @GetMapping("/produit/{produitId}")
    @Operation(summary = "GET: Lister les mouvements d'un produit", description = "Récupère l'historique complet de tous les mouvements associés à un produit spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mouvements récupérés avec succès", content = @Content(schema = @Schema(implementation = StockMouvementResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID de produit invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<StockMouvementResponseDTO> byProduit(
            @PathVariable Long produitId) {
        return stockMouvementService.getAllMouvementsByProduit(produitId)
                .stream()
                .map(stockMouvementMapper::toResponse)
                .toList();
    }

    /**
     * GET /api/v1/stock-mouvements/utilisateur/{utilisateurId}
     * Récupère tous les mouvements effectués par un utilisateur
     * 
     * @param utilisateurId L'identifiant unique de l'utilisateur
     * @return Liste de tous les mouvements de l'utilisateur
     */
    @GetMapping("/utilisateur/{utilisateurId}")
    @Operation(summary = "GET: Lister les mouvements d'un utilisateur", description = "Récupère l'historique complet de tous les mouvements de stock effectués par un utilisateur spécifique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mouvements récupérés avec succès", content = @Content(schema = @Schema(implementation = StockMouvementResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID utilisateur invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public List<StockMouvementResponseDTO> byUtilisateur(
            @Parameter(description = "Identifiant unique de l'utilisateur", example = "1") @PathVariable Long utilisateurId) {
        return stockMouvementService.getAllMouvementsByUtilisateur(utilisateurId)
                .stream()
                .map(stockMouvementMapper::toResponse)
                .toList();
    }
}
