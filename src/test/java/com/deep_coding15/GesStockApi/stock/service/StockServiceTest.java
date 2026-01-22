package com.deep_coding15.GesStockApi.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    public void createStock__shouldFailed__whenProduitNotExists() {
        // GIVEN
        Produit produit = new Produit();
        produit.setId(1L);

        Stock stock = new Stock();

        stock.setProduit(produit);
        stock.setQuantite(100);

        when(produitRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> stockService.createStock(
                        stock.getProduit().getId(),
                        stock.getQuantite()));

        // On verifie que il n'a pas appele le repositoiry pour le save
        verify(stockRepository, never())
                .save(any());

    }

    @Test
    public void createStock__shouldSucceeded__whenProduitExists() {
        // GIVEN
        Produit produit = new Produit();
        produit.setId(1L);

        Stock stock = new Stock();

        stock.setProduit(produit);
        stock.setQuantite(100);

        // Simulation des appels réels du service
        when(produitRepository.findById(produit.getId()))
                .thenReturn(Optional.of(produit));

        // On simule qu'aucun stock n'existe encore pour ce produit
        when(stockRepository.findByProduitId(produit.getId()))
                .thenReturn(Optional.empty());

        // WHEN
        stockService.createStock(produit.getId(), stock.getQuantite());

        // THEN

        // On vérifie qu'un objet Stock a été sauvegardé avec les bonnes valeurs
        verify(stockRepository)
            .save(argThat(stockSaved -> stockSaved.getProduit().getId().equals(produit.getId()) &&
                stockSaved.getQuantite().equals(stock.getQuantite())));
    }

    @Test
    public void getStock__shouldSucceeded__whenProduitExists(){
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
    public void getStock__shouldFailed__whenProduitNotExists(){
        Produit produit = new Produit();
        produit.setId(1L);

        Stock stock = new Stock();
        stock.setProduit(produit);

        when(stockRepository.findByProduitId(produit.getId()))
            .thenReturn(Optional.empty());

        //Stock result = stockService.getStockByProduitId(produit.getId());

        assertThrows(EntityNotFoundException.class, 
            () -> stockService.getStockByProduitId(produit.getId()));
    }

}
