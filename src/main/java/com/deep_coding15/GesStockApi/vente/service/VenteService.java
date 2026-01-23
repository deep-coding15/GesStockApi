package com.deep_coding15.GesStockApi.vente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;
import com.deep_coding15.GesStockApi.vente.entity.Vente;
import com.deep_coding15.GesStockApi.vente.repository.VenteRepository;


@Service
public class VenteService {

    private VenteRepository venteRepository;
    private UtilisateurRepository utilisateurRepository;

    public VenteService(
        VenteRepository venteRepository,
        UtilisateurRepository utilisateurRepository
    ) {
        this.venteRepository       = venteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Vente createVente(Vente vente){
        
        if(Utils.isCollectionEmpty(vente.getLignesVente()))
        {
            throw new EntityIllegalArgumentException(
                "Vente", "lignesVente", 
                vente.getLignesVente().toString());
        }

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(vente.getUtilisateur().getId());
        
        if(!utilisateurOptional.isPresent()) {
            throw new EntityNotFoundException(
            "Vente", "utilisateurId", 
                vente.getUtilisateur().getId().toString());
        }

        vente.setUtilisateur(utilisateurOptional.get());

        // Lie chaque ligne à la vente
        vente.getLignesVente().forEach(ligne -> ligne.setVente(vente));   

        return venteRepository.save(vente);
    }

    public Vente getVenteById(Long venteId) {

        if(Utils.isNegativeOrNull(venteId))
            throw new EntityIllegalArgumentException(
        "Vente", "id", 
            venteId.toString());
        
        Vente vente = venteRepository.findById(venteId)
            .orElseThrow(() ->new EntityNotFoundException(
                        "Vente", "id",
                                venteId.toString()));

        return vente;
    }
    
    /* public List<Vente> getVentesByProduitId(Long produitId) */ 

    public List<Vente> getVentes() {
        return venteRepository.findAll();
    }

}
