package com.deep_coding15.GesStockApi.stock.enums;

import java.util.Arrays;

import com.deep_coding15.GesStockApi.common.exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.utils.CodeEnumInterface;

public enum TypeMouvementStockEnum implements CodeEnumInterface{
    INITIAL("INITIAL", "Stock Initial"), 
    ENTREE("ENTREE", "Entree de stock"),
    SORTIE("SORTIE", "Sortie de stock"),
    AJUSTEMENT("AJUSTEMENT", "Ajustement de stock");

    private final String code;
    private final String libelle;
    private TypeMouvementStockEnum(String code, String libelle){
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode(){
        return this.code;
    }

    public String getLibelle(){
        return this.libelle;
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
