# Inner Classes

---

## Overview

This project contains a collection of independent exercises focused on Inner Classes in Java, organized into the following packages:
- InnerClasses.InnerClasses — Member Inner Classes
- 	InnerClasses.LocalClasses — Local Classes
- InnerClasses.AnonymousClasses — Anonymous Classes
-	InnerClasses.StaticAlignedClasses — Static Nested Classes

### Each exercise includes:
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

The goal is to provide hands-on understanding of how inner classes work, when to use them, and how they improve organization, encapsulation, and code expressiveness.

---

## What Are Inner Classes?

Inner Classes are classes declared inside other classes.
They allow:
	- Strong grouping of related classes
	- Direct access between inner and outer contexts
	- Greater encapsulation
	- Clear modular organization when objects are tightly related

---

## Main Types

### 1. Member Inner Classes
- Declared inside another class (but outside methods).
- Have full access to the outer class instance.

### 2. Local Classes
- Classes declared inside a method.
- They only exist within the method where they are defined.

### 3. Anonymous Classes
- Classes created without a name, usually to override methods or implement interfaces inline.

### 4. Static Nested Classes
- Declared as static.
- Do not depend on an instance of the outer class.

---

## Concepts Used

### The exercises demonstrate:
- Object composition through inner classes
- Access to the outer class via OuterClass.this
- Local scoping rules
- Polymorphism with anonymous classes
- Static and non-static nested structures
- Iteration over lists of nested objects
- Modular domain-driven organization

---

## Exercises Summary

Below is a description of each module and what it demonstrates.

---

### 1. Member Inner Classes — University & Student

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

### 2. Local Classes — Event & Ticket

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

### 3. Anonymous Classes — Events, Notifiers & Notifications

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

### 4. Static Nested Classes — Library, Book & Statistics

	Package: InnerClasses.StaticAlignedClasses

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
- Instantiation via new Library.Book(...)
- Independent from the outer class instance
- Useful for grouping domain-related utilities

---

#### Learning Objectives

- Differences between Member Inner, Local, Anonymous, and Static Nested classes
- When and why to use each type
- Encapsulation and scoping rules in nested structures
- How inner classes improve modular design
- How to override behavior dynamically using anonymous classes
- How to organize domain logic inside composite structures
- Interaction between inner classes and lists
- Clean architecture using Application + Domain organization

---