package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriePutRequestDTO {

    @NotBlank
    public String Code;

    @NotBlank
    public String Libelle;

    @NotBlank
    public String Description;
}
