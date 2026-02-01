package com.deep_coding15.GesStockApi.vente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "VenteLigneRequest",
    description = "Ligne de vente (produit avec quantité) pour une vente",
    example = "{\"id\": 1, \"quantite\": 5}"
)
public class VenteLigneRequestDTO {
    @Schema(example = "1", description = "Identifiant unique du produit vendu", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long id;
    
    @Schema(example = "5", description = "Quantité vendue du produit (doit être > 0)", requiredMode = Schema.RequiredMode.REQUIRED)
    public int quantite;
}
