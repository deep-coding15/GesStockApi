package com.deep_coding15.GesStockApi.stock.dto;

public class StockMouvementRequestDTO {
    public Long produitId;
    public String type; // ENTREE : SORTIE : REAJUSTEMENT
    public int quantite;
    public Long utilisateurId;
    public String commentaire;
}
