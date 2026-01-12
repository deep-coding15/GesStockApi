1. Structure recommandée pour TON projet (MVP → scalable)

com.deep_coding15.gesstockapi
│
├── config/                 # sécurité, swagger, cors
│
├── produit/
│   ├── Produit.java
│   ├── ProduitRepository.java
│   ├── ProduitService.java
│   ├── ProduitController.java
│   └── dto/
│       ├── ProduitRequest.java
│       └── ProduitResponse.java
│
├── stock/
│   ├── Stock.java
│   ├── StockRepository.java
│   ├── StockService.java
│   └── StockController.java
│
├── vente/
│   ├── Vente.java
│   ├── VenteRepository.java
│   ├── VenteService.java
│   └── VenteController.java
│
├── utilisateur/
│   ├── User.java
│   ├── Role.java
│   ├── UserRepository.java
│   └── UserService.java
│
├── common/
│   ├── exception/
│   ├── audit/
│   └── base/
│
└── GesStockApiApplication.java
