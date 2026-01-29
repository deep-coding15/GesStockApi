package com.deep_coding15.GesStockApi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurResponseDTO {
    
    public Long id;
    public Long roleId;
    public String email;
    public String username;
    public boolean actif = true;    

    // getters / setters
}

