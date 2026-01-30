package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitPatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitPutRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.produit.ProduitResponseDTO;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;

import com.deep_coding15.GesStockApi.catalogue.mapper.ProduitMapper;

import com.deep_coding15.GesStockApi.catalogue.service.ProduitService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/produits")
public class ProduitController {

    private final ProduitMapper produitMapper;
    private final ProduitService produitService;

    public ProduitController(
            ProduitMapper produitMapper,
            ProduitService produitService) {
        this.produitService = produitService;
        this.produitMapper = produitMapper;
    }

    /* ===================== CREATE ===================== */

    /**
     * @param produit
     * @return ResponseEntity<Produit>
     */
    @Operation(summary = "Créer un produit", description = "Ajoute un nouveau produit dans le catalogue")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produit créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "409", description = "Référence déjà existante")
    })
    @PostMapping("/")
    public ResponseEntity<ProduitResponseDTO> createProduit(@Valid @RequestBody ProduitCreateRequestDTO produitDto) {
        Produit produit = produitMapper.toEntity(produitDto);

        Produit produitCree = produitService.createProduit(produit);

        ProduitResponseDTO produitResponseDTO = produitMapper.toResponse(produitCree);
        return new ResponseEntity<>(produitResponseDTO, HttpStatus.CREATED);
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
    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> getProduit(@PathVariable Long id) {

        Produit produit = produitService.getProduitById(id);
        ProduitResponseDTO produitResponseDTO = produitMapper.toResponse(produit);

        return ResponseEntity.ok(produitResponseDTO);
    }

    /**
     * @param reference
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/reference/{reference}")
    public ResponseEntity<ProduitResponseDTO> getProduitByReference(@PathVariable String reference) {

        Produit produit = produitService.getProduitByReference(reference);
        ProduitResponseDTO produitResponseDTO = produitMapper.toResponse(produit);

        return ResponseEntity.ok(produitResponseDTO);
    }

    /**
     * @param nom
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/nom/{nom}")
    public ResponseEntity<ProduitResponseDTO> getProduitByNom(@PathVariable String nom) {

        Produit produit = produitService.getProduitByNom(nom);
        ProduitResponseDTO produitResponseDTO = produitMapper.toResponse(produit);

        return ResponseEntity.ok(produitResponseDTO);
    }

    /**
     * @param description
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/description/{description}")
    public ResponseEntity<ProduitResponseDTO> getProduitByDescription(@PathVariable String description) {

        Produit produit = produitService.getProduitByDescription(description);
        ProduitResponseDTO produitResponseDTO = produitMapper.toResponse(produit);

        return ResponseEntity.ok(produitResponseDTO);
    }

    /**
     * @param id
     * @return ResponseEntity<Produit>
     */
    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<ProduitResponseDTO>> getProduitByCategorie(@PathVariable Long categorieId) {

        List<Produit> listProduits = produitService.getProduitsByCategorieId(categorieId);

        return ResponseEntity.ok(produitMapper.toResponseList(listProduits));
    }

    /* ===================== UPDATE ===================== */
    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> putProduit(
            @PathVariable Long id,
            @Valid @RequestBody ProduitPutRequestDTO produitDto) {

        Produit produit = produitMapper.toEntity(produitDto);

        return ResponseEntity.ok(
                produitMapper.toResponse(produitService.putProduit(id, produit)));
    }

    /* ===================== PATCH ===================== */
    @PatchMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> patchProduit(
            @PathVariable Long id,
            @Valid @RequestBody ProduitPatchRequestDTO dto) {

        Produit produit = produitMapper.toEntity(dto);
        return ResponseEntity.ok(
                produitMapper.toResponse(produitService.patchProduit(id, produit)));
    }

    /* ===================== DELETE ===================== */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

}
