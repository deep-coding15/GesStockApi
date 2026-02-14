package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitPatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitPutRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitResponseDTO;
import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.mapper.ProduitMapper;
import com.deep_coding15.GesStockApi.catalogue.service.ProduitService;

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
    name = "Produit",
    description = "Gestion complète des produits dans le catalogue (CRUD + recherche)"
)
@ApiResponses({
    @ApiResponse(responseCode = "400", description = "Requête invalide ou paramètres manquants"),
    @ApiResponse(responseCode = "401", description = "Non autorisé - authentification requise"),
    @ApiResponse(responseCode = "403", description = "Interdit - permissions insuffisantes"),
    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {

    private final ProduitMapper produitMapper;
    private final ProduitService produitService;

    public ProduitController(
            ProduitMapper produitMapper,
            ProduitService produitService) {
        this.produitService = produitService;
        this.produitMapper = produitMapper;
    }

    /**
     * POST /api/v1/produits/
     * Crée un nouveau produit dans le catalogue
     * @param produitDto Données du produit à créer
     * @return Le produit créé avec le code 201
     */
    @Operation(
        summary = "POST: Créer un produit",
        description = "Crée un nouveau produit dans le catalogue avec validation des données"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "Produit créé avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides (nom/catégorie manquants)"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (categorieId inexistant)"),
            @ApiResponse(responseCode = "409", description = "Nom ou référence de produit déjà existant"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/")
    public ResponseEntity<ProduitResponseDTO> createProduit(@Valid @RequestBody ProduitCreateRequestDTO produitDto) {
        Produit produit = produitMapper.toEntity(produitDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produitMapper.toResponse(produitService.createProduit(produit)));
    }

    /**
     * GET /api/v1/produits/
     * Récupère tous les produits
     * @return Liste de tous les produits du catalogue
     */
    @GetMapping("/")
    @Operation(
        summary = "GET: Lister tous les produits",
        description = "Récupère la liste complète de tous les produits du catalogue"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produits récupérés avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "204", description = "Aucun produit disponible"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<ProduitResponseDTO>> getAllProduits() {
        List<Produit> produits = produitService.getProduits();
        return ResponseEntity.ok(produitMapper.toResponseList(produits));
    }

    /**
     * GET /api/v1/produits/{id}
     * Récupère un produit par son identifiant
     * @param id L'identifiant unique du produit
     * @return Le produit trouvé
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "GET: Récupérer un produit par ID",
        description = "Récupère un produit en utilisant son identifiant unique"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produit récupéré avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> getProduit(
            @Parameter(description = "Identifiant unique du produit", example = "1")
            @PathVariable Long id) {
        Produit produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produitMapper.toResponse(produit));
    }

    /**
     * GET /api/v1/produits/reference/{reference}
     * Récupère un produit par sa référence
     * @param reference La référence unique du produit
     * @return Le produit trouvé
     */
    @GetMapping("/reference/{reference}")
    @Operation(
        summary = "GET: Récupérer un produit par référence",
        description = "Récupère un produit en utilisant sa référence unique"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produit récupéré avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (référence inexistante)"),
            @ApiResponse(responseCode = "400", description = "Référence invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> getProduitByReference(
            @Parameter(description = "Référence unique du produit", example = "PROD-001")
            @PathVariable String reference) {
        Produit produit = produitService.getProduitByReference(reference);
        return ResponseEntity.ok(produitMapper.toResponse(produit));
    }

    /**
     * GET /api/v1/produits/nom/{nom}
     * Récupère un produit par son nom
     * @param nom Le nom du produit
     * @return Le produit trouvé
     */
    @GetMapping("/nom/{nom}")
    @Operation(
        summary = "GET: Récupérer un produit par nom",
        description = "Récupère un produit en utilisant son nom"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produit récupéré avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (nom inexistant)"),
            @ApiResponse(responseCode = "400", description = "Nom invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> getProduitByNom(
            @Parameter(description = "Nom du produit", example = "Laptop Pro")
            @PathVariable String nom) {
        Produit produit = produitService.getProduitByNom(nom);
        return ResponseEntity.ok(produitMapper.toResponse(produit));
    }

    /**
     * GET /api/v1/produits/description/{description}
     * Récupère un produit par sa description
     * @param description La description du produit
     * @return Le produit trouvé
     */
    @GetMapping("/description/{description}")
    @Operation(
        summary = "GET: Récupérer un produit par description",
        description = "Récupère un produit en utilisant sa description"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produit récupéré avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (description inexistante)"),
            @ApiResponse(responseCode = "400", description = "Description invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> getProduitByDescription(
            @Parameter(description = "Description du produit", example = "Laptop haute performance")
            @PathVariable String description) {
        Produit produit = produitService.getProduitByDescription(description);
        return ResponseEntity.ok(produitMapper.toResponse(produit));
    }

    /**
     * GET /api/v1/produits/categorie/{categorieId}
     * Récupère tous les produits d'une catégorie
     * @param categorieId L'identifiant unique de la catégorie
     * @return Liste de tous les produits de la catégorie
     */
    @GetMapping("/categorie/{categorieId}")
    @Operation(
        summary = "GET: Récupérer les produits d'une catégorie",
        description = "Récupère tous les produits associés à une catégorie en utilisant l'identifiant de la catégorie"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produits récupérés avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID de catégorie invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<ProduitResponseDTO>> getProduitByCategorie(
            @Parameter(description = "Identifiant unique de la catégorie", example = "1")
            @PathVariable Long categorieId) {
        List<Produit> produits = produitService.getProduitsByCategorieId(categorieId);
        return ResponseEntity.ok(produitMapper.toResponseList(produits));
    }

    /**
     * PUT /api/v1/produits/{id}
     * Remplace complètement un produit
     * @param id L'identifiant unique du produit
     * @param produitDto Les nouvelles données du produit (tous les champs obligatoires)
     * @return Le produit mis à jour
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "PUT: Mettre à jour complètement un produit",
        description = "Remplace complètement un produit (tous les champs obligatoires doivent être fournis)"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produit mis à jour avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides ou incomplètes"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> putProduit(
            @Parameter(description = "Identifiant unique du produit", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProduitPutRequestDTO produitDto) {
        Produit produit = produitMapper.toEntity(produitDto);
        return ResponseEntity.ok(
                produitMapper.toResponse(produitService.putProduit(id, produit)));
    }

    /**
     * PATCH /api/v1/produits/{id}
     * Met à jour partiellement un produit
     * @param id L'identifiant unique du produit
     * @param dto Les données à mettre à jour (optionnelles)
     * @return Le produit mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(
        summary = "PATCH: Mettre à jour partiellement un produit",
        description = "Met à jour partiellement un produit (les champs non fournis ne sont pas modifiés)"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Produit mis à jour avec succès",
                content = @Content(schema = @Schema(implementation = ProduitResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides ou malformées"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> patchProduit(
            @Parameter(description = "Identifiant unique du produit", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ProduitPatchRequestDTO dto) {
        Produit produit = produitMapper.toEntity(dto);
        return ResponseEntity.ok(
                produitMapper.toResponse(produitService.patchProduit(id, produit)));
    }

    /**
     * DELETE /api/v1/produits/{id}
     * Supprime un produit
     * @param id L'identifiant unique du produit à supprimer
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "DELETE: Supprimer un produit",
        description = "Supprime complètement un produit du catalogue"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès (pas de contenu)"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "409", description = "Impossible de supprimer: produit utilisé dans des stocks"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<Void> deleteProduit(
            @Parameter(description = "Identifiant unique du produit", example = "1")
            @PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

}
