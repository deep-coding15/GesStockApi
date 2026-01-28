package com.deep_coding15.GesStockApi.catalogue.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriePatchRequestDTO {

    public String Code;

    public String Libelle;

    public String Description;
}
