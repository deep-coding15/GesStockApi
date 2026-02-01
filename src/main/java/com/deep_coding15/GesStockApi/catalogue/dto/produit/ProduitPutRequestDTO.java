package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de remplacement complet d'un produit")
public class ProduitPutRequestDTO {

    @NotNull
    @Schema(example = "1", description = "Identifiant unique du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @NotBlank
    @Schema(example = "Lait Entier", description = "Nom du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;

    @NotBlank
    @Schema(example = "PROD-001", description = "Référence du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reference;

    @NotBlank
    @Schema(example = "Lait frais entier de qualité premium", description = "Description du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Positive
    @Schema(example = "2.50", description = "Prix unitaire du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal prix;
    
    @NotNull
    @Schema(example = "1", description = "Identifiant de la catégorie du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categorieId;

    // getters / setters
}
