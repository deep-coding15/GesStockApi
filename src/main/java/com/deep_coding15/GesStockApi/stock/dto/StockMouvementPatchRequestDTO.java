package com.deep_coding15.GesStockApi.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMouvementPatchRequestDTO {
    public Long produitId;
    public String type; // ENTREE : SORTIE : REAJUSTEMENT
    public int quantite;
    public Long utilisateurId;
    public String commentaire;
}
