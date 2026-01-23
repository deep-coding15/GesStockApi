package com.deep_coding15.GesStockApi.catalogue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.repository.CategorieRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    /**
     * @param categorie
     * @return Categorie
     */
    public Categorie createCategorie(Categorie categorie) {

        if (categorieRepository.existsByCode(categorie.getCode())) {
            throw new EntityAlreadyExistsException(
                    "Categorie", "code",
                    categorie.getCode());
        }

        return categorieRepository.save(categorie);
    }

    /**
     * @return List<Categorie>
     */
    public List<Categorie> getCategories() {
        return this.categorieRepository.findAll();
    }

    /**
     * @param id
     * @return Categorie
     */
    public Categorie getCategorieById(Long id) {

        if (id < 0)
            throw new IllegalArgumentException("L'Id n'est pas valide.");

        Categorie categorie = categorieRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Categorie",
                                "id", id.toString()));

        return categorie;
    }

    public Categorie getCategorieByCode(String code) {
        
        if(code == null || code.isEmpty() || code.isBlank())
            throw new IllegalArgumentException("L'email n'est pas valide.");

        Categorie categorie = categorieRepository
                .findByCode(code).orElseThrow(() -> new EntityNotFoundException(
                        "Categorie", 
                        "code", code));

        return categorie;
    }

    public Categorie getCatgorieByLibelle(String libelle) {
        
        if(libelle == null || libelle.isEmpty() || libelle.isBlank())
            throw new IllegalArgumentException("L'username n'est pas valide.");

        Categorie categorie = categorieRepository
                .findByLibelle(libelle).orElseThrow(
                    () -> new EntityNotFoundException(
                        "Categorie", 
                        "libelle", libelle));

        return categorie;
    }

}
