package com.deep_coding15.GesStockApi.security.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "RoleCreateRequest",
    description = "Requête de création d'un rôle utilisateur",
    example = "{\"code\": \"ADMIN\", \"libelle\": \"Administrateur système\"}"
)
public class RoleCreateRequestDTO {
    
    @NotBlank(message = "Code du rôle requis")
    @Schema(example = "ADMIN", description = "Code unique du rôle (majuscules recommandées)", requiredMode = Schema.RequiredMode.REQUIRED)
    public String code;

    @Schema(example = "Administrateur système", description = "Libellé descriptif du rôle (optionnel)")
    public String libelle;
}