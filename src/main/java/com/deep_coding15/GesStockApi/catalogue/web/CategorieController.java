package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.catalogue.dto.categorie.CategorieCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.categorie.CategoriePatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.categorie.CategoriePutRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.categorie.CategorieResponseDTO;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;

import com.deep_coding15.GesStockApi.catalogue.mapper.CategorieMapper;

import com.deep_coding15.GesStockApi.catalogue.service.CategorieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Catégorie", description = "API de gestion complète des catégories de produits (CRUD)")
@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {

    private final CategorieService categorieService;
    private final CategorieMapper categorieMapper;

    public CategorieController(CategorieService categorieService, CategorieMapper categorieMapper) {
        this.categorieService = categorieService;
        this.categorieMapper = categorieMapper;
    }

    /**
     * POST /api/v1/categories/
     * Crée une nouvelle catégorie de produit
     * 
     * @param dto Données de la catégorie à créer
     * @return La catégorie créée avec le code 201
     */
    @PostMapping("/")
    @Operation(summary = "POST: Créer une catégorie", description = "Crée une nouvelle catégorie de produit avec validation des données")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Catégorie créée avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides (code/libellé/description manquants ou vides)"),
            @ApiResponse(responseCode = "409", description = "Code de catégorie déjà existant"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<CategorieResponseDTO> createCategorie(@Valid @RequestBody CategorieCreateRequestDTO dto) {

        Categorie categorie = categorieMapper.toEntity(dto);

        Categorie categorieCree = categorieService.createCategorie(categorie);

        CategorieResponseDTO responseDTO = categorieMapper.toResponse(categorieCree);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    /**
     * GET /api/v1/categories/{id}
     * Récupère une catégorie par son identifiant
     * 
     * @param id L'identifiant unique de la catégorie
     * @return La catégorie trouvée
     */
    @GetMapping("/{id}")
    @Operation(summary = "GET: Récupérer une catégorie par ID", description = "Récupère une catégorie de produit en utilisant son identifiant unique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégorie récupérée avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<CategorieResponseDTO> getcategorie(@PathVariable Long id) {
        try {
            Categorie categorie = categorieService.getCategorieById(id);
            CategorieResponseDTO dto = categorieMapper.toResponse(categorie);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * GET /api/v1/categories/code/{code}
     * Récupère une catégorie par son code
     * 
     * @param code Le code unique de la catégorie
     * @return La catégorie trouvée
     */
    @Operation(summary = "GET: Récupérer une catégorie par code", description = "Récupère une catégorie de produit en utilisant son code unique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégorie récupérée avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (code inexistant)"),
            @ApiResponse(responseCode = "400", description = "Code invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/code/{code}")
    public ResponseEntity<CategorieResponseDTO> getCategorieByCode(@PathVariable String code) {
        try {
            Categorie categorie = categorieService.getCategorieByCode(code);
            CategorieResponseDTO dto = categorieMapper.toResponse(categorie);

            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/v1/categories/libelle/{libelle}
     * Récupère une catégorie par son libellé
     * 
     * @param libelle Le libellé de la catégorie
     * @return La catégorie trouvée
     */
    @Operation(summary = "GET: Récupérer une catégorie par libellé", description = "Récupère une catégorie de produit en utilisant son libellé")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégorie récupérée avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (libellé inexistant)"),
            @ApiResponse(responseCode = "400", description = "Libellé invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<CategorieResponseDTO> getCategorieByLibelle(@PathVariable String libelle) {
        try {
            Categorie categorie = categorieService.getCategorieByLibelle(libelle);
            CategorieResponseDTO dto = categorieMapper.toResponse(categorie);

            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
    /**
     * GET /api/v1/categories/produits
     * Récupère les catégories et leurs produits
     * 
     * @param libelle Le libellé de la catégorie
     * @return La catégorie trouvée
     */
    @Operation(summary = "GET: Récupérer une catégorie par libellé", description = "Récupère une catégorie de produit en utilisant son libellé")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégorie récupérée avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (libellé inexistant)"),
            @ApiResponse(responseCode = "400", description = "Libellé invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/produits")
    public ResponseEntity<List<CategorieResponseDTO>> getCategorieWithProducts() {
        try {
            List<Categorie> categories = categorieService.getCategories();
            List<CategorieResponseDTO> dtos = categorieMapper.toResponseList(categories);

            return new ResponseEntity<>(dtos, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/v1/categories/
     * Récupère toutes les catégories
     * 
     * @return Liste de toutes les catégories
     */
    @GetMapping("/")
    @Operation(summary = "GET: Lister toutes les catégories", description = "Récupère la liste complète de toutes les catégories de produits")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégories récupérées avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Aucune catégorie disponible"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvé")
    })
    // @Tag(name = "Récupération de catégorie", description = "Récupérer une
    // catégorie de produit par le libelle de sa catégorie")
    @ApiResponse(responseCode = "200", description = "Catégorie récupérée avec succès")
    public ResponseEntity<List<CategorieResponseDTO>> getCategories() {
        try {
            List<Categorie> categoriesTrouves = categorieService.getCategories();

            List<CategorieResponseDTO> categories = categoriesTrouves.stream()
                    .map(categorieMapper::toResponse)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: logger l'erreur
            // log.error("Erreur lors de la récupération des rôles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PATCH /api/v1/categories/{id}
     * Met à jour partiellement une catégorie
     * 
     * @param id           L'identifiant unique de la catégorie
     * @param categorieDto Les données à mettre à jour (optionnelles)
     * @return La catégorie mise à jour
     */
    @PatchMapping("/{id}")
    @Operation(summary = "PATCH: Mettre à jour partiellement une catégorie", description = "Met à jour partiellement une catégorie (les champs non fournis ne sont pas modifiés)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides ou malformées"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "409", description = "Code de catégorie en conflit (déjà existant)"),
    })
    public ResponseEntity<CategorieResponseDTO> patchcategorie(
            @PathVariable Long id,
            @Valid @RequestBody CategoriePatchRequestDTO categorieDto) {

        Categorie categorie = categorieMapper.toEntity(categorieDto);

        Categorie categorieUpdate = categorieService.patchCategorie(id, categorie);

        return new ResponseEntity<>(categorieMapper.toResponse(categorieUpdate), HttpStatus.OK);
    }

    /**
     * PUT /api/v1/categories/{id}
     * Remplace complètement une catégorie
     * 
     * @param id           L'identifiant unique de la catégorie
     * @param categorieDto Les nouvelles données de la catégorie (tous les champs
     *                     obligatoires)
     * @return La catégorie mise à jour
     */
    @PutMapping("/{id}")
    @Operation(summary = "PUT: Mettre à jour complètement une catégorie", description = "Remplace complètement une catégorie (tous les champs obligatoires doivent être fournis)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès", content = @Content(schema = @Schema(implementation = CategorieResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides ou incomplètes"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "409", description = "Code de catégorie en conflit (déjà existant)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<CategorieResponseDTO> putCategorie(
            @PathVariable Long id, @Valid @RequestBody CategoriePutRequestDTO categorieDto) {

        Categorie categorie = categorieMapper.toEntity(categorieDto);

        Categorie categorieUpdate = categorieService.putCategorie(id, categorie);

        return new ResponseEntity<>(categorieMapper.toResponse(categorieUpdate), HttpStatus.OK);
    }

    /**
     * DELETE /api/v1/categories/{id}
     * Supprime une catégorie
     * 
     * @param id L'identifiant unique de la catégorie à supprimer
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE: Supprimer une catégorie", description = "Supprime complètement une catégorie de produit (suppression logique ou physique selon l'implémentation)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Catégorie supprimée avec succès (pas de contenu)"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "409", description = "Impossible de supprimer: catégorie associée à des produits"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retourne un code 204 (Succès sans contenu)
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}