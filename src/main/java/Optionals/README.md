<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Optionals

This module contains a comprehensive exercise demonstrating the use of Optional in Java, a container class introduced in Java 8 to elegantly handle the presence or absence of values.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

Optional is a **container class introduced in Java 8 that serves as a wrapper for values that may or may not be present.** It's an **elegant way to handle the possibility of absent values, avoiding the dreaded `NullPointerException`.**

Before Optional, it was common to return `null` when a value wasn't available, leading to code filled with null checks and potential errors. **Optional makes it explicit in the code that a value may not exist**, improving code safety and readability.

---

## Architecture:

The project is organized as follows:

- **Domain**: Contains the `User` class modeling the entity
- **Services**: Contains `UserRepository` with business logic for user search
- **Application**: Contains the `Main` class demonstrating all Optional methods

---

## 1. What is Optional:

Optional is a container object that may or may not contain a non-null value. It was introduced in Java 8 to **provide a type-level solution for representing optional values instead of null references**.

### 1.1. Creating an Optional:

There are three main ways to create an Optional:
```java
// Empty Optional
Optional<String> empty = Optional.empty();

// Optional with value (throws exception if null)
Optional<String> withValue = Optional.of("John");

// Optional that can be null
Optional<String> maybeNull = Optional.ofNullable(possiblyNull);
```

### 1.2. Main Methods:

**Check presence:**
- `isPresent()` — returns true if value exists
- `isEmpty()` — returns true if empty (Java 11+)

**Get value:**
- `get()` — returns the value (throws exception if empty)
- `orElse(value)` — returns the value or a default
- `orElseGet(supplier)` — returns the value or executes function
- `orElseThrow()` — throws exception if empty

**Transform:**
- `map(function)` — transforms the value if present
- `flatMap(function)` — similar to map, but for functions that return Optional
- `filter(predicate)` — filters based on condition

**Execute actions:**
- `ifPresent(consumer)` — executes action if value exists
- `ifPresentOrElse(consumer, runnable)` — executes action or alternative

### 1.3. Benefits of Using Optional:

**Null-safety** — reduces NullPointerException occurrences

**Explicit API** — makes clear when values may be absent

**Functional style** — enables method chaining and transformations

**Better documentation** — method signatures communicate intent

### 1.4. Best Practices:

1. **Never use `Optional.get()` without checking** — use safer alternatives like `orElse()` or `orElseThrow()`
2. **Avoid Optional in fields** — designed for return types, not storage
3. **Don't use Optional with collections** — empty collections are better than `Optional<List>`
4. **Prefer `orElseGet()` over `orElse()`** — when default value creation is expensive

> **In summary:** Optional is a **powerful tool for writing safer, more expressive code** that explicitly handles the absence of values, eliminating much of the need for null checking!

---

## 2. Exercise:

### 2.1. User Profile Search with Optional

Demonstrates comprehensive usage of Optional methods in a user search scenario.

**Key Concepts:**
- Creating and returning Optional values
- Checking presence with `isPresent()` and `isEmpty()`
- Retrieving values with `orElse()`, `orElseGet()`, and `orElseThrow()`
- Executing actions with `ifPresent()` and `ifPresentOrElse()`
- Transforming values with `map()`
- Filtering values with `filter()`
- Method chaining for concise code

**Domain Class: User**
- Attributes: `id`, `name`, `email`
- All attributes are final (immutable)
- Methods:
  - `getId()`, `getName()`, `getEmail()`
  - `toString()` override for formatted output

**Service Class: UserRepository**
- Method: `findByEmail(List<User> userList, String email)`
  - Returns `Optional.of(user)` when found
  - Returns `Optional.empty()` when not found
  - Demonstrates proper Optional creation patterns

**Application:**
- Creates a list of 7 users with different email providers
- **Direct Optional printing** — displays Optional container
- **`ifPresent()`** — prints user if found
- **`ifPresentOrElse()`** — prints user or "User not found" message
- **`orElse()`** — returns default user when not found
- **`orElseGet()`** — creates fallback user only when necessary (lazy evaluation)
- **`orElseThrow()`** — throws custom exception with proper error handling
- **`map()`** — extracts only the username from Optional<User>
- **`filter()`** — returns Optional only if email contains "@gmail.com"

---

## 3. Learning Outcomes

**Complete mastery of Optional creation**
- Understanding `of()`, `empty()`, and `ofNullable()`
- Knowing when to use each creation method

**Safe value retrieval**
- Avoiding `get()` without checks
- Using `orElse()`, `orElseGet()`, and `orElseThrow()` appropriately
- Understanding lazy vs eager evaluation

**Conditional execution**
- Using `ifPresent()` for side effects
- Implementing `ifPresentOrElse()` for complete control flow

**Functional transformations**
- Applying `map()` for value transformation
- Using `filter()` for conditional Optional results
- Method chaining for cleaner code

**Exception handling**
- Creating custom exceptions with `orElseThrow()`
- Proper try-catch usage with Optional

**Best practices**
- Writing null-safe code
- Making APIs more expressive
- Improving code readability and maintainability

---