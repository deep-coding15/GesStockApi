package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurPutRequestDTO {

    @NotBlank
    public String email;

    @NotBlank
    public Long   roleId;
    
    @NotBlank
    public String motDePasse;
    
    @NotBlank
    public String username;

    // getters / setters
}

