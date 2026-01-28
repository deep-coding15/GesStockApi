package com.deep_coding15.GesStockApi.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDTO {
    
    public Long id;

    @NotBlank
    public String code;

    @NotBlank
    public String libelle;
      
    // Getters / Setters
}
