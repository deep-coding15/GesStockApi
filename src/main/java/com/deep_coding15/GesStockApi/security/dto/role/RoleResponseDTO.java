package com.deep_coding15.GesStockApi.security.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDTO {
    
    @NotNull
    public Long id;

    @NotBlank
    public String code;

    @NotBlank
    public String libelle;
      
    // Getters / Setters
}
