package com.deep_coding15.GesStockApi.stock.enums;

import java.util.Arrays;

import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;

public enum TypeMouvementStockEnum {

    ENTREE("ENTREE", "Entrée en stock"),
    SORTIE("SORTIE", "Sortie de stock"),
    AJUSTEMENT("AJUSTEMENT", "Ajustement de stock");

    private final String code;
    private final String libelle;

    TypeMouvementStockEnum(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static TypeMouvementStockEnum fromCode(String code) {
        return Arrays.stream(values())
                .filter(t -> t.code.equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new EntityIllegalArgumentException(
                            "TypeMouvementStockEnum", 
                            "code", code)
                );
    }
}

