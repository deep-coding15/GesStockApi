package com.deep_coding15.GesStockApi.vente.dto.vente;

import java.util.List;

import com.deep_coding15.GesStockApi.vente.dto.venteLigne.VenteLigneCreateRequestDTO;

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
    
    @Schema(example = "1", description = "Code du statut initial de la vente (ex: ANNULEE, VALIDEE, EN_COURS)", requiredMode = Schema.RequiredMode.REQUIRED)
    public String statutCode;
    
    @Schema(description = "Liste des lignes de vente avec produits et quantités")
    public List<VenteLigneCreateRequestDTO> venteLignes;
}
