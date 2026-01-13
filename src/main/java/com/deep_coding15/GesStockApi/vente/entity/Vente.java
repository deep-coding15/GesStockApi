package com.deep_coding15.GesStockApi.vente.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.deep_coding15.GesStockApi.common.BaseEntity;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.vente.enums.StatutVente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "vente")
public class Vente extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String reference;

    @Column(name = "date_vente", nullable = false)
    private LocalDateTime dateVente;

    @Positive
    @Column(nullable = false, precision = 38, scale = 2)
    private BigDecimal total; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatutVente statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    //getters / setters
}
