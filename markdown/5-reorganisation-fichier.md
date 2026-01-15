📌 Un domaine = un package racine (catalogue)
📌 À l’intérieur, tu sépares par responsabilité

## 1️⃣ Structure finale recommandée (MVP + évolutions futures)
com.deep_coding15.GesStockApi
│
├── GesStockApiApplication.java
│
├── config
│   ├── OpenApiConfig.java
│   ├── SecurityConfig.java
│   └── JpaConfig.java
│
├── common
│   ├── entity
│   │   ├── BaseEntity.java
│   │   └── AuditableEntity.java
│   │
│   ├── exception
│   │   ├── BusinessException.java
│   │   ├── NotFoundException.java
│   │   └── GlobalExceptionHandler.java
│   │
│   ├── util
│   │   ├── DateUtils.java
│   │   └── Constants.java
│   │
│   └── mapper
│       └── BaseMapper.java
│
├── catalogue
│   ├── entity
│   │   ├── Produit.java
│   │   └── Categorie.java
│   │
│   ├── repository
│   │   ├── ProduitRepository.java
│   │   └── CategorieRepository.java
│   │
│   ├── service
│   │   ├── ProduitService.java
│   │   └── CategorieService.java
│   │
│   ├── web
│   │   ├── ProduitController.java
│   │   └── CategorieController.java
│   │
│   ├── dto
│   │   ├── ProduitRequest.java
│   │   └── ProduitResponse.java
│   │
│   └── mapper
│       └── ProduitMapper.java
│
├── stock
│   ├── entity
│   │   └── Stock.java
│   │
│   ├── repository
│   │   └── StockRepository.java
│   │
│   ├── service
│   │   └── StockService.java
│   │
│   └── web
│       └── StockController.java
│
├── commande
│   ├── entity
│   │   ├── Commande.java
│   │   └── LigneCommande.java
│   │
│   ├── repository
│   │   ├── CommandeRepository.java
│   │   └── LigneCommandeRepository.java
│   │
│   ├── service
│   │   └── CommandeService.java
│   │
│   └── web
│       └── CommandeController.java
│
├── security
│   ├── entity
│   │   └── User.java
│   │
│   ├── repository
│   │   └── UserRepository.java
│   │
│   ├── service
│   │   └── UserService.java
│   │
│   └── web
│       └── AuthController.java
│
└── infrastructure
    ├── flyway
    │   └── V1__init_schema.sql
    │
    └── datasource
        └── DataSourceConfig.java

## 2️⃣ Ressources (src/main/resources)
src/main/resources
│
├── application.yml
├── application-dev.yml
├── application-prod.yml
│
├── db
│   └── migration
│       ├── V1__init_schema.sql
│       ├── V2__catalogue_tables.sql
│       └── V3__stock_tables.sql
│
└── static
📌 Flyway scanne automatiquement db/migration

## 3️⃣ Rôle de chaque couche (règle d’or)
# 🧱 entity
Modèle métier + mapping JPA
Aucune logique métier
Annotations @Entity, @ManyToOne, etc.

# 🗃 repository
Accès aux données
Hérite de JpaRepository
Aucune logique métier

# 🧠 service
Logique métier
Transactions
Orchestration des repositories

# 🌐 web (controller)
Exposition REST
Validation des entrées (@Valid)
Ne parle jamais directement aux entités (DTO)

# 📦 dto
Objets d’échange API
Sécurité + clarté
Pas d’annotations JPA

# 🔁 mapper
Conversion Entity ⇄ DTO
MapStruct ou manuel

# ⚙ config
Configuration technique
OpenAPI, sécurité, JPA

# 🚨 exception
Gestion globale des erreurs
@ControllerAdvice

## 4️⃣ Règles strictes à respecter
❌ À ne jamais faire : 
- Controller → Repository direct
- Entity exposée dans l’API
- Logique métier dans Controller
- SQL dans Service

✅ À toujours faire : 
- Controller → Service → Repository
- DTO dans l’API
- Flyway pour le schéma
- Validation avec @Valid

## 5️⃣ Pourquoi cette structure est “senior-level” ?
- Scalabilité
- Lisibilité
- Testabilité
- Compatible microservices
- Compatible DDD light

👉 Tu peux ajouter des modules sans casser l’existant.