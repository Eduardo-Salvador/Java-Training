<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)

# Inner Classes

This module provides a comprehensive introduction to Inner Classes.

The goal is to provide hands-on understanding of how inner classes work, when to use them, and how they improve organization, encapsulation, and code expressiveness.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

This project contains a collection of independent exercises focused on Inner Classes in Java, organized into the following packages:
- InnerClasses.InnerClasses — Member Inner Classes
- 	InnerClasses.LocalClasses — Local Classes
- InnerClasses.AnonymousClasses — Anonymous Classes
-	InnerClasses.StaticNestedClasses — Static Nested Classes

---

## Architecture

- A minimal structure with Application and Domain layers
- Independent logic showcasing different inner-class mechanisms
- A ProblemQuestion.txt file describing the original exercise
- Practical examples of:
	- Member Inner Classes
	- Local Classes
	- Anonymous Classes
	- Static Nested Classes
	- Encapsulation and scope relations
	- Modular organization using nested structures

---

### 1. What Are Inner Classes?

Inner Classes are classes declared inside other classes.
They allow:
	- Strong grouping of related classes
	- Direct access between inner and outer contexts
	- Greater encapsulation
	- Clear modular organization when objects are tightly related

---

### 2. Main Types

#### 2.1. Inner Classes (Non-Statics)
- Declared inside another class (but outside methods).
- Have full access to the outer class instance.

#### 2.2. Local Classes
- Classes declared inside a method.
- They only exist within the method where they are defined.

#### 2.3. Anonymous Classes
- Classes created without a name, usually to override methods or implement interfaces inline.

#### 2.4. Static Nested Classes
- Declared as static.
- Do not depend on an instance of the outer class.

---

### 3. Concepts Used

#### 3.1. The exercises demonstrate:
- Object composition through inner classes
- Access to the outer class via OuterClass.this
- Local scoping rules
- Polymorphism with anonymous classes
- Static and non-static nested structures
- Iteration over lists of nested objects
- Modular domain-driven organization

---

### 4. Classes Overview:

#### 4.1. Inner (non-static) Classes
- Classes within other classes that depend on an instance of the outer class to exist.
- Accesses all members (including private ones) of the outer class.
- Useful when two classes are tightly coupled.
- Syntax:
``` java
Outer outer = new Outer(); 
Inner interno = Outer.new Inner();
```

#### 4.2. Local Classes
- Classes declared within methods or blocks. 
- They only exist within the scope of that method and can access local variables that are ultimately final. 
- Rarely used today, replaced by lambdas in most cases. 
- They are useful when you need multiple methods but the class only makes sense in that specific context.

#### 4.3. Anonymous Classes
- Named classes created and instantiated in a single expression. 
- They are used for one-off and temporary implementations, such as overriding methods selectively or quickly implementing interfaces. 
- They cannot have constructors. 
- They have been largely replaced by lambdas in Java 8+, but are still useful when you need to implement multiple methods.

#### 4.4. Static Nested Classes
- Classes inside other classes marked as static.
- They do not depend on an instance of the outer class to be created.
- Used for logical grouping, patterns like Builder, or auxiliary structures (Node, etc.).
- Syntax: 
``` java
Nested nested = new Nested();
```

---

## 5. Exercises Summary

Below is a description of each module and what it demonstrates.

---

### 5.1. Member Inner Classes — University & Student

	Package: InnerClasses.InnerClasses

#### Topics:
- Member Inner Classes
- Tight coupling between entities
- Access to outer class attributes

#### Description:
The University class contains a Student inner class.
Each student is inherently linked to its university, including in the toString() output.

#### Features:
- Search student by registration number
- Remove student
- Count total students
- Print all students with reference to the parent university

#### Technical Highlights:
- Instantiation via u1.new Student(...)
- Usage of University.this.name inside Student
- Handling lists of inner-class objects

---

### 5.2. Local Classes — Event & Ticket

	Package: InnerClasses.LocalClasses

#### Topics:
- Local Classes declared inside methods
- Restricted scope
- Static-like methods within local classes

#### Description:
Inside generateTickets(), a local class Ticket is declared.
It is used solely within that method to create and handle ticket objects.

#### Features:
- Dynamic creation of ticket objects
- Printing tickets
- Summing ticket prices using a static method inside the local class

#### Technical Highlights:
- Local class defined within a method
- Encapsulated logic restricted to internal computation
- Internal list manipulation with objects created on the fly

---

### 5.3. Anonymous Classes — Events, Notifiers & Notifications

	Package: InnerClasses.AnonymousClasses

#### Topics:
- Anonymous class creation
- Inline method overriding
- Polymorphism without named classes
- Anonymous classes containing local classes

#### Description:
This module demonstrates:
- Anonymous implementations of the Notifier interface (Email, SMS, Push).
- Anonymous subclasses of Notification overriding the send() method.
- A local class inside an anonymous class.
- Anonymous implementations of the abstract class Alert.

#### Technical Highlights:
- new Interface() { ... }
- new Class() { @Override ... }
- Polymorphism with no class declarations
 -Encapsulated behavior created inline

---

### 5.4. Static Nested Classes — Library, Book & Statistics

	Package: InnerClasses.StaticNestedClasses

#### Topics:
- Static Nested Classes
- Grouping domain logic inside a parent class
- Utility classes living inside a domain container

#### Description:
The Library class contains:
- Book — represents book entities
- Statistics — calculates analytics on the book list

These classes are static and therefore do not depend on a Library instance.

#### Features:
- Printing book information
- Counting total books
- Calculating the average publication year

#### Technical Highlights:
- Instantiation via `new Book(...)`
- Independent from the outer class instance
- Useful for grouping domain-related utilities

---

#### 6. Learning Objectives

- Differences between Member Inner, Local, Anonymous, and Static Nested classes
- When and why to use each type
- Encapsulation and scoping rules in nested structures
- How inner classes improve modular design
- How to override behavior dynamically using anonymous classes
- How to organize domain logic inside composite structures
- Interaction between inner classes and lists
- Clean architecture using Application + Domain organization

---