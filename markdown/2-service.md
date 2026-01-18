1. Service
📌 Rôle
- Contient la logique métier
- Fait le lien entre Controller et Repository
- C’est ici que le métier vit

2. Son rôle
- C’est le cerveau métier
- Vérifie les règles :
  - unicité de la référence
  - existence du produit
- Centralise la logique
👉 Si demain tu changes :
- l’API
- le frontend
- le mode d’accès
👉 le Service ne change pas