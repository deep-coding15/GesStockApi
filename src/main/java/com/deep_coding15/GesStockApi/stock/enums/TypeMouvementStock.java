package com.deep_coding15.GesStockApi.stock.enums;

public enum TypeMouvementStock {
    INITIAL("INITIAL", "Stock Initial"), 
    ENTREE("ENTREE", "Entree de stock"),
    SORTIE("SORTIE", "Sortie de stock"),
    AJUSTEMENT("AJUSTEMENT", "Ajustement de stock");

    private final String code;
    private final String libelle;
    private TypeMouvementStock(String code, String libelle){
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode(){
        return this.code;
    }

    public String getLibelle(){
        return this.libelle;
    }

}
