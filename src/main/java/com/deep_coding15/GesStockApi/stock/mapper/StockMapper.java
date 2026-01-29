package com.deep_coding15.GesStockApi.stock.mapper;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.catalogue.service.ProduitService;
import com.deep_coding15.GesStockApi.stock.dto.stock.StockResponseDTO;
import com.deep_coding15.GesStockApi.stock.dto.stock_mouvement.StockMouvementResponseDTO;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.service.StockService;

@Component
public class StockMapper {

    private final StockService stockService;
    private final ProduitService produitService;
    public StockMapper(
        StockService stockService,
        ProduitService produitService
    ){
        this.stockService   = stockService;
        this.produitService = produitService;
    }
    
    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/
    /* public Stock toEntity(StockCreateRequestDTO dto) {
        
        Stock stock = new Stock();
        //utilisateur.setRole();
        Produit produit = produitService.getProduitById(dto.getProduitId());
        
        stock.setQuantite(dto.getQuantiteInitiale());
        stock.setProduit(produit);
        
        return stock;
    } */
    
    /* public Stock toEntity(StockUpdateRequestDTO dto) {
        
        Stock stock = new Stock();
        //utilisateur.setRole();
        Produit produit = produitService.getProduitById(dto.getProduitId());
        
        stock.setId(dto.getId());
        stock.setQuantite(dto.getQuantite());
        stock.setProduit(produit);

        return stock;
    } */
    
    /* public Stock toEntity(StockPatchRequestDTO dto) {
        
        Stock stock = new Stock();
        //utilisateur.setRole();
        Produit produit = produitService.getProduitById(dto.getProduitId());
        
        stock.setId(dto.getId());
        stock.setQuantite(dto.getQuantite());
        stock.setProduit(produit);

        return stock;
    } */

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/
    public StockResponseDTO toResponseDTO(Stock stock) {
        StockResponseDTO dto = new StockResponseDTO();
        dto.setId(stock.getId());
        dto.setQuantite(stock.getQuantite());
        dto.setProduitId(stock.getProduit().getId());
        dto.setMouvements(
            stock.getMouvements()
                 .stream()
                 .map(this::toMouvementResponse)
                 .toList()
        );
        return dto;
    }

    private StockMouvementResponseDTO toMouvementResponse(StockMouvement m) {
        StockMouvementResponseDTO dto = new StockMouvementResponseDTO();
        dto.setType(m.getTypeMouvement().getCode());
        dto.setQuantite(m.getQuantite());
        dto.setDateMouvement(m.getDateMouvement().toString());
        dto.setUtilisateurId(m.getUtilisateur().getId());
        dto.setCommentaire(m.getCommentaire());
        return dto;
    }

}
