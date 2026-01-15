package com.deep_coding15.GesStockApi.vente.dto;

import java.util.List;

public class VenteResponseDTO {
    public Long id;
    public String reference;
    public String dateVente;
    public String utilisateur;
    public String statut;
    public double total;
    public List<VenteLigneResponseDTO> ligneVentes;
}
