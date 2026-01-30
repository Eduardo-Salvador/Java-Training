<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)

# Parameterizing Behaviors

This module offers a comprehensive introduction to Parameterized Behaviors.
The goal is to provide a practical understanding of how parameterized behaviors make code more flexible, allowing you to pass behaviors (logic/action) as parameters to methods.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

This module contains an exercise demonstrating the use of **Parameterized Behaviors** in Java.

The idea is to show how to parameterize behaviors dynamically, avoiding rigid conditionals and allowing greater flexibility in the code.

---

## Architecture
The exercise is organized into three main packages:

- **Domain** — contains the `PaymentStrategy` interface
- **Services** — contains the `PaymentProcessor` class responsible for processing payments
- **Application** — contains the `Main` class demonstrating the use of payment strategies

There is also a **ProblemQuestion.txt** file with the original problem statement.

---

## 1. What are Parameterized Behaviors?
It is the **basis of functional programming and Java 8+ Streams**

Parameterization of behaviors serves to ***make code more flexible, allowing you to pass behaviors (logic/action) as parameters to methods***, instead of just data. ### 1.1. Main Uses:

### 1.1. Main uses:

**Code Reuse** - A single method can have different behaviors without code duplication.

**Flexibility** - You define "what to do" at the time of the call, not when you write the method.

**Eliminates Conditionals** - Instead of multiple `if/else` statements for different behaviors, you pass the logic directly.

### 1.2. Functional Interfaces are used to parameterize behaviors:

```java
// Predicate<T> - receives T, returns boolean (tests/filters)
Predicate<Integer> isEven = n -> n % 2 == 0;
boolean result = isEven.test(4); // true

// Consumer<T> - receives T, returns nothing (actions)
Consumer<String> print = s -> System.out.println(s);
print.accept("Hello"); // prints "Hello"

// Function<T, R> - receives T, returns R (transformations)
Function<String, Integer> size = s -> s.length();

int size = size.apply("Java"); // 4

// Supplier<T> - receives nothing, returns T (value generation)
Supplier<Double> random = () -> Math.random();
double value = random.get(); // random number

// BiFunction<T, U, R> - receives T and U, returns R
BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

int sum = sum.apply(5, 3); // 8

// Comparator<T> - compares two objects
Comparator<Product> byPrice = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
```

### You can also create your own functional interfaces:

```java
@FunctionalInterface
public interface Calculator {
  double calculate(double a, double b);
}

// Usage
public double execute(double x, double y, Calculator calc) {
  return calc.calculate(x, y);
}

execute(10, 5, (a, b) -> a + b); // sum = 15
execute(10, 5, (a, b) -> a * b); // multiplication = 50
execute(10, 5, (a, b) -> a - b); // subtraction = 5
```

> You define an **interface with a method (parameter)**, and pass the **implementation of that method as a lambda, anonymous class, or reference** when needed.

- `Predicate` and others are just one of the ready-made interfaces that Java offers!

---

## 2. Exercise Structure:

### 2.1. ParameterizingBehaviors.Domain
Contains the interface that defines the contract for payment strategies.

**Interface: PaymentStrategy**

- Method: `void pay(double amount)`
- Represents the payment behavior that can be dynamically parameterized

---

### 2.2. ParameterizingBehaviors.Services
Contains the class responsible for processing payments using a provided strategy.

**Class: PaymentProcessor**

- Method: `processPayment(double amount, PaymentStrategy strategy)`
- Receives the amount and the payment strategy
- Calls `strategy.pay(amount)` to execute the parameterized behavior

---

### 2.3. ParameterizingBehaviors.Application
Demonstrates multiple real-world scenarios for using parameterized behaviors.

**Class: Main**
Executes different payment strategies:

- **Credit Card Strategy**
  - Applies a 2% discount if the amount > 3000
  - Displays a payment message via credit card
- **Pix Strategy**
  - Applies a 10% discount if the amount > 3000
  - Displays a payment message via Pix
- **Bank Slip Strategy**
  - Applies a 5% discount if the amount > 3000
  - Displays a payment message via bank slip

**Demonstrated Scenarios:**
- Processing a single payment with `PaymentProcessor`
- Iterating over a list of strategies (`List<PaymentStrategy>`)
- Executing multiple payments with different parameterized behaviors.

> Both ways of using behaviors were applied: via Lambdas (next project topic on Github) and via Anonymous Classes.

---

## 3. Summary:

- Use of different forms of behavioral parameters.
- Demonstrate good practices for avoiding rigid conditionals.
- Show how to encapsulate different business logics in independent strategies.
- Encourage modular and extensible design.
- Avoid fixed and repetitive conditionals.
- Demonstrate how different strategies can be applied dynamically.
- Clearly show how the Object-oriented signage facilitates extensibility and maintenance.

---