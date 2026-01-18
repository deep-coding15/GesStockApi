package com.deep_coding15.GesStockApi.security.dto;

import com.deep_coding15.GesStockApi.security.entity.Role;

public class UtilisateurResponseDTO {
    public Long Id;
    public String username;
    public Role role;
    public boolean actif = true;    
}

