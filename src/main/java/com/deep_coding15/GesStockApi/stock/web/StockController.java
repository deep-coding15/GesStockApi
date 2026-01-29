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

import jakarta.validation.Valid;

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
    @PostMapping("/")
    public ResponseEntity<StockResponseDTO> createStock(
        @Valid @RequestBody StockCreateRequestDTO dto) {

        Stock stock = stockService.createStock(
                dto.getProduitId(),
                dto.getQuantiteInitiale(),
                dto.getUtilisateurId()
        );

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
                stockService.getStockById(id)), HttpStatus.OK
        );
    }

    /** 
     * @param produitId
     * @return ResponseEntity<Stock>
     */
    @GetMapping("/produit/{produitId}")
    public ResponseEntity<StockResponseDTO> getStockByProduitId(@PathVariable Long produitId){
        return new ResponseEntity<>(stockMapper
            .toResponseDTO(stockService.getStockByProduitId(produitId)), 
            HttpStatus.OK);
    }

    /* ===================== UPDATE ===================== */
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
                dto.getCommentaire()
        );

        return new ResponseEntity<>(
            stockMapper.toResponseDTO(stock), 
            HttpStatus.OK
        );
    }
}
