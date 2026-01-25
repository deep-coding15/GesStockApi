package com.deep_coding15.GesStockApi.stock.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityBusinessException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.common.utils.Utils;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStockEnum;
import com.deep_coding15.GesStockApi.stock.repository.StockMouvementRepository;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;

import jakarta.transaction.Transactional;

@Service
public class StockService {

    private StockRepository stockRepository;
    private ProduitRepository produitRepository;
    private StockMouvementRepository stockMouvementRepository;
    private UtilisateurRepository utilisateurRepository;

    public StockService(
            StockRepository stockRepository,
            ProduitRepository produitRepository,
            StockMouvementRepository stockMouvementRepository,
            UtilisateurRepository utilisateurRepository) {
        this.stockRepository = stockRepository;
        this.produitRepository = produitRepository;
        this.stockMouvementRepository = stockMouvementRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public Stock createStock(Produit produit, Integer quantiteInitiale, Utilisateur utilisateur) {

        if (Utils.isNegativeOrNullOrZero(quantiteInitiale))
            throw new EntityIllegalArgumentException("Stock", 
        "quantiteInitiale", String.valueOf(quantiteInitiale));

        if (produit == null || Utils.isNegativeOrNullOrZero(produit.getId()))
            throw new EntityIllegalArgumentException(
                    "Produit", "id", "null");

        Produit produitExist = produitRepository.findById(produit.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id",
                        produit.getId().toString()));

        // Est ce qu'il existe un stock pour ce produit
        if (stockRepository.existsByProduitId(produitExist.getId()))
            throw new EntityBusinessException(
                    "Produit", "id", produitExist.getId().toString(), 
                    "Il existe dejà un stock pour ce produit");

        Utilisateur utilisateurExist = utilisateurRepository.findById(utilisateur.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utilisateur", "id",
                        utilisateur.getId().toString()));

        Stock stock = new Stock();
        stock.setProduit(produitExist);
        stock.setQuantite(0);

        StockMouvement mouvement = new StockMouvement();
        mouvement.setTypeMouvement(TypeMouvementStockEnum.INITIAL);
        mouvement.setQuantite(quantiteInitiale);
        mouvement.setDateMouvement(LocalDateTime.now());
        mouvement.setCommentaire("STOCK INITIAL");

        mouvement.setUtilisateur(utilisateurExist);
        mouvement.setProduit(produitExist);
        mouvement.setStock(stock);

        stock.setQuantite(quantiteInitiale);
        stock.setMouvements(new ArrayList<>());

        stock.getMouvements().add(mouvement);

        // Sauvegarde automatiquement le mouvement car  
        // la relation est cascade = CascadeType.ALL
        return stockRepository.save(stock);
    }

    public Stock getStockById(Long id) {
        if (Utils.isNegativeOrNullOrZero(id))
            throw new EntityNotFoundException(
                    "Stock", "id",
                    id.toString());
        return stockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stock", "id", id.toString()));
    }

    public Stock getStockByProduitId(Long produitId) {
        if (Utils.isNegativeOrNullOrZero(produitId)) {
            throw new EntityIllegalArgumentException(
                    "Stock", "produitId",
                    produitId.toString());
        }

        Optional<Stock> stock = stockRepository.findByProduitId(produitId);

        if (!stock.isPresent())
            throw new EntityNotFoundException(
                    "Stock", "produitId",
                    produitId.toString());

        return stock.get();
    }

    public List<Stock> getStocks() {
        return this.stockRepository.findAll();
    }

    public List<StockMouvement> getMouvementsByStock(Long stockId) {
        return stockMouvementRepository.findAllByStockId(stockId);
    }

    @Transactional
    public Stock patchStockQuantite(
            Long stockId,
            int delta,
            String type,
            Utilisateur utilisateur,
            String commentaire) {

        if (delta == 0)
            throw new EntityIllegalArgumentException("Stock", "delta", "0");

        TypeMouvementStockEnum typeM;
        try {
            typeM = TypeMouvementStockEnum.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EntityIllegalArgumentException(
                    "StockMouvement", "type", type);
        }

        if (typeM == TypeMouvementStockEnum.SORTIE && delta > 0)
            throw new EntityBusinessException(
                    "StockMouvement", "delta", String.valueOf(delta),
                    "Une sortie doit avoir un delta négatif");

        if (typeM == TypeMouvementStockEnum.ENTREE && delta < 0)
            throw new EntityBusinessException(
                    "StockMouvement", "delta", String.valueOf(delta),
                    "Une entrée doit avoir un delta positif");

        Stock stock = this.getStockById(stockId);

        int nouvelleQuantite = stock.getQuantite() + delta;

        if (Utils.isNegativeOrNull(nouvelleQuantite))
            throw new EntityBusinessException(
                    "Stock", "nouvelle quantite",
                    String.valueOf(nouvelleQuantite), "Stock insuffisant");

        Produit produit = stock.getProduit();

        Utilisateur utilisateurExist = utilisateurRepository.findById(utilisateur.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utilisateur", "id",
                        utilisateur.getId().toString()));

        StockMouvement mouvement = new StockMouvement();
        mouvement.setTypeMouvement(typeM);
        mouvement.setQuantite(delta);
        mouvement.setDateMouvement(LocalDateTime.now());

        mouvement.setUtilisateur(utilisateurExist);
        mouvement.setCommentaire(commentaire);
        mouvement.setStock(stock);
        mouvement.setProduit(produit);

        stock.setQuantite(nouvelleQuantite);
        stock.getMouvements().add(mouvement);

        return stockRepository.save(stock);
    }

    @Transactional
    public void deleteStock(Long id) {

        if (Utils.isNegativeOrNullOrZero(id)) {
            throw new EntityIllegalArgumentException(
                    "Stock", "id", id.toString());
        }

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Stock", "id", id.toString()));

        stockRepository.delete(stock);
    }

}
