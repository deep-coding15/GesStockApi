package com.deep_coding15.GesStockApi.stock.dto;

import java.util.List;

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
