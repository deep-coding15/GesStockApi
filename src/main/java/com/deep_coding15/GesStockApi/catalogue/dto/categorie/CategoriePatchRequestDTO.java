package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de modification partielle d'une catégorie (mise à jour partielle)")
public class CategoriePatchRequestDTO {

    @Schema(example = "CAT-LAITIER", description = "Code unique de la catégorie (optionnel)")
    public String code = null;

    @Schema(example = "Produits Laitiers", description = "Libellé de la catégorie (optionnel)")
    public String libelle = null;

    @Schema(example = "Ensemble des produits laitiers disponibles", description = "Description de la catégorie (optionnel)")
    public String description = null;
    
    @Schema(example = "true", description = "Statut d'activation de la catégorie (optionnel)")
    public boolean actif = false;
}
