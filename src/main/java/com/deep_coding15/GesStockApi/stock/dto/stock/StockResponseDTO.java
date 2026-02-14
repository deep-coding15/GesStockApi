package com.deep_coding15.GesStockApi.stock.dto.stock;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

import com.deep_coding15.GesStockApi.stock.dto.stock_mouvement.StockMouvementResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "StockResponse",
    description = "Réponse contenant les détails complets d'un stock et ses mouvements associés",
    example = "{\"id\": 1, \"produitId\": 1, \"quantite\": 150, \"mouvements\": []}"
)
public class StockResponseDTO {
    @Schema(example = "1", description = "Identifiant unique du stock (auto-généré)")
    public Long id;
    
    @Schema(example = "1", description = "Identifiant du produit associé à ce stock")
    public Long produitId;
    
    @Schema(example = "150", description = "Quantité actuelle en stock (peut être zéro)")
    public int quantite;
    
    @Schema(description = "Historique de tous les mouvements de stock (ENTREE/SORTIE/etc)")
    private List<StockMouvementResponseDTO> mouvements;
}
