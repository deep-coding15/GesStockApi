package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitCreateRequestDTO {

    @NotBlank
    public String nom;

    @NotBlank
    public String description;

    @Positive
    public double prix;

    @NotNull
    public Long categorieId;
}
