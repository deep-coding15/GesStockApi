package com.deep_coding15.GesStockApi.vente.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "VenteResponse",
    description = "Réponse contenant les détails complets d'une vente avec tous ses articles",
    example = "{\"id\": 1, \"reference\": \"VENTE-2025-001\", \"dateVente\": \"2025-01-30T14:30:00\", \"utilisateur\": \"john.doe\", \"statut\": \"CONFIRMEE\", \"total\": 150.75, \"ligneVentes\": []}"
)
public class VenteResponseDTO {
    @Schema(example = "1", description = "Identifiant unique de la vente (auto-généré)")
    public Long id;
    
    @Schema(example = "VENTE-2025-001", description = "Référence unique et immutable de la vente")
    public String reference;
    
    @Schema(example = "2025-01-30T14:30:00", description = "Date et heure exacte de la transaction")
    public String dateVente;
    
    @Schema(example = "john.doe", description = "Nom d'utilisateur du vendeur/caissier")
    public String utilisateur;
    
    @Schema(example = "CONFIRMEE", description = "Statut actuel: PENDING, CONFIRMEE, VALIDEE, ANNULEE, etc")
    public String statut;
    
    @Schema(example = "150.75", description = "Montant total calculé (somme de tous les articles)")
    public double total;
    
    @Schema(description = "Liste complète des articles vendus avec quantités et prix")
    public List<VenteLigneResponseDTO> ligneVentes;
}
