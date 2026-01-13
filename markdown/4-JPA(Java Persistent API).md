## Explication base de données + JPA proprement
C’est le socle. Si cette partie est claire, tout le reste devient simple.
Je vais te guider pas à pas, sans raccourci, comme en environnement pro.
OBJECTIF DE CETTE ÉTAPE
À la fin, tu sauras :
- comment Spring Boot se connecte à une base de données
- ce que fait JPA au démarrage
- comment les tables sont créées
- comment éviter les erreurs classiques

Nous allons utiliser H2 d’abord (base en mémoire), puis je te dirai comment passer à MySQL.

1. Principe fondamental : JPA ≠ Base de données
👉 JPA est une couche d’abstraction
👉 Ce n’est PAS la base de données
👉 JPA traduit :
- Objet Java ↔ Table SQL
Tu écris :
```Java
@Entity
class Produit { ... }
```
JPA crée :

```sql
CREATE TABLE produit (...)
```
2. Choix pédagogique : H2 (base en mémoire)
Pourquoi H2 maintenant ?
- zéro installation
- démarre instantanément
- parfaite pour apprendre
- très utilisée en entreprise pour les tests

3. Dépendance H2 (vérifie ton pom.xml)
Tu dois avoir au minimum :

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
Si elle n’y est pas :
ajoute-la
recharge Maven (Reload Project)

4. Configuration application.yml
Crée (ou modifie) :

📁 src/main/resources/application.yml
```yaml
server:
  port: 8089

spring:
  datasource:
    url: jdbc:h2:mem:gesstock
    driver-class-name: org.h2.Driver
    username: deep-coding15
    password:

  h2:
    console:
      enabled: true
      path: /h2-console

  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```
5. Ce que fait chaque ligne (à comprendre)
```yml
ddl-auto: update
```
create → recrée tout à chaque démarrage ❌

update → adapte le schéma sans perte (OK dev)

validate → vérifie uniquement

none → prod

👉 update est parfait pour apprendre

```yml
show-sql: true
```
➡️ Tu verras le SQL généré dans la console
➡️ Très formateur

6. Démarre l’application
Tu dois voir dans les logs :

création de table produit

aucune erreur datasource

Tomcat sur 8089

7. Accéder à la base H2
Ouvre ton navigateur :

bash
Copier le code
http://localhost:8089/h2-console
Paramètres :

JDBC URL : jdbc:h2:mem:gesstock

User : sa

Password : (vide)

➡️ Connect

Tu dois voir :

table produit

colonnes correspondant à ton entity

8. Comprendre le lien Entité ↔ Table
Dans Produit.java :
```java
@Entity
@Table(name = "produit")
```
➡️ Table SQL : produit

```java
@Column(nullable = false, unique = true)
private String reference;
```
➡️ SQL :

```sql
reference VARCHAR NOT NULL UNIQUE
```
👉 Tout est déterministe

9. Erreurs classiques (à éviter)
❌ oublier la dépendance H2
❌ mauvaise URL JDBC
❌ ddl-auto=create en production
❌ mettre de la logique dans l’entité
❌ paniquer devant le SQL auto-généré

10.  Quand passer à MySQL ?
Pas maintenant.
Mais plus tard, tu changeras seulement :
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gesstock
    username: root
    password: xxxx
```
👉 Le code Java ne change PAS

11. Ce que tu as maintenant (important)
Tu as :

une API fonctionnelle

une base connectée

un ORM opérationnel

une architecture saine

👉 Tu es officiellement entrée dans le backend réel.

12. Prochaine étape logique (je te propose 2 options)
1️⃣ Comprendre les relations JPA (@OneToMany, @ManyToOne)
2️⃣ Mettre en place les migrations (Flyway) comme EF Core
3️⃣ Ajouter le module Stock relié à Produit

👉 Mon conseil : Flyway (très formateur)

```java
@ManyToOne(optional = false)
@JoinColumn(name = "categorie_id")
private Categorie categorie;
```
optional = false : interdit un produit sans catégorie
=> Un produit à une seule catégorie
=> Une catégorie peut contenir un ou plusieurs produits
Donc  1 produit -> 1 Catégorie
      1 catégorie -> N produits
      plusieurs produits => 1 seule catégrorie

# Traduction SQL exacte
```sql
categorie (
  id BIGINT PRIMARY KEY
)

produit (
  id BIGINT PRIMARY KEY,
  categorie_id BIGINT NOT NULL,
  FOREIGN KEY (categorie_id) REFERENCES categorie(id)
)
```
En JPA : 
  - @ManyToOne = je possède la FK
  - @OneToMany = je ne possède pas la FK
  - optional = false => interdit un produit sans categorie
Conclusion : 
  - la clé étrangère est toujours du côté @ManyToOne