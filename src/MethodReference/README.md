# Method Reference

## Overview
This module contains a collection of independent exercises demonstrating **Method References** in Java.

**Brief explanation:**  
A *method reference* is a shorthand syntax for using an existing method as a lambda expression.  
Instead of writing a lambda like `x -> obj.method(x)`, a method reference lets you write simply `obj::method`.  
They improve readability and reduce boilerplate when the lambda only calls an existing method.

The exercises follow the same structure as the previous modules, organized into packages **MethodReference.E1**, **E2**, and **E3**.  
Each exercise contains:
- A **Domain** class representing the entity used in the example
- An **Application** class demonstrating method reference usage
- A **ProblemQuestion.txt** file with the original instructions

---

## Folder Structure

### 1. MethodReference.E1  
Demonstrates method references with **instance methods**, **static methods**, and **Comparator** utilities.

**Key Concepts:**
- Referencing an instance method of a class (`Product::getName`)
- Referencing a static method (`Product::compareByPrice`)
- Combining method references with `Comparator.comparing`
- Using method references in list sorting
- Printing elements using `System.out::println`

**Domain Class: Product**
- Fields: `name`, `price`
- Methods:
  - `getName()` and `getPrice()`
  - `static compareByPrice(Product p1, Product p2)` — used as a comparator
  - `toString()` override for formatted printing

**Application:**
- Creates a list of products
- Sorts products alphabetically ignoring case using  
  `Comparator.comparing(Product::getName, String::compareToIgnoreCase)`
- Prints the list using `System.out::println`
- Sorts products by price using `Product::compareByPrice`
- Prints again using method reference

---

### 2. MethodReference.E2  
Demonstrates method references to **conversion methods** and the use of **Function**.

**Key Concepts:**
- Using method reference for parsing: `Integer::parseInt`
- Converting values back to strings: `String::valueOf`
- Applying transformations with `Function<T, R>`
- Iterating collections and applying method-referenced functions

**Domain Class: Empty**
- Placeholder domain class for structural consistency

**Application:**
- Creates a list of numeric strings
- Converts all strings to integers using `Integer::parseInt`
- Prints converted values with `System.out::println`
- Converts integers back to string using `String::valueOf`
- Prints updated list

---

### 3. MethodReference.E3  
Demonstrates method references to **custom static methods** for processing collections.

**Key Concepts:**
- Referencing custom utility methods (`Utility::printDecorated`)
- Using method references with `forEach`
- Clear separation between domain logic and formatting logic

**Domain Class: Utility**
- Static method:  
  `printDecorated(String s)` – responsible for printing strings (can be expanded in future)

**Application:**
- Creates a list of city names
- Prints the list using `System.out::println`
- Prints again using custom method reference `Utility::printDecorated`

---

## Purpose
- Introduce method references as a cleaner alternative to simple lambda expressions  
- Show practical scenarios where they increase readability  
- Demonstrate their usage together with Java’s functional interfaces and collection processing  

---

## Files Included
- **Domain/** classes (Product, Utility, Empty)
- **Application/** classes (Main for each exercise)
- **ProblemQuestion.txt** for each exercise package

---

## How to Run
1. Navigate to any **Application** folder  
2. Run the `Main` class  
3. Observe console output demonstrating method reference behavior  

---

## Requirements
- **Java 17+**
- Basic understanding of lambdas, collections, and functional interfaces  

---

## Notes
- Clean method reference syntax  
- Demonstrates all four types of method references  
  - `object::instanceMethod`  
  - `Class::staticMethod`  
  - `Class::instanceMethod`  
  - `ClassName::new` (future exercises may include this)  
- Helps simplify repetitive lambda expressions  

---