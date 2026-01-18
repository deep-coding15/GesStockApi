# Façon professionnelle actuelle pour implémenter une API (MVP)

## 1. Principe fondamental : API First (Contract-Driven)

La pratique professionnelle dominante consiste à **concevoir d’abord le contrat d’API**, puis à implémenter le code à partir de ce contrat.

Bénéfices :
* Stabilité entre front et back
* Lisibilité métier
* Évolutivité
* Tests facilités

## 2. Architecture standard recommandée
```yml
Controller → DTO → Service → Repository → Entity
              ↑        ↓
            Mapper (MapStruct)
```

### Règle absolue
* ❌ Les entités JPA ne sortent jamais de l’API
* ✅ Seuls les DTO traversent la frontière HTTP
* 
## 3. Découpage des packages (par domaine métier)
```yml
com.deep_coding15.GesStockApi
│
├── catalogue (Produit / Catalogue)
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   ├── service
│   └── mapper
│
├── security (Role / Utilisateur)
├── stock (Stock / StockMouvement)
│   
├── vente (Vente / VenteLigne)
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── service
│   └── mapper
│
└── common
    ├── exception
    ├── response
    ├── entity (BaseEntity / HelloController)
    └── enums
```

Chaque domaine est autonome. Aucun package “fourre-tout”.

## 4. DTO : séparation stricte des usages
En pratique professionnelle, **on ne crée jamais un seul DTO**.

| DTO         | Usage              |
| ----------- | ------------------ |
| CreateDTO   | POST               |
| UpdateDTO   | PUT / PATCH        |
| ResponseDTO | Réponse API        |
| LightDTO    | Références légères |

### Exemple : Produit
* ProduitCreateDTO
* ProduitUpdateDTO
* ProduitResponseDTO
* ProduitLightDTO

## 5. Relations entre entités : via DTO uniquement
### Bonne pratique
Les relations sont exposées via des **DTO de référence**, jamais via des entités.
```yml
ProduitResponseDTO
└── CategorieLightDTO
```

## 6. Relations métier du MVP
### Utilisateur – Role
* 1 utilisateur → 1 rôle
```yml
UtilisateurResponseDTO
├── id
├── username
└── RoleDTO
```

### Vente – VenteLigne – Produit
* Vente = agrégat racine
* VenteLigne n’existe pas seule
```yml
VenteResponseDTO
├── id
├── statut
└── List<VenteLigneDTO>
    └── ProduitLightDTO
```

## 7. Services : logique métier uniquement
Règles :
* Pas de DTO HTTP (@RequestBody)
* Pas de ResponseEntity
* Pas de logique de présentation

Le service :
* valide les règles métier
* gère les transitions d’état
* orchestre les repositories

## 8. Controllers : très fins
Responsabilités :
* recevoir la requête
* appeler le service
* retourner la réponse
Aucune logique métier.

## 9. Mapper : indispensable en entreprise
### Pourquoi ?
* Centralise les transformations
* Réduit le code spaghetti
* Testable

### Outil standard
**MapStruct**
Un mapper par domaine métier.

## 10. Enums : bonne pratique
* Stockés comme STRING en base
* Exposés comme STRING dans l’API
```yml
StatutVente
- BROUILLON
- VALIDEE
- ANNULEE
```

## 11. Validation
* ❌ Pas dans les entités
* ✅ Uniquement dans les DTO d’entrée
```yml
@NotNull
@Positive
private BigDecimal prix;
```

## 12. Résumé exécutif
✔ API contract d’abord
✔ DTO spécialisés
✔ Entities isolées du HTTP
✔ Mapper obligatoire
✔ Services riches, controllers minces
✔ Agrégats métier respectés
✔ Architecture par domaine

Ce cadre correspond aux **standards attendus en entreprise**, en audit technique, et dans un projet académique sérieux.

C'est une formalisation professionnelle actuelle dans un canvas structuré, aligné avec les pratiques réellement utilisées en entreprise (API-first, DTO, services riches, controllers fins, mappers, agrégats métier).

Ce document peut servir :
- de référence de codage,
- de base pour un rapport ou une soutenance,
- de guide pour implémenter chaque module sans dette technique.

Suite logique recommandée
- Choisir un module pilote (je recommande Vente)

Implémenter :
- Controller
- Service
- Mapper
- Repository
en suivant strictement le canvas

Dupliquer le modèle pour les autres domaines