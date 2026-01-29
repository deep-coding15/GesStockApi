package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurCreateRequestDTO {

    @NotBlank
    public String email;

    @Positive
    public Long   roleId;
    
    @NotBlank
    public String motDePasse;
    
    @NotBlank
    public String username;

    // getters / setters
}
