package com.deep_coding15.GesStockApi.catalogue.service;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityAlreadyExistsException;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /** 
     * @param produit
     * @return Produit
     */
    public Produit createProduit(Produit produit) {

        if(Utils.isStringUseless(produit.getReference()))
            throw new EntityIllegalArgumentException(
        "produit", "reference", 
        produit.getReference());

        if (produitRepository.existsByReference(produit.getReference())) {
            throw new EntityAlreadyExistsException(
                "Produit", "reference", 
                produit.getReference());
        }

        return produitRepository.save(produit);
    }

    /** 
     * @return List<Produit>
     */
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

    /** 
     * @param id
     * @return Produit
     */
    public Produit getProduitById(Long id) {
        if(id < 0)
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
        if(Utils.isStringUseless(reference))
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
        if(Utils.isStringUseless(nom))
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
        if(Utils.isStringUseless(description))
            throw new EntityIllegalArgumentException(
        "Produit", "description", 
        description);

        return produitRepository.findByDescription(description)
                .orElseThrow(() -> new EntityNotFoundException(
                    "Produit", "description", description));
    }

    /** 
     * @param ref
     * @return boolean
     */
    public boolean isReferenceValide(String ref) {
        return ref != null && !ref.isBlank();
    }

}
