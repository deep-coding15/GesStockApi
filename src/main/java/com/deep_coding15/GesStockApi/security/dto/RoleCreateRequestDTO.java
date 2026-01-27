package com.deep_coding15.GesStockApi.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleCreateRequestDTO {
    
    @NotBlank
    public String code;

    public String libelle;
    
    //Getters / Setters
}