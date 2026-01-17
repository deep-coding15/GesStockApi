package com.deep_coding15.GesStockApi.security.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleCreateRequestDTO {
    @NotBlank
    public String code;

    //Getters / Setters
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}