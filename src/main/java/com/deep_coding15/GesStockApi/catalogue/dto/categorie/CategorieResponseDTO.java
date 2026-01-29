package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorieResponseDTO {
    public Long id;
    public String Code;
    public String Libelle;
    public String Description;
    public boolean actif;
}
