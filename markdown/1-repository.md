# Un repository est la couche d'acces aux données.
- Il communique avec la base de donnée
- Il encapsule les opérations CRUD
- Il évite d’écrire du SQL ou du JPQL à la main
# JpaRepository<T, ID> est une interface fournie par Spring Data JPA.
Ici :
- T = Produit → l’entité
- ID = Long → le type de la clé primaire (@Id)

# ✅ Spring Data JPA (JpaRepository) fournit déjà sans écrire une seule ligne :
- save
- findById
- findAll
- deleteById
- delete()
- count()
- existsById()
👉 Spring génère automatiquement l’implémentation à l’exécution.

1. Son rôle
- Accès base de données
- 100% technique
- Aucune logique métier
    existsByReference(...)
➡️ Spring génère la requête SQL automatiquement
➡️ Tu ne l’as pas écrite
➡️ Tu dois juste respecter le nom

# Optional<Produit> findByReference(String reference);
Spring Data JPA analyse le nom de la méthode :
findBy → requête de sélection
Reference → champ de l’entité Produit

➡️ Cela correspond à : 
`sql SELECT * FROM produit WHERE reference = ?`

# Pourquoi Optional<Produit> ?
Évite les NullPointerException
Oblige le développeur à gérer le cas produit non trouvé
Exemple d’utilisation :
```java
Produit produit = produitRepository.findByReference(ref)
        .orElseThrow(() -> new EntityNotFoundException("Produit introuvable"));
```
# Méthode existsByReference
`java boolean existsByReference(String reference);`
Vérifie si un produit existe déjà avec cette référence.

➡️ Requête générée :
```sql
SELECT COUNT(*) > 0 FROM produit WHERE reference = ?
```

Cas d’usage typique
Avant la création d’un produit :
```java
if (produitRepository.existsByReference(dto.getReference())) {
    throw new EntityIllegalArgumentException("Produit", "reference", dto.getReference);
}
```
👉 Très utile pour garantir l’unicité métier (souvent couplé à @Column(unique = true)).