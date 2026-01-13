# Structure finale recommandée
com.deep_coding15.gesstockapi
└── catalogue
    ├── entity
    │   ├── Produit.java
    │   └── Categorie.java
    │
    ├── repository
    │   ├── ProduitRepository.java
    │   └── CategorieRepository.java
    │
    ├── service
    │   ├── ProduitService.java
    │   └── CategorieService.java
    │
    ├── web
    │   ├── ProduitController.java
    │   └── CategorieController.java
    │
    ├── dto
    │   ├── ProduitRequest.java
    │   └── ProduitResponse.java
    │
    └── mapper
        └── ProduitMapper.java

📌 Un domaine = un package racine (catalogue)
📌 À l’intérieur, tu sépares par responsabilité