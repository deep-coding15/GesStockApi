package com.deep_coding15.GesStockApi.stock.mapper;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.stock.dto.stock_mouvement.StockMouvementResponseDTO;
import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;

@Component
public class StockMouvementMapper {

    public StockMouvementResponseDTO toResponse(StockMouvement m) {
        StockMouvementResponseDTO dto = new StockMouvementResponseDTO();
        dto.setId(m.getId());
        dto.setType(m.getTypeMouvement().getCode());
        dto.setQuantite(m.getQuantite());
        dto.setDateMouvement(m.getDateMouvement().toString());
        dto.setProduitId(m.getStock().getProduit().getId());
        dto.setUtilisateurId(m.getUtilisateur().getId());
        dto.setCommentaire(m.getCommentaire());
        return dto;
    }
}
