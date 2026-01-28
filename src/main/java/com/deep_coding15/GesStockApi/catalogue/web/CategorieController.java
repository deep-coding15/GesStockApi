package com.deep_coding15.GesStockApi.catalogue.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.catalogue.dto.CategorieCreateRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategoriePatchRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategoriePutRequestDTO;
import com.deep_coding15.GesStockApi.catalogue.dto.CategorieResponseDTO;

import com.deep_coding15.GesStockApi.catalogue.entity.Categorie;

import com.deep_coding15.GesStockApi.catalogue.mapper.CategorieMapper;

import com.deep_coding15.GesStockApi.catalogue.service.CategorieService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategorieController {

    private final CategorieService categorieService;
    private final CategorieMapper categorieMapper;

    public CategorieController(CategorieService categorieService, CategorieMapper categorieMapper) {
        this.categorieService = categorieService;
        this.categorieMapper = categorieMapper;
    }

    /**
     * @param produit
     * @return ResponseEntity<Produit>
     */
    @PostMapping("/")
    public ResponseEntity<CategorieResponseDTO> createCategorie(@Valid @RequestBody CategorieCreateRequestDTO dto) {

        Categorie categorie = categorieMapper.toEntity(dto);

        Categorie categorieCree = categorieService.createCategorie(categorie);

        CategorieResponseDTO responseDTO = categorieMapper.toResponse(categorieCree);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieResponseDTO> getcategorie(@PathVariable Long id) {
        try {
            Categorie categorie = categorieService.getCategorieById(id);
            CategorieResponseDTO dto = categorieMapper.toResponse(categorie);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/code/{code}")
    public ResponseEntity<CategorieResponseDTO> getCategorieByCode(@PathVariable String code) {
        try {
            Categorie categorie = categorieService.getCategorieByCode(code);
            CategorieResponseDTO dto = categorieMapper.toResponse(categorie);

            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/libelle/{libelle}")
    public ResponseEntity<CategorieResponseDTO> getCategorieByLibelle(@PathVariable String libelle) {
        try {
            Categorie categorie = categorieService.getCategorieByLibelle(libelle);
            CategorieResponseDTO dto = categorieMapper.toResponse(categorie);

            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CategorieResponseDTO>> getCategories() {
        try {
            List<Categorie> categoriesTrouves = categorieService.getCategories();

            List<CategorieResponseDTO> categories = categoriesTrouves.stream()
                    .map(categorieMapper::toResponse)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: logger l'erreur
            // log.error("Erreur lors de la récupération des rôles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/produits")
    public ResponseEntity<List<CategorieResponseDTO>> getCategoriesWithProduits() {
        try {
            List<Categorie> categoriesTrouves = categorieService.getCategories();

            List<CategorieResponseDTO> categories = categoriesTrouves.stream()
                    .map(categorieMapper::toResponse)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: logger l'erreur
            // log.error("Erreur lors de la récupération des rôles", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategorieResponseDTO> patchcategorie(
            @PathVariable Long id,
            @Valid @RequestBody CategoriePatchRequestDTO categorieDto) {

        Categorie categorie = categorieMapper.toEntity(categorieDto);

        Categorie categorieUpdate = categorieService.patchCategorie(id, categorie);

        return new ResponseEntity<>(categorieMapper.toResponse(categorieUpdate), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieResponseDTO> putcategorie(
            @PathVariable Long id, @Valid @RequestBody CategoriePutRequestDTO categorieDto) {

        Categorie categorie = categorieMapper.toEntity(categorieDto);

        Categorie categorieUpdate = categorieService.patchCategorie(id, categorie);

        return new ResponseEntity<>(categorieMapper.toResponse(categorieUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retourne un code 204 (Succès sans contenu)
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
    }
}
