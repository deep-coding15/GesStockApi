package com.deep_coding15.GesStockApi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);
    
    Optional<Utilisateur> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
