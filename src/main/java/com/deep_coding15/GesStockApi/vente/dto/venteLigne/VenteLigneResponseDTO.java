package com.deep_coding15.GesStockApi.vente.dto.venteLigne;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(
    name = "VenteLigneResponse",
    description = "Détails d'une ligne de vente (article avec quantité et prix)",
    example = "{\"produit\": \"Lait Entier\", \"quantite\": 5, \"prixUnitaire\": 2.50}"
)
@Getter
@Setter
public class VenteLigneResponseDTO {
    @Schema(example = "Lait Entier", description = "Id du produit vendu")
    public Long produitId;
    
    @Schema(example = "5", description = "Quantité vendue de ce produit")
    public int quantite;
    
    @Schema(example = "2.50", description = "Prix unitaire du produit appliqué au moment de la vente")
    public double prixUnitaire; // Prix applique au moment de la vente
}
