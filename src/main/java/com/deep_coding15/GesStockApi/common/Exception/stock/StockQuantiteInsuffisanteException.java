package com.deep_coding15.GesStockApi.common.exception.stock;

import com.deep_coding15.GesStockApi.common.exception.BusinessException;

public class StockQuantiteInsuffisanteException extends BusinessException {

    public StockQuantiteInsuffisanteException(Integer quantiteDemande, Integer quantiteDisponible) {
        super(
                "Stock" + ", Quantité de stock insuffisant. Disponible : " + quantiteDisponible + " , Quantité Demandé : " + quantiteDemande,
                "STOCK_QUANTITE_INSUFFISANT_EXCEPTION");
    }
}
