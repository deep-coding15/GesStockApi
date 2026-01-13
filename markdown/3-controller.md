1. Controller (REST API)
📌 Rôle
- Expose des endpoints HTTP
- Ne contient aucune logique métier
- Appelle uniquement le service
  
2. Son rôle
Gère HTTP uniquement
Connait : @GetMapping, @PostMapping
Ne fait aucune logique métier
Traduit :
- HTTP → Java
- Java → HTTP
❌ Pas de save() ici
❌ Pas de règles métier
