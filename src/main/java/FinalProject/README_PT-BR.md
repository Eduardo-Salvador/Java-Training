<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Pet Adoption System v2.0

Projeto final do repositório Java-OOP-Training. Expande o sistema CLI da v1.0 para uma aplicação desktop completa integrando todos os tópicos abordados ao longo do curso.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)

</div>

---

## Visão Geral

O sistema gerencia um abrigo de adoção de pets. A equipe pode cadastrar novos animais, pesquisar por diferentes critérios, processar adoções e acompanhar o estado do abrigo por meio de um relatório ao vivo. Um servidor de notificações em segundo plano transmite atualizações em tempo real para todos os terminais conectados sempre que um pet é cadastrado ou adotado, simulando um ambiente de abrigo com múltiplos terminais.

---

## Arquitetura

```
domain/         Entidade Pet, enums (PetType, PetSex, PetStatus), DTOs como Records
repository/     Interface genérica PetRepository e PetRepositoryImpl com JDBC
service/        PetService — Toda a lógica de negócio, Streams e Lambdas
factory/        PetFactory — Centraliza a criação de Pet a partir de DTOs de request
config/         DatabaseConfig — Singleton gerenciando a conexão JDBC
gui/            MainFrame, RegisterPanel, SearchPanel, EditPanel, ReportPanel (Swing)
server/         NotificationServer e NotificationClient (Channels e Sockets)
util/           PetValidator — Métodos estáticos de validação
exception/      PetNotFoundException, PetValidationException, DatabaseConnectionException
test/           PetServiceTest e PetRepositoryTest (JUnit 5 + Mockito)
```

A camada de GUI se comunica apenas com o `PetService`. O serviço mantém um cache em memória com `CopyOnWriteArrayList` populado na inicialização a partir do banco, mantendo as operações de leitura fora do banco. As operações de escrita persistem no MySQL e sincronizam o cache imediatamente. O servidor de notificações roda em uma thread dedicada com canais NIO não bloqueantes, recebendo mensagens via `ConcurrentLinkedQueue` e entregando para todos os clientes conectados de forma assíncrona.

---

## Funcionalidades

**Aba Cadastro** — Formulário para registrar um novo pet com validação completa. Em caso de sucesso exibe um diálogo de confirmação e limpa o formulário. Em caso de erro de validação exibe a mensagem específica sem fechar o formulário.

**Aba Busca** — Pesquisa por nome, raça ou tipo com suporte a correspondência parcial. Resultados exibidos em uma tabela. A partir dos resultados a equipe pode adotar um pet ou excluir um registro após diálogo de confirmação.

**Aba Edição** — Carrega um pet pelo ID, permite modificar qualquer campo e persiste a atualização.

**Aba Resumo** — Relatório ao vivo com total de pets por tipo, peso médio por tipo e o pet mais velho cadastrado. Um botão Atualizar recalcula todos os valores sob demanda.

**Aba Notificações** — Log em tempo real de todos os eventos de cadastro e adoção transmitidos pelo servidor.

---

## Pré-requisitos

- Java 21
- Docker

---

## Configuração

**1. Clone o repositório**

```bash
git clone https://github.com/Eduardo-Salvador/Java-Training
cd Java-OOP-Training
```

**2. Configure o arquivo `.env` na raiz do projeto**

```env
DB_NAME=petadoption
DB_PORT=3307
DB_URL=jdbc:mysql://localhost:3307/petadoption?allowPublicKeyRetrieval=true&useSSL=false
DB_USER=root
DB_PASSWORD=123
```

**3. Suba o container Docker**

```bash
docker compose up -d
```

**4. Execute a aplicação**

Execute `FinalProject.Main` pela IDE ou via Maven.

---

## Conceitos Aplicados

- Programação Orientada a Objetos e Padrões de Projeto (Builder, Factory, Singleton)
- Records e DTOs
- Generics
- Coleções e Estruturas de Dados Concorrentes
- Exceções Customizadas
- Lambdas, Streams, Method References e Optional
- GUI com Swing
- Threads, Canais NIO, Sockets e CompletableFuture
- Maven
- JDBC com MySQL
- Docker
- JUnit 5 e Mockito