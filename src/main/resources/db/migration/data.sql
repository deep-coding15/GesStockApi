-- =====================================
-- ROLES
-- =====================================
INSERT INTO role (code, libelle) VALUES ('ADMIN', 'Administrateur');
INSERT INTO role (code, libelle) VALUES ('CAISSIER', 'Caissier');
INSERT INTO role (code, libelle) VALUES ('GERANT', 'Gérant');

-- =====================================
-- UTILISATEURS
-- =====================================
INSERT INTO utilisateur (username, email, mot_de_passe, telephone, actif, role_id, created_at, updated_at)
VALUES ('admin', 'admin@example.com', 'password', '0600000001', true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO utilisateur (username, email, mot_de_passe, telephone, actif, role_id, created_at, updated_at)
VALUES ('caissier1', 'caissier1@example.com', 'password', '0600000002', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO utilisateur (username, email, mot_de_passe, telephone, actif, role_id, created_at, updated_at)
VALUES ('gerant1', 'gerant1@example.com', 'password', '0600000003', true, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================
-- CATEGORIES PRODUITS
-- =====================================
INSERT INTO categorie_produit (code, libelle, description)
VALUES ('CAT001', 'Boissons', 'Boissons non-alcoolisées');

INSERT INTO categorie_produit (code, libelle, description)
VALUES ('CAT002', 'Snacks', 'Snacks salés et sucrés');

-- =====================================
-- PRODUITS
-- =====================================
INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD001', '1234567890123', 'Eau minérale 1L', 'Bouteille d’eau minérale', 5.00, true, 1);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD002', '1234567890124', 'Jus Orange 1L', 'Bouteille de jus d’orange', 8.50, true, 1);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD003', '1234567890125', 'Chips Nature', 'Paquet de chips', 6.00, true, 2);

-- =====================================
-- STOCK INITIAL
-- =====================================
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (1, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (2, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (3, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================
-- MOUVEMENTS DE STOCK
-- =====================================
INSERT INTO stock_mouvement (produit_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (1, 'ENTREE', 100, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (produit_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (2, 'ENTREE', 50, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (produit_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (3, 'ENTREE', 75, CURRENT_TIMESTAMP, 1, 'Stock initial');

-- =====================================
-- VENTES
-- =====================================
INSERT INTO vente (reference_vente, date_vente, prix_total_ht, utilisateur_id, statut_vente, created_at, updated_at)
VALUES ('VENTE001', CURRENT_TIMESTAMP, 19.00, 2, 'VALIDEE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================
-- LIGNES DE VENTE
-- =====================================
INSERT INTO vente_ligne (vente_id, produit_id, quantite, prix_unitaire)
VALUES (1, 1, 2, 5.00);  -- 2x Eau minérale

INSERT INTO vente_ligne (vente_id, produit_id, quantite, prix_unitaire)
VALUES (1, 3, 1, 6.00);  -- 1x Chips

-- =====================================
-- FIN DATA
-- =====================================
