package com.deep_coding15.GesStockApi.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.never;
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

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;
    
    @Test
    public void createUtilisateur__shouldFailed__whenEmailAlreadyExists(){
        // GIVEN
        String email = "email@email.com";
        Utilisateur utilisateur = new Utilisateur();
        
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse("password");
        utilisateur.setRole(new Role());
        utilisateur.setUsername(email);

        when(utilisateurRepository.existsByEmail(email))
            .thenReturn(true); // dis que l'utilisateur existe
        // WHEN 

        assertThrows(EntityAlreadyExistsException.class, 
                () -> utilisateurService.createUtilisateur(utilisateur));

        //On verifie que il n'a pas appele le repositoiry pour le save
        verify(
                utilisateurRepository, 
                never())
            .save(any());

    }

    @Test
    public void createUtilisateur__shouldSucceeded__whenEmailNotExists() {
        String email = "email@email.com";
        // GIVEN
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse("password");
        utilisateur.setUsername("email");
        //utilisateur.setRole(new Role());

        // WHEN + THEN 
        when(utilisateurRepository.existsByEmail(email))
            .thenReturn(false); // renvoies vrai pour l'inexistence de l'email

        utilisateurService.createUtilisateur(utilisateur);
        
        verify(
            utilisateurRepository
        ).save(utilisateur);
    }

    @Test
    public void getUtilisateurByEmail__shouldSucceeded__whenEmailAlreadyExists() {
        
        // GIVEN 
        String email = "email@email.com";
        Utilisateur utilisateur = new Utilisateur();        
        utilisateur.setEmail(email);

        when(utilisateurRepository.findByEmail(email))
            .thenReturn(Optional.of(utilisateur));

        // WHEN
        Utilisateur utilisateurResult = utilisateurService.getUtilisateurByEmail(email);
        
        // THEN        
        assertNotNull(utilisateurResult);
        assertEquals(email, utilisateurResult.getEmail());
        
        verify(utilisateurRepository).findByEmail(email);
    } 

    @Test
    public void getUtilisateurByEmail__shouldFailed__whenEmailNotExists() {
        
        // GIVEN
        String email = "email@email.com";

        when(utilisateurRepository.findByEmail(email))
            .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(
            EntityNotFoundException.class, 
            () -> utilisateurService.getUtilisateurByEmail(email)
        );

        verify(utilisateurRepository).findByEmail(email);

    }
    
    @Test
    public void getUtilisateurByUsername__shouldSucceeded__whenUsernameAlreadyExists() {
        
        // GIVEN 
        String username = "username";
        Utilisateur utilisateur = new Utilisateur();        
        utilisateur.setUsername(username);

        when(utilisateurRepository.findByUsername(username))
            .thenReturn(Optional.of(utilisateur));

        // WHEN
        Utilisateur utilisateurResult = utilisateurService.getUtilisateurByUsername(username);
        
        // THEN        
        assertNotNull(utilisateurResult);
        assertEquals(username, utilisateurResult.getUsername());
        
        verify(utilisateurRepository).findByUsername(username);
    } 

    @Test
    public void getUtilisateurByUsername__shouldFailed__whenUsernameNotExists() {
        
        // GIVEN
        String username = "username";

        when(utilisateurRepository.findByUsername(username))
            .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(
            EntityNotFoundException.class, 
            () -> utilisateurService.getUtilisateurByUsername(username)
        );

        verify(utilisateurRepository).findByUsername(username);

    }

    @Test
    public void getUtilisateurs__shouldAlwaysSucceeded() {

        // GIVEN
        List<Utilisateur> listUtilisateurs = List.of(
            new Utilisateur(),
            new Utilisateur()
        );

        when(utilisateurRepository.findAll())
                .thenReturn(listUtilisateurs);
        
        // WHEN
        List<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();

        // THEN 
        assertNotNull(utilisateurs);
        assertEquals(2, utilisateurs.size());

        // Vérifier que la méthode findAll() a été appelée exactement une fois sur utilisateurRepository.
        verify(utilisateurRepository, times(1)).findAll();

    }

    @Test
    public void getUtilisateurs__shouldReturnEmptyList__whenNoUserExists() {

        // GIVEN
        when(utilisateurRepository.findAll())
                .thenReturn(List.of());

        // WHEN 
        List<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();

        // THEN 
        assertNotNull(utilisateurs);
        assertTrue(utilisateurs.isEmpty());

        verify(utilisateurRepository, times(1)).findAll();
    }
}
