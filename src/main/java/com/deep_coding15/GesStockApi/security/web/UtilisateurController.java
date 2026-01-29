package com.deep_coding15.GesStockApi.security.web;

import java.util.List;
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

import com.deep_coding15.GesStockApi.security.dto.UtilisateurCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.UtilisateurPatchRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.UtilisateurPutRequestDTO;
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
            UtilisateurMapper utilisateurMapper) {
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/")
    public ResponseEntity<UtilisateurResponseDTO> createUtilisateur(@Valid @RequestBody UtilisateurCreateRequestDTO dto) {

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

            return new ResponseEntity<>(dto, HttpStatus.OK);
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

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurByemail(@PathVariable String email) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
            UtilisateurResponseDTO dto = utilisateurMapper.toResponse(utilisateur);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Set<UtilisateurResponseDTO>> getUtilisateurs() {
        
        //return new ResponseEntity<>(utilisateurService.getUtilisateurs(), HttpStatus.OK);
        try {
            List<Utilisateur> utilisateursTrouves = utilisateurService.getUtilisateurs();

            Set<UtilisateurResponseDTO> utilisateurs = utilisateurMapper
                .toResponseSet(utilisateursTrouves);

            return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: logger l'erreur
            // log.error("Erreur lors de la récupération des rôles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> patchUtilisateur(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurPatchRequestDTO utilisateurDto) {

        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);

        Utilisateur utilisateurUpdate = utilisateurService.patchUtilisateur(id, utilisateur);

        return new ResponseEntity<>(utilisateurMapper.toResponse(utilisateurUpdate), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurResponseDTO> putUtilisateur(
        @PathVariable Long id, @Valid @RequestBody UtilisateurPutRequestDTO utilisateurDto) {
        
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);

        Utilisateur utilisateurUpdate = utilisateurService.patchUtilisateur(id, utilisateur);

        return new ResponseEntity<>(utilisateurMapper.toResponse(utilisateurUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retourne un code 204 (Succès sans contenu)
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
    
}
