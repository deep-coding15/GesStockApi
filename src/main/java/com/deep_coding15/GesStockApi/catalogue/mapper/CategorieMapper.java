package com.deep_coding15.GesStockApi.catalogue.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.catalogue.dto.CategorieCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategoriePatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategorieResponseDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategorieUpdateRequestDTO;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;

/**
 * @Component Dit à Spring :
 *            « Cette classe peut être injectée ailleurs »
 */
@Component
public class CategorieMapper {

    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/

    /**
     * @param dto
     * @return categorie
     */
    // DTO -> Entity
    public Categorie toEntity(CategorieCreateRequestDTO dto) {

        Categorie categorie = new Categorie();
        categorie.setCode(dto.getCode());
        categorie.setLibelle(dto.getLibelle());
        categorie.setDescription(dto.getDescription());

        return categorie;
    }

    public Categorie toEntity(CategorieUpdateRequestDTO dto) {

        Categorie categorie = new Categorie();
        categorie.setId(dto.getId());

        categorie.setCode(dto.getCode());
        categorie.setLibelle(dto.getLibelle());
        categorie.setDescription(dto.getDescription());

        return categorie;
    }
    
    public Categorie toEntity(CategoriePatchRequestDTO dto) {

        Categorie categorie = new Categorie();
        categorie.setId(dto.getId());

        categorie.setCode(dto.getCode());
        categorie.setLibelle(dto.getLibelle());
        categorie.setDescription(dto.getDescription());

        return categorie;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/

    /**
     * @param categorie
     * @return categorieResponseDTO
     */
    // Entity -> DTO
    public CategorieResponseDTO toResponse(Categorie categorie) {

        CategorieResponseDTO dto = new CategorieResponseDTO();

        dto.setId(categorie.getId());
        dto.setCode(categorie.getCode());
        dto.setLibelle(categorie.getLibelle());
        dto.setDescription(categorie.getDescription());
        dto.setActif(categorie.getActif());

        return dto;
    }

    public Set<CategorieResponseDTO> toResponseSet(Set<Categorie> categories) {
        return categories.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }

}
 
