package com.deep_coding15.GesStockApi.catalogue.dto.categorie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriePatchRequestDTO {

    public String code = null;

    public String libelle = null;

    public String description = null;
    
    public boolean actif = false;
}
