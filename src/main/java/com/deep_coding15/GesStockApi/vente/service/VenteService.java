package com.deep_coding15.GesStockApi.vente.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.common.Exception.EntityBusinessException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;
import com.deep_coding15.GesStockApi.stock.service.StockService;
import com.deep_coding15.GesStockApi.vente.entity.Vente;
import com.deep_coding15.GesStockApi.vente.enums.StatutVenteEnum;
import com.deep_coding15.GesStockApi.vente.repository.VenteRepository;

import jakarta.transaction.Transactional;

@Service
public class VenteService {

    private VenteRepository venteRepository;
    private UtilisateurRepository utilisateurRepository;
    private StockRepository stockRepository;
    private StockService stockService;

    public VenteService(
            VenteRepository venteRepository,
            UtilisateurRepository utilisateurRepository,
            StockRepository stockRepository,
            StockService stockService
        ) {
        this.venteRepository       = venteRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.stockRepository       = stockRepository;
        this.stockService          = stockService;
    }

    @Transactional
    public Vente createVente(Vente vente, Utilisateur utilisateur) {

        if (vente == null) {
            throw new EntityIllegalArgumentException(
                "Vente", "vente", "null");
        }

        if (vente.getId() != null) {
            throw new EntityIllegalArgumentException(
                    "Vente", "id", "doit être null lors de la création");
        }

        if (Utils.isNegativeOrNullOrZero(utilisateur.getId())) {
            throw new EntityIllegalArgumentException(
                    "Utilisateur", "id",
                    utilisateur.getId().toString());
        }

        Utilisateur utilisateurOptional = utilisateurRepository.findById(utilisateur.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vente", "utilisateurId",
                        utilisateur.getId().toString()));

        if (Utils.isCollectionEmpty(vente.getLignesVente())) {
            throw new EntityIllegalArgumentException(
                    "Vente", "lignesVente",
                    "vide ou null");
        }

        vente.setUtilisateur(utilisateurOptional);

        // Lie chaque ligne à la vente : lignes + stock
        vente.getLignesVente().forEach(ligne -> {

            if (Utils.isNegativeOrNullOrZero(ligne.getQuantite())) {
                throw new EntityBusinessException(
                        "LigneVente", "quantite",
                        String.valueOf(ligne.getQuantite()),
                        "La quantité doit être > 0");
            }

            ligne.setVente(vente);

            Stock stock = stockRepository.findByProduitId(ligne.getProduit()
                .getId()).orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id",
                        ligne.getProduit().getId().toString()));

            // Gestion du stock
            stockService.patchStockQuantite(
                stock.getId(), 
                -ligne.getQuantite(), "SORTIE", 
                utilisateurOptional, 
                "Vente produit : " + ligne.getProduit().getNom());

            
            if(stock.getQuantite() < ligne.getQuantite())
            {
                throw new EntityBusinessException(
                        "Stock", "produit quantite",
                        String.valueOf(ligne.getQuantite()),
                        "Le stock est insuffisant. Stock avant " + stock.getQuantite());
            }

            stock.setQuantite(stock.getQuantite() - ligne.getQuantite());

            ligne.setVente(vente);
        });

        vente.setUtilisateur(utilisateurOptional);

        return venteRepository.save(vente);
    }

    public Vente getVenteById(Long venteId) {

        if (Utils.isNegativeOrNull(venteId))
            throw new EntityIllegalArgumentException(
                    "Vente", "id",
                    venteId.toString());

        Vente vente = venteRepository.findById(venteId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vente", "id",
                        venteId.toString()));

        return vente;
    }

    public List<Vente> getVentesByStatus(String statut) {
        StatutVenteEnum statutVenteEnum;
        try {
            statutVenteEnum = StatutVenteEnum.valueOf(statut);
        } catch (Exception e) {
            // TODO: handle exception
            throw new EntityIllegalArgumentException(
                    "StatutVenteEnum", "code", statut);
        }

        return venteRepository.findByStatutVente(statutVenteEnum);
    }

    public List<Vente> getVentesByUtilisateur(Utilisateur utilisateur) {

        if (Utils.isNegativeOrNull(utilisateur.getId()))
            throw new EntityIllegalArgumentException(
                    "Utilisateur", "id",
                    utilisateur.getId().toString());

        if (!utilisateurRepository.existsById(utilisateur.getId())) {
            throw new EntityNotFoundException(
                    "Utilisateur", "id",
                    utilisateur.getId().toString());
        }

        return venteRepository.findByUtilisateurId(utilisateur.getId());
    }

    /* public List<Vente> getVentesByProduitId(Long produitId) */

    @Transactional
    public Vente patchVenteStatut(Long venteId, String statutCode) {
        if (Utils.isNegativeOrNullOrZero(venteId))
            throw new EntityIllegalArgumentException(
                    "Vente", "id",
                    venteId);

        StatutVenteEnum statutVenteEnum;
        try {
            statutVenteEnum = StatutVenteEnum.valueOf(statutCode);
        } catch (Exception e) {
            throw new EntityIllegalArgumentException(
                    "StatutVenteEnum", "code", statutCode,
                    "Ce code n'existe pas.");
        }

        Vente vente = venteRepository.findById(venteId).orElseThrow(
                () -> new EntityIllegalArgumentException(
                        "Vente", "id", venteId,
                        "Cette vente n'existe pas."));

        vente.setStatutVente(statutVenteEnum);

        return vente;
    }

    public List<Vente> getVentes() {
        return venteRepository.findAll();
    }

    @Transactional
    public void deleteVente(Long id) {
        if (Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException(
                    "Vente", "id",
                    id);
        venteRepository.deleteById(id);
    }

}
