# Lambdas

## Overview
This module contains a set of independent exercises that demonstrate the use of **Java Lambdas** and the main functional interfaces from the `java.util.function` package.  
The exercises are organized into three categories:

- **Consumer**
- **Function**
- **Predicate**

Each category contains:
- A **Domain** class that models the entity used in the exercise
- An **Application** class with the `main` method demonstrating lambda usage
- A `ProblemQuestion.txt` file with the original problem description

---

## Folder Structure

### 1. **Lambdas.Consumer**
Demonstrates the use of the **Consumer<T>** functional interface.

#### Key Concepts:
- Processing lists with lambdas
- Method references (`System.out::println`)
- Chaining consumers with `andThen`
- Executing multiple consumers dynamically

#### Domain Class: `Product`
- Fields: `name`, `price`
- Method: `processProducts(List<Product>, Consumer<Product>)`

#### Application:
- Default print consumer
- Discount consumer
- Uppercase formatting consumer
- Chaining consumers
- Executing a list of consumers

---

### 2. **Lambdas.Function**
Demonstrates the use of **Function<T,R>** and **Predicate<T>**.

#### Key Concepts:
- Mapping values using `Function`
- Filtering using `Predicate`
- Transforming collections (extracting and converting names)
- Combining filtering and transformation

#### Domain Class: `Product`
- Filters products via `filterProducts(...)`
- Maps product names via `mapProducts(...)`

#### Application:
- Filtering by price (greater-than / less-than conditions)
- Mapping names to uppercase
- Applying mapping after filtering

---

### 3. **Lambdas.Predicate**
Demonstrates the use of **Predicate<T>** for conditional evaluation.

#### Key Concepts:
- Filtering objects with custom predicates
- Combining conditions (AND)
- Negating conditions (`predicate.negate()`)

#### Domain Class: `Employee`
- Fields: `name`, `age`, `salary`
- Static method `filterEmployee(List<Employee>, Predicate<Employee>)` prints matching employees.

#### Application:
- Filtering by salary, age, name initials
- Compound conditions using multiple criteria
- Negating a predicate for inverse filtering

---

## Purpose
- Introduce Java's functional programming features
- Show real use cases for Lambdas in collections
- Demonstrate the main functional interfaces used in enterprise Java

---

## Files Included
- `Domain/` classes  
- `Application/` classes  
- `ProblemQuestion.txt`  

---

## How to Run
1. Navigate into any of the `Application` folders.
2. Run the `Main` class.
3. Observe console output illustrating lambda operations.

---

## Requirements
- Java 17+
- Basic understanding of collections and OOP

---

## Notes
- Clean lambda syntax
- Functional interface usage
- Better control of data processing pipelines in Java

---
