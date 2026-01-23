package com.deep_coding15.GesStockApi.catalogue.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProduitPatchRequestDTO {

    private String nom;
    private String description;
    private BigDecimal prix;
    private Long categorieId;

    // getters / setters
}
