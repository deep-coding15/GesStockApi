package com.deep_coding15.GesStockApi.vente.entity;

import java.math.BigDecimal;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Positive;

@Entity
@Table(
    name = "vente_ligne",
    uniqueConstraints = @UniqueConstraint(columnNames = {"vente_id", "produit_id"})
)
public class VenteLigne extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vente_id")
    private Vente vente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @Column(nullable = false)
    private int quantite;

    @Positive
    @Column(name = "prix_unitaire", nullable = false, precision = 38, scale = 2)
    private BigDecimal prixUnitaire;    
}
