package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.deep_coding15.GesStockApi.catalogue.dto.CategorieCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategoriePatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategorieUpdateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;
import com.deep_coding15.GesStockApi.catalogue.mapper.CategorieMapper;
import com.deep_coding15.GesStockApi.catalogue.service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {

    private final CategorieMapper categorieMapper;
    private final CategorieService categorieService;

    public CategorieController(
            CategorieMapper categorieMapper,
            CategorieService categorieService
        ) {
        this.categorieService = categorieService;
        this.categorieMapper = categorieMapper;
    }

    /* ===================== CREATE ===================== */

    /**
     * @param categorie
     * @return ResponseEntity<categorie>
     */
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody CategorieCreateRequestDTO categorieDto) {
        Categorie categorie = categorieMapper.toEntity(categorieDto);

        Categorie categorieCree = categorieService.createCategorie(categorie);
        return new ResponseEntity<>(categorieCree, HttpStatus.CREATED);
    }

    /* ===================== READ ===================== */

    /**
     * @return ResponseEntity<List<categorie>>
     */
    @GetMapping("/")
    public ResponseEntity<List<Categorie>> getCategories() {
        return ResponseEntity.ok(categorieService.getCategories());
    }

    /**
     * @param id
     * @return ResponseEntity<categorie>
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable Long id) {
        return ResponseEntity.ok(categorieService.getCategorieById(id));
    }

    /**
     * @param code
     * @return ResponseEntity<categorie>
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<Categorie> getCategorieByReference(@PathVariable String code) {
        return ResponseEntity.ok(categorieService.getCategorieByCode(code));
    }

    /**
     * @param libelle
     * @return ResponseEntity<categorie>
     */
    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<Categorie> getCategorieByNom(@PathVariable String libelle) {
        return ResponseEntity.ok(categorieService.getCategorieByLibelle(libelle));
    }

    /**
     * @param description
     * @return ResponseEntity<categorie>
     */
    @GetMapping("/description/{description}")
    public ResponseEntity<Categorie> getCategorieByDescription(@PathVariable String description) {
        return ResponseEntity.ok(categorieService.getCategorieByDescription(description));
    }

    /* ===================== UPDATE ===================== */
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(
            @PathVariable Long id,
            @RequestBody CategorieUpdateRequestDTO categorieDto) {

        Categorie categorie = categorieMapper.toEntity(categorieDto);

        return ResponseEntity.ok(
                categorieService.updateCategorie(id, categorie));
    }

    /* ===================== PATCH ===================== */
    @PatchMapping("/{id}")
    public ResponseEntity<Categorie> patchCategorie(
            @PathVariable Long id,
            @RequestBody CategoriePatchRequestDTO dto) {

        Categorie categorie = categorieMapper.toEntity(dto);
        return ResponseEntity.ok(
                categorieService.patchCategorie(id, categorie));
    }

    /* ===================== DELETE ===================== */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletecategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }

}
