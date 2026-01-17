package com.deep_coding15.GesStockApi.security.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.security.dto.RoleCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.RoleResponseDTO;
import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.mapper.RoleMapper;
import com.deep_coding15.GesStockApi.security.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    public final RoleService roleService;

    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper  = roleMapper;
    }

    /** 
     * @PostMapping 
     * Indique que cette méthode :
     *  répond à une requête HTTP POST
     *  sur l’URL du contrôleur (ex: /api/v1/roles)
     * @RequestBody :
     *  lit le JSON envoyé par le client
     *  le convertit en objet Java
     *  Ici, le JSON est converti directement en entité JPA Role
     * @param role
     * @return ResponseEntity<Role>
     * Retourne :
     *  le rôle créé
     *  avec le code HTTP 201 CREATED
     */
    @PostMapping
    public ResponseEntity<RoleResponseDTO> creerRole(@RequestBody RoleCreateRequestDTO dto) {
        
        // Il n'a pas d'Id : contient les arguments du DTO
        Role role = roleMapper.toEntity(dto); 
        
        Role roleCree = roleService.createRole(role); // Rôle crée par le service
        RoleResponseDTO response = roleMapper.toResponse(roleCree);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    } 

}
