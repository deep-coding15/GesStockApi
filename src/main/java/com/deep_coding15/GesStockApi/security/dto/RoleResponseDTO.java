package com.deep_coding15.GesStockApi.security.dto;

public class RoleResponseDTO {
    public Long id;
    public String code;
      
    // Getters / Setters
    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }

    public Long getId() { return this.id; }
    public String getCode() { return this.code; }
}
