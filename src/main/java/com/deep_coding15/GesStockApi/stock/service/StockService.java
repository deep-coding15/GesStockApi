package com.deep_coding15.GesStockApi.stock.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deep_coding15.GesStockApi.catalogue.entity.Produit;
import com.deep_coding15.GesStockApi.catalogue.repository.ProduitRepository;
import com.deep_coding15.GesStockApi.common.Exception.EntityIllegalArgumentException;
import com.deep_coding15.GesStockApi.common.Exception.EntityNotFoundException;
import com.deep_coding15.GesStockApi.common.utils.Utils;
import com.deep_coding15.GesStockApi.stock.entity.Stock;
import com.deep_coding15.GesStockApi.stock.repository.StockRepository;

@Service
public class StockService {

    private StockRepository stockRepository;
    private ProduitRepository produitRepository;

    public StockService(
            StockRepository stockRepository,
            ProduitRepository produitRepository) {
        this.stockRepository = stockRepository;
        this.produitRepository = produitRepository;
    }

    public void createStock(Long produitId, Integer quantite) {

        if (Utils.isNegativeOrNull(produitId) || Utils.isNegativeOrNull(quantite)) {
            throw new EntityIllegalArgumentException(
                    "Stock", "produitId, quantite",
                    produitId.toString().concat(" ").concat(quantite.toString()));
        }

        Optional<Produit> produitOptionnel = produitRepository.findById(produitId);

        if (!produitOptionnel.isPresent()) {
            throw new EntityNotFoundException(
                    "Produit", "id",
                    produitId.toString());
        }

        Optional<Stock> stock = stockRepository.findByProduitId(produitId);
        
        Stock stockSave = null;
        if (!stock.isPresent()) {
            stockSave = new Stock();
            stockSave.setProduit(produitOptionnel.get());
            stockSave.setQuantite(quantite);
        }
        else{
            stockSave = stock.get();
            stockSave.setQuantite(stockSave.getQuantite() + quantite);
        }

        this.stockRepository.save(stockSave);
    }

    public Stock getStockByProduitId(Long produitId) {
        if (Utils.isNegativeOrNull(produitId)) {
            throw new EntityIllegalArgumentException(
                    "Stock", "produitId",
                    produitId.toString());
        }

        Optional<Stock> stock = stockRepository.findByProduitId(produitId);

        if(!stock.isPresent())
            throw new EntityNotFoundException(
        "Stock", "produitId", 
            produitId.toString());
        
        return stock.get();
    }
}
