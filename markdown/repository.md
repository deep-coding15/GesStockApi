✅ Spring Data JPA (JpaRepository) fournit déjà :
- save
- findById
- findAll
- deleteById

2. Son rôle
- Accès base de données
- 100% technique
- Aucune logique métier
    existsByReference(...)
➡️ Spring génère la requête SQL automatiquement
➡️ Tu ne l’as pas écrite
➡️ Tu dois juste respecter le nom