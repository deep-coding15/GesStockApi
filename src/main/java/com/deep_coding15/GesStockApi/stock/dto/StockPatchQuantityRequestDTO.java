package com.deep_coding15.GesStockApi.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPatchQuantityRequestDTO {
    private int delta; // + ou -
    /** INITIAL,
    * ENTREE,
    * SORTIE,
    * REAJUSTEMENT
     */
    private String typeMouvement;
    private String commentaire;
    private Long utilisateurId;

}
