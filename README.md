# 🏖️ API de Gestão de Férias

API REST desenvolvida em **Spring Boot** para gerenciamento de férias de servidores públicos.

---

## 📋 Índice

- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
  - [Com Docker (Recomendado)](#opção-1---com-docker-recomendado)
  - [Sem Docker](#opção-2---sem-docker)
- [Endpoints da API](#-endpoints-da-api)
- [Exemplos de Uso](#-exemplos-de-uso)
- [Estrutura do Projeto](#-estrutura-do-projeto)

---

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.4**
- **Spring Data JPA**
- **PostgreSQL 15**
- **Maven**
- **Docker & Docker Compose**

---

## 📦 Pré-requisitos

### Com Docker:
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

### Sem Docker:
- [Java JDK 17](https://adoptium.net/temurin/releases/?version=17)
- [PostgreSQL 12+](https://www.postgresql.org/download/)

---

## 🔧 Como Executar

### Opção 1 - Com Docker (Recomendado)

O Docker já cria o banco de dados e insere dados de teste automaticamente!

**1. Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd ferias-api
```

**2. Inicie o Docker Desktop**

Aguarde até aparecer "Docker Desktop is running".

**3. Execute o projeto:**
```bash
docker-compose up --build
```

**4. Aguarde as mensagens:**
```
ferias-db   | database system is ready to accept connections
ferias-api  | Started FeriasApiApplication in X.XXX seconds
```

**5. Acesse a API:**
- http://localhost:8080/api/servidores/1/ferias
- http://localhost:8080/api/ferias/1

**6. Para parar:**
```bash
docker-compose down
```

---

### Opção 2 - Sem Docker

**1. Crie o banco de dados no PostgreSQL:**
```sql
CREATE DATABASE ferias_db;
```

**2. Execute o script de criação das tabelas:**

O script está em `docker/init.sql`. Execute-o no seu cliente PostgreSQL (pgAdmin, DBeaver, etc).

**3. Configure a senha do banco:**

Edite o arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.password=SUA_SENHA_AQUI
```

**4. Execute a aplicação:**

Windows:
```bash
.\mvnw spring-boot:run
```

Linux/Mac:
```bash
./mvnw spring-boot:run
```

**5. Acesse a API:**
- http://localhost:8080/api/servidores/1/ferias

---

## 📡 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/servidores/{id}/ferias` | Lista todos os períodos de férias de um servidor |
| `GET` | `/api/ferias/{id}` | Retorna detalhes de um período específico (com pagamento) |
| `POST` | `/api/ferias` | Cria uma nova solicitação de férias |

---

## 💡 Exemplos de Uso

### Listar férias de um servidor

**Requisição:**
```
GET http://localhost:8080/api/servidores/1/ferias
```

**Resposta:**
```json
[
  {
    "id": 1,
    "dataInicio": "2024-01-15",
    "dataFim": "2024-01-30",
    "quantidadeDias": 15,
    "anoReferencia": 2023,
    "status": "APROVADO"
  },
  {
    "id": 2,
    "dataInicio": "2024-07-01",
    "dataFim": "2024-07-15",
    "quantidadeDias": 15,
    "anoReferencia": 2023,
    "status": "APROVADO"
  }
]
```

---

### Detalhes de um período de férias

**Requisição:**
```
GET http://localhost:8080/api/ferias/1
```

**Resposta:**
```json
{
  "id": 1,
  "servidor": {
    "id": 1,
    "nome": "João da Silva",
    "matricula": "SERV001",
    "email": "joao.silva@gov.br"
  },
  "dataInicio": "2024-01-15",
  "dataFim": "2024-01-30",
  "quantidadeDias": 15,
  "anoReferencia": 2023,
  "status": "APROVADO",
  "observacao": "Férias de janeiro",
  "pagamento": {
    "id": 1,
    "valorBruto": 5500.00,
    "valorLiquido": 4800.00,
    "dataPagamento": "2024-01-10",
    "tipoPagamento": "PAGAMENTO_NORMAL"
  }
}
```

---

### Criar nova solicitação de férias

**Requisição:**
```
POST http://localhost:8080/api/ferias
Content-Type: application/json

{
  "servidorId": 1,
  "dataInicio": "2025-06-01",
  "dataFim": "2025-06-15",
  "anoReferencia": 2025,
  "observacao": "Férias de junho"
}
```

**Resposta (201 Created):**
```json
{
  "id": 5,
  "servidor": {
    "id": 1,
    "nome": "João da Silva",
    "matricula": "SERV001",
    "email": "joao.silva@gov.br"
  },
  "dataInicio": "2025-06-01",
  "dataFim": "2025-06-15",
  "quantidadeDias": 15,
  "anoReferencia": 2025,
  "status": "PENDENTE",
  "observacao": "Férias de junho",
  "pagamento": null
}
```

---

## 📁 Estrutura do Projeto

```
ferias-api/
├── src/
│   └── main/
│       ├── java/com/rh/ferias_api/
│       │   ├── controller/
│       │   │   └── FeriasController.java
│       │   ├── dto/
│       │   │   ├── PagamentoFeriasDTO.java
│       │   │   ├── PeriodoFeriasDetalhadoDTO.java
│       │   │   ├── PeriodoFeriasRequestDTO.java
│       │   │   ├── PeriodoFeriasResumoDTO.java
│       │   │   └── ServidorDTO.java
│       │   ├── exception/
│       │   │   ├── GlobalExceptionHandler.java
│       │   │   └── ResourceNotFoundException.java
│       │   ├── model/
│       │   │   ├── PagamentoFerias.java
│       │   │   ├── PeriodoFerias.java
│       │   │   ├── Servidor.java
│       │   │   └── Usuario.java
│       │   ├── repository/
│       │   │   ├── PeriodoFeriasRepository.java
│       │   │   └── ServidorRepository.java
│       │   ├── service/
│       │   │   └── FeriasService.java
│       │   └── FeriasApiApplication.java
│       └── resources/
│           └── application.properties
├── docker/
│   └── init.sql
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## 🗄️ Modelo do Banco de Dados

```
┌─────────────┐       ┌──────────────────┐       ┌──────────────────┐
│  servidor   │       │  periodo_ferias  │       │ pagamento_ferias │
├─────────────┤       ├──────────────────┤       ├──────────────────┤
│ id (PK)     │──┐    │ id (PK)          │──┐    │ id (PK)          │
│ nome        │  │    │ servidor_id (FK) │  │    │ periodo_id (FK)  │
│ cpf         │  └───>│ data_inicio      │  └───>│ valor_bruto      │
│ matricula   │       │ data_fim         │       │ valor_liquido    │
│ email       │       │ quantidade_dias  │       │ data_pagamento   │
└─────────────┘       │ ano_referencia   │       │ tipo_pagamento   │
      │               │ status           │       └──────────────────┘
      │               │ observacao       │
      v               └──────────────────┘
┌─────────────┐
│   usuario   │
├─────────────┤
│ id (PK)     │
│ servidor_id │
│ username    │
│ senha       │
└─────────────┘
```

---

## 👤 Autor

Desenvolvido para o processo seletivo **Sergipe Tech**.