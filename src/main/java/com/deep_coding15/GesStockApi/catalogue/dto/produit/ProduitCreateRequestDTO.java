package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "ProduitCreateRequest",
    description = "Requête de création d'un produit dans le catalogue",
    example = "{\"nom\": \"Lait Entier\", \"description\": \"Lait frais entier de qualité premium\", \"prix\": 25.50, \"categorieId\": 1}"
)
public class ProduitCreateRequestDTO {

    @NotBlank(message = "Nom du produit requis")
    @Schema(example = "Lait Entier", description = "Nom du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    public String nom;

    @NotBlank(message = "Description du produit requise")
    @Schema(example = "Lait frais entier de qualité premium", description = "Description du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    public String description;

    @Positive(message = "Prix doit être positif")
    @Schema(example = "25.50", description = "Prix unitaire du produit en devise locale", requiredMode = Schema.RequiredMode.REQUIRED)
    public double prix;

    @NotNull(message = "Catégorie requise")
    @Schema(example = "1", description = "Identifiant unique de la catégorie du produit", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long categorieId;
}
