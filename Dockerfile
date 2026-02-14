# Etape1 : Build de l'application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier uniquement pom.xml d'abord (optimisation du cache Docker)
COPY pom.xml .

# Télécharger les dépendances (optimisation du cache Docker)
RUN mvn dependency:go-offline

# Copier le reste du code source
COPY src ./src

# Compiler l'application
RUN mvn clean package -DskipTests

# Etape2 : Runtime de l'application
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copier le JAR généré depuis l'étape de build
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port
EXPOSE 8088

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
