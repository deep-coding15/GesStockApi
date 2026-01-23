package com.deep_coding15.GesStockApi.security.entity;

import com.deep_coding15.GesStockApi.common.BaseEntity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "utilisateur")
public class Utilisateur extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique =  true, length =  50)
    private String username;

    @Column(nullable = false, unique =  true, length =  150)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    private boolean actif = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    // getters / setters
    public Long getId() {return id;}
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public boolean getActif() {return actif;}
    public Role getRole() {return role;}

    public void setId(Long id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setMotDePasse(String motDePasse) {this.motDePasse = motDePasse;}
    public void setActif(boolean actif) {this.actif = actif;}
    public void setRole(Role role) {this.role = role;}
}

