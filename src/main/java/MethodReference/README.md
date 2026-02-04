<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)

# Method References

This module contains a set of independent exercises demonstrating the use of Method References in Java, a concise syntax for referencing existing methods in place of lambda expressions.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

Method Reference is a shorthand way to use an existing method in place of a lambda expression. 

Instead of writing a lambda like `x -> obj.method(x)`, you can simply use `obj::method`.

This reduces unnecessary code and improves readability when the lambda simply calls an existing method, making the code cleaner and more expressive!

---

## Architecture:

The exercises are organized into five categories:

- **E1 - Sorting with Method Reference**
- **E2 - Type Conversion with Method Reference**
- **E3 - Utility Methods with Method Reference**
- **E4 - Instance Method Reference**
- **E5 - Constructor Reference**

Each category contains:

- A **Domain** class, which models the entity used in the exercise
- An **Application** class, with the *main* method demonstrating the use of method references
- A **ProblemQuestion.txt** file with the original problem description

---

## 1. What are Method References:

Method References are a concise way to reference methods without executing them, introduced in Java 8 alongside lambda expressions. It is essentially a **simplified syntax for lambdas that only call an existing method**.

### 1.1. Basic Syntax:

The syntax uses the **`::` operator (double colon)**:
```java
ClassName::methodName
```

### 1.2. Types of Method Reference:

**1.2.1 Reference to a static method**
```java
// Lambda
Function<String, Integer> parser = s -> Integer.parseInt(s);

// Method Reference
Function<String, Integer> parser = Integer::parseInt;
```

**1.2.2. Reference to an instance method of a specific object**
```java
String text = "Hello";

// Lambda
Supplier<Integer> length = () -> text.length();

// Method Reference
Supplier<Integer> length = text::length;
```

**1.2.3. Reference to an instance method of an arbitrary type**
```java
// Lambda
Function<String, String> upperCase = s -> s.toUpperCase();

// Method Reference
Function<String, String> upperCase = String::toUpperCase;
```

**1.2.4. Reference to a constructor**
```java
// Lambda
Supplier<ArrayList<String>> list = () -> new ArrayList<>();

// Method Reference
Supplier<ArrayList<String>> list = ArrayList::new;
```

### 1.3. Advantages of Method References:

**Cleaner code** - less verbosity than lambdas

**Reusability** - leveraging existing methods

**Readability** - clearer code intent

**Maintainability** - changes to the method are automatically reflected

### 1.4. Signature Compatibility:

For a method reference to work, it is necessary that:

1. **Number of parameters match**
2. **Parameter types are compatible**
3. **Return type is compatible**

> **In summary:** Method References are **NOT limited to Java interfaces**. They work with any functional interface (including your own), as long as the method signature is compatible with the abstract method of the interface!

---

## 2. Exercises:

### 2.1. MethodReference.E1 - Sorting with Method Reference
Demonstrates method references with instance methods, static methods, and **Comparator** utilities.

**Key concepts:**
- Reference to instance method of a class (`Product::getName`)
- Reference to static method (`Product::compareByPrice`)
- Combining method reference with `Comparator.comparing`
- Sorting lists using method references
- Printing elements using `System.out::println`

**Domain Class: Product**
- Attributes: `name`, `price`
- Methods:
  - `getName()` and `getPrice()`
  - `static compareByPrice(Product p1, Product p2)` — used as comparator
  - `toString()` overridden for formatted printing

**Application:**
- Creates a list of products
- Sorts alphabetically ignoring case using `Comparator.comparing(Product::getName, String::compareToIgnoreCase)`
- Prints the list with `System.out::println`
- Sorts by price using `Product::compareByPrice`
- Prints again using method reference

---

### 2.2. MethodReference.E2 - Type Conversion with Method Reference
Demonstrates method references applied to **conversion** methods and use of **Function**.

**Key concepts:**
- String to integer conversion with `Integer::parseInt`
- Integer to string conversion with `String::valueOf`
- Transformations applied with `Function<T, R>`
- Collection iteration applying referenced functions

**Domain Class: N/A**

**Application:**
- Creates a list of strings containing numbers
- Converts all to integers using `Integer::parseInt`
- Prints values with `System.out::println`
- Converts integers back to string using `String::valueOf`
- Prints the updated list

---

### 2.3. MethodReference.E3 - Utility Methods with Method Reference
Demonstrates the use of method references for **custom utility methods**.

**Key concepts:**
- Reference to custom static method (`Utility::printDecorated`)
- Using method reference with `forEach`
- Clear separation between domain logic and presentation logic

**Domain Class: Utility**
- Static method: `printDecorated(String s)` — responsible for printing strings with custom formatting

**Application:**
- Creates a list of city names
- Prints the list using `System.out::println`
- Prints again using the method reference `Utility::printDecorated`

---

### 2.4. MethodReference.E4 - Instance Method Reference
Demonstrates reference to **instance method of a specific object**.

**Key concepts:**
- Reference to instance method (`object::instanceMethod`)
- Using `Consumer<T>` with method reference
- Composing method references with transformations
- Reusing method references

**Domain Class: MessagePrinter**
- Method: `printMessage(String message)` — prints the message with additional formatting

**Application:**
- Creates an instance of `MessagePrinter`
- Creates a list of messages
- Uses `Consumer<String>` with method reference `messagePrinter::printMessage`
- Iterates through the list applying the consumer
- Combines method reference with `String::toUpperCase` for transformation
- Prints messages in uppercase using composition

---

### 2.5. MethodReference.E5 - Constructor Reference
Demonstrates reference to **constructor** using `Class::new`.

**Key concepts:**
- Reference to constructor (`User::new`)
- Using `BiFunction<T, U, R>` with constructor reference
- Creating custom functional interface (`UserFactory`)
- Dynamic object construction from multiple data sources

**Domain Class: User**
- Attributes: `name`, `age`
- Constructor: `User(String name, Integer age)`

**Domain Class: UserFactory**
- Custom functional interface
- Abstract method: `User create(String name, Integer age)`

**Application:**
- Creates separate lists of names and ages
- Uses `BiFunction<String, Integer, User>` with `User::new`
- Combines data from lists to create `User` objects
- Demonstrates the use of custom functional interface
- Uses `UserFactory` with the same constructor reference
- Prints all created users

---

## 3. Objectives Achieved

**Complete mastery of the four types of method reference**
- Reference to static method
- Reference to instance method of specific object
- Reference to instance method of arbitrary type
- Reference to constructor

**Practical application of method references**
- Replacing simple lambdas with method references
- Cleaner and more readable code
- Reusing existing methods

**Integration with functional interfaces**
- Usage with `Consumer`, `Function`, `BiFunction`
- Creating custom functional interfaces
- Composition and chaining of operations

**Advanced techniques**
- Sorting with `Comparator` and method references
- Data transformation with type conversions
- Dynamic object construction
- Separation of concerns (domain vs presentation)

---