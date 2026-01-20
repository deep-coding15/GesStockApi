package com.deep_coding15.GesStockApi.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        
        if(utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new EntityAlreadyExistsException(
                "Utilisateur", "email", 
                utilisateur.getEmail());
        }

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurById(Long id) {
        
        if(id < 0)
            throw new IllegalArgumentException("L'Id n'est pas valide.");

        Utilisateur utilisateur = utilisateurRepository
                .findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                        "Utilisateur", 
                        "id", id.toString()));

        return utilisateur;
    }
    
    public Utilisateur getUtilisateurByEmail(String email) {
        
        if(email == null || email.isEmpty() || email.isBlank())
            throw new IllegalArgumentException("L'email n'est pas valide.");

        Utilisateur utilisateur = utilisateurRepository
                .findByEmail(email).orElseThrow(
                    () -> new EntityNotFoundException(
                        "Utilisateur", 
                        "email", email));

        return utilisateur;
    }
    
    public Utilisateur getUtilisateurByUsername(String username) {
        
        if(username == null || username.isEmpty() || username.isBlank())
            throw new IllegalArgumentException("L'username n'est pas valide.");

        Utilisateur utilisateur = utilisateurRepository
                .findByUsername(username).orElseThrow(
                    () -> new EntityNotFoundException(
                        "Utilisateur", 
                        "username", username));

        return utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}
