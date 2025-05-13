# ----- Etapa 1: Compilación (Builder) -----
# Usamos una imagen de Maven con JDK 11 (Eclipse Temurin es una buena opción)
FROM maven:3.9.6-eclipse-temurin-11 AS builder

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar el caché de Docker para las dependencias
COPY pom.xml .
# Descargamos las dependencias (opcional, pero puede acelerar builds si el pom.xml no cambia mucho)
# RUN mvn dependency:go-offline -B

# Copiamos el resto del código fuente de tu aplicación
COPY src ./src

# Limpiamos, compilamos y empaquetamos la aplicación, generando el .war
# El nombre del artefacto es SpringUsoSesionesUsuario-0.0.1-SNAPSHOT.war según tu pom.xml
# -DskipTests se usa para saltar las pruebas. Si tus pruebas funcionan, puedes quitarlo.
RUN mvn clean package -DskipTests

# ----- Etapa 2: Ejecución (Runtime) -----
# Usamos una imagen de JRE 11 de Alpine, que es ligera
FROM eclipse-temurin:11-jre-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el .war generado en la etapa 'builder' al directorio actual de la imagen final
COPY --from=builder /app/target/SpringUsoSesionesUsuario-0.0.1-SNAPSHOT.war app.war

# Exponemos el puerto en el que tu aplicación Spring Boot se ejecuta (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación cuando el contenedor inicie
ENTRYPOINT ["java","-jar","/app.war"]
