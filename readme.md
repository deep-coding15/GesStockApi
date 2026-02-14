<<<<<<< Updated upstream
GesStockApi
API REST de gestion de commerce (vente et stock) - Spring Boot 3.2.5
=======
# 📦 GesStockApi – API REST de gestion de stock et de vente

GesStockApi est une **API REST backend** développée avec **Spring Boot 3**, conçue pour gérer les **produits**, **catégories**, **stocks**, **mouvements de stock** et **utilisateurs** d’un système de commerce.

---

# 🎯 Objectif du projet
Ce projet a été conçu comme :
- un socle backend propre
- un support d’apprentissage Spring Boot
- une base réutilisable pour un système de gestion de stock d'un commerce plus avancé
- Proposer un **MVP propre, modulaire et extensible**, respectant les bonnes pratiques REST et Spring et TDD.

---

## 🧠 Ce que démontre ce projet

- Conception d’une **API REST professionnelle**
- Architecture modulaire et maintenable
- Utilisation correcte de **Spring Data JPA**
- Gestion métier du **stock avec traçabilité** (mouvements de stock)
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

---

## CI & CD
![CI](https://github.com/deep-coding15/GesStockApi/actions/workflows/ci.yml/badge.svg)

---

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
```scss
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
```
---
>>>>>>> Stashed changes

Base URL: http://localhost:8089﻿

Modules
Catalogue: Gestion des catégories et produits
Stock: Gestion des stocks et mouvements de stock
Security: Gestion des utilisateurs et rôles
Health: Endpoints de santé de l'API
﻿

Health
Endpoints de santé et vérification de l'API

﻿

GET
Home
http://localhost:8089/
Add request description…

﻿

GET
Health Check
http://localhost:8089/api/health
Add request description…

﻿

GET
Ping
http://localhost:8089/ping
Add request description…

﻿

Categories
Gestion des catégories de produits

Base path: /api/v1/categories

﻿

POST
Create Category
http://localhost:8089/api/v1/categories/
Add request description…

﻿

Body
raw (json)
View More
json
{
  "code": "CAT001",
  "libelle": "Électronique",
  "description": "Produits électroniques et accessoires"
}
GET
Get All Categories
http://localhost:8089/api/v1/categories/
Add request description…

﻿

GET
Get Categories With Products
http://localhost:8089/api/v1/categories/produits
Add request description…

﻿

GET
Get Category by ID
http://localhost:8089/api/v1/categories/:id
Add request description…

﻿

Path Variables
id
GET
Get Category by Code
http://localhost:8089/api/v1/categories/code/:code
Add request description…

﻿

Path Variables
code
GET
Get Category by Libelle
http://localhost:8089/api/v1/categories/libelle/:libelle
Add request description…

﻿

Path Variables
libelle
PUT
Update Category (PUT)
http://localhost:8089/api/v1/categories/:id
Add request description…

﻿

Path Variables
id
Body
raw (json)
json
{
  "code": "CAT001",
  "libelle": "Électronique Mise à jour",
  "description": "Description mise à jour",
  "actif": true
}
PATCH
Partial Update Category (PATCH)
http://localhost:8089/api/v1/categories/:id
Add request description…

﻿

Path Variables
id
Body
raw (json)
json
{
  "libelle": "Nouveau libellé",
  "actif": true
}
DELETE
Delete Category
http://localhost:8089/api/v1/categories/:id
Add request description…

﻿

Path Variables
id
Products
Gestion des produits

Base path: /api/v1/produits

﻿

POST
Create Product
http://localhost:8089/api/v1/produits/
Add request description…

﻿

Body
raw (json)
View More
json
{
  "nom": "Smartphone XYZ",
  "description": "Smartphone dernière génération",
  "prix": 599.99,
  "categorieId": 1
}
GET
Get All Products
http://localhost:8089/api/v1/produits/
Add request description…

﻿

GET
Get Product by ID
http://localhost:8089/api/v1/produits/:id
Add request description…

﻿

Path Variables
id
GET
Get Product by Reference
http://localhost:8089/api/v1/produits/reference/:reference
Add request description…

﻿

Path Variables
reference
GET
Get Product by Name
http://localhost:8089/api/v1/produits/nom/:nom
Add request description…

﻿

Path Variables
nom
GET
Get Product by Description
http://localhost:8089/api/v1/produits/description/:description
Add request description…

﻿

Path Variables
description
GET
Get Products by Category
http://localhost:8089/api/v1/produits/categorie/:categorieId
Add request description…

﻿

Path Variables
categorieId
PUT
Update Product (PUT)
http://localhost:8089/api/v1/produits/:id
Add request description…

﻿

Path Variables
id
Body
raw (json)
View More
json
{
  "id": 1,
  "nom": "Smartphone XYZ Pro",
  "reference": "REF-001",
  "description": "Smartphone Pro dernière génération",
  "prix": 799.99,
  "categorieId": 1
}
PATCH
Partial Update Product (PATCH)
http://localhost:8089/api/v1/produits/:id
Add request description…

﻿

Path Variables
id
Body
raw (json)
json
{
  "id": 1,
  "prix": 549.99
}
DELETE
Delete Product
http://localhost:8089/api/v1/produits/:id
Add request description…

﻿

Path Variables
id
Stock
Gestion des stocks

Base path: /api/v1/stocks

﻿

POST
Create Stock
http://localhost:8089/api/v1/stocks
Add request description…

﻿

Body
raw (json)
json
{
  "produitId": 1,
  "quantiteInitiale": 100,
  "utilisateurId": 1
}
GET
Get All Stocks
http://localhost:8089/api/v1/stocks
Add request description…

﻿

GET
Get Stock by ID
http://localhost:8089/api/v1/stocks/id/:id
Add request description…

﻿

Path Variables
id
GET
Get Stock by Product ID
http://localhost:8089/api/v1/stocks/produit/:produitId
Add request description…

﻿

Path Variables
produitId
PATCH
Update Stock Quantity (PATCH)
http://localhost:8089/api/v1/stocks/:id/quantite
Add request description…

﻿

Path Variables
id
Body
raw (json)
json
{
  "delta": 10,
  "typeMouvement": "ENTREE",
  "commentaire": "Réapprovisionnement",
  "utilisateurId": 1
}
Stock Movements
Consultation des mouvements de stock

<<<<<<< Updated upstream
Base path: /api/v1/stock-mouvements

﻿

GET
Get Movements by Stock ID
http://localhost:8089/api/v1/stock-mouvements/stock/:stockId
Add request description…
=======
---

## 🧪 Tests
- Collection Postman complète
- Tous les endpoints CRUD testables
- Variables d’environnement (baseUrl)

---

## 📈 Évolutions prévues
* Authentification JWT / Spring Security
* Base de données MySQL / PostgreSQL
* Gestion des ventes et facturation
* Pagination, tri, filtres
* Dockerisation

---

# Diagramme d’architecture 
>>>>>>> Stashed changes

﻿

<<<<<<< Updated upstream
Path Variables
stockId
GET
Get Movements by Product ID
http://localhost:8089/api/v1/stock-mouvements/produit/:produitId
Add request description…
=======
## Focus module stock
```scss
[Stock]
│
├── quantite
├── produitId
│
└── 'Mouvements de stock'
├── type (ENTREE | SORTIE)
├── quantite
├── utilisateurId
└── date
```
>>>>>>> Stashed changes

﻿

Path Variables
produitId
GET
Get Movements by User ID
http://localhost:8089/api/v1/stock-mouvements/utilisateur/:utilisateurId
Add request description…

﻿

Path Variables
utilisateurId
Roles
Gestion des rôles utilisateurs

Base path: /api/v1/roles

﻿

POST
Create Role
http://localhost:8089/api/v1/roles/
Add request description…

﻿

Body
raw (json)
json
{
  "code": "ADMIN",
  "libelle": "Administrateur"
}
GET
Get All Roles
http://localhost:8089/api/v1/roles/
Add request description…

﻿

GET
Get Role by ID
http://localhost:8089/api/v1/roles/:id
Add request description…

﻿

Path Variables
id
GET
Get Role by Code
http://localhost:8089/api/v1/roles/code/:code
Add request description…

﻿

Path Variables
code
PUT
Update Role (PUT)
http://localhost:8089/api/v1/roles/:id
Add request description…

﻿

Path Variables
id
Body
raw (json)
json
{
  "code": "ADMIN",
  "libelle": "Super Administrateur"
}
PATCH
Partial Update Role (PATCH)
http://localhost:8089/api/v1/roles/:id
Add request description…

<<<<<<< Updated upstream
﻿
=======
---
>>>>>>> Stashed changes

Path Variables
id
Body
raw (json)
json
{
  "libelle": "Nouveau libellé"
}
DELETE
Delete Role
http://localhost:8089/api/v1/roles/:id
Add request description…

<<<<<<< Updated upstream
﻿

Path Variables
id
Users
Gestion des utilisateurs

Base path: /api/v1/users

﻿

POST
Create User
http://localhost:8089/api/v1/users
Add request description…
=======
- Lydivine Merveille Magne Tsafack
- Étudiante en 4e en Génie Informatique
- Projet personnel – API REST Java Spring Boot
- [Mon Email](tsafackmerveillem@gmail.com)
- [Mon LinkedIn](https://www.linkedin.com/in/lydivine-merveille-magne-tsafack)
- [Mon GitHub](https://github.com/deep-coding15)

---

# Swagger
- The Swagger UI page will then be available at http://localhost:8088/swagger-ui/index.html#/ 
- And the OpenAPI description will be available at the following url for json format: http://localhost:8088/v3/api-docs
>>>>>>> Stashed changes

﻿

<<<<<<< Updated upstream
Body
raw (json)
json
{
  "email": "user@example.com",
  "username": "johndoe",
  "password": "securePassword123",
  "roleId": 1
}
GET
Get All Users
http://localhost:8089/api/v1/users/
Add request description…
=======
---

# Lancer l'application et les tests
## Lancer l'application :
./mvnw spring-boot:run
>>>>>>> Stashed changes

﻿

GET
Get User by ID
http://localhost:8089/api/v1/users/:id
Add request description…

﻿

Path Variables
id
GET
Get User by Username
http://localhost:8089/api/v1/users/name/:username
Add request description…

﻿

Path Variables
username