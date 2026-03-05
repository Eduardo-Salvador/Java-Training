<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Design Patterns

This module covers four foundational creational design patterns: Builder, Factory, Singleton and DTO. Design patterns are reusable solutions to recurring problems in software development. They are not ready-made code but models for structuring code to solve specific types of problems in an organized and maintainable way.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Architecture

- **Builder**: `PizzaOrder`, `Main`
- **Factory**: `Notification`, `EmailNotification`, `SmsNotification`, `PushNotification`, `NotificationFactory`, `Main`
- **Singleton**: `Config`, `EagerConfig`, `LazyConfig`, `LazyConfigNoThreadSafe`, `EnumConfig`, `Main`
- **DataTransferObject**: `Domain` (`User`, `UserRequestDTO`, `UserResponseDTO`), `Service` (`UserService`), `Main`

---

## 1. Builder

The Builder pattern is applied when an object requires many constructor parameters, especially when several are optional. Instead of passing everything in a single constructor call, the object is assembled step by step through a fluent interface.

The pattern consists of a private constructor in the main class, an inner static `Builder` class that accumulates the values, setter methods on the Builder that each return `this` to enable chaining, and a `build()` method that instantiates the final object.

```java
Usuario usuario = new Usuario.Builder()
    .nome("Eduardo")
    .email("eduardo@email.com")
    .idade(25)
    .build();
```

The `build()` method is also the appropriate place for validation, ensuring the object is only created in a valid state.

---

## 2. Factory

The Factory pattern centralizes and hides object creation logic. Instead of using `new` scattered throughout the codebase, creation is delegated to a single place. The caller receives an instance typed to an interface or abstract class, with no knowledge of the concrete type created.

```java
Notification notification = NotificationFactory.create("EMAIL");
notification.send("Hello!");
```

Adding a new type requires only a new class and one line in the Factory. Nothing else changes. The pattern also enforces programming to interfaces, since the returned variable is always typed to the contract rather than the implementation.

---

## 3. Singleton

The Singleton pattern guarantees that a class has only one instance throughout the application and that this instance is globally accessible. It is appropriate for resources that must exist only once, such as database connections, application configuration, and thread pools.

Three implementation approaches exist, each with different tradeoffs.

**Eager** creates the instance when the class is loaded, before any call to `getInstance()`. It is thread-safe by nature because the JVM guarantees that static initialization is atomic. The tradeoff is that the object is created even if never used.

```java
public class Config {
    private static final Config INSTANCE = new Config();
    private Config() {}
    public static Config getInstance() { return INSTANCE; }
}
```

**Lazy** creates the instance only on the first request. It avoids allocating the object if unnecessary but requires thread-safety measures. Without synchronization, two threads can enter the null check simultaneously and create two separate instances, breaking the pattern. The solution is Double-Checked Locking: the first check outside `synchronized` avoids blocking on every call once the instance exists, the second check inside guarantees only one thread creates it, and `volatile` ensures all threads see the updated value immediately without CPU cache interference.

```java
public class Config {
    private static volatile Config INSTANCE;
    private Config() {}
    public static Config getInstance() {
        if (INSTANCE == null) {
            synchronized (Config.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Config();
                }
            }
        }
        return INSTANCE;
    }
}
```

**Enum** is the simplest and safest approach. The JVM guarantees a single instance, it is thread-safe by nature like Eager, and it is protected against serialization and reflection. Serialization is a risk in traditional Singleton implementations because deserializing an object creates a new instance, breaking the guarantee. With enum this does not occur.

```java
public enum Config {
    INSTANCE;
    public void doSomething() { ... }
}
```

It is important to note that Singleton guarantees a single instance but does not guarantee thread-safe access to its internal state. Shared mutable fields such as maps or counters still require proper synchronization. `ConcurrentHashMap` is the standard choice for shared map state, and `AtomicInteger` for shared counters.

| | Eager | Lazy | Enum |
|--|-------|------|------|
| Creation | On class load | On first call | On class load |
| Thread-safe | Yes | Yes with volatile | Yes |
| Memory | Always allocates | Allocates only if used | Always allocates |
| Complexity | Simple | Medium | Simple |
| Recommended when | Always used | Heavy and occasional | General case |

---

## 4. DTO — Data Transfer Object

The DTO pattern is used to carry data between application layers. A DTO has no business logic and no behavior, it only transports data. Its purpose is to decouple what is stored internally from what is exposed externally.

Database entities typically contain more information than should be exposed. An entity may hold passwords, sensitive identifiers, and heavy relationships. Returning the entity directly exposes sensitive data, couples the API contract to the database structure, and makes any internal change a potential breaking change for the caller. The DTO acts as an isolation layer, allowing the entity to evolve internally without affecting the external contract.

```
Client -> RequestDTO -> Service -> Entity -> Database
Database -> Entity -> Service -> ResponseDTO -> Client
```

The Record type introduced in Java 16 is the natural fit for DTOs. It is immutable, eliminates boilerplate, and carries only data with no logic.

```java
public record UserResponseDTO(Long id, String name, String email) {}
public record UserRequestDTO(String name, String email, String password) {}
```

The conversion between entity and DTO is handled manually in the service layer or through mapping libraries such as MapStruct.

---

## 5. Exercises

### 5.1. Builder — PizzaOrder

Implementation of a pizza order using the Builder pattern. The `PizzaOrder` class exposes only the inner `Builder`, keeping its own constructor private. The `build()` method validates that required fields `size`, `crust` and `sauce` are present before instantiation. The `toppings` list is stored as an unmodifiable list to preserve immutability after construction.

**Key concepts:** private constructor, inner static Builder, method chaining, validation in `build()`, unmodifiable collections.

---

### 5.2. Factory — NotificationFactory

Implementation of a notification system with three concrete types: `EmailNotification`, `SmsNotification` and `PushNotification`, all implementing the `Notification` interface. The `NotificationFactory` uses a switch expression to return the correct implementation. Input is normalized with `toUpperCase()` to make type resolution case-insensitive.

**Key concepts:** interface as return type, switch expression, case-insensitive resolution, programming to interfaces.

---

### 5.3. Singleton — ApplicationConfig

Implementation of the three Singleton approaches through a shared `Config` interface. `EagerConfig` uses static final initialization. `LazyConfig` applies Double-Checked Locking with `volatile`. `EnumConfig` uses the enum approach. A fourth class `LazyConfigNoThreadSafe` was added to demonstrate the race condition in practice: when 10 threads access it concurrently before any instance exists, the output shows different hashcodes proving multiple instances were created. All thread-safe implementations use `ConcurrentHashMap` for the internal properties map.

**Key concepts:** Eager, Lazy with Double-Checked Locking, Enum Singleton, `volatile`, `synchronized`, `ConcurrentHashMap`, race condition demonstration.

---

### 5.4. DTO — UserDTO

Implementation of a user management system demonstrating the separation between internal entity and external representation. The `User` entity holds id, name, email, password and cpf internally and is built using the Builder pattern. Two Records act as DTOs: `UserRequestDTO` carries name, email and password from the client, and `UserResponseDTO` returns only id, name and email. The `UserService` handles creation, retrieval by id and listing, always converting to DTO before returning. Sensitive fields never appear in the output.

**Key concepts:** entity vs DTO separation, Record as DTO, request and response DTOs, manual conversion in the service layer, sensitive data isolation, Builder combined with DTO.

---

## 6. Results

- Builder pattern for constructing complex objects step by step with validation at build time
- Factory pattern for centralizing object creation and programming to interfaces
- Singleton pattern in three approaches: Eager, Lazy with Double-Checked Locking, and Enum
- Thread-safety in Singleton: `volatile`, `synchronized`, and `ConcurrentHashMap` for internal state
- Race condition demonstrated in practice with `LazyConfigNoThreadSafe`
- DTO pattern for isolating internal entities from external contracts
- Records as the modern approach to implementing DTOs
- Combination of patterns: Builder used inside DTO exercise for entity construction

---