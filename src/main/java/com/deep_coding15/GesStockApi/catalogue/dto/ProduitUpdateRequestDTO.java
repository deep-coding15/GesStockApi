package com.deep_coding15.GesStockApi.catalogue.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitUpdateRequestDTO {
    public int produitId;
    public String reference;
    public String nom;
    public String description;
    public double prix;
    public Long categorieId;
}

