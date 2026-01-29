package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorieResponseDTO {
    public Long id;
    public String code;
    public String libelle;
    public String description;
    public boolean actif;
}
