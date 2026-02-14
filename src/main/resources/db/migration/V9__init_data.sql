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
VALUES ('admin1', 'admin@example.com', '$2a$12$.8eUazSe8iLHTtG6IyN7t.sy/11SWO34IR1Nbeddy/5XYGGS55Yfm', '0600000001', true, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO utilisateur (username, email, mot_de_passe, telephone, actif, role_id, created_at, updated_at)
VALUES ('caissier1', 'caissier1@example.com', '$2a$12$JLkVPiOJ7WFRDSMjoZv4s.qzmGq0avDjLVQBQkvvRayTx2OxFzmaq', '0600000002', true, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO utilisateur (username, email, mot_de_passe, telephone, actif, role_id, created_at, updated_at)
VALUES ('gerant1', 'gerant1@example.com', '$2a$12$OZH/kYktIsNot9s/JadYv.dUrsbDMc4rZfgeJ81BWml.obNEutjui', '0600000003', true, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================
-- CATEGORIES PRODUITS
-- =====================================
INSERT INTO categorie (code, libelle, description)
VALUES ('CAT001', 'Boissons', 'Boissons non-alcoolisées');

INSERT INTO categorie (code, libelle, description)
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

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD004', '1234567890126', 'Chocolat Noir', 'Tablette 100g', 10.00, true, 2);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD005', '1234567890127', 'Coca-Cola 330ml', 'Canette de soda', 4.50, true, 1);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD006', '1234567890128', 'Lait 1L', 'Brique de lait', 6.50, true, 1);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD007', '1234567890129', 'Chips Barbecue', 'Paquet de chips BBQ', 6.50, true, 2);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD008', '1234567890130', 'Gaufre', 'Gaufre au chocolat', 7.00, true, 2);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD009', '1234567890131', 'Thé 500ml', 'Bouteille de thé glacé', 5.50, true, 1);

INSERT INTO produit (reference, code_barre, nom, description, prix_unitaire, actif, categorie_id)
VALUES ('PROD010', '1234567890132', 'Chips Fromage', 'Paquet de chips au fromage', 6.50, true, 2);

-- =====================================
-- STOCK INITIAL
-- =====================================
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (1, 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (2, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (3, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (4, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (5, 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (6, 70, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (7, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (8, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (9, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO stock (produit_id, quantite, created_at, updated_at) VALUES (10, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================
-- MOUVEMENTS DE STOCK
-- =====================================
INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (1, 'INITIAL', 100, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (2, 'INITIAL', 80, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (3, 'INITIAL', 60, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (4, 'INITIAL', 50, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (5, 'INITIAL', 120, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (6, 'INITIAL', 70, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (7, 'INITIAL', 90, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (8, 'INITIAL', 30, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (9, 'INITIAL', 50, CURRENT_TIMESTAMP, 1, 'Stock initial');

INSERT INTO stock_mouvement (stock_id, type_mouvement, quantite, date_mouvement, utilisateur_id, commentaire)
VALUES (10, 'INITIAL', 40, CURRENT_TIMESTAMP, 1, 'Stock initial');

-- =====================================
-- VENTES DE TEST
-- =====================================
INSERT INTO vente (reference_vente, date_vente, prix_total_ht, utilisateur_id, statut_vente, created_at, updated_at)
VALUES ('VENTE001', CURRENT_TIMESTAMP, 25.50, 2, 'VALIDEE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO vente_ligne (vente_id, produit_id, quantite, prix_unitaire)
VALUES (1, 1, 2, 5.00);

INSERT INTO vente_ligne (vente_id, produit_id, quantite, prix_unitaire)
VALUES (1, 3, 1, 6.00);

INSERT INTO vente_ligne (vente_id, produit_id, quantite, prix_unitaire)
VALUES (1, 5, 2, 4.50);
