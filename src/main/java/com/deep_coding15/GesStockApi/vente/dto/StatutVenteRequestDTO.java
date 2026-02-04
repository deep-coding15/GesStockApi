package com.deep_coding15.GesStockApi.vente.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Requête pour définir le statut d'une vente")
public class StatutVenteRequestDTO {
    @Schema(example = "CONFIRMEE", description = "Code du statut de la vente", requiredMode = Schema.RequiredMode.REQUIRED)
    public String code;
}
