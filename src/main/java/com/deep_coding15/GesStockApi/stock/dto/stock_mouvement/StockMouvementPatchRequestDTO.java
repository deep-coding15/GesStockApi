package com.deep_coding15.GesStockApi.stock.dto.stock_mouvement;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de modification de stock")
public class StockMouvementPatchRequestDTO {
    public Long produitId;

    @Schema(example = "ENTREE", description = "Type de mouvement")
    public String typeMouvement; // ENTREE : SORTIE : REAJUSTEMENT
    
    @Schema(example = "10", description = "Variation de la quantité")
    public Integer quantite;

    @Schema(example = "1", description = "ID de l'utilisateur")
    public Long utilisateurId;

    @Schema(example = "Réapprovisionnement")
    public String commentaire;
}
