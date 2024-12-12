FROM eclipse-temurin:21-jdk-alpine

# Copiar o JAR para o container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Configurar a entrada para usar variáveis de ambiente
ENTRYPOINT ["java", "-Dspring.application.name=${SERVICE_NAME}", "-Dserver.port=${SERVER_PORT}", "-jar", "/app.jar"]