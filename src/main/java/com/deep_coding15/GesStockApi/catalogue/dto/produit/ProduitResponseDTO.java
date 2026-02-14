package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "ProduitResponse",
    description = "Réponse contenant les détails complets d'un produit du catalogue",
    example = "{\"id\": 1, \"reference\": \"PROD-001\", \"nom\": \"Lait Entier\", \"description\": \"Lait frais\", \"prixUnitaire\": 2.50, \"categorieCode\": \"LAITIER\", \"actif\": true}"
)
public class ProduitResponseDTO {
    @Schema(example = "1", description = "Identifiant unique du produit (auto-généré)")
    public Long id;
    
    @Schema(example = "PROD-001", description = "Référence unique et immutable du produit")
    public String reference;
    
    @Schema(example = "Lait Entier", description = "Nom du produit (modifiable)")
    public String nom;
    
    @Schema(example = "Lait frais entier de qualité premium", description = "Description détaillée du produit")
    public String description;
    
    @Schema(example = "2.50", description = "Prix unitaire de vente du produit en devise locale")
    public double getPrixUnitaire;
    
    @Schema(example = "LAITIER", description = "Code de la catégorie associée au produit")
    public String categorieCode;
    
    @Schema(example = "true", description = "État du produit (true=actif et disponible, false=inactif/supprimé)")
    public boolean actif;
}
