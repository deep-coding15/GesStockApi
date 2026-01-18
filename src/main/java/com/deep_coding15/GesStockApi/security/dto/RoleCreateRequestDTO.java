package com.deep_coding15.GesStockApi.security.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleCreateRequestDTO {
    @NotBlank
    public String code;

    @NotBlank
    public String libelle;
    
    //Getters / Setters
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return this.code;
    }
    
    public String getLibelle() {
        return this.libelle;
    }
}