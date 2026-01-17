package com.deep_coding15.GesStockApi.security.entity;

import com.deep_coding15.GesStockApi.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    
    // Getters / Setters
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) { this.code = code; }
}
