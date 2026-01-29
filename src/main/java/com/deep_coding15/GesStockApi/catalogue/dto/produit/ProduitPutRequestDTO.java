package com.deep_coding15.GesStockApi.catalogue.dto.produit;


import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitPutRequestDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String reference;

    @NotBlank
    private String description;

    @Positive
    private BigDecimal prix;
    
    @NotNull
    private Long categorieId;

    // getters / setters
}
