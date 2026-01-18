1️⃣ Diagramme de dépendances du projet

Voici une version textuelle/ASCII de la structure de dépendances, pour visualiser les relations entre packages et couches :

com.deep_coding15.GesStockApi
│
├── config
│    └── (Configuration technique, utilisée par tous les packages)
│
├── common
│    ├── entity          <-- BaseEntity, AuditableEntity
│    ├── exception       <-- GlobalExceptionHandler, NotFoundException
│    └── util            <-- Constantes et helpers
│
├── catalogue
│    ├── entity          <-- Produit, Categorie
│    ├── repository      <-- ProduitRepository -> JpaRepository<Produit>
│    ├── service         <-- ProduitService -> utilise repository
│    ├── dto             <-- ProduitRequest / ProduitResponse
│    └── web             <-- ProduitController -> utilise service
│
├── stock
│    ├── entity          <-- Stock
│    ├── repository
│    ├── service
│    └── web
│
├── commande
│    ├── entity          <-- Commande, LigneCommande
│    ├── repository
│    ├── service
│    └── web
│
├── security
│    ├── entity          <-- User
│    ├── repository
│    ├── service
│    └── web             <-- AuthController
│
└── infrastructure
     ├── flyway          <-- Migration DB
     └── datasource      <-- Configuration DataSource


Légende des flèches :

Controller -> Service -> Repository -> Entity

Service -> Common (exception, util)

Entity -> Common/BaseEntity

2️⃣ Migration progressive vers cette structure

Voici un plan étape‑par‑étape :

Étape 0 : Préparer

Créer un nouveau package racine : com.deep_coding15.gesstockapi

Lister tous les packages existants et identifier où se trouvent tes entités actuelles

Étape 1 : Packages pour entités

Déplacer toutes les entités dans leurs packages respectifs :

catalogue/entity → Produit, Categorie

stock/entity → Stock

commande/entity → Commande, LigneCommande

Créer common/entity pour les bases BaseEntity ou AuditableEntity

Étape 2 : Packages Repository

Créer repository dans chaque module

Déplacer les interfaces existantes (JpaRepository) vers ce package

Vérifier les imports des Services

Étape 3 : Packages Service

Créer service dans chaque module

Déplacer les classes services existantes

Assurer que Service n’accède qu’aux repositories, pas aux controllers

Étape 4 : Packages Controller

Créer web dans chaque module

Déplacer les controllers REST

Vérifier qu’ils n’utilisent que les Services et DTO

Étape 5 : DTO + Mapper

Créer dto et mapper pour chaque module

Refactoriser controllers et services pour utiliser les DTO

Étape 6 : Common et Config

Créer common pour exception, utils, base entity

Créer config pour toutes les configs techniques (datasource, JPA, security, Swagger…)

Déplacer tout ce qui est “transversal” ici

Étape 7 : Flyway + Infrastructure

Déplacer les scripts SQL dans infrastructure/flyway

Configurer application.yml pour que Flyway puisse les lire automatiquement

Vérifier la synchronisation avec JPA

Étape 8 : Validation

Compiler le projet

Vérifier que tous les imports sont corrects

Tester chaque module avec Postman ou H2

┌────────────────────┐
│  ProduitController │
│  (REST / HTTP)     │
└─────────▲──────────┘
          │ appelle
          │
┌─────────┴──────────┐
│   ProduitService   │
│  (Logique métier)  │
└─────────▲──────────┘
          │ utilise
          │
┌─────────┴──────────┐
│ ProduitRepository  │   ← Interface
│ (Contrat métier)   │
└─────────▲──────────┘
          │ implémenté par
          │
┌─────────┴──────────┐
│ Proxy Spring Data  │   ← Classe générée à l’exécution
│ (Hibernate/JPA)    │
└─────────▲──────────┘
          │ exécute
          │
┌─────────┴──────────┐
│  Base de données   │
│ (MySQL / Postgre)  │
└────────────────────┘

👉 Point clé :
Tu n’appelles jamais directement la base de données.
Tu appelles une interface, Spring se charge du reste.
