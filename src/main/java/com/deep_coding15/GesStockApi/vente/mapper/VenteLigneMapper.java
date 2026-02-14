package com.deep_coding15.GesStockApi.vente.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;

import com.deep_coding15.GesStockApi.vente.dto.venteLigne.VenteLigneCreateRequestDTO;
import com.deep_coding15.GesStockApi.vente.dto.venteLigne.VenteLigneResponseDTO;
import com.deep_coding15.GesStockApi.vente.entity.VenteLigne;

@Component
public class VenteLigneMapper {
    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/
    public VenteLigne toEntity(VenteLigneCreateRequestDTO dto) {
        
        Produit produit = new Produit();
        produit.setId(dto.getProduitId());
        
        VenteLigne venteLigne = new VenteLigne();
        venteLigne.setProduit(produit);
        venteLigne.setQuantite(dto.getQuantite());      
        
        return venteLigne;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/
    public VenteLigneResponseDTO toResponse(VenteLigne venteLigne) {

        VenteLigneResponseDTO dto = new VenteLigneResponseDTO();
        
        dto.setProduitId(venteLigne.getProduit().getId());
        dto.setQuantite(venteLigne.getQuantite());
        dto.setPrixUnitaire(venteLigne.getPrixUnitaire().doubleValue());

        return dto;
    }
    
    public List<VenteLigneResponseDTO> toResponseList(List<VenteLigne> venteLignes) {

        List<VenteLigneResponseDTO> venteLigneResponseDto = 
            venteLignes.stream()  // crée un flux de VenteLigne
                .map(this::toResponse) // transforme chaque VenteLigne en VenteLigneResponseDTO
                .collect(Collectors.toList()); // reconstruit un Set<VenteLigneResponseDTO>

        return venteLigneResponseDto;
    }
    
}
