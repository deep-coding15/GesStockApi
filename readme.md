# 📦 GesStockApi – API REST de gestion de stock et de vente

GesStockApi est une **API REST backend** développée avec **Spring Boot 3**, conçue pour gérer les **produits**, **catégories**, **stocks**, **mouvements de stock** et **utilisateurs** d’un système de commerce.

🎯 Objectif : proposer un **MVP propre, modulaire et extensible**, respectant les bonnes pratiques REST et Spring.

---

## 🧠 Ce que démontre ce projet

- Conception d’une **API REST professionnelle**
- Architecture modulaire et maintenable
- Utilisation correcte de **Spring Data JPA**
- Gestion métier du **stock avec traçabilité**
- Validation, exceptions métier et statuts HTTP
- Tests complets via **Postman**

---

## 🛠️ Stack technique

- Java 17  
- Spring Boot 3.2.5  
- Spring Web (REST)  
- Spring Data JPA  
- H2 Database (in-memory)  
- Maven  
- JUnit 5
- Postman (tests des endpoints)

## 🧩 Modules fonctionnels

- **Catalogue**
  - Catégories
  - Produits
- **Stock**
  - Stock par produit
  - Mouvements (entrée / sortie)
- **Sécurité (MVP)**
  - Utilisateurs
  - Rôles
- **Health**
  - Monitoring API
  
---

## 🔄 Gestion intelligente du stock

Chaque modification de stock :
- met à jour la quantité courante
- génère automatiquement un **mouvement de stock**
- est traçable par **produit**, **stock** et **utilisateur**

➡️ Approche orientée **métier réel**

---


## 🧩 Architecture & Modules
GesStockApi
├── catalogue
│   ├── categories
│   └── produits
├── stock
│   ├── stocks
│   └── mouvements de stock
├── security
│   ├── utilisateurs
│   └── rôles
├── common
│   ├── exceptions
│   └── utils
└── health

## 🌐 Exemple d’endpoint

```http
PATCH /api/v1/stocks/{id}
```
```json
{
  "delta": 10,
  "typeMouvement": "ENTREE",
  "commentaire": "Réapprovisionnement",
  "utilisateurId": 1
}
```

## 🧪 Tests
- Collection Postman complète
- Tous les endpoints CRUD testables
- Variables d’environnement (baseUrl)

## 📈 Évolutions prévues
* Authentification JWT / Spring Security
* Base de données MySQL / PostgreSQL
* Gestion des ventes et facturation
* Pagination, tri, filtres
* Dockerisation

# 3️⃣ Diagramme d’architecture (clair & pédagogique)

### 📐 Vue globale (ASCII – parfaite pour README)
```scss
             ┌───────────────┐
             │   Client API  │
             │ (Postman / UI)│
             └───────┬───────┘
                     │ HTTP (REST)
                     ▼
          ┌─────────────────────────┐
          │      Controllers        │
          │  (REST Endpoints)       │
          └─────────┬───────────────┘
                    ▼
          ┌─────────────────────────┐
          │        Services          │
          │  - Logique métier        │
          │  - Validation            │
          │  - Transactions          │
          └─────────┬───────────────┘
                    ▼
          ┌─────────────────────────┐
          │      Repositories        │
          │   (Spring Data JPA)      │
          └─────────┬───────────────┘
                    ▼
          ┌─────────────────────────┐
          │        Database          │
          │        H2 (MVP)          │
          └─────────────────────────┘
```

### Focus module stock
```scss
[Stock]
│
├── quantite
├── produitId
│
└── [Mouvements de stock]
├── type (ENTREE | SORTIE)
├── quantite
├── utilisateurId
└── date
```

🔑 Fonctionnalités principales
📂 Catalogue

CRUD Catégories

CRUD Produits

Recherche par :

ID

Code

Libellé

Référence

Catégorie

📦 Stock

Création d’un stock par produit

Consultation du stock

Mise à jour de la quantité via mouvements de stock

Historique des mouvements (entrée / sortie)

🔄 Mouvements de stock

Consultation par :

Stock

Produit

Utilisateur

Traçabilité complète des opérations

👥 Sécurité (basique – MVP)

Gestion des rôles

Gestion des utilisateurs

Association utilisateur ↔ rôle

🩺 Health

Vérification de l’état de l’API

Endpoints de diagnostic simples

🌐 Base URL
http://localhost:8089

📌 Endpoints principaux
Health
GET  /ping
GET  /api/health

Catégories
POST   /api/v1/categories/
GET    /api/v1/categories/
GET    /api/v1/categories/{id}
GET    /api/v1/categories/code/{code}
PUT    /api/v1/categories/{id}
PATCH  /api/v1/categories/{id}
DELETE /api/v1/categories/{id}

Produits
POST   /api/v1/produits/
GET    /api/v1/produits/
GET    /api/v1/produits/{id}
GET    /api/v1/produits/reference/{reference}
GET    /api/v1/produits/categorie/{categorieId}
PUT    /api/v1/produits/{id}
PATCH  /api/v1/produits/{id}
DELETE /api/v1/produits/{id}

Stocks
POST  /api/v1/stocks/
GET   /api/v1/stocks/
GET   /api/v1/stocks/{id}
GET   /api/v1/stocks/produit/{produitId}
PATCH /api/v1/stocks/{id}/quantite

Mouvements de stock
GET /api/v1/stock-mouvements/stock/{stockId}
GET /api/v1/stock-mouvements/produit/{produitId}
GET /api/v1/stock-mouvements/utilisateur/{utilisateurId}

Utilisateurs
POST /api/v1/users/
GET  /api/v1/users/
GET  /api/v1/users/{id}
GET  /api/v1/users/name/{username}

Rôles
POST   /api/v1/roles/
GET    /api/v1/roles/
GET    /api/v1/roles/{id}
GET    /api/v1/roles/code/{code}
PUT    /api/v1/roles/{id}
PATCH  /api/v1/roles/{id}
DELETE /api/v1/roles/{id}

🧪 Tests API

Une collection Postman complète est fournie (export JSON)

Tous les endpoints CRUD sont testables directement

Utilisation de variables ({{baseUrl}})

🗄️ Base de données

H2 in-memory

Données réinitialisées à chaque redémarrage

Idéal pour :

MVP

Tests

Démonstration

⚠️ Accès H2 Console possible si activé dans application.yml

📈 État du projet

✅ MVP fonctionnel
✅ Architecture propre et modulaire
✅ Bonnes pratiques REST
⏳ Sécurité avancée (JWT, auth) à venir
⏳ Migration vers base persistante (MySQL/PostgreSQL) à venir

🎯 Objectif du projet

Ce projet a été conçu comme :

- un socle backend propre

- un support d’apprentissage Spring Boot

- une base réutilisable pour un système de gestion de commerce plus avancé

👩‍💻 Auteur

Lydivine Merveille Magne Tsafack
Étudiante en 4e en Génie Informatique
Projet académique – API REST Java Spring Boot

The Swagger UI page will then be available at http://server:port/context-path/swagger-ui.html and the OpenAPI description will be available at the following url for json format: http://server:port/context-path/v3/api-docs

server: The server name or IP

port: The server port

context-path: The context path of the application

Documentation will be available in yaml format as well, on the following path : /v3/api-docs.yaml

