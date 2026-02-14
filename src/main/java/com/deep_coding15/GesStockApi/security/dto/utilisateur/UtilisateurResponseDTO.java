package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(
    name = "UtilisateurResponse",
    description = "Réponse contenant les détails complets d'un utilisateur du système",
    example = "{\"id\": 1, \"email\": \"user@example.com\", \"username\": \"john.doe\", \"actif\": true, \"role\": {\"id\": 1, \"code\": \"USER\", \"libelle\": \"Utilisateur standard\"}}"
)
public class UtilisateurResponseDTO {
    
    @NotNull
    @Schema(example = "1", description = "Identifiant unique de l'utilisateur (auto-généré)")
    public Long id;

    @NotBlank
    @Schema(example = "user@example.com", description = "Adresse email unique et immutable de l'utilisateur")
    public String email;

    @NotBlank
    @Schema(example = "john.doe", description = "Nom d'utilisateur unique pour l'authentification")
    public String username;
    
    @Schema(example = "true", description = "État de l'utilisateur (true=actif, false=désactivé/suspendu)")
    public boolean actif = true;   
    
    @NotNull
    @Schema(description = "Détails complets du rôle assigné à cet utilisateur")
    public RoleResponseDTO role;
}

