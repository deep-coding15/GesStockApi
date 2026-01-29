package com.deep_coding15.GesStockApi.stock.dto.stock_mouvement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMouvementResponseDTO {
    public Long id;
    public Long produitId;
    public Integer quantite;
    public String type;
    public Long utilisateurId;
    public String dateMouvement;
    public String commentaire;
}
