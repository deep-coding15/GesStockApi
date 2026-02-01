package com.deep_coding15.GesStockApi.security.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête de modification partielle d'un rôle")
public class RolePatchRequestDTO {
    
    @Schema(example = "ADMIN", description = "Code unique du rôle (optionnel)")
    public String code;

    @Schema(example = "Administrateur système", description = "Libellé descriptif du rôle (optionnel)")
    public String libelle;
}
