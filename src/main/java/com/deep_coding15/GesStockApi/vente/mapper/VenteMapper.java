package com.deep_coding15.GesStockApi.vente.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.vente.dto.vente.VenteCreateRequestDTO;
import com.deep_coding15.GesStockApi.vente.dto.vente.VenteResponseDTO;
import com.deep_coding15.GesStockApi.vente.entity.Vente;


@Component
public class VenteMapper {

    private VenteLigneMapper venteLigneMapper;

    public VenteMapper(VenteLigneMapper venteLigneMapper) {
        this.venteLigneMapper = venteLigneMapper;
    }

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
    public VenteResponseDTO toResponseDTO(Vente vente) {
        VenteResponseDTO dto = new VenteResponseDTO();
        dto.setId(vente.getId());
        dto.setReference(vente.getReferenceVente());
        dto.setDateVente(vente.getDateVente().toString());
        dto.setUtilisateurId(vente.getUtilisateur().getId());
        dto.setStatut(vente.getStatutVente().toString());
        dto.setTotal(vente.getPrixTotalHT().doubleValue());

        dto.setVenteLignes(
            venteLigneMapper.toResponseList(
                vente.getLignesVente()));

        return dto;
    }

    public List<VenteResponseDTO> toResponseDTOList(List<Vente> listVentes) {
        List<VenteResponseDTO> dtos = new ArrayList<VenteResponseDTO>();
        dtos = listVentes.stream()
            .map(this::toResponseDTO)
                .collect(Collectors.toList());
                
        return dtos;
    }

}
