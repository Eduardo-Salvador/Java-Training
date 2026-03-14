# Final Project

# Pet Adoption System v2.0

---

## Objective

This project is the final challenge of the Java Training repository. It expands the CLI system from v1.0 into a complete desktop application that integrates every topic covered throughout the course.

The system manages a pet adoption shelter. The shelter has a growing number of animals waiting to be adopted, and the staff needs a tool to register new pets, track their status, search for them by different criteria, process adoptions, and monitor the overall state of the shelter through a summary report.

The application has three main areas accessible through a graphical interface with tabs:

**Registration:** The staff registers a new pet by filling in a form with the animal's name, type, sex, age, weight, breed and address of origin. All fields are validated before the pet is saved. Required fields cannot be left blank. Age must be a number between 0.0 and 20.0, where values below 1.0 represent age in fraction of year (for example, 0.5 means six months). Weight must be between 0.5 kg and 60.0 kg. Name and breed must contain only letters. When the registration succeeds, a confirmation message is shown. When validation fails, the specific error is shown to the staff without closing the form.

**Search and Adoption:** The staff searches for pets by name, breed or type. Partial matches are supported, so searching for "flu" would find "Fluffy". The results are displayed in a table showing all relevant information. From the results, the staff can select a pet and mark it as adopted, which changes its status and removes it from the available pool. The staff can also delete a pet record after a confirmation step. Only one pet can be adopted or deleted at a time.

**Report:** The staff views a live summary of the shelter showing the total number of pets grouped by type, the average weight per type, and the oldest registered pet. A refresh button recalculates everything on demand.

In addition to the graphical interface, the system runs a background notification server. Every time a pet is registered or marked as adopted, a message is automatically broadcast to all connected clients in real time. This simulates a multi-terminal shelter environment where different staff members on the same network receive updates without refreshing manually.

The shelter owner can:

- Register a new pet through a graphical form
- Search for pets by name, breed or type with partial match support
- Adopt a pet directly from the search results
- Delete a pet record from the search results
- View a live shelter summary report
- Receive real-time notifications on any connected terminal when a pet is registered or adopted

The system must persist all data in a MySQL database running in a Docker container, apply layered architecture with separation between domain, repository, service and GUI, and cover the entire service layer with unit tests using JUnit 5 and Mockito.

---

## Knowledge Applied

- Object-Oriented Programming and Design Patterns (Builder, Factory, Singleton)
- Records and DTOs
- Generics
- Collections and Concurrent Data Structures
- Exceptions (custom exceptions)
- Utility Classes
- Lambdas, Streams, Method References and Optional
- GUI with Swing
- Threads, Channels, Sockets and CompletableFuture
- Maven
- JDBC with MySQL
- Docker
- JUnit 5 and Mockito

---

## Architecture

The project must follow a layered architecture with the following package structure:

```
domain/         Pet entity, enums (PetType, PetSex, PetStatus), DTOs as Records
repository/     Generic PetRepository interface and PetRepositoryImpl with JDBC
service/        PetService — all business logic, Streams and Lambdas live here
factory/        PetFactory — centralizes Pet creation from request DTOs
config/         DatabaseConfig — Singleton managing the JDBC connection
gui/            MainFrame, RegisterPanel, SearchPanel, ReportPanel (Swing)
server/         NotificationServer and NotificationClient (Channels and Sockets)
util/           PetValidator — static validation methods
exception/      PetNotFoundException, PetValidationException, DatabaseConnectionException
test/           PetServiceTest and PetRepositoryTest (JUnit 5 + Mockito)
```

---

## Step-by-Step Features

### Step 1: Infrastructure

Configure the Maven project with all required dependencies. Create the `docker-compose.yml` and `.env` files. The `.env` file must store the database name, password and port. MySQL must run in a Docker container. Create the `init.sql` file that initializes the schema automatically on first startup. The `pets` table must contain at minimum: `id`, `name`, `type`, `sex`, `age`, `weight`, `breed`, `address`, `status` and `created_at`.

**Rules:**
- All connection parameters must be read from `.env`, not hardcoded
- The application must not start if the database connection fails
- Log4J2 must log all SQL operations at DEBUG level and all errors at ERROR level

---

### Step 2: Domain Layer

Create the `PetType` enum with at least: `DOG`, `CAT`, `BIRD`, `RABBIT`, `OTHER`.

Create the `PetSex` enum with: `MALE`, `FEMALE`.

Create the `PetStatus` enum with: `AVAILABLE`, `ADOPTED`, `UNDER_TREATMENT`.

Create the `Pet` entity using the **Builder pattern**. The entity must have a private constructor, an inner static `Builder` class, and a `build()` method that validates required fields before instantiation. Required fields are `name`, `type`, `sex` and `age`. All other fields are optional.

Create `PetRequestDTO` and `PetResponseDTO` as **Records**. The `PetRequestDTO` carries data from the GUI to the service. The `PetResponseDTO` carries data from the service back to the GUI. The `Pet` entity must never reach the GUI layer directly.

Create `PetFactory` using the **Factory pattern**. It must expose a single static method `create(PetRequestDTO request)` that returns a fully built `Pet`.

**Rules:**
- The `Pet` constructor must be private, only the Builder can instantiate it
- `build()` must throw `PetValidationException` if any required field is null or blank
- `PetRequestDTO` and `PetResponseDTO` must be Records, no classic classes

---

### Step 3: Repository Layer

Create the generic `PetRepository` interface:

```
PetRepository<T, ID>
    void save(T entity)
    Optional<T> findById(ID id)
    List<T> findAll()
    List<T> findByType(PetType type)
    void update(T entity)
    void delete(ID id)
```

Implement `PetRepositoryImpl` which implements `PetRepository<Pet, Long>`. All operations must use `PreparedStatement`. The connection must come from `DatabaseConfig` (Singleton). Generated keys must be retrieved and set back on the entity after `save`. Log every operation.

Create `DatabaseConfig` using the **Singleton pattern** with the Eager approach. It must read connection parameters from the `.env` file using `dotenv-java`. It must expose a single `getConnection()` method.

**Rules:**
- No raw `Statement` allowed, only `PreparedStatement`
- `findById` must return `Optional.empty()` when no result is found, not null
- All `SQLExceptions` must be caught and rethrown as `DatabaseConnectionException`

---

### Step 4: Service Layer

Create `PetValidator` with static methods. It must validate:

- Name: mandatory, letters only, minimum 2 characters
- Age: between 0.0 and 20.0 (values below 1.0 represent age in fraction of year)
- Weight: between 0.5 kg and 60.0 kg
- Address: must contain street, number and city

Create `PetService` which depends on `PetRepository<Pet, Long>` and `NotificationServer` injected via constructor. The service must maintain an in-memory `CopyOnWriteArrayList<Pet>` cache populated on startup from the database.

The following methods must be implemented using **Streams and Lambdas**, no imperative loops:

`register(PetRequestDTO)` validates the request, delegates creation to `PetFactory`, persists via repository, adds to cache, and broadcasts a notification asynchronously using `CompletableFuture.runAsync()`. Returns `PetResponseDTO`.

`findById(Long)` returns `Optional<PetResponseDTO>`. Throws `PetNotFoundException` if absent.

`findAll()` returns `List<PetResponseDTO>` from the cache using `stream().map()`.

`findByNameAndBreed(String name, String breed)` filters the cache using `filter()` with case-insensitive `contains`. Returns `List<PetResponseDTO>`.

`findByType(PetType)` filters the cache. Returns `List<PetResponseDTO>`.

`adopt(Long id)` changes the pet status to `ADOPTED`, updates via repository, updates the cache, and broadcasts a notification. Throws `PetNotFoundException` if id does not exist.

`countByType()` returns `Map<PetType, Long>` using `Collectors.groupingBy` and `Collectors.counting`.

`findOldest()` returns `Optional<PetResponseDTO>` using `Stream.max`.

`averageWeightByType()` returns `Map<PetType, Double>` using `Collectors.groupingBy` and `Collectors.averagingDouble`.

**Rules:**
- No `for` or `while` loops in `PetService`, all operations must use Streams
- `findById`, `findOldest` and every lookup that may find nothing must return `Optional`
- `CopyOnWriteArrayList` is mandatory for the cache, justify the choice in a comment
- Notification broadcast must be async and must not block the caller

---

### Step 5: Notification Server

Implement `NotificationServer` using `ServerSocketChannel` in non-blocking mode with a `Selector`. The server must run on a dedicated thread started when the application launches. It must accept multiple client connections and broadcast messages to all connected clients when `broadcast(String message)` is called.

Implement `NotificationClient` that connects to the server on a dedicated thread and prints incoming messages to a log panel in the GUI.

**Rules:**
- The server must not run on the Swing event dispatch thread
- Broadcasting must not block the caller, it must be called via `CompletableFuture.runAsync()`
- Use `ConcurrentLinkedQueue` to buffer pending broadcast messages safely between threads

---

### Step 6: GUI with Swing

Implement `MainFrame` as the application window. It must use a `JTabbedPane` with three tabs:

**Register Tab** — `RegisterPanel`
Form with `JTextField` for name, breed, address and age. `JComboBox` for type and sex. A submit button that calls `service.register()`. On success, show a confirmation dialog and clear the form. On `PetValidationException`, show the validation message in an error dialog without closing the form. On any other exception, show a generic error dialog.

**Search Tab** — `SearchPanel`
Search form with fields for name and breed and a type filter via `JComboBox`. A search button that calls `service.findByNameAndBreed()` or `service.findByType()`. Results displayed in a `JTable` built from a `List<PetResponseDTO>`. Selecting a row and clicking Adopt calls `service.adopt()` and refreshes the table. Selecting a row and clicking Delete calls `service.delete()` after a YES/NO confirmation dialog and refreshes the table. Only one row can be selected at a time.

**Report Tab** — `ReportPanel`
Summary panel that displays: total pets per type (from `countByType()`), average weight per type (from `averageWeightByType()`), and the oldest registered pet (from `findOldest()`). A Refresh button recalculates all values live using Streams.

**Rules:**
- All service calls from the GUI must be performed on the Swing event dispatch thread via `SwingUtilities.invokeLater()` or `SwingWorker` if the operation is slow
- The GUI must never call the repository directly, only the service
- No null checks on search results, use `Optional` and handle empty cases with `ifPresentOrElse`

---

### Step 7: Tests

Write `PetServiceTest` using JUnit 5 and Mockito. The repository and the notification server must be mocked. Tests must cover:

- `register` — happy path, returns correct DTO and calls `repository.save` exactly once
- `register` — throws `PetValidationException` when name is blank
- `findById` — returns correct DTO when pet exists
- `findById` — throws `PetNotFoundException` when id does not exist
- `adopt` — changes status and calls `repository.update` exactly once
- `adopt` — throws `PetNotFoundException` when id does not exist
- `findByNameAndBreed` — returns filtered results correctly
- `countByType` — returns correct counts per type

Write `PetRepositoryTest` covering `save`, `findById`, `findAll`, `update` and `delete` against the real Docker MySQL container or an H2 in-memory database.

**Rules:**
- No `@Mock` on classes with static methods — Mockito cannot mock static methods
- Each test must have a single assertion focus — one behavior per test
- Naming convention: `shouldReturnX_WhenY`
- `@BeforeEach` must reset the service state before each test

---

## Summary

This system integrates every topic from the Java Training repository into a single coherent application. Each concept solves a problem that exists naturally in the domain: Builder handles a complex entity with optional fields, Streams replace imperative loops in filtering and reporting, CopyOnWriteArrayList protects the shared cache from concurrent access, Channels and Sockets deliver real-time notifications to connected terminals, and Mockito isolates the service layer in unit tests by replacing the database with a mock. No topic is included artificially.

---