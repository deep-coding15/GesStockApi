CREATE TABLE stock (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    produit_id BIGINT NOT NULL,
    quantite_disponible INT DEFAULT 0,
    quantite_reservee INT DEFAULT 0,
    quantite_totale INT GENERATED ALWAYS AS (quantite_disponible + quantite_reservee),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produit_id) REFERENCES produit(id)
);
