package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "UtilisateurCreateRequest",
    description = "Requête de création d'un utilisateur avec rôle",
    example = "{\"email\": \"user@example.com\", \"username\": \"john.doe\", \"motDePasse\": \"SecurePassword123!\", \"roleId\": 1}"
)
public class UtilisateurCreateRequestDTO {

    @NotBlank(message = "Email requis")
    @Schema(example = "user@example.com", description = "Adresse email unique de l'utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    public String email;

    @Positive(message = "ID de rôle doit être positif")
    @Schema(example = "1", description = "Identifiant du rôle assigné à l'utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long   roleId;
    
    @NotBlank(message = "Mot de passe requis")
    @Schema(example = "SecurePassword123!", description = "Mot de passe sécurisé de l'utilisateur (min 8 caractères)", requiredMode = Schema.RequiredMode.REQUIRED)
    public String motDePasse;
    
    @NotBlank(message = "Username requis")
    @Schema(example = "john.doe", description = "Nom d'utilisateur unique (alphanumériques et _ -)", requiredMode = Schema.RequiredMode.REQUIRED)
    public String username;
}
