# Projeto de E-commerce com Tolerância a Falhas

Este projeto demonstra um sistema de e-commerce distribuído com serviços REST para E-commerce, Store, Exchange e Fidelity, utilizando Docker e suporte a tolerância a falhas.

## Requisitos
- Docker e Docker Compose instalados
- Maven instalado
- Java 21 JDK configurado

## Como rodar

1. **Build do projeto**:
   ```bash
   mvn clean package
   ```

2. **Subir os containers**:
   ```bash
   docker-compose up --build --scale exchange=2
   ```

3. **Testar a aplicação**:
   Use um cliente HTTP (como Postman ou cURL) para chamar o endpoint principal:
   ```http
   GET http://localhost:8080/buy
   ```

## Serviços Disponíveis
- **E-commerce**: Porta 8080
- **Store**: Porta 8081
- **Exchange**: Escalável, Porta 8082
- **Fidelity**: Porta 8083

## Observação
Os serviços se comunicam internamente via nomes dos containers definidos no `docker-compose.yml`. Certifique-se de que todos os serviços estão em execução para testes completos.