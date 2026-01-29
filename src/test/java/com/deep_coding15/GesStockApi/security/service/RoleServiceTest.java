package com.deep_coding15.GesStockApi.security.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.security.entity.Role;
import com.deep_coding15.GesStockApi.security.repository.RoleRepository;

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

    private Role roleAdmin;

    @BeforeEach
    void setUp() {
        roleAdmin = new Role(); // Création d’un objet Role utilisé comme donnée d’entrée du test
        roleAdmin.setId(1L);
        roleAdmin.setCode("ADMIN"); // Attribution du code du rôle (clé métier unique)
        roleAdmin.setLibelle("Administrateur");
    }

    @Nested
    @DisplayName("Tests pour createRole")
    class CreateRoleTests {

        @Test
        // Indique que cette méthode est un test JUnit
        void createRole_shouldFail_whenCodeAlreadyExists() {

            // Configuration du comportement du repository simulé :
            // si on vérifie l’existence du code "ADMIN"
            // alors le repository répondra "true"
            when(
                    roleRepository.existsByCode("ADMIN")).thenReturn(true);

            // Vérifie que l’appel à createRole déclenche une exception
            // de type EntityAlreadyExistsException
            assertThrows(
                    EntityAlreadyExistsException.class,
                    // Appel réel de la méthode métier testée
                    () -> roleService.createRole(roleAdmin));

            // Vérifie que la méthode save(...) du repository
            // n’a JAMAIS été appelée
            verify(
                    roleRepository,
                    never()).save(any());
        }

        @Test
        void createRole__shouldSucceeded__whenCodeNotExists() {
            when(roleRepository.existsByCode("ADMIN")).thenReturn(false);
            when(roleRepository.save(any(Role.class))).thenReturn(roleAdmin);

            Role created = roleService.createRole(roleAdmin);

            assertThat(created).isNotNull();
            assertThat(created.getCode()).isEqualTo("ADMIN");
            verify(roleRepository).save(roleAdmin);
        }
    }

    @Nested
    @DisplayName("Tests pour patchRole")
    class PatchRoleTests {

        @Test
        void patchRole__shouldSucceeded__whenRoleAlreadyExists() {
            Role patchData = new Role();
            patchData.setLibelle("Nouveau Libelle");

            when(roleRepository.findById(1L)).thenReturn(Optional.of(roleAdmin));
            when(roleRepository.save(any(Role.class))).thenAnswer(i -> i.getArguments()[0]);

            Role result = roleService.patchRole(1L, patchData);

            assertThat(result.getLibelle()).isEqualTo("Nouveau Libelle");
            assertThat(result.getCode()).isEqualTo("ADMIN"); // Inchangé
        }

        @Test
        void patchRole__shouldFailed__whenIdIsInvalid() {
            assertThatThrownBy(() -> roleService.patchRole(0L, roleAdmin))
                    .isInstanceOf(EntityIllegalArgumentException.class);
        }

        @Test
        void patchRole__shouldFailed__whenRoleNotFound() {
            when(roleRepository.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> roleService.patchRole(99L, roleAdmin))
                    .isInstanceOf(EntityNotFoundException.class);
        }
    }
    
    @Nested
    @DisplayName("Tests pour patchRole")
    class PutRoleTests {

        @Test
        void putRole__shouldSucceeded__whenRoleAlreadyExists() {
            Role putData = new Role();
            putData.setLibelle("Nouveau Libelle");

            when(roleRepository.findById(1L)).thenReturn(Optional.of(roleAdmin));
            when(roleRepository.save(any(Role.class))).thenAnswer(i -> i.getArguments()[0]);

            Role result = roleService.patchRole(1L, putData);

            assertThat(result.getLibelle()).isEqualTo("Nouveau Libelle");
            assertThat(result.getCode()).isEqualTo("ADMIN"); // Inchangé
        }

        @Test
        void putRole__shouldFailed__whenIdIsInvalid() {
            assertThatThrownBy(() -> roleService.patchRole(0L, roleAdmin))
                    .isInstanceOf(EntityIllegalArgumentException.class);
        }

        @Test
        void putRole__shouldFailed__whenRoleNotFound() {
            when(roleRepository.findById(99L)).thenReturn(Optional.empty());

            assertThatThrownBy(() -> roleService.patchRole(99L, roleAdmin))
                    .isInstanceOf(EntityNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("Tests pour deleteRole")
    class DeleteRoleTests{

        @Test
        void deleteRole__shouldSucceed() {
           when(roleRepository.existsById(1L)).thenReturn(true);

            roleService.deleteRole(1L);

            verify(roleRepository).deleteById(1L);
        }

        @Test
        void deleteRole__shouldFailed__WhenIdNotFound() {
            when(roleRepository.existsById(1L)).thenReturn(false);

            assertThatThrownBy(() -> roleService.deleteRole(1L))
                .isInstanceOf(EntityNotFoundException.class);
        }
    }

    @Test
    @DisplayName("shouldReturnAllRoles")
    void shouldReturnAllRoles() {
        when(roleRepository.findAll()).thenReturn(List.of(roleAdmin));

        Set<Role> roles = roleService.getRoles();

        assertThat(roles).hasSize(1).contains(roleAdmin);
    }

    /* @ParameterizedTest
    @ValueSource(strings = { "ADMIN", "CAISSIER", "GERANT" })
    void getRoleByCode_shouldFail_whenCodeNotExists(String code) {

        // GIVEN : le repository retourne null
        when(roleRepository.findByCode(code))
                .thenReturn(null);

        // WHEN + THEN : l'appel doit lever une exception
        assertThrows(
                EntityIllegalArgumentException.class,
                () -> roleService.getRoleByCode(code));
    }

    @ParameterizedTest
    @ValueSource(strings = { "ADMIN", "CAISSIER", "GERANT" })
    void getRoleByCode_shouldSucceed_whenCodeExists(String code) {

        // GIVEN : un role existant en base
        Role role = new Role();
        role.setId(1L);
        role.setCode(code);
        role.setLibelle("Role: " + code);

        // Le repository retourne ce rôle
        when(roleRepository.findByCode(code))
                .thenReturn(role);

        // WHEN : appel de la méthode métier
        Role result = roleService.getRoleByCode(code);

        // THEN : vérifications
        assertNotNull(result);
        assertEquals(code, result.getCode());
        assertEquals(role.getLibelle(), result.getLibelle());

        // Vérifie que le repository a bien été appelé une seule fois
        verify(roleRepository, times(1)).findByCode(code);
    } */
}
