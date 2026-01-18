package com.deep_coding15.GesStockApi.stock.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Integer quantite;

    /* Un stock unique par produit */
    @OneToOne
    @JoinColumn(name = "produit_id", unique = true)
    private Produit produit;

    @OneToMany(mappedBy = "stock")
    private List<StockMouvement> mouvements;
}