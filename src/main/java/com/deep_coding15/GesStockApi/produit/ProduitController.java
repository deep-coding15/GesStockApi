package com.deep_coding15.GesStockApi.produit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {
        Produit produitCree = produitService.creerProduit(produit);
        return new ResponseEntity<>(produitCree, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produit>> listerProduits() {
        return ResponseEntity.ok(produitService.listerProduits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.trouverParId(id));
    }
}
