package com.deep_coding15.GesStockApi.vente.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.vente.dto.VenteCreateRequestDTO;
import com.deep_coding15.GesStockApi.vente.dto.VenteResponseDTO;
import com.deep_coding15.GesStockApi.vente.entity.Vente;


@Component
public class VenteMapper {

    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/
    /* public Vente toEntity(VenteCreateRequestDTO dto) {

        Vente vente = new Vente();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getUtilisateurId());

        vente.setUtilisateur(utilisateur);
        vente.setStatutVente(1);

        return vente;
    } */

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/
    /* public VenteResponseDTO toResponseDTO(Vente vente) {
        VenteResponseDTO dto = new VenteResponseDTO();
        dto.setId(vente.getId());
        dto.setQuantite(vente.getQuantite());
        dto.setProduitId(vente.getProduit().getId());
        dto.setMouvements(
                vente.getMouvements()
                        .stream()
                        .map(this::toMouvementResponse)
                        .toList());
        return dto;
    } */
    /* public List<VenteResponseDTO> toResponseDTOList(Vente vente) {
        VenteResponseDTO dto = new VenteResponseDTO();
        dto.setId(vente.getId());
        dto.setQuantite(vente.getQuantite());
        dto.setProduitId(vente.getProduit().getId());
        dto.setMouvements(
                vente.getMouvements()
                        .stream()
                        .map(this::toMouvementResponse)
                        .toList());
        return dto;
    } */

}
