package com.deep_coding15.GesStockApi.stock.dto.stock;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "StockCreateRequest",
    description = "Requête de création d'un stock pour un produit",
    example = "{\"produitId\": 1, \"quantiteInitiale\": 100, \"utilisateurId\": 1}"
)
public class StockCreateRequestDTO {
    
    @NotNull
    @Positive
    @Schema(example = "1", description = "Identifiant unique du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long produitId;

    @NotNull
    @Positive
    @Schema(example = "100", description = "Quantité initiale en stock", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantiteInitiale;

    @NotNull
    @Positive
    @Schema(example = "1", description = "Identifiant de l'utilisateur qui crée le stock", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long utilisateurId;
}