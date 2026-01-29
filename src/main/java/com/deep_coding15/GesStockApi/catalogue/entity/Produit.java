package com.deep_coding15.GesStockApi.catalogue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.deep_coding15.GesStockApi.common.BaseEntity;
import com.deep_coding15.GesStockApi.common.utils.Utils;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "produit", uniqueConstraints = @UniqueConstraint(columnNames = "reference"))
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
     */
    @ManyToOne(optional = false) // chaque produit doit avoir une categorie
    @JoinColumn(name = "categorie_id")
    @JsonBackReference // child
    private Categorie categorie;

    @PrePersist
    public void beforeInsert() {
        this.generateReferenceProduit();
    }

    // Génération automatique de la référence
    public void generateReferenceProduit() {

        if (Utils.isStringUseless(reference)) {
            String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            this.reference = "PRD-" + datePart;
        }

    }

}
