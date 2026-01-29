package com.deep_coding15.GesStockApi.catalogue.service;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.CategorieRepository;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProduitService(
            CategorieRepository categorieRepository,
            ProduitRepository produitRepository) {
        this.categorieRepository = categorieRepository;
        this.produitRepository = produitRepository;
    }

    /**
     * @param produit
     * @return Produit
     */
    @Transactional
    public Produit createProduit(Produit produit) {

        if (Utils.isStringUseless(produit.getNom())
                || Utils.isStringUseless(produit.getDescription())
                || Utils.isNegativeOrNull(produit.getPrixUnitaire())
                || Utils.isNegativeOrNullOrZero(produit.getCategorie().getId())) {
            throw new EntityIllegalArgumentException(
                    "produit", "reference",
                    produit.getReference(), "Certains champs sont manquants.");
        }

        if (produitRepository.existsByReference(produit.getReference())) {
            throw new EntityAlreadyExistsException(
                    "Produit", "reference",
                    produit.getReference());
        }

        Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                .orElseThrow(() -> new EntityNotFoundException("Categorie", "id",
                        produit.getCategorie().getId().toString()));

        produit.setCategorie(categorie);
        return produitRepository.save(produit);
    }

    /**
     * @return List<Produit>
     */
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

    public List<Produit> getProduitsByCategorieId(Long categorieId) {
        if (Utils.isNegativeOrNull(categorieId))
            throw new EntityIllegalArgumentException(
                    "Produit", "categorieId",
                    categorieId);

        List<Produit> produitsParCategorie = produitRepository.findByCategorieId(categorieId);

        return produitsParCategorie;
    }

    public List<Produit> getProduitsByCategorieCode(String code) {
        if (Utils.isStringUseless(code))
            throw new EntityIllegalArgumentException(
                    "Produit", "categorie : code",
                    code);

        List<Produit> produitsParCategorie = produitRepository.findByCategorieCode(code);

        return produitsParCategorie;
    }

    /**
     * @param id
     * @return Produit
     */
    public Produit getProduitById(Long id) {
        if (Utils.isNegativeOrNull(id))
            throw new EntityIllegalArgumentException(
                    "Produit", "id",
                    "L'id n'est pas valide");

        return produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id", id.toString()));
    }

    /**
     * @param reference
     * @return Produit
     */
    public Produit getProduitByReference(String reference) {
        if (Utils.isStringUseless(reference))
            throw new EntityIllegalArgumentException(
                    "Produit", "reference",
                    reference);

        return produitRepository.findByReference(reference)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id", reference));
    }

    /**
     * @param reference
     * @return Produit
     */
    public Produit getProduitByNom(String nom) {
        if (Utils.isStringUseless(nom))
            throw new EntityIllegalArgumentException(
                    "Produit", "nom",
                    nom);

        return produitRepository.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "nom", nom));
    }

    /**
     * @param reference
     * @return Produit
     */
    public Produit getProduitByDescription(String description) {
        if (Utils.isStringUseless(description))
            throw new EntityIllegalArgumentException(
                    "Produit", "description",
                    description);

        return produitRepository.findByDescription(description)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "description", description));
    }

    ////////////////////////////////////////////////////////

    @Transactional
    public Produit putProduit(Long id, Produit produit) {

        if (Utils.isNegativeOrNull(id)) {
            throw new EntityIllegalArgumentException(
                    "Produit", "id", id.toString());
        }

        if (Utils.isStringUseless(produit.getNom())
                || Utils.isStringUseless(produit.getReference())
                || Utils.isStringUseless(produit.getDescription())
                || Utils.isNegativeOrNull(produit.getPrixUnitaire())
                || Utils.isNegativeOrNullOrZero(produit.getCategorie().getId())) {
            throw new EntityIllegalArgumentException(
                    "produit", "reference",
                    produit.getReference(), "Certains champs sont manquants.");
        }

        Produit produitExistant = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id", id.toString()));

        // Mise à jour champ par champ
        produitExistant.setNom(produit.getNom());
        produitExistant.setDescription(produit.getDescription());
        produitExistant.setPrixUnitaire(produit.getPrixUnitaire());
        produitExistant.setReference(produit.getReference());
        //produitExistant.setCategorie(produit.getCategorie());

        if (produit.getCategorie() != null) {
            Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Categorie", "id", produit.getCategorie().getId().toString()));

            produitExistant.setCategorie(categorie);
        } else {
            Categorie categorie = produitRepository.findCategorieById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produit", "categorieId", produit.getId().toString(),
                            "pas de categorie pour ce produit."));

            produitExistant.setCategorie(categorie);
        }

        return produitRepository.save(produitExistant);
    }

    ///////////////////////////////////////////////////////////
    @Transactional
    public Produit patchProduit(Long id, Produit produit) {

        if (Utils.isNegativeOrNullOrZero(id)) {
            throw new EntityIllegalArgumentException("Produit", "id", id.toString());
        }

        Produit produitExistant = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id", id.toString()));

        if (!Utils.isStringUseless(produit.getNom()))
            produitExistant.setNom(produit.getNom());

        if (!Utils.isStringUseless(produit.getDescription()))
            produitExistant.setDescription(produit.getDescription());

        if (!Utils.isNegativeOrNullOrZero(produit.getPrixUnitaire()))
            produitExistant.setPrixUnitaire(produit.getPrixUnitaire());

        if (produit.getCategorie() != null) {
            Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Categorie", "id", produit.getCategorie().getId().toString()));

            produitExistant.setCategorie(categorie);
        } else {
            Categorie categorie = produitRepository.findCategorieById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produit", "categorieId", produit.getId().toString(),
                            "pas de categorie pour ce produit."));

            produitExistant.setCategorie(categorie);
        }

        return produitRepository.save(produitExistant);
    }

    ///////////////////////////////////////////////////////////
    @Transactional
    public boolean deleteProduit(Long id) {

        if (Utils.isNegativeOrNull(id)) {
            throw new EntityIllegalArgumentException(
                    "Produit", "id", id.toString());
        }

        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit", "id", id.toString()));

        produitRepository.deleteById(produit.getId());
        return true;
        // produitRepository.delete(produit);
    }

    /**
     * @param ref
     * @return boolean
     */
    public boolean isReferenceValide(String ref) {
        return ref != null && !ref.isBlank();
    }

}
