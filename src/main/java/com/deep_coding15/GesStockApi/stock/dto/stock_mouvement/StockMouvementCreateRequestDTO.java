package com.deep_coding15.GesStockApi.stock.dto.stock_mouvement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de création d'un mouvement de stock")
public class StockMouvementCreateRequestDTO {
    @Schema(example = "ENTREE", description = "Type de mouvement: ENTREE, SORTIE, REAJUSTEMENT", requiredMode = Schema.RequiredMode.REQUIRED)
    public String type;
    
    @Schema(example = "50", description = "Quantité du mouvement", requiredMode = Schema.RequiredMode.REQUIRED)
    public int quantite;
    
    @Schema(example = "1", description = "Identifiant de l'utilisateur effectuant le mouvement", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long utilisateurId;
    
    @Schema(example = "Réapprovisionnement suite à rupture", description = "Commentaire sur le mouvement (optionnel)")
    public String commentaire;
}
