package com.deep_coding15.GesStockApi.stock.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deep_coding15.GesStockApi.security.entity.Utilisateur;

import com.deep_coding15.GesStockApi.stock.dto.stock.StockCreateRequestDTO;
import com.deep_coding15.GesStockApi.stock.dto.stock.StockPatchQuantityRequestDTO;
import com.deep_coding15.GesStockApi.stock.dto.stock.StockResponseDTO;

import com.deep_coding15.GesStockApi.stock.entity.Stock;

import com.deep_coding15.GesStockApi.stock.mapper.StockMapper;

import com.deep_coding15.GesStockApi.stock.service.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
	name = "Stock",
	description = "Gestion complète des stocks de produits (CRUD + mouvements de stock)"
)
@ApiResponses({
		@ApiResponse(responseCode = "400", description = "Requête invalide ou paramètres manquants"),
		@ApiResponse(responseCode = "401", description = "Non autorisé - authentification requise"),
		@ApiResponse(responseCode = "403", description = "Interdit - permissions insuffisantes"),
		@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
})
@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

	private final StockService stockService;
	private final StockMapper stockMapper;

	public StockController(
			StockService stockService,
			StockMapper stockMapper) {
		this.stockService = stockService;
		this.stockMapper = stockMapper;
	}

	/**
	 * POST /api/v1/stocks/
	 * Crée un nouveau stock pour un produit
	 * @param dto Données du stock à créer (produitId, quantiteInitiale, utilisateurId)
	 * @return Le stock créé avec le code 201
	 */
	@Operation(
		summary = "POST: Créer un stock",
		description = "Crée un stock initial pour un produit. Un seul stock par produit est autorisé"
	)
	@ApiResponses({
			@ApiResponse(
				responseCode = "201",
				description = "Stock créé avec succès",
				content = @Content(schema = @Schema(implementation = StockResponseDTO.class))
			),
			@ApiResponse(responseCode = "400", description = "Données invalides (quantité négative, IDs manquants)"),
			@ApiResponse(responseCode = "404", description = "Produit ou utilisateur non trouvé"),
			@ApiResponse(responseCode = "409", description = "Stock déjà existant pour ce produit"),
			@ApiResponse(responseCode = "401", description = "Authentification requise"),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
	})
	/**
	 * POST /api/v1/stocks/
	 * Crée un nouveau stock pour un produit
	 * @param dto Données du stock à créer (produitId, quantiteInitiale, utilisateurId)
	 * @return Le stock créé avec le code 201
	 */
	@PostMapping("/")
	public ResponseEntity<StockResponseDTO> createStock(
			@Valid @RequestBody StockCreateRequestDTO dto) {
		Stock stock = stockService.createStock(
                dto.getProduitId(),
                dto.getQuantiteInitiale(),
                dto.getUtilisateurId());

        return new ResponseEntity<>(stockMapper.toResponseDTO(stock), HttpStatus.CREATED);
    }

	/**
	 * GET /api/v1/stocks/
	 * Récupère tous les stocks
	 * @return Liste de tous les stocks
	 */
	@Operation(
		summary = "GET: Lister tous les stocks",
		description = "Récupère la liste complète de tous les stocks du système"
	)
	@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = "Stocks récupérés avec succès",
				content = @Content(schema = @Schema(implementation = StockResponseDTO.class))
			),
			@ApiResponse(responseCode = "204", description = "Aucun stock disponible"),
			@ApiResponse(responseCode = "401", description = "Authentification requise"),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
	})
	@GetMapping("/")
	public ResponseEntity<List<StockResponseDTO>> getAllStocks() {
		List<Stock> stocks = stockService.getStocks();
		List<StockResponseDTO> response = stocks.stream()
			.map(stockMapper::toResponseDTO)
			.collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * GET /api/v1/stocks/{id}
	 * Récupère un stock par son identifiant
	 * @param id L'identifiant unique du stock
	 * @return Le stock trouvé
	 */
	@GetMapping("/{id}")
	@Operation(
		summary = "GET: Récupérer un stock par ID",
		description = "Récupère un stock en utilisant son identifiant unique"
	)
	@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = "Stock récupéré avec succès",
				content = @Content(schema = @Schema(implementation = StockResponseDTO.class))
			),
			@ApiResponse(responseCode = "404", description = "Stock non trouvé (ID inexistant)"),
			@ApiResponse(responseCode = "400", description = "ID invalide (non numérique)"),
			@ApiResponse(responseCode = "401", description = "Authentification requise"),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
	})
	public ResponseEntity<StockResponseDTO> getStock(
			@Parameter(description = "Identifiant unique du stock", example = "1")
			@PathVariable Long id) {
		Stock stock = stockService.getStockById(id);
		return new ResponseEntity<>(stockMapper.toResponseDTO(stock), HttpStatus.OK);
	}

	/**
	 * GET /api/v1/stocks/produit/{produitId}
	 * Récupère le stock d'un produit
	 * @param produitId L'identifiant unique du produit
	 * @return Le stock du produit
	 */
	@GetMapping("/produit/{produitId}")
	@Operation(
		summary = "GET: Récupérer le stock d'un produit",
		description = "Récupère le stock associé à un produit en utilisant son identifiant"
	)
	@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = "Stock récupéré avec succès",
				content = @Content(schema = @Schema(implementation = StockResponseDTO.class))
			),
			@ApiResponse(responseCode = "404", description = "Stock ou produit non trouvé"),
			@ApiResponse(responseCode = "400", description = "ID de produit invalide (non numérique)"),
			@ApiResponse(responseCode = "401", description = "Authentification requise"),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
	})
	public ResponseEntity<StockResponseDTO> getStockByProduitId(
			@Parameter(description = "Identifiant unique du produit", example = "1")
			@PathVariable Long produitId) {
		Stock stock = stockService.getStockByProduitId(produitId);
		return new ResponseEntity<>(stockMapper.toResponseDTO(stock), HttpStatus.OK);
	}

	/**
	 * PATCH /api/v1/stocks/{id}/quantite
	 * Met à jour la quantité d'un stock via un mouvement
	 * @param id L'identifiant unique du stock
	 * @param dto Données du mouvement (delta, typeMouvement, utilisateurId, commentaire optionnel)
	 * @return Le stock mis à jour
	 */
	@PatchMapping("/{id}/quantite")
	@Operation(
		summary = "PATCH: Mettre à jour la quantité de stock",
		description = "Met à jour la quantité d'un stock via un mouvement (INITIAL/ENTREE/SORTIE/REAJUSTEMENT)"
	)
	@ApiResponses({
			@ApiResponse(
				responseCode = "200",
				description = "Stock mis à jour avec succès",
				content = @Content(schema = @Schema(implementation = StockResponseDTO.class))
			),
			@ApiResponse(responseCode = "400", description = "Données invalides (delta négatif, type mouvement invalide)"),
			@ApiResponse(responseCode = "404", description = "Stock ou utilisateur non trouvé"),
			@ApiResponse(responseCode = "401", description = "Authentification requise"),
			@ApiResponse(responseCode = "409", description = "Quantité insuffisante pour cette sortie"),
			@ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
	})
	public ResponseEntity<StockResponseDTO> patchQuantite(
			@Parameter(description = "Identifiant unique du stock", example = "1")
			@PathVariable Long id,
			@Valid @RequestBody StockPatchQuantityRequestDTO dto) {

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(dto.getUtilisateurId());

		Stock stock = stockService.patchStockQuantite(
				id,
				dto.getDelta(),
				dto.getTypeMouvement(),
				utilisateur,
				dto.getCommentaire());

		return new ResponseEntity<>(
				stockMapper.toResponseDTO(stock),
				HttpStatus.OK);
	}
}
