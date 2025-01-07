# JavaSharkPDV

Este proyecto es una aplicación Java para la gestión de puntos de venta, costos y acreditaciones. Incluye un backend desarrollado con **Spring Boot** y utiliza **MySQL** como base de datos.

## Requisitos Previos

Tener instalados los siguientes programas antes de continuar:

- **Docker**: Para levantar los contenedores necesarios.
- **Java 17**: La versión requerida del JDK para ejecutar el proyecto.

## Configuración del Proyecto

### Clonar el Repositorio


git clone https://github.com/nafioren/JavaSharkPDV.git
cd JavaSharkPDV

## Levantar el Proyecto con Docker
Levanta los contenedores usando Docker Compose:
docker-compose up --build

El archivo docker-compose.yml hará lo siguiente:

- Construirá la imagen de la aplicación Java usando el Dockerfile.  
- Levantará un contenedor MySQL con la base de datos PDV.  
- La aplicación estará disponible en: [http://localhost:8080](http://localhost:8080)

## Configuración Manual Base de Datos MySQL

- Si no usas Docker, configura una base de datos MySQL manualmente:  

   Crea la base de datos:  
     ```sql
     CREATE DATABASE PDV;
     ```

   Configura el archivo `application.properties` para conectar a la base de datos local:  
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/pdv  
     spring.datasource.username=root  
     spring.datasource.password=root
     ```

## Ejecutar el Proyecto Localmente

- Compila el proyecto:  
  ```bash
  mvn clean package

- Ejecuta el JAR generado:
  ```bash
  java -jar target/JavaSharkPDV-0.0.1-SNAPSHOT.jar
  ```

Pruebas y Reportes
Ejecutar Pruebas Unitarias
El proyecto utiliza Surefire para ejecutar las pruebas:
mvn test

# Generar Reporte de Cobertura (JaCoCo)

Para generar un informe de cobertura de pruebas con JaCoCo, sigue estos pasos:

### Paso 1: Ejecutar el comando de Maven

Ejecuta el siguiente comando en la terminal desde la raíz del proyecto:

```bash
mvn jacoco:prepare-agent test jacoco:report
```


## Dockerfile
El archivo Dockerfile utiliza una estructura de dos etapas:

Etapa 1: Construcción del Proyecto
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar los archivos necesarios
COPY pom.xml ./
COPY src ./src

- Construir la aplicación
RUN mvn clean package -DskipTests

Etapa 2: Imagen Ligera para Ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

- Copiar el JAR generado desde la etapa de construcción
COPY --from=builder /app/target/JavaSharkPDV-0.0.1-SNAPSHOT.jar app.jar

- Exponer el puerto usado por la aplicación
EXPOSE 8080

- Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

## Docker Compose

El archivo `docker-compose.yml` levanta la aplicación y la base de datos:

```yaml
version: '3.8'

services:
  java-app:
    build:
      context: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql-db
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/PDV
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root

  mysql-db:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: PDV
    volumes:
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    ports:
      - "3306:3306"
```
	  
## Tecnologías Utilizadas

- *Java 17*

- *Spring Boot 3.x*

- *MySQL*

- *Docker*

- *Postman*

- *JUnit 5*

- *Swagger/OpenAPI*

- *Testcontainers*








