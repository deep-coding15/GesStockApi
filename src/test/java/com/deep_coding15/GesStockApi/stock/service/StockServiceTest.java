package com.deep_coding15.GesStockApi.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityBusinessException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;

import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStock;

import com.deep_coding15.GesStockApi.stock.repository.StockMouvementRepository;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockMouvementRepository stockMouvementRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void createStock_shouldCreateStockWithInitialMovement() {

        Produit produit = new Produit();
        produit.setId(1L);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);

        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(stockRepository.existsByProduitId(1L)).thenReturn(false);
        when(stockRepository.save(any(Stock.class))).thenAnswer(i -> i.getArgument(0));

        Stock stock = stockService.createStock(produit, 10, utilisateur);

        assertNotNull(stock);
        assertEquals(10, stock.getQuantite());
        assertEquals(1, stock.getMouvements().size());

        StockMouvement mouvement = stock.getMouvements().get(0);
        assertEquals(TypeMouvementStock.INITIAL, mouvement.getTypeMouvement());
        assertEquals(10, mouvement.getQuantite());
    }

    @Test
    void createStock_shouldThrowIfStockAlreadyExists() {

        Produit produit = new Produit();
        produit.setId(1L);

        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        when(stockRepository.existsByProduitId(1L)).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class,
                () -> stockService.createStock(produit, 10, new Utilisateur()));
    }

    @Test
    public void createStock__shouldFailed__whenProduitNotExists() {
        // GIVEN
        Produit produit = new Produit();
        produit.setId(1L);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);

        Stock stock = new Stock();

        stock.setProduit(produit);
        stock.setQuantite(100);

        when(produitRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> stockService.createStock(
                        stock.getProduit(),
                        stock.getQuantite(), utilisateur));

        // On verifie que il n'a pas appele le repositoiry pour le save
        verify(stockRepository, never())
                .save(any());

    }

    @Test
    void patchStockQuantite_shouldIncreaseStock() {

        Stock stock = new Stock();
        stock.setId(1L);
        stock.setQuantite(5);
        stock.setProduit(new Produit());
        stock.setMouvements(new ArrayList<>());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(stockRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Stock updated = stockService.patchStockQuantite(
                1L, 3, TypeMouvementStock.ENTREE.getCode(), utilisateur, "Entree de stock");

        assertEquals(8, updated.getQuantite());
        assertEquals(1, updated.getMouvements().size());
    }

    @Test
    void patchStockQuantite_shouldFailIfInsufficientStock() {

        Stock stock = new Stock();
        stock.setId(1L);
        stock.setQuantite(2);
        stock.setProduit(new Produit());
        stock.setMouvements(new ArrayList<>());

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        assertThrows(EntityBusinessException.class,
                () -> stockService.patchStockQuantite(
                        1L, -5, TypeMouvementStock.SORTIE.getCode(), new Utilisateur(), "Vente"));
    }

    @Test
    void deleteStock_shouldDeleteExistingStock() {

        Stock stock = new Stock();
        stock.setId(1L);

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        stockService.deleteStock(1L);

        verify(stockRepository).delete(stock);
    }

    @Test
    public void getStock__shouldSucceeded__whenProduitExists() {
        Produit produit = new Produit();
        produit.setId(1L);

        Stock stock = new Stock();
        stock.setProduit(produit);

        when(stockRepository.findByProduitId(produit.getId()))
                .thenReturn(Optional.of(stock));

        Stock result = stockService.getStockByProduitId(produit.getId());

        assertNotNull(result);
        assertEquals(produit.getId(),
                result.getProduit().getId());

        verify(stockRepository, times(1)).findByProduitId(produit.getId());
    }

    @Test
    public void getStock__shouldFailed__whenProduitNotExists() {
        Produit produit = new Produit();
        produit.setId(1L);

        Stock stock = new Stock();
        stock.setProduit(produit);

        when(stockRepository.findByProduitId(produit.getId()))
                .thenReturn(Optional.empty());

        // Stock result = stockService.getStockByProduitId(produit.getId());

        assertThrows(EntityNotFoundException.class,
                () -> stockService.getStockByProduitId(produit.getId()));
    }

}
