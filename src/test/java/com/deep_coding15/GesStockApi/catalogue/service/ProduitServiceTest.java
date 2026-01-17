package com.deep_coding15.GesStockApi.catalogue.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {
    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitService produitService;

    @Test
    void doit_refuser_creation_si_reference_existe() {
        // GIVEN (préparation)
        Produit produit = new Produit();
        produit.setReference("REF001");

        // WHEN 
        when(produitRepository.existsByReference("REF001"))
            .thenReturn(true);

        // WHEN + THEN
        assertThrows(IllegalArgumentException.class, 
            () -> produitService.creerProduit(produit));
        
        verify(produitRepository, never()).save(any());
    }
}
