package com.deep_coding15.GesStockApi.vente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep_coding15.GesStockApi.vente.entity.Vente;
import com.deep_coding15.GesStockApi.vente.enums.StatutVenteEnum;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    
    <Optional> Vente findByReferenceVente(String reference);

    boolean existsByReferenceVente(String reference);

    List<Vente> findByStatutVente(StatutVenteEnum statut);
    
    List<Vente> findByUtilisateurId(Long utilisateurId);

}
