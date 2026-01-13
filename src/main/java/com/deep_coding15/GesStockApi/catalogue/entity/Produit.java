package com.deep_coding15.GesStockApi.catalogue.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.deep_coding15.GesStockApi.common.BaseEntity;

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

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(length = 255)
    private String description;

    @Positive
    @Column(name = "prix_unitaire", nullable = false, precision = 38, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(nullable = false)
    private Boolean actif = true;

    @ManyToOne(optional = true) //todo: A mettre a false car chaque produit doit avoir une categorie. Ici a true juste pour les tests
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    /* *********************************************** */
    /* *********************GETTERS******************* */
    /* *********************************************** */
    public Long getId()                 { return this.id; }
    public String getNom()              { return this.nom; }
    public Boolean getActif()           { return this.actif; }
    public String getReference()        { return this.reference; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }
    public LocalDateTime getUpdateAt()  { return this.updatedAt; }
    public String getDescription()      { return this.description; }
    public BigDecimal getPrixUnitaire() { return this.prixUnitaire; }
    
    /* *********************************************** */
    /* *********************SETTERS******************* */
    /* *********************************************** */
    public void setId(Long id)                          { this.id = id; }
    public void setNom(String nom)                      { this.nom = nom; }
    public void setActif(Boolean actif)                 { this.actif = actif; }
    public void setReference(String reference)          { this.reference = reference; }
    public void setCreatedAt(LocalDateTime createdAt)   { this.createdAt = createdAt; }
    public void setUpdateAt(LocalDateTime updateAt)     { this.updatedAt = updatedAt; }
    public void setDescription(String description)      { this.description = description; }
    public void setPrixUnitaire(BigDecimal prixUnitaire){ this.prixUnitaire = prixUnitaire; }
}
