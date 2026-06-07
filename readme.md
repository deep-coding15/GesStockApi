# GesStockApi

API REST de gestion de stock et de ventes — Spring Boot 3.2.5

![CI](https://github.com/deep-coding15/GesStockApi/actions/workflows/ci.yml/badge.svg)
![CD](https://github.com/deep-coding15/GesStockApi/actions/workflows/cd.yml/badge.svg) 

---

## Objectif

GesStockApi est un backend REST conçu pour gérer les produits, catégories, stocks, mouvements de stock, ventes et utilisateurs d'un commerce. Il sert de socle modulaire et extensible, respectant les bonnes pratiques REST, Spring Boot et TDD.

---

## Stack technique

| Technologie | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring Security | HTTP Basic Auth + RBAC |
| Spring Data JPA | — |
| H2 Database | in-memory (dev) |
| Flyway | migrations versionnées |
| springdoc-openapi | 2.5.0 (Swagger UI) |
| Maven | — |
| JUnit 5 | TDD |

---

## Modules fonctionnels

```
GesStockApi
├── catalogue
│   ├── catégories          /api/v1/categories
│   └── produits            /api/v1/produits
├── stock
│   ├── stocks              /api/v1/stocks
│   └── mouvements          /api/v1/stock-mouvements
├── vente
│   ├── ventes              /api/v1/ventes
│   └── lignes de vente     /api/v1/vente-lignes
├── security
│   ├── utilisateurs        /api/v1/users
│   └── rôles               /api/v1/roles
└── health                  /api/health
```

---

## Lancer l'application

```bash
./mvnw spring-boot:run
```

L'API est disponible sur : `http://localhost:8088`

---

## Swagger UI

| URL | Description |
|---|---|
| `http://localhost:8088/swagger-ui/index.html` | Interface Swagger |
| `http://localhost:8088/v3/api-docs` | Spec OpenAPI JSON |

### S'authentifier sur Swagger

Cliquer sur le bouton **Authorize** en haut de la page et renseigner :

| Champ | Valeur |
|---|---|
| Username | `admin1` |
| Password | `pass1234` |

---

## Sécurité

L'API utilise **Spring Security avec HTTP Basic Authentication**.

### Rôles et accès

| Rôle | Endpoints accessibles |
|---|---|
| `ADMIN` | Tout (users, rôles, catalogue, stock, ventes) |
| `GERANT` | Catalogue, stock, ventes |
| `CAISSIER` | Ventes uniquement |

### Comptes de développement (H2 in-memory)

| Username | Rôle | Mot de passe |
|---|---|---|
| `admin1` | ADMIN | `pass1234` |
| `gerant1` | GERANT | `pass1234` |
| `caissier1` | CAISSIER | `pass1234` |

> Ces comptes sont insérés par Flyway au démarrage (`V9__init_data.sql`). Les mots de passe sont hashés en BCrypt.

---

## Base de données H2

La base est **in-memory** : elle est recréée à chaque démarrage.

### Console web

`http://localhost:8088/h2-console`

| Champ | Valeur |
|---|---|
| JDBC URL | `jdbc:h2:mem:gesstockdb` |
| Username | `sa` |
| Password | *(vide)* |

### Connexion via DBeaver

Un serveur TCP H2 est démarré sur le port `9092` pour les outils externes.

| Champ | Valeur |
|---|---|
| JDBC URL | `jdbc:h2:tcp://localhost:9092/mem:gesstockdb` |
| Username | `sa` |
| Password | *(vide)* |

> DBeaver doit se connecter **après** le démarrage de l'application (base in-memory).

---

## Gestion du stock

Chaque modification de stock :
- met à jour la quantité courante
- génère automatiquement un mouvement de stock tracé (type, quantité, utilisateur, date)

---

## Tests

- Tests unitaires JUnit 5 (TDD)
- Collection Postman disponible pour tous les endpoints CRUD

---

## CI / CD

- **CI** : build + tests Maven à chaque push
- **CD** : déploiement Docker automatisé

---

## Auteur

**Lydivine Merveille Magne Tsafack** — Étudiante en 4e année Génie Informatique

- Email : tsafackmerveillem@gmail.com
- LinkedIn : [lydivine-merveille-magne-tsafack](https://www.linkedin.com/in/lydivine-merveille-magne-tsafack)
- GitHub : [deep-coding15](https://github.com/deep-coding15)
