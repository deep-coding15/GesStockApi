package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitResponseDTO {
    public Long id;
    public String reference;
    public String nom;
    public String description;
    public double getPrixUnitaire;
    public String categorieCode;
    public boolean actif; 
}
