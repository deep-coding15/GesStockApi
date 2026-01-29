package com.deep_coding15.GesStockApi.stock.dto.stock_mouvement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMouvementCreateRequestDTO {
    public String type; // ENTREE : SORTIE : REAJUSTEMENT
    public int quantite;
    public Long utilisateurId;
    public String commentaire;
}
