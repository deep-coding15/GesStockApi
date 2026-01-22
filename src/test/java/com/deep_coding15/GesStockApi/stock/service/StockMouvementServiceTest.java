package com.deep_coding15.GesStockApi.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

import com.deep_coding15.GesStockApi.stock.entity.StockMouvement;
import com.deep_coding15.GesStockApi.stock.enums.TypeMouvementStock;
import com.deep_coding15.GesStockApi.stock.repository.StockMouvementRepository;

@ExtendWith(MockitoExtension.class)
public class StockMouvementServiceTest {

    @Mock
    private StockMouvementRepository stockMouvementRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private StockMouvementService stockMouvementService;

    private StockMouvement createValidStockMouvement() {
        StockMouvement sm = new StockMouvement();

        sm.setQuantite(10);
        sm.setTypeMouvement(TypeMouvementStock.ENTREE);

        Utilisateur user = new Utilisateur();
        user.setId(1L);

        Produit produit = new Produit();
        produit.setId(1L);

        sm.setUtilisateur(user);
        sm.setProduit(produit);

        return sm;
    }

    @Test
    void createStockMouvement_shouldFail_whenQuantiteIsNegativeOrZero() {

        // GIVEN
        StockMouvement sm = createValidStockMouvement();
        sm.setQuantite(0);

        // WHEN + THEN
        assertThrows(
                EntityIllegalArgumentException.class,
                () -> stockMouvementService.createStockMouvement(sm));
    }

    @Test
    void createStockMouvement_shouldFail_whenUtilisateurNotFound() {

        // GIVEN
        StockMouvement sm = createValidStockMouvement();

        when(utilisateurRepository.findById(1L))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(
                EntityNotFoundException.class,
                () -> stockMouvementService.createStockMouvement(sm));
    }

    @Test
    void createStockMouvement_shouldFail_whenProduitNotFound() {

        // GIVEN
        StockMouvement sm = createValidStockMouvement();

        when(utilisateurRepository.findById(1L))
                .thenReturn(Optional.of(sm.getUtilisateur()));

        when(produitRepository.findById(1L))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(
                EntityNotFoundException.class,
                () -> stockMouvementService.createStockMouvement(sm));
    }

    @Test
    void createStockMouvement_shouldSucceed_whenAllConditionsAreValid() {

        // GIVEN
        StockMouvement sm = createValidStockMouvement();

        when(utilisateurRepository.findById(1L))
                .thenReturn(Optional.of(sm.getUtilisateur()));

        when(produitRepository.findById(1L))
                .thenReturn(Optional.of(sm.getProduit()));

        when(stockMouvementRepository.save(any(StockMouvement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        StockMouvement result = stockMouvementService.createStockMouvement(sm);

        // THEN
        assertNotNull(result);
        assertEquals(10, result.getQuantite());

        verify(stockMouvementRepository, times(1)).save(sm);
    }

    @Test
    void getAllMouvementsByProduitId__shouldFailed__whenProduitNotExists() {

        when(produitRepository.existsById(1L)).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> stockMouvementService.getAllMouvementsByProduitId(1L));

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
        List<StockMouvement> result = stockMouvementService.getAllMouvementsByProduitId(1L);

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(produit.getId(), result.get(0).getProduit().getId());
    }

}
