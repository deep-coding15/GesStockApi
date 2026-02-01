package com.deep_coding15.GesStockApi.vente.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.vente.dto.VenteCreateRequestDTO;
import com.deep_coding15.GesStockApi.vente.dto.VenteResponseDTO;

import com.deep_coding15.GesStockApi.vente.entity.Vente;
import com.deep_coding15.GesStockApi.vente.service.VenteService;
import com.deep_coding15.GesStockApi.vente.mapper.VenteMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import java.util.List;

@Tag(
    name = "Vente",
    description = "Gestion complète des ventes et transactions commerciales"
)
@ApiResponses({
    @ApiResponse(responseCode = "400", description = "Requête invalide ou paramètres manquants"),
    @ApiResponse(responseCode = "401", description = "Non autorisé - authentification requise"),
    @ApiResponse(responseCode = "403", description = "Interdit - permissions insuffisantes"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
@RestController
@RequestMapping("/api/v1/ventes")
public class VenteController {
    
    private final VenteService venteService;
    private final VenteMapper venteMapper;

    public VenteController(
            VenteService venteService,
            VenteMapper venteMapper) {
        this.venteService = venteService;
        this.venteMapper = venteMapper;
    }

    /**
     * POST /api/v1/ventes/
     * Crée une nouvelle vente
     * @param dto Données de la vente à créer (articles, montant, client, etc.)
     * @return La vente créée avec le code 201
     */
    /* @Operation(
        summary = "POST: Créer une vente",
        description = "Crée une nouvelle vente avec ses articles associés et met à jour les stocks"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "Vente créée avec succès",
                content = @Content(schema = @Schema(implementation = VenteResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides (articles manquants, montant négatif)"),
            @ApiResponse(responseCode = "404", description = "Produit ou utilisateur non trouvé"),
            @ApiResponse(responseCode = "409", description = "Quantité insuffisante en stock pour un article"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/")
    public ResponseEntity<VenteResponseDTO> createVente(
            @Valid @RequestBody VenteCreateRequestDTO dto) {
        
        Vente venteCreee = venteService.createVente(vente);
        VenteResponseDTO responseDTO = venteMapper.toResponse(venteCreee);
        
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
 */
    /**
     * GET /api/v1/ventes/
     * Récupère toutes les ventes
     * @return Liste de toutes les ventes
     */
    /* @GetMapping("/")
    @Operation(
        summary = "GET: Lister toutes les ventes",
        description = "Récupère la liste complète de toutes les ventes du système"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Ventes récupérées avec succès",
                content = @Content(schema = @Schema(implementation = VenteResponseDTO.class))
            ),
            @ApiResponse(responseCode = "204", description = "Aucune vente disponible"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<VenteResponseDTO>> getAllVentes() {
        List<Vente> ventes = venteService.getAllVentes();
        List<VenteResponseDTO> responseList = ventes.stream()
            .map(venteMapper::toResponse)
            .toList();
        
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    } */

    /**
     * GET /api/v1/ventes/{id}
     * Récupère une vente par son identifiant
     * @param id L'identifiant unique de la vente
     * @return La vente trouvée
     */
    /* @GetMapping("/{id}")
    @Operation(
        summary = "GET: Récupérer une vente par ID",
        description = "Récupère une vente en utilisant son identifiant unique avec tous ses détails et articles"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Vente récupérée avec succès",
                content = @Content(schema = @Schema(implementation = VenteResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Vente non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<VenteResponseDTO> getVente(
            @Parameter(description = "Identifiant unique de la vente", example = "1")
            @PathVariable Long id) {
        
        Vente vente = venteService.getVenteById(id);
        VenteResponseDTO responseDTO = venteMapper.toResponse(vente);
        
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
 */
    /**
     * DELETE /api/v1/ventes/{id}
     * Supprime une vente (avec annulation des mouvements de stock associés)
     * @param id L'identifiant unique de la vente à supprimer
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "DELETE: Supprimer une vente",
        description = "Supprime une vente et restaure les stocks des articles associés"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vente supprimée avec succès (pas de contenu)"),
            @ApiResponse(responseCode = "404", description = "Vente non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "409", description = "Impossible de supprimer: vente finalisée ou livrée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVente(
            @Parameter(description = "Identifiant unique de la vente", example = "1")
            @PathVariable Long id) {
        venteService.deleteVente(id);
    }
}
