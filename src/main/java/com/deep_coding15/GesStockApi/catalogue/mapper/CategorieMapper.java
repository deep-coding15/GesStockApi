package com.deep_coding15.GesStockApi.catalogue.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.catalogue.dto.CategorieCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategoriePatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategoriePutRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategorieResponseDTO;
import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.entity.Produit;

@Component
public class CategorieMapper {
    
    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/
    public Categorie toEntity(CategorieCreateRequestDTO dto) {
        
        Categorie categorie = new Categorie();
        //utilisateur.setRole();
        
        categorie.setActif(true);
        categorie.setCode(dto.getCode());
        categorie.setDescription(dto.getDescription());
        categorie.setLibelle(dto.getLibelle());
        List<Produit> listProduits = new ArrayList();
        categorie.setProduits(listProduits);
        
        return categorie;
    }

    public Categorie toEntity(CategoriePatchRequestDTO dto) {
        
        Categorie categorie = new Categorie();
        //utilisateur.setRole();
        
        categorie.setActif(true);
        categorie.setCode(dto.getCode());
        categorie.setDescription(dto.getDescription());
        categorie.setLibelle(dto.getLibelle());
        List<Produit> listProduits = new ArrayList();
        categorie.setProduits(listProduits);
        
        return categorie;
    }

    public Categorie toEntity(CategoriePutRequestDTO dto) {
        
        Categorie categorie = new Categorie();
        //utilisateur.setRole();
        
        categorie.setActif(true);
        categorie.setCode(dto.getCode());
        categorie.setDescription(dto.getDescription());
        categorie.setLibelle(dto.getLibelle());
        List<Produit> listProduits = new ArrayList();
        categorie.setProduits(listProduits);
        
        return categorie;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/
    public CategorieResponseDTO toResponse(Categorie categorie) {

        CategorieResponseDTO dto = new CategorieResponseDTO();

        dto.setId(categorie.getId());
        dto.setCode(categorie.getCode());
        dto.setLibelle(categorie.getLibelle());
        dto.setDescription(categorie.getDescription());
        dto.setActif(categorie.getActif());

        return dto;
    }

    public List<CategorieResponseDTO> toResponseList(List<Categorie> categories) {
        return categories.stream()
            .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public Set<CategorieResponseDTO> toResponseSet(List<Categorie> categories) {
        return categories.stream()
            .map(this::toResponse)
                .collect(Collectors.toSet());
    }

}
