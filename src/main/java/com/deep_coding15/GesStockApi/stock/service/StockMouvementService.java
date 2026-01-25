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
import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStockEnum;
import com.deep_coding15.GesStockApi.stock.repository.StockMouvementRepository;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;

/**
 * Read only
 * StockMouvementService
 */
@Service
public class StockMouvementService {

    private StockMouvementRepository stockMouvementRepository;
    private StockRepository stockRepository;
    private ProduitRepository produitRepository;
    private UtilisateurRepository utilisateurRepository;

    public StockMouvementService(
        ProduitRepository produitRepository,
        StockRepository stockRepository,
        StockMouvementRepository stockMouvementRepository,
        UtilisateurRepository utilisateurRepository
    ) {
        this.produitRepository = produitRepository;
        this.stockRepository = stockRepository;
        this.stockMouvementRepository = stockMouvementRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<StockMouvement> getAllMouvementsByProduit(Long produitId) {

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

    public List<StockMouvement> getAllMouvementsByStock(Long stockId) {

        if(Utils.isNegativeOrNull(stockId)) {
            throw new EntityIllegalArgumentException(
                "StockMouvement", 
                "StockId", 
                stockId);
        }

        if(!stockRepository.existsById(stockId))
            throw new EntityNotFoundException(
        "Stock", "id", 
                stockId.toString());

        return  stockMouvementRepository.findAllByStockId(stockId);
    }

    public List<StockMouvement> getAllMouvementsByUtilisateur(Long utilisateurId) {

        if(Utils.isNegativeOrNull(utilisateurId)) {
            throw new EntityIllegalArgumentException(
                "StockMouvement", 
                "UtilisateurId", 
                utilisateurId);
        }

        if(!utilisateurRepository.existsById(utilisateurId))
            throw new EntityNotFoundException(
        "Utilisateur", "id", 
                utilisateurId.toString());

        return  stockMouvementRepository.findAllByUtilisateurId(utilisateurId);
    }

    public List<StockMouvement> getAllMouvementsByType(String type) {

        TypeMouvementStockEnum typeM;

        try {
            typeM = TypeMouvementStockEnum.valueOf(type);
        } catch (Exception e) {
            throw new EntityNotFoundException(
        "TypeMouvementStockEnum", "code", 
                type);
        }

        return  stockMouvementRepository.findAllByTypeMouvement(typeM);
    }
}
