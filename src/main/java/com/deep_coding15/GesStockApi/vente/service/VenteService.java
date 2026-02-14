package com.deep_coding15.GesStockApi.vente.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.exception.EntityBusinessException;
import com.deep_coding15.GesStockApi.common.exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.exception.stock.StockQuantiteInsuffisanteException;
import com.deep_coding15.GesStockApi.common.utils.Utils;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;
import com.deep_coding15.GesStockApi.stock.service.StockService;

import com.deep_coding15.GesStockApi.vente.dto.vente.VenteCreateRequestDTO;
import com.deep_coding15.GesStockApi.vente.dto.venteLigne.VenteLigneCreateRequestDTO;
import com.deep_coding15.GesStockApi.vente.entity.Vente;
import com.deep_coding15.GesStockApi.vente.entity.VenteLigne;
import com.deep_coding15.GesStockApi.vente.enums.StatutVenteEnum;
import com.deep_coding15.GesStockApi.vente.mapper.VenteLigneMapper;
import com.deep_coding15.GesStockApi.vente.repository.VenteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VenteService {

    private VenteRepository venteRepository;
    private UtilisateurRepository utilisateurRepository;
    private ProduitRepository produitRepository;
    private StockRepository stockRepository;
    private StockService stockService;

    public VenteService(
            VenteRepository venteRepository,
            UtilisateurRepository utilisateurRepository,
            StockRepository stockRepository,
            StockService stockService,
            ProduitRepository produitRepository
            ) {
        this.venteRepository = venteRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.produitRepository = produitRepository;
    }

    @Transactional
    public Vente createVente(VenteCreateRequestDTO venteDto) {

        if (Utils.isNegativeOrNullOrZero(venteDto.getUtilisateurId())) {
            throw new EntityIllegalArgumentException(
                    "Utilisateur", "id",
                    venteDto.getUtilisateurId().toString());
        }

        if (Utils.isCollectionEmpty(venteDto.getVenteLignes())) {
            throw new EntityIllegalArgumentException(
                    "Vente", "lignesVente",
                    "vide ou null");
        }

        Utilisateur utilisateurOptional = utilisateurRepository.findById(venteDto.getUtilisateurId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Vente", "utilisateurId",
                        venteDto.getUtilisateurId().toString()));

        Vente vente = new Vente();

        vente.setUtilisateur(utilisateurOptional);
        vente.setDateVente(LocalDateTime.now());
        vente.setStatutVente(StatutVenteEnum.valueOf(venteDto.getStatutCode()));

        // Lie chaque ligne à la vente : lignes + stock
        for (VenteLigneCreateRequestDTO ligne : venteDto.getVenteLignes()) {

            if (Utils.isNegativeOrNullOrZero(ligne.getQuantite())) {
                throw new EntityIllegalArgumentException(
                        "LigneVente", "quantite",
                        String.valueOf(ligne.getQuantite()),
                        "La quantité doit être strictement positive");
            }

            Stock stock = stockRepository.findByProduitId(ligne.getProduitId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produit", "id", ligne.getProduitId().toString()));

            VenteLigne venteLigne = new VenteLigne();
            venteLigne.setProduit(stock.getProduit());
            venteLigne.setQuantite(ligne.getQuantite());
            venteLigne.setPrixUnitaire(stock.getProduit().getPrixUnitaire());

            // Vérification AVANT sortie
            if (stock.getQuantite() < ligne.getQuantite()) {
                throw new StockQuantiteInsuffisanteException(
                        ligne.getQuantite(),
                        stock.getQuantite());
            }

            // Gestion du stock CENTRALISÉE
            stockService.patchStockQuantite(
                    stock.getId(),
                    -ligne.getQuantite(),
                    "SORTIE",
                    utilisateurOptional,
                    "Vente produit : " + ligne.getProduitId());

            // Relation bidirectionnelle vente <-> venteLigne
            /* venteLigne.setVente(vente);
            vente.getLignesVente().add(venteLigne); */
            vente.ajouterLigneVente(venteLigne);
        }

        vente.setStatutVente(StatutVenteEnum.VALIDEE);
        
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

    public List<Vente> getVentesByUtilisateurId(Long utilisateurId) {

        if (Utils.isNegativeOrNull(utilisateurId))
            throw new EntityIllegalArgumentException(
                    "Utilisateur", "id",
                    utilisateurId.toString());

        if (!utilisateurRepository.existsById(utilisateurId)) {
            throw new EntityNotFoundException(
                    "Utilisateur", "id",
                    utilisateurId.toString());
        }

        return venteRepository.findByUtilisateurId(utilisateurId);
    }
    
    public List<Vente> getVentesByProduitId(Long produitId) {

        if (Utils.isNegativeOrNull(produitId))
            throw new EntityIllegalArgumentException(
                    "Produit", "id",
                    produitId.toString());

        if (!produitRepository.existsById(produitId)) {
            throw new EntityNotFoundException(
                    "Produit", "id",
                    produitId.toString());
        }

        return venteRepository.findByLignesVente_Produit_Id(produitId);
    }

    public List<Vente> getVentes() {
        return venteRepository.findAll();
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
            statutVenteEnum = StatutVenteEnum.fromCode(statutCode.toUpperCase());
        } catch (Exception e) {
            throw new EntityIllegalArgumentException(
                    "StatutVenteEnum", "code", statutCode,
                    "Ce code n'existe pas.");
        }

        Vente vente = venteRepository.findById(venteId).orElseThrow(
                () -> new EntityNotFoundException(
                        "Vente", "id", venteId.toString(),
                        "Cette vente n'existe pas."));

        vente.setStatutVente(statutVenteEnum);
        
        vente = venteRepository.save(vente);

        return vente;
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
