package com.deep_coding15.GesStockApi.catalogue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.repository.CategorieRepository;

import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;

import com.deep_coding15.GesStockApi.common.utils.Utils;

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

        if (Utils.isNegativeOrNull(id))
            throw new EntityIllegalArgumentException("Categorie", "id", id.toString());

        Categorie categorie = categorieRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Categorie",
                                "id", id.toString()));

        return categorie;
    }

    public Categorie getCategorieByCode(String code) {

        if (Utils.isStringUseless(code))
            throw new EntityIllegalArgumentException("Categorie", "code", code);

        Categorie categorie = categorieRepository
                .findByCode(code).orElseThrow(() -> new EntityNotFoundException(
                        "Categorie",
                        "code", code));

        return categorie;
    }

    public Categorie getCategorieByLibelle(String libelle) {

        if (Utils.isStringUseless(libelle))
            throw new EntityIllegalArgumentException("Categorie", "libelle", libelle);

        Categorie categorie = categorieRepository
                .findByLibelle(libelle).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Categorie",
                                "libelle", libelle));

        return categorie;
    }
    
    public Categorie getCategorieByDescription(String description) {

        if (Utils.isStringUseless(description))
            throw new EntityIllegalArgumentException(
        "Categorie", "description", description);

        Categorie categorie = categorieRepository
                .findByLibelle(description).orElseThrow(
                        () -> new EntityNotFoundException(
                                "Categorie",
                                "description", description));

        return categorie;
    }

    public Categorie updateCategorie(Long id, Categorie categorie) {

        if (Utils.isNegativeOrNull(id)) {
            throw new EntityIllegalArgumentException(
                    "Categorie", "id", id.toString());
        }

        Categorie categorieExistant = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categorie", "id", id.toString()));

        // Mise à jour champ par champ
        categorieExistant.setCode(categorie.getCode());
        categorieExistant.setDescription(categorie.getDescription());
        categorieExistant.setLibelle(categorie.getLibelle());
        categorieExistant.setActif(categorie.getActif());
        
        return categorieRepository.save(categorieExistant);
    }

    public Categorie patchCategorie(Long id, Categorie categorie) {

        if (Utils.isNegativeOrNull(id)) {
            throw new EntityIllegalArgumentException("Categorie", "id", id.toString());
        }

        Categorie categorieExistant = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categorie", "id", id.toString()));

        
        if (categorie.getCode() != null)
            categorieExistant.setCode(categorie.getCode());

        if (categorie.getDescription() != null)
            categorieExistant.setDescription(categorie.getDescription());

        if (categorie.getLibelle() != null)
            categorieExistant.setLibelle(categorie.getLibelle());
        
        if (categorie.getActif() != categorieExistant.getActif())
            categorieExistant.setActif(categorie.getActif());        

        return categorieRepository.save(categorie);
    }

    public boolean deleteCategorie(Long id) {

        if (Utils.isNegativeOrNull(id)) {
            throw new EntityIllegalArgumentException(
                    "Categorie", "id", id.toString());
        }

        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Categorie", "id", id.toString()));

        categorieRepository.deleteById(categorie.getId());
        return true;
        // produitRepository.delete(produit);
    }

}
