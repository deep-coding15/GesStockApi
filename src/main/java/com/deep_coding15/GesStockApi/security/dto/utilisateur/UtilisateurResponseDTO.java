package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import com.deep_coding15.GesStockApi.security.dto.role.RoleResponseDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurResponseDTO {
    
    @NotNull
    public Long id;

    @NotBlank
    public String email;

    @NotBlank
    public String username;
    public boolean actif = true;   
    
    @NotNull
    public RoleResponseDTO role;

    // getters / setters
}

