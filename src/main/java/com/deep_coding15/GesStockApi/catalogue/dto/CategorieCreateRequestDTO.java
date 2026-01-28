package com.deep_coding15.GesStockApi.catalogue.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorieCreateRequestDTO {

    @NotBlank
    public String Code;

    @NotBlank
    public String Libelle;

    @NotBlank
    public String Description;
}
