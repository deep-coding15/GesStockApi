package com.deep_coding15.GesStockApi.vente.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

import com.deep_coding15.GesStockApi.vente.entity.Vente;
import com.deep_coding15.GesStockApi.vente.entity.VenteLigne;

import com.deep_coding15.GesStockApi.vente.repository.VenteRepository;

@ExtendWith(MockitoExtension.class)
public class VenteServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private VenteRepository venteRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private VenteService venteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVente_shouldSucceed_whenValidVente() {

        // GIVEN : un utilisateur et une vente avec lignes
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);

        VenteLigne ligne = new VenteLigne();
        ligne.setQuantite(2);
        ligne.setPrixUnitaire(new BigDecimal("10.0"));

        Vente vente = new Vente();
        vente.setUtilisateur(utilisateur);
        vente.setLignesVente(List.of(ligne));

        // Mock du repo utilisateur
        when(utilisateurRepository.findById(1L))
            .thenReturn(Optional.of(utilisateur));

        // Mock du repo vente
        when(venteRepository.save(vente)).thenReturn(vente);

        // WHEN
        Vente result = venteService.createVente(vente);

        // THEN
        assertNotNull(result);
        assertEquals(utilisateur, result.getUtilisateur());
        verify(venteRepository, times(1)).save(vente);
    }

    @Test
    void createVente_shouldFail_whenNoLignes() {

        Vente vente = new Vente();
        vente.setLignesVente(List.of()); // vide

        assertThrows(
            EntityIllegalArgumentException.class,
            () -> venteService.createVente(vente)
        );

    }

    @Test
    void createVente_shouldFail_whenUtilisateurNotFound() {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);

        VenteLigne ligne = new VenteLigne();
        ligne.setQuantite(2);
        ligne.setPrixUnitaire(new BigDecimal("10.0"));

        Vente vente = new Vente();
        vente.setUtilisateur(utilisateur);
        vente.setLignesVente(List.of(ligne));

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () -> venteService.createVente(vente)
        );
    }

    @Test
    void getVenteById_shouldSucceed_whenVenteExists() {

        Vente vente = new Vente();
        vente.setId(1L);

        when(venteRepository.findById(1L)).thenReturn(Optional.of(vente));

        Vente result = venteService.getVenteById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getVenteById_shouldFail_whenVenteNotFound() {

        when(venteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundException.class,
            () -> venteService.getVenteById(1L)
        );
    }

}
