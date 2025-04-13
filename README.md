# 📟 Microserviço de Pedidos com Spring Boot, MongoDB e RabbitMQ

Este projeto é um microserviço construído com **Spring Boot** que permite salvar pedidos de clientes em um banco **MongoDB** e utiliza **RabbitMQ** para troca de mensagens. Os serviços são orquestrados com **Docker Compose**.

---

## 🚀 Tecnologias utilizadas

- Java 21+
- Spring Boot
- MongoDB
- RabbitMQ
- Docker e Docker Compose

---

## 📆 Subindo os serviços com Docker Compose

O projeto inclui um arquivo `docker-compose.yml` para facilitar a criação dos containers do MongoDB e RabbitMQ.

### 📁 docker-compose.yml

```yaml
services:
  mongodb:
    image: mongo
    ports:
      - 27017:27017
    environment:
      - MONGO_INITDB_ROOT_USERNAME=admin
      - MONGO_INITDB_ROOT_PASSWORD=123

  rabbitmq:
    image: rabbitmq:3.13-management
    ports:
      - 15672:15672
      - 5672:5672
```

### ✅ Como subir os serviços

```bash
docker-compose up -d
```

---

## ⚙️ Como funciona o microserviço

### 📥 Requisição de exemplo

O microserviço recebe um JSON com os dados do pedido, conforme o exemplo abaixo:

```json
{
  "CodigoPedido": 1,
  "CodigoCliente": 2,
  "itens": [
    {
      "produto": "lapis",
      "quantidade": 5,
      "preco": 2
    },
    {
      "produto": "caneta",
      "quantidade": 7,
      "preco": 4
    }
  ]
}
```

### 📏 O que acontece ao receber a requisição:

1. Os dados são salvos na coleção `tb_orders` no MongoDB.
2. O microserviço calcula automaticamente:
   - O **total** de cada pedido (quantidade × preço).
   - O **total geral** de todos os pedidos daquele cliente.

---

## 📤 Resposta esperada

```json
{
  "codigoPedido": 1,
  "codigoCliente": 2,
  "itens": [
    {
      "produto": "lapis",
      "quantidade": 5,
      "preco": 2
    },
    {
      "produto": "caneta",
      "quantidade": 7,
      "preco": 4
    }
  ],
  "totalPedido": 38,
  "totalGeralCliente": 100
}
```

> **totalPedido** = (5×2) + (7×4) = 10 + 28 = 38  
> **totalGeralCliente** = soma de todos os pedidos anteriores do cliente com `codigoCliente = 2`

---

## 📚 Endpoints disponíveis

| Método | Rota                    | Descrição                            |
|--------|-------------------------|--------------------------------------|
| POST   | `/orders`               | Cria um novo pedido                  |
| GET    | `/orders?cliente=2`     | Retorna todos os pedidos do cliente |
| GET    | `/orders/total?cliente=2` | Retorna o total dos pedidos do cliente |

---

## 🛠️ Configuração no `application.properties`

```properties
spring.application.name=orderms

spring.data.mongodb.authentication-database=admin
spring.data.mongodb.auto-index-creation=true
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=orderproject
spring.data.mongodb.username=admin
spring.data.mongodb.password=123
```

---

## ✒️ Autor

* [**Romulo Matheus**](https://github.com/Romulomdr) - *At the moment developer Java back-end* [<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/romulo-dantasmdr/)

