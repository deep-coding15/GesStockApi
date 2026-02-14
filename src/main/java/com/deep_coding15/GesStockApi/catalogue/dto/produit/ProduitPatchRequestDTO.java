package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de modification partielle d'un produit")
public class ProduitPatchRequestDTO {

    @NotNull
    @Schema(example = "1", description = "Identifiant unique du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(example = "Lait Entier", description = "Nom du produit (optionnel)")
    private String nom;

    @Schema(example = "PROD-001", description = "Référence du produit (optionnel)")
    private String reference;

    @Schema(example = "Lait frais entier", description = "Description du produit (optionnel)")
    private String description;

    @Schema(example = "2.50", description = "Prix unitaire du produit (optionnel)")
    private BigDecimal prix;

    @Schema(example = "1", description = "Identifiant de la catégorie (optionnel)")
    private Long categorieId;

    // getters / setters
}
