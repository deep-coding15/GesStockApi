GesStockApi - API de Gestion de Commerce
API REST complète pour la gestion de commerce (vente et stock) développée avec Spring Boot 3.2.5.
🚀 Getting Started
Base URL: http://localhost:8089
Cette API fournit des endpoints pour gérer l'ensemble des opérations commerciales incluant la gestion du catalogue produits, des stocks, des ventes et des utilisateurs.
Variables de Collection
baseUrl: URL de base de l'API (défaut: http://localhost:8089)

📚 Modules Disponibles
1. Health
Base path: /api/health
Endpoints de santé et vérification de l'état de l'API. Utilisez ces endpoints pour monitorer la disponibilité et les performances de l'API.
2. Categories
Base path: /api/v1/categories
Gestion complète des catégories de produits. Permet de créer, consulter, modifier et supprimer des catégories pour organiser votre catalogue.
Opérations disponibles:
Lister toutes les catégories
Obtenir une catégorie par ID
Créer une nouvelle catégorie
Mettre à jour une catégorie existante
Supprimer une catégorie

3. Products (Produits)
Base path: /api/v1/produits
Gestion du catalogue de produits avec toutes les informations nécessaires (nom, description, prix, catégorie, etc.).
Opérations disponibles:
Lister tous les produits
Obtenir un produit par ID
Créer un nouveau produit
Mettre à jour un produit existant
Supprimer un produit
Rechercher des produits par catégorie

4. Stock
Base path: /api/v1/stocks
Gestion des niveaux de stock pour chaque produit. Permet de suivre les quantités disponibles et d'effectuer des ajustements.
Opérations disponibles:
Consulter tous les stocks
Obtenir le stock d'un produit spécifique
Mettre à jour les quantités en stock
Ajouter du stock (entrée)
Retirer du stock (sortie)

5. Stock Movements (Mouvements de Stock)
Base path: /api/v1/stock-mouvements
Consultation de l'historique complet des mouvements de stock. Chaque mouvement est tracé avec la date, le type (ENTREE/SORTIE/INITIAL/AJUSTEMENT), la quantité et l'utilisateur responsable.
Opérations disponibles:
Lister tous les mouvements
Obtenir les mouvements par ID de stock
Obtenir les mouvements par ID de produit
Filtrer les mouvements par période

Types de mouvements:
INITIAL: Stock initial lors de la création
ENTREE: Ajout de stock (réapprovisionnement)
SORTIE: Retrait de stock (vente, perte, etc.)

6. Roles
Base path: /api/v1/roles
Gestion des rôles utilisateurs pour le contrôle d'accès et les permissions.
Opérations disponibles:
Lister tous les rôles
Obtenir un rôle par ID
Créer un nouveau rôle
Mettre à jour un rôle
Supprimer un rôle

7. Users (Utilisateurs)
Base path: /api/v1/users
Gestion des comptes utilisateurs avec leurs informations et rôles associés.
Opérations disponibles:
Lister tous les utilisateurs
Obtenir un utilisateur par ID
Créer un nouvel utilisateur
Mettre à jour un utilisateur
Supprimer un utilisateur
Assigner des rôles aux utilisateurs

8. Ventes (Sales)
Base path: /api/v1/ventes
Gestion des transactions de vente. Chaque vente peut contenir plusieurs lignes de produits.
Opérations disponibles:
Créer une nouvelle vente
Consulter toutes les ventes
Obtenir une vente par ID
Mettre à jour une vente
Annuler une vente

9. Vente Lignes (Sale Lines)
Base path: /api/v1/vente-lignes
Gestion des lignes de vente individuelles (détails des produits vendus dans chaque transaction).
Opérations disponibles:
Ajouter une ligne à une vente
Consulter les lignes d'une vente
Mettre à jour une ligne de vente
Supprimer une ligne de vente

🔐 Authentication
L'API utilise un système d'authentification basé sur les rôles. Assurez-vous d'inclure les credentials appropriés dans vos requêtes.
📊 Format des Réponses
Toutes les réponses de l'API sont au format JSON. Les codes de statut HTTP standard sont utilisés:
200 OK: Requête réussie
201 Created: Ressource créée avec succès
400 Bad Request: Données invalides
404 Not Found: Ressource non trouvée
500 Internal Server Error: Erreur serveur

💡 Exemples d'Utilisation
Créer un Produit


JSON








POST /api/v1/produits
{
  "nom": "Produit Example",
  "description": "Description du produit",
  "prix": 29.99,
  "categorieId": 1
}


Consulter les Mouvements d'un Produit


Plain Text








GET /api/v1/stock-mouvements/produit/1


Créer une Vente


JSON








POST /api/v1/ventes
{
  "utilisateurId": 1,
  "dateVente": "2026-02-04T10:00:00",
  "lignes": [
    {
      "produitId": 1,
      "quantite": 2,
      "prixUnitaire": 29.99
    }
  ]
}


🛠️ Technologies
Framework: Spring Boot 3.2.5
Java Version: 17+
Database: Compatible avec bases relationnelles (MySQL, PostgreSQL, etc.)
Architecture: REST API

📞 Support
Pour toute question ou problème, veuillez consulter la documentation de chaque endpoint ou contacter la developpeuse.
[email:  tsafackmerveillem@gmail.com]


Version: 1.0.0
Last Updated: February 2026
