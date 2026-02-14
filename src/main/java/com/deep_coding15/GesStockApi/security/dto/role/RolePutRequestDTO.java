package com.deep_coding15.GesStockApi.security.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de remplacement complet d'un rôle")
public class RolePutRequestDTO {

    @NotBlank
    @Schema(example = "ADMIN", description = "Code unique du rôle", requiredMode = Schema.RequiredMode.REQUIRED)
    public String code;

    @NotBlank
    @Schema(example = "Administrateur système", description = "Libellé descriptif du rôle", requiredMode = Schema.RequiredMode.REQUIRED)
    public String libelle;
}
