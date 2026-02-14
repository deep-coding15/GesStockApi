# Image officielle Java 17 légère
FROM eclipse-temurin:17-jdk-alpine

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier le jar compilé
COPY target/*.jar app.jar

# Exposer le port
EXPOSE 8088

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
