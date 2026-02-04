package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de remplacement complet d'une catégorie")
public class CategoriePutRequestDTO {

    @NotBlank
    @Schema(example = "CAT-LAITIER", description = "Code unique de la catégorie", requiredMode = Schema.RequiredMode.REQUIRED)
    public String code;

    @NotBlank
    @Schema(example = "Produits Laitiers", description = "Libellé de la catégorie", requiredMode = Schema.RequiredMode.REQUIRED)
    public String libelle;

    @NotBlank
    @Schema(example = "Ensemble des produits laitiers disponibles", description = "Description de la catégorie", requiredMode = Schema.RequiredMode.REQUIRED)
    public String description;

    @NotNull
    @Schema(example = "true", description = "Statut d'activation de la catégorie")
    public boolean actif;
}
