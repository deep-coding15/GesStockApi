// ===============================
// DTOs ALIGNÉS AVEC L’API CONTRACT
// ===============================

// -------- ROLE --------
public class RoleRequestDTO {
    public String nom;
}

public class RoleResponseDTO {
    public Long id;
    public String nom;
}

// -------- UTILISATEUR --------
public class UtilisateurRequestDTO {
    public String username;
    public String password;
    public Long roleId;
}

public class UtilisateurResponseDTO {
    public Long id;
    public String username;
    public String role;
    public boolean actif = true;
}

// -------- CATEGORIE --------

public class CategorieRequestDTO {
    public String Code;
    public String Libelle;
    public String Description;
}

public class CategorieResponseDTO {
    public Long id;
    public String Code;
    public String Libelle;
    public String Description;
    public boolean actif;
}

// -------- PRODUIT --------
public class ProduitRequestDTO {
    public String reference;
    public String designation;
    public double prix;
    public Long categorieId;
}

public class ProduitResponseDTO {
    public Long id;
    public String reference;
    public String designation;
    public double prix;
    public String categorie;
}

// -------- STOCK --------
public class StockRequestDTO {
    public Long produitId;
    public int quantite;
}

public class StockResponseDTO {
    public Long produitId;
    public int quantite;
}

// -------- MOUVEMENT DE STOCK --------
public class StockMouvementRequestDTO {
    public Long produitId;
    public String type; // ENTREE : SORTIE : REAJUSTEMENT
    public int quantite;
    public Long utilisateurId;
    public String commentaire;
}

public class MouvementStockResponseDTO {
    public Long id;
    public int produitId;
    public int quantite;
    public String type;
    public Long utilisateurId;
    public String dateMouvement;
}

// -------- STATUT VENTE --------
public class StatutVenteRequestDTO {
    public String code;
    public String libelle;
}

public class StatutVenteResponseDTO {
    public Long id;
    public String code;
    public String libelle;
}

// -------- VENTE --------
public class VenteLigneRequestDTO {
    public Long produitId;
    public int quantite;
}

public class VenteRequestDTO {
    public Long utilisateurId;
    public Long statutId;
    public java.util.List<VenteLigneRequestDTO> lignes;
}

public class VenteLigneResponseDTO {
    public String produit;
    public int quantite;
    public double prixUnitaire;
}

public class VenteResponseDTO {
    public Long id;
    public String dateVente;
    public String utilisateur;
    public String statut;
    public double total;
    public java.util.List<VenteLigneResponseDTO> lignes;
}


- DTO Request : ce que l’API accepte
- DTO Response : ce que l’API retourne
- Aucun couplage direct avec les entités JPA

Structure prête pour :
- validation (@NotNull, @NotBlank, etc.)
- mapping (MapStruct ou manuel)
- évolution sans casser l’API