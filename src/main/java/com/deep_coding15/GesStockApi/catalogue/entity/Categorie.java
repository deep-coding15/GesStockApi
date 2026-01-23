package com.deep_coding15.GesStockApi.catalogue.entity;

import java.util.List;

import com.deep_coding15.GesStockApi.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categorie")
@Getter
@Setter
public class Categorie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String libelle;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private boolean actif = true;

    // Categorie
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;
    
    // getters / setters

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getActif() {
        return this.actif;
    }
}
