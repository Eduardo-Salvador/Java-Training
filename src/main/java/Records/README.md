<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Records

This module covers Records, a compact way to declare immutable data classes introduced in Java 16. The compiler automatically generates the constructor, getters, `equals()`, `hashCode()` and `toString()` based on the declared fields.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Architecture

Each folder contains the exercise class with its implementation and a `problemQuestion.txt` file with the exercise description.

- **Exercise**: `TransactionRecord`, `Main`

---

## 1. Overview

A record replaces a class that only holds data. One line substitutes constructor, getters, `equals()`, `hashCode()` and `toString()`.

All fields are implicitly `final`. You cannot change the state of a record after it is created, which makes it naturally thread-safe without any synchronization.

---

## 2. Records in Concurrent Contexts

Because records are immutable, they are ideal for carrying data between threads without risk of race conditions. In `CompletableFuture` chains you frequently need to pass results from one stage to the next, a record is a natural fit.

Immutability forces transformation instead of mutation. When a stage needs to change a value, it creates a new record rather than modifying the existing one, making the data flow explicit and safe.

---

## 3. Pros and Cons

**Pros:** eliminates boilerplate, immutability guaranteed by the compiler, thread-safe by nature, value semantics, two records with the same data are equal, expressive naming that documents intent.

**Cons:** cannot extend another class, only implement interfaces. All fields are mandatory `final`, if mutable state is needed, record does not fit. Not suitable for that require a no-arg constructor and mutable fields. Cannot add instance fields beyond those declared in the header.

---

## 4. When to Use and When Not To

Use when the object is purely a data carrier, when immutability is desired, when data will travel between threads, when you need a simple DTO in an API, or when replacing classes that only have fields and getters.

Do not use when inheritance is needed, when the entity has mutable state, when it is a database entity, or when the class requires complex business logic.

---

## 5. Exercise

### 5.1. Exercise — Transaction Record

Builds an immutable `TransactionRecord` with compact constructor validation for amount, sender and receiver. Implements an alternative constructor that generates a random `transactionId` via `UUID.randomUUID()` and sets the initial status to `"PENDING"`. Processes 3 transactions through a `CompletableFuture` pipeline that evaluates each one and creates a new record with status `"APPROVED"` or `"REQUIRES_REVIEW"` based on the transaction value.

**Key concepts:** compact constructor, field validation, alternative constructor, custom method, immutability enforcing transformation in `thenApply`, integration with `CompletableFuture`.

---

## 6. Results

- Replacing boilerplate data classes with records
- Compact constructor for field validation and transformation before assignment
- Alternative constructors delegating to the canonical one
- Custom methods inside records
- Immutability as a natural thread-safety guarantee
- Records as data carriers in `CompletableFuture` chains
- Transformation pattern: creating new records instead of mutating existing ones

---