package com.deep_coding15.GesStockApi.security.entity;

import com.deep_coding15.GesStockApi.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@Setter
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
    
    private String telephone;

    @Column(nullable = false)
    private boolean actif = true;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    // getters / setters
    public boolean getActif() {return this.actif;}
}

