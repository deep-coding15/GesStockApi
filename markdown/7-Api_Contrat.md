# API CONTRACT – MVP Gestion de Stock & Vente
# methodName_expectedBehavior_condition
createRole_shouldFail_whenCodeAlreadyExists

## Conventions générales

* Base URL : `/api/v1`
* Format : JSON
* Versionnement par URL
* 1 utilisateur = 1 rôle

---

## 1. Rôles

### Créer un rôle

**POST** `/roles`

```json
{
  "code": "ADMIN",
  "libelle": "***", //optionnel
}
```

### Lister les rôles

**GET** `/roles`
```json
{
  "id": 1,
  "code": "ADMIN",
  "libelle": "***", //optionnel
}
```
---

## 2. Utilisateurs

### Créer un utilisateur

**POST** `/utilisateurs`

```json
{
  "username": "caissier1",
  "email": "caissier1@email.com",
  "password": "123456",
  "roleId": 2
}
```

### Lister les utilisateurs

**GET** `/utilisateurs`

```json
{
  "id": 1,
  "username": "caissier1",
  "email": "caissier1@email.com",
  "roleId": 2
}
```
---

## 3. Catégories

### Créer une catégorie

**POST** `/categories`

```json
{
  "code": "CAT_INFO",
  "designation": "Informatique"
}
```

### Lister les catégories

**GET** `/categories`

---

## 4. Produits

### Créer un produit

**POST** `/produits`

```json
{
  "reference": "PRD001",
  "designation": "Clavier",
  "prix": 120,
  "categorieId": 1
}
```

### Lister les produits

**GET** `/produits`

### Lister les produits par catégorie

**GET** `/categories/{id}/produits`

---

## 5. Stock

### Initialiser le stock d’un produit

**POST** `/stocks`

```json
{
  "produitId": 1,
  "quantite": 100
}
```

### Consulter le stock d’un produit

**GET** `/stocks/produit/{produitId}`

---

## 6. Mouvements de stock

### Ajouter un mouvement

**POST** `/stocks/mouvements`

```json
{
  "produitId": 1,
  "quantite": 10,
  "type": "ENTREE"
}
```

Types : `ENTREE`, `SORTIE`

### Historique des mouvements

**GET** `/stocks/{produitId}/mouvements`

---

## 7. Statuts de vente

### Créer un statut

**POST** `/statuts-vente`

```json
{
  "code": "VALIDEE",
  "libelle": "Vente validée"
}
```

### Lister les statuts

**GET** `/statuts-vente`

---

## 8. Ventes

### Créer une vente

**POST** `/ventes`

```json
{
  "utilisateurId": 1,
  "statutId": 1,
  "lignes": [
    { "produitId": 1, "quantite": 2 },
    { "produitId": 3, "quantite": 1 }
  ]
}
```

Règles métier :

* Stock suffisant requis
* Décrément automatique du stock
* Génération de mouvements `SORTIE`

### Consulter une vente

**GET** `/ventes/{id}`

### Lister les ventes

**GET** `/ventes`

---

## 9. Résumé des relations métier

* Utilisateur → Rôle : ManyToOne
* Produit → Catégorie : ManyToOne
* Produit → Stock : OneToOne
* Stock → MouvementStock : OneToMany
* Vente → VenteLigne : OneToMany
* VenteLigne → Produit : ManyToOne
* Vente → StatutVente : ManyToOne
* Vente → Utilisateur : ManyToOne
