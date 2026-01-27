package com.deep_coding15.GesStockApi.vente.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.math.BigDecimal;

import com.deep_coding15.GesStockApi.common.BaseEntity;
import com.deep_coding15.GesStockApi.common.utils.Utils;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import com.deep_coding15.GesStockApi.vente.enums.StatutVenteEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vente")
public class Vente extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String referenceVente;

    @Column(name = "date_vente", nullable = false)
    private LocalDateTime dateVente = LocalDateTime.now();

    @Positive
    @Column(nullable = false, precision = 38, scale = 2)
    private BigDecimal prixTotalHT; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private StatutVenteEnum statutVente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL)
    private List<VenteLigne> lignesVente;

    @PrePersist
    public void beforeInsert(){
        this.generateReferenceVente();
        this.setTotalPrixHT();
    }

    @PreUpdate
    public void beforeUpdate(){
        this.setTotalPrixHT();
    }
    // Génération automatique de la référence
    public void generateReferenceVente() {

        if (Utils.isStringUseless(referenceVente)) {
            String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            this.referenceVente = "VTE-" + datePart;
        }
        
    }

    public void setTotalPrixHT(){

        if(Utils.isCollectionEmpty(this.lignesVente)){
            this.prixTotalHT = BigDecimal.ZERO;
            return;
        }

        this.prixTotalHT = this.lignesVente.stream()
            .filter(ligne -> ligne.getQuantite() > 0)
            .map(ligne -> ligne.getPrixUnitaire()
                .multiply(BigDecimal.valueOf(ligne.getQuantite())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);        
    }

    //getters / setters
}
