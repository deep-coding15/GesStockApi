package com.deep_coding15.GesStockApi.catalogue.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {
    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitService produitService;

    @Test
    void createProduct__shouldSucceeded__whenReferenceNotExists() {
        // GIVEN (préparation)
        Produit produit = new Produit();
        produit.setReference("REF001");
        produit.setNom("Clavier");
        produit.setDescription("Clavier");
        produit.setPrixUnitaire(new BigDecimal("120.0"));

        // WHEN
        when(produitRepository.existsByReference(produit.getReference()))
                .thenReturn(false);

        produitService.createProduit(produit);
        // WHEN + THEN

        verify(produitRepository).save(produit);
    }

    @Test
    void createProduct__shouldFailed__whenReferenceExists() {
        // GIVEN (préparation)
        Produit produit = new Produit();
        produit.setReference("REF001");
        produit.setNom("Clavier");
        produit.setDescription("Clavier");
        produit.setPrixUnitaire(new BigDecimal("120.0"));
        // WHEN
        when(produitRepository.existsByReference(produit.getReference()))
                .thenReturn(true);

        // WHEN + THEN
        assertThrows(EntityAlreadyExistsException.class,
                () -> produitService.createProduit(produit));

        verify(produitRepository, never()).save(any());
    }

    @Test
    public void getProduits__shouldAlwaysSucceeded() {

        // GIVEN
        List<Produit> listProduits = List.of(
                new Produit(),
                new Produit());

        when(produitRepository.findAll())
                .thenReturn(listProduits);

        // WHEN
        List<Produit> produits = produitService.getProduits();

        // THEN
        assertNotNull(produits);
        assertEquals(2, produits.size());

        // Vérifier que la méthode findAll() a été appelée exactement une fois sur
        // utilisateurRepository.
        verify(produitRepository, times(1)).findAll();

    }

    @Test
    public void getProduits__shouldReturnEmptyList__whenNoProduitExists() {

        // GIVEN
        when(produitRepository.findAll())
                .thenReturn(List.of());

        // WHEN
        List<Produit> produits = produitService.getProduits();

        // THEN
        assertNotNull(produits);
        assertTrue(produits.isEmpty());

        verify(produitRepository, times(1)).findAll();
    }

    @Test
    void updateProduit_shouldSucceed_whenProduitExists() {

        // GIVEN
        Long produitId = 1L;

        Produit existant = new Produit();
        existant.setId(produitId);
        existant.setNom("Ancien nom");

        Produit updated = new Produit();
        updated.setNom("Nouveau nom");

        when(produitRepository.findById(produitId))
                .thenReturn(Optional.of(existant));

        when(produitRepository.save(any(Produit.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Produit result = produitService.putProduit(produitId, updated);

        // THEN
        assertNotNull(result);
        assertEquals("Nouveau nom", result.getNom());
    }

    @Test
    void updateProduit_shouldFail_whenIdIsInvalid() {

        Produit produit = new Produit();

        assertThrows(
                EntityIllegalArgumentException.class,
                () -> produitService.putProduit(0L, produit));
    }

    @Test
    void updateProduit_shouldFail_whenProduitDoesNotExist() {

        when(produitRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> produitService.putProduit(1L, new Produit()));
    }

    @Test
    void patchProduit_shouldUpdateOnlyProvidedFields() {

        Produit produit = new Produit();
        produit.setId(1L);
        produit.setNom("Ancien nom");

        Produit dto = new Produit();
        dto.setNom("Nouveau nom");

        when(produitRepository.findById(1L))
                .thenReturn(Optional.of(produit));

        when(produitRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        Produit result = produitService.patchProduit(1L, dto);

        assertEquals("Nouveau nom", result.getNom());
    }

    @Test
    void deleteProduit_shouldSucceed_whenProduitExists() {

        Produit produit = new Produit();
        produit.setId(1L);

        when(produitRepository.findById(1L))
                .thenReturn(Optional.of(produit));

        assertTrue(produitService.deleteProduit(1L));

        verify(produitRepository).deleteById(1L);
    }

    @Test
    void deleteProduit_shouldFail_whenProduitDoesNotExist() {

        when(produitRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> produitService.deleteProduit(1L));
    }

}
