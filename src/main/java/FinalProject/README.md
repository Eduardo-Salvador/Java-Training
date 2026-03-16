<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Pet Adoption System v2.0

Final project of the Java-OOP-Training repository. Expands the CLI system from v1.0 into a complete desktop application integrating all topics covered throughout the course.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)

</div>

---

## Overview

The system manages a pet adoption shelter. The shelter staff can register new animals, search by different criteria, process adoptions, and monitor the shelter's status through a live report. A background notification server broadcasts real-time updates to all connected terminals whenever a pet is registered or adopted, simulating a multi-terminal shelter environment.

---

## Architecture

```
domain/         Pet entity, enums (PetType, PetSex, PetStatus), DTOs as Records
repository/     Generic PetRepository interface and PetRepositoryImpl with JDBC
service/        PetService — All business logic, Streams and Lambdas
factory/        PetFactory — Centralizes Pet creation from request DTOs
config/         DatabaseConfig — Singleton managing the JDBC connection
gui/            MainFrame, RegisterPanel, SearchPanel, EditPanel, ReportPanel (Swing)
server/         NotificationServer and NotificationClient (Channels and Sockets)
util/           PetValidator — Static validation methods
exception/      PetNotFoundException, PetValidationException, DatabaseConnectionException
test/           PetServiceTest and PetRepositoryTest (JUnit 5 + Mockito)
```

The GUI layer only communicates with `PetService`. The service holds an in-memory cache backed by `CopyOnWriteArrayList` populated on startup from the database, keeping read operations off the database entirely. Write operations persist to MySQL and synchronize the cache immediately. The notification server runs on a dedicated thread using non-blocking NIO channels, receiving broadcast messages via a `ConcurrentLinkedQueue` and delivering them to all connected clients asynchronously.

---

## Features

**Register tab** — Form to register a new pet with full validation. On success, displays a confirmation dialog and clears the form. On validation failure, displays the specific error without closing the form.

**Search tab** — Search by name, breed or type with partial match support. Results displayed in a table. From the results, staff can adopt a pet or delete a record after a confirmation dialog.

**Edit tab** — Load a pet by ID, modify any field and persist the update.

**Summary tab** — Live report showing total pets by type, average weight by type and the oldest registered pet. An Update button recalculates all values on demand.

**Notifications tab** — Real-time log of all registration and adoption events broadcast by the server.

---

## Prerequisites

- Java 21
- Docker

---

## Setup

**1. Clone the repository**

```bash
git clone https://github.com/Eduardo-Salvador/Java-Training
cd Java-OOP-Training
```

**2. Configure the `.env` file in the project root**

```env
DB_NAME=petadoption
DB_PORT=3307
DB_URL=jdbc:mysql://localhost:3307/petadoption?allowPublicKeyRetrieval=true&useSSL=false
DB_USER=root
DB_PASSWORD=123
```

**3. Start the Docker container**

```bash
docker compose up -d
```

**4. Run the application**

Run `FinalProject.Main` from your IDE or via Maven.

---

## Concepts Applied

- Object-Oriented Programming and Design Patterns (Builder, Factory, Singleton)
- Records and DTOs
- Generics
- Collections and Concurrent Data Structures
- Custom Exceptions
- Lambdas, Streams, Method References and Optional
- GUI with Swing
- Threads, NIO Channels, Sockets and CompletableFuture
- Maven
- JDBC with MySQL
- Docker
- JUnit 5 and Mockito