package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de remplacement complet d'un utilisateur")
public class UtilisateurPutRequestDTO {

    @NotBlank
    @Schema(example = "user@example.com", description = "Adresse email de l'utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    public String email;

    @NotBlank
    @Schema(example = "1", description = "Identifiant du rôle", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long   roleId;
    
    @NotBlank
    @Schema(example = "SecurePassword123!", description = "Mot de passe sécurisé", requiredMode = Schema.RequiredMode.REQUIRED)
    public String motDePasse;
    
    @NotBlank
    @Schema(example = "john.doe", description = "Nom d'utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    public String username;

    // getters / setters
}

