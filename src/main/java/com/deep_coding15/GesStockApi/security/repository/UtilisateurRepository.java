package com.deep_coding15.GesStockApi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
}
