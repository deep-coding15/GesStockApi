package com.deep_coding15.GesStockApi.stock.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import com.deep_coding15.GesStockApi.stock.dto.stock.StockCreateRequestDTO;
import com.deep_coding15.GesStockApi.stock.dto.stock.StockPatchQuantityRequestDTO;
import com.deep_coding15.GesStockApi.stock.dto.stock.StockResponseDTO;

import com.deep_coding15.GesStockApi.stock.entity.Stock;

import com.deep_coding15.GesStockApi.stock.mapper.StockMapper;

import com.deep_coding15.GesStockApi.stock.service.StockService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Stock", description = "Gestion du stock par produit")
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    public StockController(
            StockService stockService,
            StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    /**
     * @param stock
     * @return ResponseEntity<Stock>
     */
    @Operation(summary = "Créer un stock", description = "Crée un stock initial pour un produit (un seul stock par produit)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stock créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Paramètres invalides"),
            @ApiResponse(responseCode = "404", description = "Produit ou utilisateur introuvable"),
            @ApiResponse(responseCode = "409", description = "Stock déjà existant pour ce produit")
    })
    @PostMapping("/")
    public ResponseEntity<StockResponseDTO> createStock(
            @Valid @RequestBody StockCreateRequestDTO dto) {

        Stock stock = stockService.createStock(
                dto.getProduitId(),
                dto.getQuantiteInitiale(),
                dto.getUtilisateurId());

        return new ResponseEntity<>(stockMapper.toResponseDTO(stock), HttpStatus.CREATED);
    }

    /**
     * @return ResponseEntity<List<Stock>>
     */
    @GetMapping("/")
    public ResponseEntity<List<StockResponseDTO>> getAllStocks() {
        return new ResponseEntity<>(stockService.getStocks()
                .stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * @param id
     * @return ResponseEntity<Stock>
     */
    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDTO> getStock(@PathVariable Long id) {
        return new ResponseEntity<>(stockMapper.toResponseDTO(
                stockService.getStockById(id)), HttpStatus.OK);
    }

    /**
     * @param produitId
     * @return ResponseEntity<Stock>
     */
    @GetMapping("/produit/{produitId}")
    public ResponseEntity<StockResponseDTO> getStockByProduitId(@PathVariable Long produitId) {
        return new ResponseEntity<>(stockMapper
                .toResponseDTO(stockService.getStockByProduitId(produitId)),
                HttpStatus.OK);
    }

    /* ===================== UPDATE ===================== */
    @Operation(summary = "Modifier la quantité de stock", description = "Met à jour le stock via un mouvement (INITIAL / ENTREE / SORTIE / REAJUSTEMENT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock mis à jour"),
            @ApiResponse(responseCode = "400", description = "Quantité invalide"),
            @ApiResponse(responseCode = "404", description = "Stock ou utilisateur introuvable")
    })
    @PatchMapping("/{id}/quantite")
    public ResponseEntity<StockResponseDTO> patchQuantite(
            @PathVariable Long id,
            @RequestBody StockPatchQuantityRequestDTO dto) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getUtilisateurId());

        Stock stock = stockService.patchStockQuantite(
                id,
                dto.getDelta(),
                dto.getTypeMouvement(),
                utilisateur,
                dto.getCommentaire());

        return new ResponseEntity<>(
                stockMapper.toResponseDTO(stock),
                HttpStatus.OK);
    }
}
