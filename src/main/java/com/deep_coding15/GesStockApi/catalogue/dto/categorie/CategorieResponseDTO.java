package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Réponse contenant les détails d'une catégorie de produit")
public class CategorieResponseDTO {
    @Schema(example = "1", description = "Identifiant unique de la catégorie")
    public Long id;
    
    @Schema(example = "CAT-LAITIER", description = "Code unique de la catégorie")
    public String code;
    
    @Schema(example = "Produits Laitiers", description = "Libellé de la catégorie")
    public String libelle;
    
    @Schema(example = "Ensemble des produits laitiers disponibles", description = "Description détaillée de la catégorie")
    public String description;
    
    @Schema(example = "true", description = "Statut d'activation de la catégorie")
    public boolean actif;
}
