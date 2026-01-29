package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriePutRequestDTO {

    @NotBlank
    public String code;

    @NotBlank
    public String libelle;

    @NotBlank
    public String description;

    public boolean actif;
}
