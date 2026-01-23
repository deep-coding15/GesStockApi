package com.deep_coding15.GesStockApi.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.repository.StockMouvementRepository;

@Service
public class StockMouvementService {

    private StockMouvementRepository stockMouvementRepository;
    private ProduitRepository produitRepository;
    private UtilisateurRepository utilisateurRepository;

    public StockMouvementService(
        ProduitRepository produitRepository,
        StockMouvementRepository stockMouvementRepository,
        UtilisateurRepository utilisateurRepository
    ) {
        this.produitRepository = produitRepository;
        this.stockMouvementRepository = stockMouvementRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public StockMouvement createStockMouvement(StockMouvement stockMouvement) {

        // Validation métier : quantité
        if(Utils.isNegativeOrNull(stockMouvement.getQuantite())){
            throw new EntityIllegalArgumentException(
        "StockMouvement", 
        "quantite", 
                stockMouvement.getQuantite().toString()
            );
        }

        // Vérifier et rattacher l'utilisateur
        Utilisateur utilisateur = utilisateurRepository
        .findById(stockMouvement.getUtilisateur().getId())
        .orElseThrow(() -> new EntityNotFoundException(
            "Utilisateur", 
            "id", 
            stockMouvement.getUtilisateur().getId().toString()
        ));
        
        // Vérifier et rattacher le produit
        Produit produit = produitRepository
            .findById(stockMouvement.getProduit().getId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Produit", 
                "id", 
                stockMouvement.getProduit().getId().toString()
            ));
        
        // Rattachement explicite (important)
        stockMouvement.setUtilisateur(utilisateur);
        stockMouvement.setProduit(produit);

        return stockMouvementRepository.save(stockMouvement);
    }

    public List<StockMouvement> getAllMouvementsByProduitId(Long produitId) {

        if(Utils.isNegativeOrNull(produitId)) {
            throw new EntityIllegalArgumentException(
                "StockMouvement", 
                "produitId", 
                produitId);
        }

        if(!produitRepository.existsById(produitId))
            throw new EntityNotFoundException(
        "Produit", "id", 
                produitId.toString());

        return  stockMouvementRepository.findAllByProduitId(produitId);
    }
}
