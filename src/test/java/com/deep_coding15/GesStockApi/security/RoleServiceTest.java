package com.deep_coding15.GesStockApi.security;

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

import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.repository.RoleRepository;
import com.deep_coding15.GesStockApi.security.service.RoleService;

@ExtendWith(MockitoExtension.class)
// Indique à JUnit d’activer Mockito pour cette classe de test
class RoleServiceTest {

    @Mock
    // Crée un faux RoleRepository (aucun accès à la base de données)
    private RoleRepository roleRepository;

    @InjectMocks
    // Crée une instance réelle de RoleService
    // et y injecte automatiquement le RoleRepository simulé
    private RoleService roleService;

    @Test
    // Indique que cette méthode est un test JUnit
    void createRole_shouldFail_whenCodeAlreadyExists() {

        // Création d’un objet Role utilisé comme donnée d’entrée du test
        Role role = new Role();

        // Attribution du code du rôle (clé métier unique)
        role.setCode(
            "ADMIN"
        );

        // Configuration du comportement du repository simulé :
        // si on vérifie l’existence du code "ADMIN"
        // alors le repository répondra "true"
        when(
            roleRepository.existsByCode(
                "ADMIN"
            )
        ).thenReturn(
            true
        );

        // Vérifie que l’appel à createRole déclenche une exception
        // de type IllegalArgumentException
        assertThrows(
            IllegalArgumentException.class,
            // Appel réel de la méthode métier testée
            () -> roleService.createRole(
                role
            )
        );

        // Vérifie que la méthode save(...) du repository
        // n’a JAMAIS été appelée
        verify(
            roleRepository,
            never()
        ).save(
            any()
        );
    }
}
