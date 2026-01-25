package com.deep_coding15.GesStockApi.stock.web;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import com.deep_coding15.GesStockApi.stock.dto.StockCreateRequestDTO;
import com.deep_coding15.GesStockApi.stock.dto.StockPatchQuantityRequestDTO;
import com.deep_coding15.GesStockApi.stock.dto.StockResponseDTO;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.mapper.StockMapper;
import com.deep_coding15.GesStockApi.stock.service.StockService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    public StockController(
        StockService stockService,
        StockMapper stockMapper
    ) {
        this.stockService = stockService;
        this.stockMapper  = stockMapper;
    }

    /** 
     * @param stock
     * @return ResponseEntity<Stock>
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockResponseDTO createStock(@RequestBody StockCreateRequestDTO dto) {
        
        Produit produit = new Produit();
        produit.setId(dto.getProduitId());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getUtilisateurId());

        Stock stock = stockService.createStock(
                produit,
                dto.getQuantiteInitiale(),
                utilisateur
        );

        return stockMapper.toResponseDTO(stock);
    }

    /** 
     * @return ResponseEntity<List<Stock>>
     */
    @GetMapping
    public List<StockResponseDTO> getAllStocks() {
        return stockService.getStocks()
                .stream()
                .map(stockMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /** 
     * @param id
     * @return ResponseEntity<Stock>
     */
    @GetMapping("/id/{id}")
    public StockResponseDTO getStock(@PathVariable Long id) {
        return stockMapper.toResponseDTO(
                stockService.getStockById(id)
        );
    }

    /** 
     * @param produitId
     * @return ResponseEntity<Stock>
     */
    @GetMapping("/produit/{produitId}")
    public StockResponseDTO getStockByProduitId(@PathVariable Long produitId){
        return stockMapper.toResponseDTO(stockService.getStockByProduitId(produitId));
    }

    /* ===================== UPDATE ===================== */
    @PatchMapping("/{id}/quantite")
    public StockResponseDTO patchQuantite(
            @PathVariable Long id,
            @RequestBody StockPatchQuantityRequestDTO dto) {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getUtilisateurId());

        Stock stock = stockService.patchStockQuantite(
                id,
                dto.getDelta(),
                dto.getTypeMouvement(),
                utilisateur,
                dto.getCommentaire()
        );

        return stockMapper.toResponseDTO(stock);
    }
}
