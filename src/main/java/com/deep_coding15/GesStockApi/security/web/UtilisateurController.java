package com.deep_coding15.GesStockApi.security.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.security.dto.UtilisateurCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.UtilisateurResponseDTO;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.mapper.UtilisateurMapper;
import com.deep_coding15.GesStockApi.security.service.UtilisateurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UtilisateurController {

    public final UtilisateurService utilisateurService;

    public final UtilisateurMapper utilisateurMapper;

    public UtilisateurController(
        UtilisateurService utilisateurService,
        UtilisateurMapper utilisateurMapper
    ) {
        this.utilisateurMapper  = utilisateurMapper;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<UtilisateurResponseDTO> creerUtilisateur(@Valid @RequestBody UtilisateurCreateRequestDTO dto) {

        Utilisateur utilisateur = utilisateurMapper.toEntity(dto);

        Utilisateur utilisateurCree = utilisateurService.createUtilisateur(utilisateur);
        UtilisateurResponseDTO responseDTO = utilisateurMapper.toResponse(utilisateurCree);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateur(@PathVariable Long id) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            UtilisateurResponseDTO dto = utilisateurMapper.toResponse(utilisateur);

            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @GetMapping("/name/{username}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurByUsername(@PathVariable String username) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByUsername(username);
            UtilisateurResponseDTO dto = utilisateurMapper.toResponse(utilisateur);

            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UtilisateurResponseDTO>> getUtilisateurs() {
        try {
            List<Utilisateur> utilisateursTrouves = utilisateurService.getUtilisateurs();

            List<UtilisateurResponseDTO> roles = utilisateursTrouves.stream() // crée un flux de Role
                    .map(utilisateurMapper::toResponse) // transforme chaque Role en RoleResponseDTO
                    .collect(Collectors.toList()); // reconstruit un Set<RoleResponseDTO>

            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: logger l'erreur
            //log.error("Erreur lors de la récupération des rôles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
