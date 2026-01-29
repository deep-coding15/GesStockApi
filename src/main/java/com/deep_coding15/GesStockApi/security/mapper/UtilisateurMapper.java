package com.deep_coding15.GesStockApi.security.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.security.dto.role.RoleResponseDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurPatchRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurPutRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.utilisateur.UtilisateurResponseDTO;
import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UtilisateurMapper {
    
    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/
    public Utilisateur toEntity(UtilisateurCreateRequestDTO dto) {
        
        Utilisateur utilisateur = new Utilisateur();
        
        Role role = new Role();
        role.setId(dto.getRoleId());
        
        utilisateur.setRole(role);
        utilisateur.setActif(true);
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getMotDePasse());
        utilisateur.setUsername(dto.getUsername());
        
        return utilisateur;
    }
    
    public Utilisateur toEntity(UtilisateurPatchRequestDTO dto) {
        
        Utilisateur utilisateur = new Utilisateur();

        Role role = new Role();
        role.setId(dto.getRoleId());
        
        utilisateur.setRole(role);        
        utilisateur.setActif(true);
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getMotDePasse());
        utilisateur.setUsername(dto.getUsername());
        
        return utilisateur;
    }
    
    public Utilisateur toEntity(UtilisateurPutRequestDTO dto) {
        
        Utilisateur utilisateur = new Utilisateur();
        
        Role role = new Role();
        role.setId(dto.getRoleId());

        utilisateur.setRole(role);
        utilisateur.setActif(true);
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getMotDePasse());
        utilisateur.setUsername(dto.getUsername());
        
        return utilisateur;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/
    public UtilisateurResponseDTO toResponse(Utilisateur utilisateur) {

        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();
        RoleResponseDTO roleDto = toroleResponseDTO(utilisateur.getRole());

        dto.setId(utilisateur.getId());
        dto.setRole(roleDto);
        dto.setEmail(utilisateur.getEmail());
        dto.setUsername(utilisateur.getUsername());
        dto.setActif(utilisateur.getActif());

        return dto;
    }
    
    public List<UtilisateurResponseDTO> toResponseList(List<Utilisateur> utilisateurs) {


        List<UtilisateurResponseDTO> utilisateurResponseDto = 
            utilisateurs.stream()  // crée un flux de Utilisateur
                .map(this::toResponse) // transforme chaque Utilisateur en UtilisateurResponseDTO
                .collect(Collectors.toList()); // reconstruit un Set<UtilisateurResponseDTO>

        return utilisateurResponseDto;
    }
    
    public Set<UtilisateurResponseDTO> toResponseSet(List<Utilisateur> utilisateurs) {


        Set<UtilisateurResponseDTO> utilisateurResponseDto = 
            utilisateurs.stream()  // crée un flux de Utilisateur
                .map(this::toResponse) // transforme chaque Utilisateur en UtilisateurResponseDTO
                .collect(Collectors.toSet()); // reconstruit un Set<UtilisateurResponseDTO>

        return utilisateurResponseDto;
    }

    public RoleResponseDTO toroleResponseDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();

        dto.setId(role.getId());
        dto.setCode(role.getCode());
        dto.setLibelle(role.getLibelle());

        return dto;
    }

}
