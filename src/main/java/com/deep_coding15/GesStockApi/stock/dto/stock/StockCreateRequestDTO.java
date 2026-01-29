package com.deep_coding15.GesStockApi.stock.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCreateRequestDTO {
    
    @NotNull
    @Positive
    private Long produitId;

    @NotNull
    @Positive
    private Integer quantiteInitiale;

    @NotNull
    @Positive
    private Long utilisateurId;
}