# Generics

This module provides a complete introduction to Java Generics, focusing on type parameterization, generic classes, generic methods, wildcards, and the interaction between generics and polymorphism.
All exercises include their own ProblemQuestion.txt file inside the corresponding directory.

---

## 1. Foundations: What Are Generics?
Generics allow types (classes and interfaces) to be parameters when defining classes, methods, and interfaces.

You create Classes, Methods, etc., and you don't define what type it will receive, thus making it a Generic type, able to receive and work with the same behavior for various types, avoiding rewriting the same code multiple times.

They enable:
- Type safety (compile-time validation of types)
- Prevention of ClassCastException
- Reusability of algorithms and data containers
- More expressive APIs

In Java, generics are implemented via type erasure, but the syntax allows developers to model logic in a type-safe and flexible way.

**Typical notation:**

    class Box<T> { ... }
    T value;
    List<String> list;

---

## 2. Generic Classes
A generic class declares one or more type parameters.
This allows the same class to store or process different kinds of objects without rewriting code.

Example from the module:

### Box<T>
    Box<String> stringBox = new Box<>("Hello");

    Box<Integer> numberBox = new Box<>(10);

    Box<Animal> animalBox = new Box<>(new Animal("Bolt"));

**Characteristics:**
- T is a placeholder for a real type.
- Each instance binds T to a specific type.
- Eliminates need for casting.
- Increases reusability.

### Pair<K, V>
A generic class with two type parameters:

    Pair<String, Integer> age = new Pair<>("Age", 25);

    Pair<String, String> name = new Pair<>("Name", "John");

Used to model key–value structures or dual associations.

---

## 3. Generic Methods

A **generic method** declares its own type parameters independently of the class it is in.

Syntax:

    public static <T> void printArray(T[] array) { ... }

Examples from this module:
- printArray(T[] array)
- maxValue(T a, T b, T c) with <T extends Comparable<T>>
- containsElements(List<T> list, T element)
- swap(T[] array, T e1, T e2)
- copyList(List<? extends T> from, List<? super T> to)

Generic methods provide:
- Reusable logic independent of object types
-  Strong compile-time checking
-  in algorithms and utilities

---

## 4. Wildcards in Generics
Wildcards (?) allow controlled flexibility when working with groups of related types.

There are three main forms:

### 4.1 ? extends T — Upper-Bounded Wildcard
Used when reading objects as type T or its subclasses.

**Meaning:**
“The list contains objects that are T or any subclass of T.”

Example:

    public static void printAnimalSounds(List<? extends Animal> animals)

**Use cases:**
- You consume values (read)
- You do not insert new specific subclasses safely

### 4.2 ? super T — Lower-Bounded Wildcard
Used when writing/inserting.

**Meaning:**
“The list accepts T or any superclass of T.”

Example:

    public static void add(List<? super Animal> list, List<? extends Animal> animals)

**Use cases:**
- Used for inserting objects of type T
- Supports covariance when adding elements

### 4.3 Unbounded Wildcard ?

**Meaning:**
“I don't know the type.”

*Use cases:**
- You only want to read Object
- Type is irrelevant for the operation

---

## 5. Wildcards and Polymorphism
Wildcards enable polymorphic behavior inside generic structures.

Example in this module:

    List<Dog> dogs;

    List<Cat> cats;

    List<Lion> lions;

    AnimalManager.printAnimalSounds(dogs);

    AnimalManager.printAnimalSounds(cats);

    AnimalManager.printAnimalSounds(lions);


Without wildcards:
- You could not pass List<Dog> to a method expecting List<Animal>
- Java generics are invariant (List<Dog> ≠ List<Animal>)

With ? extends Animal:
- Any subtype list works polymorphic
- Each animal still executes its own overridden makeSound()

This demonstrates:
- Compile-time type safety
- Runtime polymorphism with overridden behavior
- Contravariance and covariance patterns

---

## 6. Module Overview and Exercise Documentation
Each directory contains a ProblemQuestion.txt describing the requirements for that exercise.

---

### 6.1 Generic Classes
#### Covers:
- Creating generic containers
- Using single type parameters (T)
- Using multiple type parameters (K, V)
- Overriding toString()
- Working with various bindings (String, Integer, domain objects)

#### Exercises:
**Box<T>**
- Store and return generic content
- Demonstrate binding to different types
- Application: Box<T>, Main

**Pair<K, V>**
- Store key/value pairs using generics
- Multiple type parameters
- Application: Pair<K, V>, Main

---

### 6.2 Generic Methods
#### Covers:
- Defining independent type parameters
- Array processing with generics
- Using bounded types (<T extends Comparable<T>>)
- Working with lists
- Implementing utilities such as swap, copy, max value, contains

#### Exercises:
**printArray()**
- Identify array type at runtime
- Iterate over generic arrays

**maxValue()**
- Determine maximum element using Comparable
- containsElements()
- Check list membership generically

**swap()**
- Swap two elements inside a generic array

**copyList()**
- Uses wildcards:
  - < ? extends T > for reading 
  - < ? super T > for inserting
- Practical demonstration of PECS (Producer Extends, Consumer Super)

---

### 6.3 Wildcards and Polymorphism
#### Covers:
- Covariant lists (? extends Animal)
- Contravariant lists (? super Animal)
- Polymorphic behavior inside bounded wildcards
- Methods that accept specialized lists while preserving safety

#### Exercises:

**printAnimalSounds()**
- Accepts List<? extends Animal>
- Prints each animal
- Calls overridden makeSound()

**add()**
- Demonstrates safe insertion using ? super Animal
- Allows merging lists of subtypes into a parent list

Domain:
- Animal, Dog, Cat, Lion
- Controllers: AnimalManager

---

## 7. Summary

This Generics module provides a complete foundation for understanding:
- Type parameterization and generic type safety
- How to create and use generic classes
- How to implement generic methods with bounded and unbounded parameters
- How wildcards enable controlled polymorphism within collections
- The distinction between extends and super in generic contexts
- Reusable utilities that adapt to different type bindings
- Practical data manipulation with generics and polymorphism

Every exercise is paired with a ProblemQuestion.txt, ensuring that each concept is learned through structured practice.

---