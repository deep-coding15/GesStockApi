package com.deep_coding15.GesStockApi.security.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "RoleResponse",
    description = "Réponse contenant les détails complets d'un rôle utilisateur",
    example = "{\"id\": 1, \"code\": \"ADMIN\", \"libelle\": \"Administrateur système\"}"
)
public class RoleResponseDTO {
    
    @NotNull
    @Schema(example = "1", description = "Identifiant unique du rôle (auto-généré)")
    public Long id;

    @NotBlank
    @Schema(example = "ADMIN", description = "Code unique et immutable du rôle")
    public String code;

    @NotBlank
    @Schema(example = "Administrateur système", description = "Libellé descriptif et modifiable du rôle")
    public String libelle;
}
