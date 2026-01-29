package com.deep_coding15.GesStockApi.stock.dto.stock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateRequestDTO {
    public Long id;
    public Long produitId;
    public int quantite;
}
