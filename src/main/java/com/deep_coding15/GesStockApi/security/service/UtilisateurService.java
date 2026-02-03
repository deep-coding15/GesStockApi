package com.deep_coding15.GesStockApi.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.common.exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.RoleRepository;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

@Service
public class UtilisateurService {
    private RoleRepository roleRepository;
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(
            UtilisateurRepository utilisateurRepository,
            RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        if(Utils.isStringUseless(utilisateur.getEmail()) 
            || Utils.isStringUseless(utilisateur.getUsername()) 
                || Utils.isStringUseless(utilisateur.getMotDePasse())){
            throw new EntityIllegalArgumentException("Utilisateur", 
            "", "", "username ou email ou mot de passe vides.");
        }

        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new EntityAlreadyExistsException(
                    "Utilisateur", "email",
                    utilisateur.getEmail());
        }
        if (utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
            throw new EntityAlreadyExistsException(
                    "Utilisateur", "username",
                    utilisateur.getUsername());
        }

        if (!roleRepository.existsById(utilisateur.getRole().getId()))
            throw new EntityNotFoundException("Role", "id", utilisateur.getRole().getId().toString());

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurById(Long id) {

        if (Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Utilisateur", "id", id.toString());

        Utilisateur utilisateur = utilisateurRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Utilisateur",
                                "id", id.toString()));

        return utilisateur;
    }

    public Utilisateur getUtilisateurByEmail(String email) {

        if (Utils.isStringUseless(email))
            throw new EntityIllegalArgumentException("utilisateur", "email", email);

        Utilisateur utilisateur = utilisateurRepository
                .findByEmail(email).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Utilisateur",
                                "email", email));

        return utilisateur;
    }

    public Utilisateur getUtilisateurByUsername(String username) {

        if (Utils.isStringUseless(username))
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

    public Utilisateur patchUtilisateur(Long id, Utilisateur utilisateur) {
        if (Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Utilisateur", "id", id.toString());

        Utilisateur utilisateurUpdate = utilisateurRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Utilisateur",
                                "id", id.toString()));

        if (!Utils.isStringUseless(utilisateur.getEmail()))
            utilisateurUpdate.setEmail(utilisateur.getEmail());

        if (!Utils.isStringUseless(utilisateur.getUsername()))
            utilisateurUpdate.setUsername(utilisateur.getUsername());

        if (!Utils.isStringUseless(utilisateur.getTelephone()))
            utilisateurUpdate.setTelephone(utilisateur.getTelephone());

        return utilisateurRepository.save(utilisateurUpdate);
    }

    public Utilisateur putUtilisateur(Long id, Utilisateur utilisateur) {
        if (Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Utilisateur", "id", id.toString());

        if(Utils.isStringUseless(utilisateur.getEmail()) 
            || Utils.isStringUseless(utilisateur.getUsername()) 
                 || Utils.isStringUseless(utilisateur.getMotDePasse())){
            throw new EntityIllegalArgumentException("Utilisateur", 
            "", "", "username ou email ou mot de passe vides.");
        }

        Utilisateur utilisateurUpdate = utilisateurRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Utilisateur",
                                "id", id.toString()));

        utilisateurUpdate.setEmail(utilisateur.getEmail());

        utilisateurUpdate.setUsername(utilisateur.getUsername());

        utilisateurUpdate.setTelephone(utilisateur.getTelephone());

        return utilisateurRepository.save(utilisateurUpdate);
    }

    public void deleteUtilisateur(Long id) {
        if (Utils.isNegativeOrNullOrZero(id))
            throw new EntityIllegalArgumentException("Utilisateur", "id", id.toString());

        Utilisateur utilisateurDelete = utilisateurRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Utilisateur",
                                "id", id.toString()));

        utilisateurRepository.delete(utilisateurDelete);
    }
}
