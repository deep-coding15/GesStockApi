package com.deep_coding15.GesStockApi.security.dto;

public class UtilisateurCreateRequestDTO {
    public String email;
    public Long   roleId;
    public String password;
    public String username;

    // getters / setters
    public void setEmail(String email)       {this.email = email;} 
    public void setPassword(String password) {this.password = password;} 
    public void setRoleID(Long roleId)       {this.roleId = roleId;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail()    {return this.email;}
    public String getPassword() {return this.password;}
    public Long   getRoleId()   {return this.roleId;}
    public String getUsername() {return this.username;}
}
