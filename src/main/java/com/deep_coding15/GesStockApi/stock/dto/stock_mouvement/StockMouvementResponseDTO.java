package com.deep_coding15.GesStockApi.stock.dto.stock_mouvement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "StockMouvementResponse",
    description = "Réponse contenant les détails complets d'un mouvement de stock",
    example = "{\"id\": 1, \"produitId\": 1, \"quantite\": 50, \"type\": \"ENTREE\", \"utilisateurId\": 1, \"dateMouvement\": \"2025-01-30T14:30:00\", \"commentaire\": \"Réapprovisionnement\"}"
)
public class StockMouvementResponseDTO {
    @Schema(example = "1", description = "Identifiant unique du mouvement (auto-généré)")
    public Long id;
    
    @Schema(example = "1", description = "Identifiant du produit affecté par ce mouvement")
    public Long produitId;
    
    @Schema(example = "50", description = "Quantité affectée (+ pour entrée, - pour sortie)")
    public Integer quantite;
    
    @Schema(example = "ENTREE", description = "Type de mouvement: INITIAL, ENTREE, SORTIE, REAJUSTEMENT")
    public String type;
    
    @Schema(example = "1", description = "Identifiant de l'utilisateur ayant effectué le mouvement")
    public Long utilisateurId;
    
    @Schema(example = "2025-01-30T14:30:00", description = "Horodatage exact du mouvement")
    public String dateMouvement;
    
    @Schema(example = "Réapprovisionnement suite à rupture", description = "Notes ou raison du mouvement (optionnel)")
    public String commentaire;
}
