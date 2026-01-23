package com.deep_coding15.GesStockApi.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;
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
        
        if(Utils.isNegative(id))
            throw new EntityIllegalArgumentException("Utilisateur", "id", id.toString());

        Utilisateur utilisateur = utilisateurRepository
                .findById(id).orElseThrow(
                    () -> new EntityNotFoundException(
                        "Utilisateur", 
                        "id", id.toString()));

        return utilisateur;
    }
    
    public Utilisateur getUtilisateurByEmail(String email) {
        
        if(Utils.isStringUseless(email))
            throw new EntityIllegalArgumentException("utilisateur", "email", email);

        Utilisateur utilisateur = utilisateurRepository
                .findByEmail(email).orElseThrow(
                    () -> new EntityNotFoundException(
                        "Utilisateur", 
                        "email", email));

        return utilisateur;
    }
    
    public Utilisateur getUtilisateurByUsername(String username) {
        
        if(Utils.isStringUseless(username))
            throw new EntityIllegalArgumentException("Utilisateur", "username", username);

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
