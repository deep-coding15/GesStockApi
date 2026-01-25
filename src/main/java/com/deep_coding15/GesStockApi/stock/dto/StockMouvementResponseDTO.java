package com.deep_coding15.GesStockApi.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMouvementResponseDTO {
    public Long id;
    public int produitId;
    public int quantite;
    public String type;
    public Long utilisateurId;
    public String dateMouvement;
    public String commentaire;
}
