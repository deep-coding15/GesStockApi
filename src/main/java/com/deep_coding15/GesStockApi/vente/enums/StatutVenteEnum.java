package com.deep_coding15.GesStockApi.vente.enums;

import java.util.Arrays;

import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;

public enum StatutVenteEnum {
    EN_COURS("EN_COURS", "Entrée en stock"),
    VALIDEE("VALIDEE", "Sortie de stock"),
    ANNULEE("ANNULEE", "Ajustement de stock");
    
    private final String code;
    private final String libelle;

    StatutVenteEnum(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static StatutVenteEnum fromCode(String code) {
        return Arrays.stream(values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new EntityIllegalArgumentException(
                            "StatutVenteEnum", 
                            "code", code)
                );
    }
}
