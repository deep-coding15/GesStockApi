CREATE TABLE vente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    utilisateur_id BIGINT,
    client_id BIGINT,
    reference_vente VARCHAR(50) UNIQUE,
    date_vente TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    montant_ht DECIMAL(10,2),
    montant_tva DECIMAL(10,2),
    montant_total_ttc DECIMAL(10,2),
    montant_remise DECIMAL(10,2),
    statut VARCHAR(20) DEFAULT 'brouillon',
    mode_paiement VARCHAR(50),
    commentaire TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);
