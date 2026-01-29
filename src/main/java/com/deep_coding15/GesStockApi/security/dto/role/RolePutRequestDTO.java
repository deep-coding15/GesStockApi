package com.deep_coding15.GesStockApi.security.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePutRequestDTO {

    @NotBlank
    public String code;

    @NotBlank
    public String libelle;
}
