package com.deep_coding15.GesStockApi.stock.dto.stock;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "StockPatchQuantityRequest",
    description = "Requête de modification de la quantité d'un stock via un mouvement",
    example = "{\"delta\": 10, \"typeMouvement\": \"ENTREE\", \"commentaire\": \"Réapprovisionnement\", \"utilisateurId\": 1}"
)
public class StockPatchQuantityRequestDTO {
    @Schema(example = "10", description = "Variation de la quantité (positif pour augmenter, négatif pour diminuer)")
    private int delta;
    
    @Schema(example = "ENTREE", description = "Type de mouvement: INITIAL, ENTREE, SORTIE, REAJUSTEMENT", requiredMode = Schema.RequiredMode.REQUIRED)
    private String typeMouvement;
    
    @Schema(example = "Réapprovisionnement du stock laitier", description = "Commentaire ou raison du mouvement (optionnel)")
    private String commentaire;
    
    @Schema(example = "1", description = "Identifiant de l'utilisateur effectuant le mouvement", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long utilisateurId;

}
