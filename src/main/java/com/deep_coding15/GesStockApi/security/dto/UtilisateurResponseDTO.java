package com.deep_coding15.GesStockApi.security.dto;

public class UtilisateurResponseDTO {
    public Long id;
    public Long roleId;
    public String email;
    public String username;
    public boolean actif = true;    

    // getters / setters
    public void setActif(boolean actif) {this.actif = actif;}
    public void setEmail(String email) {this.email = email;} 
    public void setID(Long id) {this.id = id;}
    public void setRoleID(Long roleId) {this.roleId = roleId;}
    public void setUsername(String username) {this.username = username;}

    public boolean getActif() {return this.actif;}
    public String  getEmail() {return this.email;}
    public Long    getId() {return this.id;}
    public Long    getRoleId() {return this.roleId;}
    public String  getUsername() {return this.username;}
}

