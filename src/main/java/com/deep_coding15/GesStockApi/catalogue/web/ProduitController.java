package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.service.ProduitService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    /** 
     * @param produit
     * @return ResponseEntity<Produit>
     */
    @PostMapping
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {
        Produit produitCree = produitService.createProduit(produit);
        return new ResponseEntity<>(produitCree, HttpStatus.CREATED);
    }

    /** 
     * @return ResponseEntity<List<Produit>>
     */
    @GetMapping
    public ResponseEntity<List<Produit>> listerProduits() {
        return ResponseEntity.ok(produitService.getProduits());
    }

    /** 
     * @param id
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProduitById(id));
    }
}
