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

    public Produit creerProduit(Produit produit) {

        if (produitRepository.existsByReference(produit.getReference())) {
            throw new IllegalArgumentException("Un produit avec cette référence existe déjà");
        }

        return produitRepository.save(produit);
    }

    public List<Produit> listerProduits() {
        return produitRepository.findAll();
    }

    public Produit trouverParId(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit introuvable."));
    }

    public boolean referenceValide(String ref) {
        return ref != null && !ref.isBlank();
    }

}
