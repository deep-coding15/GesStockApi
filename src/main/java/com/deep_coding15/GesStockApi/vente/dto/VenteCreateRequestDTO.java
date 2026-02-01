package com.deep_coding15.GesStockApi.vente.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "VenteCreateRequest",
    description = "Requête de création d'une vente avec ses articles",
    example = "{\"utilisateurId\": 1, \"statutId\": 1, \"lignevente\": [{\"produitId\": 1, \"quantite\": 2, \"prixUnitaire\": 25.50}]}"
)
public class VenteCreateRequestDTO {
    @Schema(example = "1", description = "Identifiant de l'utilisateur effectuant la vente", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long utilisateurId;
    
    @Schema(example = "1", description = "Identifiant du statut initial de la vente (ex: PENDING, CONFIRMED)", requiredMode = Schema.RequiredMode.REQUIRED)
    public Long statutId;
    
    @Schema(description = "Liste des lignes de vente avec produits, quantités et prix unitaires")
    public List<VenteLigneRequestDTO> lignevente;
}
