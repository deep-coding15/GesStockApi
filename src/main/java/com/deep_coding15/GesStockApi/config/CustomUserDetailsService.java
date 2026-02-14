package com.deep_coding15.GesStockApi.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.common.exception.security.UsernameNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;
import com.deep_coding15.GesStockApi.security.repository.UtilisateurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository
                .findByUsername(username)
                      .orElseThrow(() -> new UsernameNotFoundException(username));

        // Transformer les rôles en GrantedAuthority
        /* Set<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
            .collect(Collectors.toSet()); */

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getMotDePasse())
                .roles(user.getRole().getCode()) // Car un seul Rôle
                .build();
    }
}
