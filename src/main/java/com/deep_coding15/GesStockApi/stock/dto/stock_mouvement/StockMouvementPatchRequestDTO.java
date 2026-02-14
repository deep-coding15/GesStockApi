package com.deep_coding15.GesStockApi.stock.dto.stock_mouvement;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de modification de stock")
public class StockMouvementPatchRequestDTO {
    @Schema(example = "1", description = "Identifiant du produit (optionnel)")
    public Long produitId;

    @Schema(example = "ENTREE", description = "Type de mouvement: ENTREE, SORTIE, REAJUSTEMENT (optionnel)")
    public String typeMouvement;
    
    @Schema(example = "10", description = "Variation de la quantité (optionnel)")
    public Integer quantite;

    @Schema(example = "1", description = "Identifiant de l'utilisateur (optionnel)")
    public Long utilisateurId;

    @Schema(example = "Réapprovisionnement du stock", description = "Commentaire sur le mouvement (optionnel)")
    public String commentaire;
}
