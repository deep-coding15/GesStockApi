package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de modification partielle d'un utilisateur")
public class UtilisateurPatchRequestDTO {

    @Schema(example = "user@example.com", description = "Adresse email de l'utilisateur (optionnel)")
    public String email;

    @Schema(example = "1", description = "Identifiant unique du rôle (optionnel)")
    public Long   roleId;

    @Schema(example = "NewPassword123!", description = "Nouveau mot de passe (optionnel)")
    public String motDePasse;

    @Schema(example = "john.doe", description = "Nom d'utilisateur (optionnel)")
    public String username;

    // getters / setters
}
