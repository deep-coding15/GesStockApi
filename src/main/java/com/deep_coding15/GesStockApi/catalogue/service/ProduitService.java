package com.deep_coding15.GesStockApi.catalogue.service;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;

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

        if (produitRepository.existsByReference(produit.getReference())) {
            throw new IllegalArgumentException("Un produit avec cette référence existe déjà");
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
    public Produit findProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable."));
    }

    /** 
     * @param ref
     * @return boolean
     */
    public boolean isReferenceValide(String ref) {
        return ref != null && !ref.isBlank();
    }

}
