# 🧠 Comprendre Spring Data JPA : comment les Repository fonctionnent

1. Principe fondamental
En Spring Data JPA, on n’implémente pas les repositories.
```java
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
```

Spring :
- détecte l’interface au démarrage
- génère dynamiquement une implémentation (proxy)
- relie cette implémentation à Hibernate
- exécute les requêtes SQL à la place du développeur

👉 Le développeur décrit l’intention, Spring fait le reste.

2. Génération dynamique (Proxy)
Au démarrage de l’application :
- Spring scanne les packages
- Trouve une interface Repository

Lit :
- l’entité (Produit)
- la clé primaire (Long)

Génère une classe interne invisible :
```java
ProduitRepository$$SpringProxy
```
Cette classe :
- implémente ton interface
- exécute les requêtes
- gère les transactions

3. Query Methods Parsing (interprétation des noms de méthodes)
Spring Data JPA analyse le nom des méthodes pour générer automatiquement des requêtes.

```java
Optional<Produit> findByReference(String reference);
```

➡️ Génère automatiquement :
```sql
SELECT p FROM Produit p WHERE p.reference = :reference
```

4. Structure d’une Query Method
```xml
<Action><By><Champ><Condition><Opérateur>
```

```java
findByCodeAndActif
```

Élément	Signification
- find	SELECT
- By	début du filtre
- Code	champ
- And	opérateur logique
- Actif	champ

5. Actions reconnues
 Mot-clé	Effet
- find	    SELECT
- get	    SELECT
- read	    SELECT
- count	    COUNT
- exists	EXISTS
- delete	DELETE

6. Opérateurs logiques
Mot-clé	SQL
- And	AND
- Or	OR

7. Comparateurs et conditions
Mot-clé	SQL
- Is / Equals	=
- Not	        !=
- LessThan	    <
- Between	    BETWEEN
- GreaterThan	>
- Like	        LIKE
- StartingWith	LIKE 'x%'
- EndingWith	LIKE '%x'
- Containing	LIKE '%x%'
- In	        IN
- IsNull    	IS NULL
- IsNotNull	    IS NOT NULL
- True	        = true
- False	        = false

8. Tri (ORDER BY)
```java
findByCategorieIdOrderByPrixDesc(Long id);
```

```sql
ORDER BY prix DESC
```

9. Pagination et tri dynamique
```java
Page<Produit> findByCategorieId(Long id, Pageable pageable);
```

➡️ Pagination + tri automatique via Pageable

10.  Accès aux relations JPA
Spring Data comprend les relations entre entités.

```java
findByProduitCategorieId(Long categorieId);
```

➡️ Navigue automatiquement :

```xml
Produit → Catégorie → id
```

11. Méthodes héritées de JpaRepository
En étendant JpaRepository<T, ID>, tu obtiens gratuitement :

Méthode	Description
- findAll()	            SELECT *
- findById()	        SELECT by PK
- save()	            INSERT / UPDATE
- deleteById()      	DELETE
- existsById()	        EXISTS
- count()	            COUNT
- getReferenceById()	Proxy Hibernate

12. Différence clé : findById vs getReferenceById
Méthode	Comportement
- findById	        SELECT immédiat
- getReferenceById	Proxy (lazy)

⚠️ getReferenceById peut lever une exception plus tard.

13. Quand utiliser @Query ?
Utiliser @Query quand :
- la requête est complexe
- jointures avancées
- agrégations
- performances critiques

```java
@Query("SELECT p FROM Produit p WHERE p.prix > :prix")
List<Produit> findProduitsChers(@Param("prix") BigDecimal prix);
```

14. Bonnes pratiques professionnelles
✔ Repositories simples
✔ Pas de logique métier dans le repository
✔ Préférer Optional
✔ Nommer clairement les méthodes
✔ 1 repository = 1 agrégat métier

15. Anti-patterns à éviter
❌ Repository avec logique métier
❌ Requêtes trop longues dans le nom
❌ Utilisation excessive de @Query
❌ Retourner null

16. Site officiel (documentation de référence)
📚 Spring Data JPA – Reference Documentation
👉 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

17. Phrase clé à retenir
Spring Data JPA transforme des noms de méthodes en requêtes SQL.