<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)

# Generics

This module provides a complete introduction to Generics in Java, focusing on type parameterization, generic classes, generic methods, wildcards, and the interaction between generics and polymorphism. All exercises include their own **ProblemQuestion.txt** file within the corresponding directory.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview
- Generics are resources for working with parameterized types. They make collections safer and more reusable.**
- Uses the **diamond operator for typing <>, always considering an object, whether it's a Wrapper or not.**
- **Type Safety is the process by which the Java compiler removes generic type information during compilation.** This means that generic types are only used to ensure type safety at compile time, but are never executed by the JVM. Thus maintaining compatibility with older versions.

**They enable:**
- Type safety (compile-time validation)
- Prevention of `ClassCastException`
- Reuse of algorithms and data structures.

- More expressive APIs.

**Typical notation:**

```java
class Box<T> { ... }
T value;
List<String> list;
List<? extends String>;
List<? super String>;
```

---

## Architecture:

- **Domain** → Classes and Entities.
- **Services** → Business rules and data manipulation.
- **App** → Initializes the application.

---

## What Was Used

### 1. Wildcards in Generics

Wildcards (`?`) allow controlled flexibility when working with related types.

Also called a wildcard character, it uses concepts such as polymorphism.

### 1.1 `? extends T` — Upper-Bounded Wildcard
Used for reading objects of type `T` or subclasses.

#### Example:
```java
public static void printAnimalSounds(List<? extends Animal> animalList){
    for (Animal a : animalList){
        System.out.println(a);
        a.makeSound();
    } // -> Everything that extends (IS A) Animal. (Dog dog = new Dog())
}
```

#### Usage:
- Consume values
- Prevent unsafe insertion of subclasses
- Accepts any type of object that extends Animal, however, I can no longer add things to the list in the method, **read-only.**
- ```? extends Type``` **and Classes below in the hierarchy** (read-only because it doesn't guarantee that it's the supertype that is used as a reference).

---

### 2. Wildcards and Polymorphism

Wildcards allow polymorphic behavior within generic structures.

Example:

```java
List<Dog> dogs;
List<Cat> cats;
List<Lion> lions;

AnimalManager.printAnimalSounds(dogs);
AnimalManager.printAnimalSounds(cats);
AnimalManager.printAnimalSounds(lions);
```

#### Without wildcards:

- It would not be possible to pass `List<Dog>` to a method that expects `List<Animal>`
- Generics are invariant.

#### With `? extends Animal`:

- Any list of subtypes works.
- Each animal executes its own `makeSound()` function.

#### Demonstrates:

- Compile-time type safety
- Runtime polymorphism
- Covariance and contravariance patterns.

### 3. Generic Classes

A generic class declares one or more type parameters. This allows the same class to store or process different types without rewriting code.

### Box
```java
Box<String> stringBox = new Box<>("Hello");
Box<Integer> numberBox = new Box<>(10);
Box<Animal> animalBox = new Box<>(new Animal("Bolt"));
```

#### Characteristics:

- `T` is a placeholder for a real type.
- Each instance associates `T` with a specific type.
- Eliminates the need for casting.
- Increases reusability.

### Pair<K, V>

A generic class with two type parameters:
```java
Pair<String, Integer> age = new Pair<>("Age", 25);
Pair<String, String> name = new Pair<>("Name", "John");
```

Used to model key-value structures or double associations.

---

### 4. Generic Methods

A generic method declares its own type parameters independently of the class it belongs to.

#### Syntax:

```java
public static <T> void printArray(T[] array) { ... }
```
- `printArray(T[] array)`
- `maxValue(T a, T b, T c)` with `<T extends Comparable>`
- `containsElements(List list, T element)`
- `swap(T[] array, T e1, T e2)`
- `copyList(List<? extends T> from, List<? super T> to)`

#### Generic methods offer:

- Reusable logic independent of concrete types.
- Strong verification at compile time.
- Usefulness in algorithms and tools.

---

### 5. Exercises:

### 5.1 Wildcards and Polymorphism
Covers:

- Covariant lists (`? extends Animal`)
- Contravariant lists (`? super Animal`)
- Polymorphism within generics
- Methods that accept specialized lists while maintaining safety

Exercises:
#### printAnimalSounds()
- Accepts `List<? extends Animal>`
- Prints each animal
- Calls an overridden `makeSound()`

#### add()
- Demonstrates safe insertion with `? super Animal`
- Joins objects of subtypes into a parent list

Domain:

- `Animal`, `Dog`, `Cat`, `Lion`
- `AnimalManager`

---

### 5.2 Generic Classes
#### Covers:

- Creation of generic containers.
- Use of single parameters (`T`).
- Use of multiple parameters (`K`, `V`).
- Overriding `toString()`.
- Use with various types (String, Integer, domain classes).

Exercises:

#### Box
- Stores and returns generic content.
- Application: `Box`, `Main`

#### Pair<K, V>
- Stores key/value pairs.
- Uses multiple type parameters.
- Application: `Pair<K, V>`, `Main`

---

### 5.3 Generic Methods
#### Covers:

- Definition of independent parameters.
- Processing of generic arrays.
- Bounded types (`<T extends Comparable>`).
- Operations with lists.
- Utility functions such as swap, copy, max, contains.

Exercises:
#### printArray()
- Identifies array type at runtime
- Iterates over generic arrays

#### maxValue()
- Determines the largest element using `Comparable`

#### containsElements()
- Checks membership in lists generically.

#### swap()
- Swaps two elements of a generic array.

#### copyList()
- Uses wildcards:
- `? extends T` for reading.
- `? super T` for writing.
- Demonstrates the PECS principle (Producer Extends, Consumer Super).

---

### 6. Learning Objectives:

- Type parameterization and compile-time safety.
- How to create and use generic classes.
- How to implement generic methods with bounded or unbounded parameters.
- How wildcards allow controlled polymorphism.
- Difference between `extends` and `super`.
- Creation of reusable generic utilities.
- Practical data manipulation with generics and polymorphism.

---