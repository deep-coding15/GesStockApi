package com.deep_coding15.GesStockApi.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStockEnum;
import com.deep_coding15.GesStockApi.stock.repository.StockMouvementRepository;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockMouvementServiceTest {

    @Mock
    private StockMouvementRepository stockMouvementRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockMouvementService stockMouvementService;

    private StockMouvement createValidStockMouvement() {
        StockMouvement sm = new StockMouvement();

        sm.setQuantite(10);
        sm.setTypeMouvement(TypeMouvementStockEnum.ENTREE);

        Utilisateur user = new Utilisateur();
        user.setId(1L);

        Produit produit = new Produit();
        produit.setId(1L);

        sm.setUtilisateur(user);
        sm.setProduit(produit);

        return sm;
    }

    @Test
    void shouldReturnMovementsByStock() {

        when(stockRepository.existsById(1L)).thenReturn(true);

        when(stockMouvementRepository.findAllByStockId(1L))
                .thenReturn(List.of(new StockMouvement()));

        List<StockMouvement> result = stockMouvementService
                .getAllMouvementsByStock(1L);

        assertEquals(1, result.size());
        verify(stockMouvementRepository).findAllByStockId(1L);
    }

    @Test
    void shouldReturnMovementsByProduit() {

        when(produitRepository.existsById(2L)).thenReturn(true);

        when(stockMouvementRepository.findAllByProduitId(2L))
                .thenReturn(List.of(new StockMouvement()));

        List<StockMouvement> result = stockMouvementService
                .getAllMouvementsByProduit(2L);

        assertEquals(1, result.size());
        verify(stockMouvementRepository).findAllByProduitId(2L);
    }

    @Test
    void shouldReturnMovementsByUtilisateur() {

        when(utilisateurRepository.existsById(3L)).thenReturn(true);

        when(stockMouvementRepository.findAllByUtilisateurId(3L))
                .thenReturn(List.of(new StockMouvement()));

        List<StockMouvement> result = stockMouvementService
                .getAllMouvementsByUtilisateur(3L);

        assertEquals(1, result.size());
        verify(stockMouvementRepository).findAllByUtilisateurId(3L);
    }

    @Test
    void shouldReturnMovementsByType() {
        
        when(stockMouvementRepository
                .findAllByTypeMouvement(TypeMouvementStockEnum.SORTIE))
                .thenReturn(List.of(new StockMouvement()));

        List<StockMouvement> result =
                stockMouvementService
                .getAllMouvementsByType(TypeMouvementStockEnum.SORTIE.getCode());

        assertEquals(1, result.size());

        verify(stockMouvementRepository)
                .findAllByTypeMouvement(TypeMouvementStockEnum.SORTIE);
    }




    @Test
    void getAllMouvementsByProduitId__shouldFailed__whenProduitNotExists() {

        when(produitRepository.existsById(1L)).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> stockMouvementService.getAllMouvementsByProduit(1L));

    }

    @Test
    void getAllMouvementsByProduitId__shouldSucceed__whenProduitExists() {

        // GIVEN : un produit existant
        Produit produit = new Produit();
        produit.setId(1L);

        StockMouvement stockMouvement = new StockMouvement();
        stockMouvement.setProduit(produit);

        List<StockMouvement> listStockMouvements = List.of(stockMouvement);

        when(produitRepository.existsById(1L))
                .thenReturn(true);

        when(stockMouvementRepository.findAllByProduitId(1L))
                .thenReturn(listStockMouvements);

        // WHEN
        List<StockMouvement> result = stockMouvementService.getAllMouvementsByProduit(1L);

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(produit.getId(), result.get(0).getProduit().getId());
    }

}
