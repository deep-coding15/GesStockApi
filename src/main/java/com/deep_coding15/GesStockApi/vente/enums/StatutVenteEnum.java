package com.deep_coding15.GesStockApi.vente.enums;

import java.util.Arrays;

import com.deep_coding15.GesStockApi.common.exception.EntityIllegalArgumentException;

import com.deep_coding15.GesStockApi.common.utils.CodeEnumInterface;

public enum StatutVenteEnum implements CodeEnumInterface {
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
