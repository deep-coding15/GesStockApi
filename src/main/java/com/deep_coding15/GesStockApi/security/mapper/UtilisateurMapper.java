package com.deep_coding15.GesStockApi.security.mapper;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.security.dto.UtilisateurCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.UtilisateurResponseDTO;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

@Component
public class UtilisateurMapper {
    
    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/
    public Utilisateur toEntity(UtilisateurCreateRequestDTO dto) {
        
        Utilisateur utilisateur = new Utilisateur();
        //utilisateur.setRole();
        
        utilisateur.setActif(false);
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setMotDePasse(dto.getPassword());
        utilisateur.setUsername(dto.username);
        
        return utilisateur;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/
    public UtilisateurResponseDTO toResponse(Utilisateur utilisateur) {

        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();

        dto.setID(utilisateur.getId());
        dto.setRoleID(utilisateur.getRole().getId());
        dto.setEmail(utilisateur.getEmail());
        dto.setUsername(utilisateur.getUsername());
        dto.setActif(utilisateur.getActif());

        return dto;
    }

}
