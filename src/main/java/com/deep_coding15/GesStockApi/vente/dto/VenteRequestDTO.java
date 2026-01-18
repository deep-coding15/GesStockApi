package com.deep_coding15.GesStockApi.vente.dto;

import java.util.List;

public class VenteRequestDTO {
    public Long utilisateurId;
    public Long statutId;
    public List<VenteLigneRequestDTO> lignevente;
    /* public String reference;
    public double total; */
}
