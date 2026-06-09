package com.deep_coding15.GesStockApi.vente.dto.vente;

public interface VenteDetailProjection {
    String getNom();
    String getStatutVente();
    Double getPrixTotalHt();
    Integer getQuantite();
}
