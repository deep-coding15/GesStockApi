# StockMouvement 
1. StockMouvement est une entité d’audit métier.
Elle n’est jamais manipulée directement par l’API, mais générée automatiquement par les actions sur le Stock afin de garantir la cohérence et la traçabilité.
2. Les mouvements de stock sont testés à deux niveaux :
- des tests JPA pour garantir l’intégrité des requêtes et
- des tests unitaires de service pour sécuriser la couche applicative.
3. Ne jamais faire confiance aux valeurs du DTO, si besoin, recharger depuis la base de données. Surtout pour un insert ou un update.