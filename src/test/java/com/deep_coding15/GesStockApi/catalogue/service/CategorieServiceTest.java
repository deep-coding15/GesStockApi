package com.deep_coding15.GesStockApi.catalogue.service;

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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.repository.CategorieRepository;
import com.deep_coding15.GesStockApi.common.exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CategorieServiceTest {
    @Mock
    public CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieService categorieService;

    @Test
    public void createCategory__shouldFailed__whenCodeAlreadyExists() {
        // GIVEN
        String code = "CAT_INFO";
        Categorie categorie = new Categorie();

        categorie.setCode("CAT_INFO");
        categorie.setLibelle("Informatique");
        categorie.setDescription("Description de la catégorie Informatique");

        when(categorieRepository.existsByCode(code))
                .thenReturn(true); // dis que l'utilisateur existe
        // WHEN

        assertThrows(EntityAlreadyExistsException.class,
                () -> categorieService.createCategorie(categorie));

        // On verifie que il n'a pas appele le repositoiry pour le save
        verify(
                categorieRepository,
                never())
                .save(any());

    }

    @Test
    public void createCategorie__shouldSucceeded__whenCodeNotExists() {
        String code = "CAT_INFO";
        Categorie categorie = new Categorie();
        // GIVEN
        categorie.setCode("CAT_INFO");
        categorie.setLibelle("Informatique");
        categorie.setDescription("Description de la catégorie Informatique");

        // WHEN + THEN
        when(categorieRepository.existsByCode(code))
                .thenReturn(false); 

        categorieService.createCategorie(categorie);

        verify(categorieRepository).save(categorie);
    }

    @Test
    public void getCategorieByCode__shouldSucceeded__whenCodeAlreadyExists() {

        // GIVEN
        String code = "CAT_INFO";
        Categorie categorie = new Categorie();
        categorie.setCode("CAT_INFO");

        when(categorieRepository.findByCode(code))
            .thenReturn(Optional.of(categorie));

        // WHEN
        Categorie categorieResult = categorieService.getCategorieByCode(code);

        // THEN
        assertNotNull(categorieResult);
        assertEquals(code, categorieResult.getCode());

        verify(categorieRepository).findByCode(code);
    }

    @Test
    public void getCategorieByCode__shouldFailed__whenCodeNotExists() {

        // GIVEN
        String code = "CAT_INFO";

        when(categorieRepository.findByCode(code))
            .thenReturn(Optional.empty());

        // WHEN + THEN
        assertThrows(
                EntityNotFoundException.class,
                () -> categorieService.getCategorieByCode(code));

        verify(categorieRepository).findByCode(code);

    }

    @Test
    public void getCategories__shouldAlwaysSucceeded() {
        // GIVEN
        List<Categorie> l_categories = List.of(
                new Categorie(),
                new Categorie());

        when(categorieRepository.findAll())
                .thenReturn(l_categories);

        List<Categorie> list_categories = categorieService.getCategories();

        assertNotNull(list_categories);

        assertEquals(2, l_categories.size());

        // Vérifier que la méthode findAll() a été appelée exactement une fois sur
        // utilisateurRepository.
        verify(categorieRepository, times(1)).findAll();
    }

    @Test
    public void getCategories__shouldReturnEmptyList__whenNoUserExists() {

        when(categorieRepository.findAll())
                .thenReturn(List.of());

        List<Categorie> list_Categories = categorieService.getCategories();

        assertEquals(0, list_Categories.size());
        verify(categorieRepository, times(1)).findAll();
    }

    @Test
    void updateCategorie_shouldFail_whenIdIsInvalid() {

        Categorie categorie = new Categorie();

        assertThrows(
                EntityIllegalArgumentException.class,
                () -> categorieService.patchCategorie(0L, categorie));
    }

    @Test
    void updateCategorie_shouldFail_whenCategorieDoesNotExist() {

        when(categorieRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> categorieService.patchCategorie(1L, new Categorie()));
    }

    @Test
    void patchCategorie_shouldUpdateOnlyProvidedFields() {

        Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setCode("CODE");

        Categorie dto = new Categorie();
        dto.setCode("Nouveau nom");

        when(categorieRepository.findById(1L))
                .thenReturn(Optional.of(categorie));

        when(categorieRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        Categorie result = categorieService.patchCategorie(1L, dto);

        assertEquals("Nouveau nom", result.getCode());
    }

    @Test
    void deleteCategorie_shouldSucceed_whenCategorieExists() {

        Categorie categorie = new Categorie();
        categorie.setId(1L);

        when(categorieRepository.findById(1L))
                .thenReturn(Optional.of(categorie));

        categorieService.deleteCategorie(1L);

        verify(categorieRepository).deleteById(1L);
    }

    @Test
    void deleteProduit_shouldFail_whenProduitDoesNotExist() {

        when(categorieRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> categorieService.deleteCategorie(1L));
    }


}
