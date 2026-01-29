package com.deep_coding15.GesStockApi.catalogue.dto.produit;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitPatchRequestDTO {

    @NotNull
    private Long id;

    private String nom;

    private String reference;

    private String description;

    private BigDecimal prix;

    private Long categorieId;

    // getters / setters
}
