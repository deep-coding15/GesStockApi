package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "CategorieCreateRequest",
    description = "Requête pour créer une nouvelle catégorie de produit",
    example = "{\"code\": \"CAT-LAITIER\", \"libelle\": \"Produits Laitiers\", \"description\": \"Ensemble des produits laitiers\"}"
)
public class CategorieCreateRequestDTO {

    @NotBlank(message = "Le code de la catégorie est obligatoire")
    @Schema(
        example = "CAT-LAITIER", 
        description = "Code unique et identifiant de la catégorie",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    public String code;

    @NotBlank(message = "Le libellé de la catégorie est obligatoire")
    @Schema(
        example = "Produits Laitiers", 
        description = "Libellé court et descriptif de la catégorie",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    public String libelle;

    @NotBlank(message = "La description de la catégorie est obligatoire")
    @Schema(
        example = "Ensemble des produits laitiers frais et de qualité", 
        description = "Description détaillée de la catégorie",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    public String description;
}
