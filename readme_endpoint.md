# 📌 GesStockApi — Liste complète des Endpoints REST

Base URL : `http://localhost:8089/api/v1`

---

## 🩺 Health Check

| Méthode | Endpoint  | Description             |
| ------- | --------- | ----------------------- |
| GET     | `/health` | Vérifie l’état de l’API |

---

## 👥 Utilisateurs

| Méthode | Endpoint                     | Description                     |
| ------- | ---------------------------- | ------------------------------- |
| POST    | `/users/`                    | Créer un utilisateur            |
| GET     | `/users/`                    | Lister tous les utilisateurs    |
| GET     | `/users/{id}`                | Récupérer un utilisateur par ID |
| GET     | `/users/email/{email}`       | Rechercher par email            |
| GET     | `/users/username/{username}` | Rechercher par username         |
| PUT     | `/users/{id}`                | Mettre à jour un utilisateur    |
| DELETE  | `/users/{id}`                | Supprimer un utilisateur        |

---

## 🔐 Rôles

| Méthode | Endpoint      | Description           |
| ------- | ------------- | --------------------- |
| POST    | `/roles/`     | Créer un rôle         |
| GET     | `/roles/`     | Lister les rôles      |
| GET     | `/roles/{id}` | Récupérer un rôle     |
| PUT     | `/roles/{id}` | Mettre à jour un rôle |
| DELETE  | `/roles/{id}` | Supprimer un rôle     |

---

## 📂 Catégories

| Méthode | Endpoint                        | Description                    |
| ------- | ------------------------------- | ------------------------------ |
| POST    | `/categories/`                  | Créer une catégorie            |
| GET     | `/categories/`                  | Lister les catégories          |
| GET     | `/categories/{id}`              | Récupérer une catégorie par ID |
| GET     | `/categories/code/{code}`       | Rechercher par code            |
| GET     | `/categories/libelle/{libelle}` | Rechercher par libellé         |
| PUT     | `/categories/{id}`              | Mettre à jour une catégorie    |
| DELETE  | `/categories/{id}`              | Supprimer une catégorie        |

---

## 🛒 Produits

| Méthode | Endpoint                            | Description                 |
| ------- | ----------------------------------- | --------------------------- |
| POST    | `/produits/`                        | Créer un produit            |
| GET     | `/produits/`                        | Lister les produits         |
| GET     | `/produits/{id}`                    | Récupérer un produit par ID |
| GET     | `/produits/reference/{reference}`   | Rechercher par référence    |
| GET     | `/produits/code/{code}`             | Rechercher par code         |
| GET     | `/produits/libelle/{libelle}`       | Rechercher par libellé      |
| GET     | `/produits/categorie/{categorieId}` | Produits par catégorie      |
| PUT     | `/produits/{id}`                    | Mettre à jour un produit    |
| DELETE  | `/produits/{id}`                    | Supprimer un produit        |

---

## 📦 Stocks

| Méthode | Endpoint                      | Description                        |
| ------- | ----------------------------- | ---------------------------------- |
| POST    | `/stocks/`                    | Créer un stock pour un produit     |
| GET     | `/stocks/`                    | Lister les stocks                  |
| GET     | `/stocks/{id}`                | Récupérer un stock                 |
| GET     | `/stocks/produit/{produitId}` | Stock par produit                  |
| PATCH   | `/stocks/{id}/quantite`       | Modifier la quantité via mouvement |
| DELETE  | `/stocks/{id}`                | Supprimer un stock                 |

---

## 🔄 Mouvements de Stock

| Méthode | Endpoint                           | Description                |
| ------- | ---------------------------------- | -------------------------- |
| POST    | `/mouvements/`                     | Créer un mouvement         |
| GET     | `/mouvements/`                     | Lister tous les mouvements |
| GET     | `/mouvements/{id}`                 | Récupérer un mouvement     |
| GET     | `/mouvements/stock/{stockId}`      | Mouvements par stock       |
| GET     | `/mouvements/produit/{produitId}`  | Mouvements par produit     |
| GET     | `/mouvements/utilisateur/{userId}` | Mouvements par utilisateur |

---

## ⚠️ Gestion des erreurs (globale)

* `400 BAD_REQUEST` → Données invalides
* `404 NOT_FOUND` → Ressource inexistante
* `409 CONFLICT` → Contrainte métier violée
* `500 INTERNAL_SERVER_ERROR` → Erreur interne

---

## 🧪 Tests

* Tous les endpoints sont testés via **Postman**
* Couverture unitaire assurée avec **JUnit 5**
* Approche **TDD** sur la logique métier critique
