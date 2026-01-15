package com.deep_coding15.GesStockApi.catalogue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.repository.CategorieRepository;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }

    public Categorie creerCategorie(Categorie categorie) {
        
        if(this.categorieRepository.existsByCode(categorie.getCode())) {
            throw new IllegalArgumentException("Un catégorie avec ce code existe déjà.");
        }

        return categorieRepository.save(categorie);
    }

    public List<Categorie> listerCategories() {
        return this.categorieRepository.findAll();
    }

    public Categorie trouverParId(Long id) {
        return this.categorieRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Catégorie introuvable."));
    }
    
}
