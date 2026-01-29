package com.deep_coding15.GesStockApi.security.dto.utilisateur;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurPatchRequestDTO {

    public String email;

    public Long   roleId;

    public String motDePasse;

    public String username;

    // getters / setters
}
