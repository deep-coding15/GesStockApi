1. Résumé mental (important pour moi)
HTTP Request
   ↓
Controller (REST)
   ↓
Service (logique métier)
   ↓
Repository (DB)

👉 Le Controller ne sait rien de la DB
👉 Le Repository ne sait rien du HTTP
👉 Le Service oriente le métier

2. Ce que Spring fait pour toi (et que tu n’écris PAS)
C’est capital de le savoir :
Spring :
- instancie les classes (@Service, @RestController)
- injecte les dépendances (constructeur)
- ouvre/ferme les transactions JPA
- convertit JSON ↔ Objet Java
- gère Tomcat
- lance l’application
👉 Tu ne contrôles pas tout
👉 Tu déclares, Spring orchestre