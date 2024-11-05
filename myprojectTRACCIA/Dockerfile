# Usa Java 23 come base per la fase di build
FROM eclipse-temurin:23-jdk-alpine as builder

# Directory di lavoro
WORKDIR /app

# Copia il pom.xml e scarica le dipendenze
COPY pom.xml ./
COPY .mvn/ .mvn/
COPY mvnw ./

# Scarica le dipendenze in anticipo (opzionale)
RUN ./mvnw dependency:go-offline -B

# Ora copia tutto il codice sorgente
COPY src ./src

# Compila l'applicazione
RUN ./mvnw clean package -DskipTests

# Usa Java 23 JRE per la fase di runtime
FROM eclipse-temurin:23-jre-alpine

WORKDIR /app

# Copia il JAR compilato dal builder
COPY --from=builder /app/target/*.jar app.jar

# Esponi la porta 8080
EXPOSE 8080

# Configura le variabili d'ambiente per il database
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_H2_CONSOLE_ENABLED=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Avvia l'applicazione
CMD ["java", "-jar", "app.jar"]