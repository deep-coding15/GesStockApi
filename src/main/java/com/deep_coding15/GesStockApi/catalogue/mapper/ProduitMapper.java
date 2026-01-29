package com.deep_coding15.GesStockApi.catalogue.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitPatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitPutRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitResponseDTO;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.entity.Produit;

/**
 * @Component Dit à Spring :
 *            « Cette classe peut être injectée ailleurs »
 */
@Component
public class ProduitMapper {

    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/

    /**
     * @param dto
     * @return produit
     */
    // DTO -> Entity
    public Produit toEntity(ProduitCreateRequestDTO dto) {

        Categorie categorie = new Categorie();
        categorie.setId(dto.getCategorieId());

        Produit produit = new Produit();
        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setPrixUnitaire(BigDecimal.valueOf(dto.getPrix()));
        produit.setCategorie(categorie);

        return produit;
    }

    public Produit toEntity(ProduitPutRequestDTO dto) {

        Categorie categorie = new Categorie();
        categorie.setId(dto.getCategorieId());

        Produit produit = new Produit();
        produit.setId(Long.valueOf(dto.getId()));
        produit.setReference(dto.getReference());
        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setPrixUnitaire(dto.getPrix());
        produit.setCategorie(categorie);

        return produit;
    }
    
    public Produit toEntity(ProduitPatchRequestDTO dto) {

        Categorie categorie = new Categorie();
        categorie.setId(dto.getCategorieId());

        Produit produit = new Produit();
        produit.setId(Long.valueOf(dto.getId()));
        produit.setReference(dto.getReference());
        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setPrixUnitaire(dto.getPrix());
        produit.setCategorie(categorie);

        return produit;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/

    /**
     * @param produit
     * @return produitResponseDTO
     */
    // Entity -> DTO
    public ProduitResponseDTO toResponse(Produit produit) {

        ProduitResponseDTO dto = new ProduitResponseDTO();

        dto.setId(produit.getId());
        dto.setReference(produit.getReference());
        dto.setNom(produit.getNom());
        dto.setDescription(produit.getDescription());
        dto.setGetPrixUnitaire(produit.getPrixUnitaire().doubleValue());
        dto.setCategorieCode(produit.getCategorie().getCode());
        dto.setActif(produit.getActif());
        return dto;
    }

    public Set<ProduitResponseDTO> toResponseSet(Set<Produit> produits) {
        return produits.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }
    
    public Set<ProduitResponseDTO> toResponseSet(List<Produit> produits) {
        return produits.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }
    
    public List<ProduitResponseDTO> toResponseList(List<Produit> produits) {
        return produits.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
 
