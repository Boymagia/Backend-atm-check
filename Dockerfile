# Estágio 1: Build
# Usa uma imagem base com o JDK 17 do Eclipse Temurin
FROM eclipse-temurin:17-jdk-focal as builder

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o Maven Wrapper e o arquivo pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Executa o build para baixar as dependências e armazena em cache
RUN ./mvnw dependency:go-offline

# Copia o código-fonte da aplicação
COPY src src

# Compila o projeto, empacota em um JAR e pula os testes
RUN ./mvnw clean install -DskipTests

# Estágio 2: Ambiente de Execução
# Usa uma imagem mais leve com o JRE do Eclipse Temurin
FROM eclipse-temurin:17-jre-focal

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia o arquivo JAR do estágio de build para o estágio de execução
COPY --from=builder /app/target/*.jar ./app.jar

# Expõe a porta que a aplicação Spring Boot irá usar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
