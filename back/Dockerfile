FROM maven:3.6.3-openjdk-17

# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

WORKDIR /app

COPY pom.xml .

# Téléchargez les dépendances Maven (ceci permet de mettre en cache les dépendances si le pom.xml n'a pas changé)
RUN mvn dependency:go-offline

# Copiez le reste des fichiers dans le conteneur
COPY src ./src

# Exécutez Maven pour construire l'application
RUN mvn clean package -DskipTests=true

CMD ["java", "-jar", "target/Hackaton-Assurance-0.0.1-SNAPSHOT.jar"]