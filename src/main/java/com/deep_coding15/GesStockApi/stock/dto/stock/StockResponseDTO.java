package com.deep_coding15.GesStockApi.stock.dto.stock;

import java.util.List;

import com.deep_coding15.GesStockApi.stock.dto.stock_mouvement.StockMouvementResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponseDTO {
    public Long id;
    public Long produitId;
    public int quantite;
    private List<StockMouvementResponseDTO> mouvements;
}
