package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deep_coding15.GesStockApi.catalogue.dto.ProduitCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.ProduitPatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.ProduitResponseDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.ProduitUpdateRequestDTO;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;

import com.deep_coding15.GesStockApi.catalogue.mapper.ProduitMapper;

import com.deep_coding15.GesStockApi.catalogue.service.ProduitService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {

    private final ProduitMapper produitMapper;
    private final ProduitService produitService;

    public ProduitController(
            ProduitMapper produitMapper,
            ProduitService produitService
        ) {
        this.produitService = produitService;
        this.produitMapper = produitMapper;
    }

    /* ===================== CREATE ===================== */

    /**
     * @param produit
     * @return ResponseEntity<Produit>
     */
    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody ProduitCreateRequestDTO produitDto) {
        Produit produit = produitMapper.toEntity(produitDto);

        Produit produitCree = produitService.createProduit(produit);
        return new ResponseEntity<>(produitCree, HttpStatus.CREATED);
    }

    /* ===================== READ ===================== */

    /**
     * @return ResponseEntity<List<Produit>>
     */
    @GetMapping("/")
    public ResponseEntity<Set<ProduitResponseDTO>> getProduits() {
        List<Produit> produits = produitService.getProduits();
        return new ResponseEntity<>(produitMapper.toResponseSet(produits), HttpStatus.OK);
    }

    /**
     * @param id
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProduitById(id));
    }

    /**
     * @param reference
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/reference/{reference}")
    public ResponseEntity<Produit> getProduitByReference(@PathVariable String reference) {
        return ResponseEntity.ok(produitService.getProduitByReference(reference));
    }

    /**
     * @param nom
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/nom/{nom}")
    public ResponseEntity<Produit> getProduitByNom(@PathVariable String nom) {
        return ResponseEntity.ok(produitService.getProduitByNom(nom));
    }

    /**
     * @param description
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/description/{description}")
    public ResponseEntity<Produit> getProduitByDescription(@PathVariable String description) {
        return ResponseEntity.ok(produitService.getProduitByDescription(description));
    }

    /**
     * @param id
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<Produit>> getProduitByCategorie(@PathVariable Long categorieId) {
        return ResponseEntity.ok(produitService.getProduitsByCategorie(categorieId));
    }

    /* ===================== UPDATE ===================== */
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(
            @PathVariable Long id,
            @RequestBody ProduitUpdateRequestDTO produitDto) {

        Produit produit = produitMapper.toEntity(produitDto);

        return ResponseEntity.ok(
                produitService.updateProduit(id, produit));
    }

    /* ===================== PATCH ===================== */
    @PatchMapping("/{id}")
    public ResponseEntity<Produit> patchProduit(
            @PathVariable Long id,
            @RequestBody ProduitPatchRequestDTO dto) {

        Produit produit = produitMapper.toEntity(dto);
        return ResponseEntity.ok(
                produitService.patchProduit(id, produit));
    }

    /* ===================== DELETE ===================== */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

}
