package com.deep_coding15.GesStockApi.security.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deep_coding15.GesStockApi.security.dto.RoleCreateRequestDTO;
import com.deep_coding15.GesStockApi.security.dto.RoleResponseDTO;
import com.deep_coding15.GesStockApi.security.entity.Role;

/**
 * @Component Dit à Spring :
 *            « Cette classe peut être injectée ailleurs »
 */
@Component
public class RoleMapper {

    /*****************************************************/
    /********************** DTO -> ENTITY ******************/
    /*****************************************************/

    /**
     * @param dto
     * @return Role
     */
    // DTO -> Entity
    public Role toEntity(RoleCreateRequestDTO dto) {

        Role role = new Role();

        role.setCode(dto.getCode());
        role.setLibelle(dto.getLibelle());

        return role;
    }

    /*****************************************************/
    /********************** ENTITY -> DTO ******************/
    /*****************************************************/

    /**
     * @param role
     * @return RoleResponseDTO
     */
    // Entity -> DTO
    public RoleResponseDTO toResponse(Role role) {

        RoleResponseDTO dto = new RoleResponseDTO();

        dto.setId(role.getId());
        dto.setCode(role.getCode());
        dto.setLibelle(role.getLibelle());

        return dto;
    }

    public Set<RoleResponseDTO> toResponseSet(Set<Role> roles) {
        return roles.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }

}
