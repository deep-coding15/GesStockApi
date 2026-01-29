CREATE TABLE ligne_vente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vente_id BIGINT NOT NULL,
    produit_id BIGINT,
    quantite INT,
    prix_unitaire_ht DECIMAL(10,2),
    taux_tva DECIMAL(5,2),
    montant_tva DECIMAL(10,2),
    montant_ligne_ht DECIMAL(10,2),
    montant_ligne_ttc DECIMAL(10,2),
    remise_pourcentage DECIMAL(5,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIG1N KEY (vente_id) REFERENCES vente(id),
    FOREIGN KEY (produit_id) REFERENCES produit(id)
);
