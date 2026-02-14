package com.deep_coding15.GesStockApi.security.web;

import java.util.List;

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

import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurPatchRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurPutRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurResponseDTO;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import com.deep_coding15.GesStockApi.security.mapper.UtilisateurMapper;

import com.deep_coding15.GesStockApi.security.service.UtilisateurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "Users", description = "API de gestion complète des utilisateurs du système (CRUD)")
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "401", description = "Non autorisé"),
        @ApiResponse(responseCode = "403", description = "Interdit"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
@RestController
@RequestMapping("/api/v1/users")
public class UtilisateurController {

    public final UtilisateurService utilisateurService;

    public final UtilisateurMapper utilisateurMapper;

    public UtilisateurController(
            UtilisateurService utilisateurService,
            UtilisateurMapper utilisateurMapper) {
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurService = utilisateurService;
    }

    /**
     * POST /api/v1/users/
     * Crée un nouvel utilisateur
     * 
     * @param dto Données de l'utilisateur à créer
     * @return L'utilisateur créé avec le code 201
     */
    @Operation(summary = "POST: Créer un utilisateur", description = "Crée un nouvel utilisateur et lui associe un rôle avec validation des données")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides (email/username manquants)"),
            @ApiResponse(responseCode = "404", description = "Rôle introuvable (roleId inexistant)"),
            @ApiResponse(responseCode = "409", description = "Email ou username déjà existant"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/")
    public ResponseEntity<UtilisateurResponseDTO> createUtilisateur(
            @Valid @RequestBody UtilisateurCreateRequestDTO dto) {

        Utilisateur utilisateur = utilisateurMapper.toEntity(dto);

        Utilisateur utilisateurCree = utilisateurService.createUtilisateur(utilisateur);

        UtilisateurResponseDTO responseDTO = utilisateurMapper.toResponse(utilisateurCree);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    /**
     * GET /api/v1/users/{id}
     * Récupère un utilisateur par son identifiant
     * 
     * @param id L'identifiant unique de l'utilisateur
     * @return L'utilisateur trouvé
     */
    @GetMapping("/{id}")
    @Operation(summary = "GET: Récupérer un utilisateur par ID", description = "Récupère un utilisateur en utilisant son identifiant unique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
    })
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurById(
            @Parameter(description = "Id unique de l'utilisateur", example = "1") @PathVariable Long id) {
        try {

            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);

            UtilisateurResponseDTO dto = utilisateurMapper.toResponse(utilisateur);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * GET /api/v1/users/name/{username}
     * Récupère un utilisateur par son username
     * 
     * @param username Le username unique de l'utilisateur
     * @return L'utilisateur trouvé
     */
    @GetMapping("/name/{username}")
    @Operation(summary = "GET: Récupérer un utilisateur par username", description = "Récupère un utilisateur en utilisant son username unique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé (username inexistant)"),
            @ApiResponse(responseCode = "400", description = "Username invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurByUsername(
            @Parameter(description = "Username unique de l'utilisateur", example = "john.doe") @PathVariable String username) {
        try {

            Utilisateur utilisateur = utilisateurService.getUtilisateurByUsername(username);

            UtilisateurResponseDTO dto = utilisateurMapper.toResponse(utilisateur);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * GET /api/v1/users/email/{email}
     * Récupère un utilisateur par son email
     * 
     * @param email L'email unique de l'utilisateur
     * @return L'utilisateur trouvé
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "GET: Récupérer un utilisateur par email", description = "Récupère un utilisateur en utilisant son email unique")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé (email inexistant)"),
            @ApiResponse(responseCode = "400", description = "Email invalide (vide)"),
    })
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurByEmail(
            @Parameter(description = "email unique de l'utilisateur", example = "john@doe.com") @PathVariable String email) {
        try {

            Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);

            UtilisateurResponseDTO dto = utilisateurMapper.toResponse(utilisateur);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * GET /api/v1/users/
     * Récupère tous les utilisateurs
     * 
     * @return Liste de tous les utilisateurs
     */
    @GetMapping("/")
    @Operation(summary = "GET: Lister tous les utilisateurs", description = "Récupère la liste complète de tous les utilisateurs du système")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateurs récupérés avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Aucun utilisateur disponible"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<UtilisateurResponseDTO>> getUtilisateurByEmail() {
        List<Utilisateur> utilisateursTrouves = utilisateurService.getUtilisateurs();
        List<UtilisateurResponseDTO> dtos = utilisateurMapper.toResponseList(utilisateursTrouves);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    /**
     * PATCH /api/v1/users/{id}
     * Met à jour partiellement un utilisateur
     * 
     * @param id             L'identifiant unique de l'utilisateur
     * @param utilisateurDto Les données à mettre à jour (optionnelles)
     * @return L'utilisateur mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(summary = "PATCH: Mettre à jour partiellement un utilisateur", description = "Met à jour partiellement un utilisateur (les champs non fournis ne sont pas modifiés)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides ou malformées"),
    })
    public ResponseEntity<UtilisateurResponseDTO> patchUtilisateur(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurPatchRequestDTO utilisateurDto) {

        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);

        Utilisateur utilisateurUpdate = utilisateurService.patchUtilisateur(id, utilisateur);

        return new ResponseEntity<>(utilisateurMapper.toResponse(utilisateurUpdate), HttpStatus.OK);
    }

    /**
     * PUT /api/v1/users/{id}
     * Remplace complètement un utilisateur
     * 
     * @param id             L'identifiant unique de l'utilisateur
     * @param utilisateurDto Les nouvelles données de l'utilisateur (tous les champs
     *                       obligatoires)
     * @return L'utilisateur mis à jour
     */
    @PutMapping("/{id}")
    @Operation(summary = "PUT: Mettre à jour complètement un utilisateur", description = "Remplace complètement un utilisateur (tous les champs obligatoires doivent être fournis)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès", content = @Content(schema = @Schema(implementation = UtilisateurResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides ou incomplètes"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "409", description = "Email/username en conflit (déjà existant)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<UtilisateurResponseDTO> putUtilisateur(
            @PathVariable Long id, @Valid @RequestBody UtilisateurPutRequestDTO utilisateurDto) {

        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);

        Utilisateur utilisateurUpdate = utilisateurService.patchUtilisateur(id, utilisateur);

        return new ResponseEntity<>(utilisateurMapper.toResponse(utilisateurUpdate), HttpStatus.OK);
    }

    /**
     * DELETE /api/v1/users/{id}
     * Supprime un utilisateur
     * 
     * @param id L'identifiant unique de l'utilisateur à supprimer
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE: Supprimer un utilisateur", description = "Supprime complètement un utilisateur du système")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Utilisateur supprimé avec succès (pas de contenu)"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "409", description = "Impossible de supprimer: utilisateur actif dans des transactions"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtilisateur(
            @Parameter(description = "Identifiant unique de l'utilisateur", example = "1") @PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }

}
