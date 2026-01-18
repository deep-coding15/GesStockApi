package com.deep_coding15.GesStockApi.security.dto;

public class RoleResponseDTO {
    public Long id;
    public String code;
    public String libelle;
      
    // Getters / Setters
    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Long getId() { return this.id; }
    public String getCode() { return this.code; }
    public String getLibelle() { return this.libelle; }
}
