package com.deep_coding15.GesStockApi.catalogue.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.deep_coding15.GesStockApi.common.BaseEntity;

@Getter
@Setter
@Entity
@Table(
    name = "produit",
    uniqueConstraints = @UniqueConstraint(columnNames = "reference")
)
public class Produit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String reference;

    private Long codeBarre;
    
    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 255)
    private String description;

    @Positive
    @Column(name = "prix_unitaire", nullable = false, precision = 38, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(nullable = false)
    private Boolean actif = true;

    /*
    * Fonctionnalité obtenue :
    * Associer un produit à une catégorie
    * Lister les produits par catégorie
    * */
    @ManyToOne(optional = true) //todo: A mettre a false car chaque produit doit avoir une categorie. Ici a true juste pour les tests
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

}
