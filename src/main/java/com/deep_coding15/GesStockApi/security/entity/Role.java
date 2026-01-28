package com.deep_coding15.GesStockApi.security.entity;

import java.util.List;

import com.deep_coding15.GesStockApi.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code; //ADMIN, USER, CAISSIER

    @Column(nullable = false, length = 100)
    private String libelle;

    @JsonManagedReference
    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;
    
    // Getters / Setters
}
