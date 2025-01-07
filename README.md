# JavaSharkPDV

Este proyecto es una aplicación Java para la gestión de puntos de venta, costos y acreditaciones. Incluye un backend desarrollado con Spring Boot y utiliza MySQL como base de datos.

---

## Requisitos Previos

Tener instalados los siguientes programas antes de continuar:

- **Docker**: Para levantar los contenedores necesarios.
- **Java 17**: La versión requerida del JDK para ejecutar el proyecto.


---

## Configuración del Proyecto

### Clonar el Repositorio

git clone <URL_DEL_REPOSITORIO>
cd JavaSharkPDV
## Levantar el Proyecto con Docker
-Levanta los contenedores usando Docker Compose:
docker-compose up --build
El archivo docker-compose.yml hará lo siguiente:
-Construirá la imagen de la aplicación Java usando el Dockerfile.
-Levantará un contenedor MySQL con la base de datos PDV.
-La aplicación estará disponible en:
 http://localhost:8080

## Configuración Manual
Base de Datos MySQL
Si no usas Docker, configura una base de datos MySQL manualmente:

-Crea la base de datos:
CREATE DATABASE PDV;
Configura el archivo application.properties para conectar a la base de datos local:

spring.datasource.url=jdbc:mysql://localhost:3306/pdv
spring.datasource.username=root
spring.datasource.password=root
## Ejecutar el Proyecto Localmente
Compila el proyecto:
mvn clean package
Ejecuta el JAR generado:
java -jar target/JavaSharkPDV-0.0.1-SNAPSHOT.jar

## Pruebas y Reportes
Ejecutar Pruebas Unitarias
El proyecto utiliza Surefire para ejecutar las pruebas:
mvn test
## Generar Reporte de Cobertura (Jacoco)
Para generar un informe de cobertura:
mvn jacoco:prepare-agent test jacoco:report
El informe estará disponible en:
target/site/jacoco/index.html





