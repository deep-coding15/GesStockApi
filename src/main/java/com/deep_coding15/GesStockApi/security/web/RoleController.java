package com.deep_coding15.GesStockApi.security.web;

import java.util.Set;

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

import com.deep_coding15.GesStockApi.security.dto.role.RoleCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.role.RolePatchRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.role.RolePutRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.role.RoleResponseDTO;
import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.mapper.RoleMapper;
import com.deep_coding15.GesStockApi.security.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(
    name = "Rôle",
    description = "API de gestion complète des rôles utilisateurs (CRUD)"
)
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "401", description = "Non autorisé"),
        @ApiResponse(responseCode = "403", description = "Interdit"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    public final RoleService roleService;

    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    /**
     * @PostMapping
     *              Indique que cette méthode :
     *              répond à une requête HTTP POST
     *              sur l’URL du contrôleur (ex: /api/v1/roles)
     * @RequestBody :
     *              lit le JSON envoyé par le client
     *              le convertit en objet Java
     *              Ici, le JSON est converti directement en entité JPA Role
     * @param role
     * @return ResponseEntity<Role>
     *         Retourne :
     *         le rôle créé
     *         avec le code HTTP 201 CREATED
    /**
     * POST /api/v1/roles/
     * Crée un nouveau rôle utilisateur
     * @param dto Données du rôle à créer (code obligatoire, libelle optionnel)
     * @return Le rôle créé avec le code 201
     */
    @PostMapping("/")
    @Operation(
        summary = "POST: Créer un rôle",
        description = "Crée un nouveau rôle utilisateur avec validation des données"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "Rôle créé avec succès",
                content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides (code manquant)"),
            @ApiResponse(responseCode = "409", description = "Code de rôle déjà existant"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleCreateRequestDTO dto) {

        // Il n'a pas d'Id : contient les arguments du DTO
        Role role = roleMapper.toEntity(dto);

        Role roleCree = roleService.createRole(role); // Rôle crée par le service
        RoleResponseDTO response = roleMapper.toResponse(roleCree);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "GET: Récupérer un rôle par ID",
        description = "Récupère un rôle utilisateur en utilisant son identifiant unique"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Rôle récupéré avec succès",
                content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<RoleResponseDTO> getRole(
            @Parameter(description = "Identifiant unique du rôle", example = "1")
            @PathVariable Long id) {
        try {
            Role roleTrouvee = roleService.getRoleById(id);
            RoleResponseDTO dto = roleMapper.toResponse(roleTrouvee);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/code/{code}")
    @Operation(
        summary = "GET: Récupérer un rôle par code",
        description = "Récupère un rôle utilisateur en utilisant son code unique"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Rôle récupéré avec succès",
                content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé (code inexistant)"),
            @ApiResponse(responseCode = "400", description = "Code invalide (vide)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<RoleResponseDTO> getRoleByCode(
            @Parameter(description = "Code unique du rôle", example = "ADMIN")
            @PathVariable String code) {
        try {
            Role roleTrouvee = roleService.getRoleByCode(code);
            RoleResponseDTO dto = roleMapper.toResponse(roleTrouvee);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET /api/v1/roles/
     * Récupère tous les rôles
     * @return Liste de tous les rôles
     */
    @GetMapping("/")
    @Operation(
        summary = "GET: Lister tous les rôles",
        description = "Récupère la liste complète de tous les rôles utilisateurs"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Rôles récupérés avec succès",
                content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))
            ),
            @ApiResponse(responseCode = "204", description = "Aucun rôle disponible"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<Set<RoleResponseDTO>> getRoles() {
        try {
            Set<Role> rolesTrouves = roleService.getRoles();

            Set<RoleResponseDTO> roles = roleMapper.toResponseSet(rolesTrouves);
            
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: logger l'erreur
            //log.error("Erreur lors de la récupération des rôles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PATCH /api/v1/roles/{id}
     * Met à jour partiellement un rôle
     * @param id L'identifiant unique du rôle
     * @param rolePatchRequestDTO Les données à mettre à jour (optionnelles)
     * @return Le rôle mis à jour
     */
    @PatchMapping("/{id}")
    @Operation(
        summary = "PATCH: Mettre à jour partiellement un rôle",
        description = "Met à jour partiellement un rôle (les champs non fournis ne sont pas modifiés)"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Rôle mis à jour avec succès",
                content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides ou malformées"),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "409", description = "Code de rôle en conflit (déjà existant)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<RoleResponseDTO> patchRole(
        @Parameter(description = "Identifiant unique du rôle", example = "1")
        @PathVariable Long id, 
        @Valid @RequestBody RolePatchRequestDTO rolePatchRequestDTO){
        Role role = roleMapper.toEntity(rolePatchRequestDTO);
        
        Role updateRole = this.roleService.patchRole(id, role);

        return new ResponseEntity<>(roleMapper.toResponse(updateRole), HttpStatus.OK);
    }

    /**
     * PUT /api/v1/roles/{id}
     * Remplace complètement un rôle
     * @param id L'identifiant unique du rôle
     * @param rolePutRequestDTO Les nouvelles données du rôle (tous les champs obligatoires)
     * @return Le rôle mis à jour
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "PUT: Mettre à jour complètement un rôle",
        description = "Remplace complètement un rôle (tous les champs obligatoires doivent être fournis)"
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Rôle mis à jour avec succès",
                content = @Content(schema = @Schema(implementation = RoleResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Données invalides ou incomplètes"),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "409", description = "Code de rôle en conflit (déjà existant)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<RoleResponseDTO> putRole(
        @Parameter(description = "Identifiant unique du rôle", example = "1")
        @PathVariable Long id, 
        @Valid @RequestBody RolePutRequestDTO rolePutRequestDTO){
        Role role = roleMapper.toEntity(rolePutRequestDTO);
        
        Role updateRole = this.roleService.putRole(id, role);

        return new ResponseEntity<>(roleMapper.toResponse(updateRole), HttpStatus.OK);
    }

    /**
     * DELETE /api/v1/roles/{id}
     * Supprime un rôle
     * @param id L'identifiant unique du rôle à supprimer
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "DELETE: Supprimer un rôle",
        description = "Supprime complètement un rôle utilisateur"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rôle supprimé avec succès (pas de contenu)"),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé (ID inexistant)"),
            @ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
            @ApiResponse(responseCode = "401", description = "Authentification requise"),
            @ApiResponse(responseCode = "409", description = "Impossible de supprimer: rôle assigné à des utilisateurs"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(
            @Parameter(description = "Identifiant unique du rôle", example = "1")
            @PathVariable Long id){
        roleService.deleteRole(id);
    }

}
