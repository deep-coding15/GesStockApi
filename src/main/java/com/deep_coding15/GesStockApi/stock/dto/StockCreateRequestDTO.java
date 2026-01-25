package com.deep_coding15.GesStockApi.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCreateRequestDTO {
    private Long produitId;
    private Integer quantiteInitiale;
    private Long utilisateurId;
}