# Etapa 1: Construcción del proyecto
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar los archivos necesarios
COPY pom.xml ./
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiar el JAR repaqueteado generado desde la etapa de construcción
COPY --from=builder /app/target/JavaSharkPDV-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto usado por la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

