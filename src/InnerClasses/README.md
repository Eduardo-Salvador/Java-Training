# Inner Classes (Java Inner Classes)

This module presents a complete view of inner classes in Java, covering its four main types:
- Inner Classes (non-static)
- Local Classes
- Anonymous Classes
- Static Nested Classes

Each directory contains the corresponding exercise and a ProblemQuestion.txt file describing the requirements.

The module combines theory + practice, demonstrating structured coupling, scope organization, enhanced encapsulation, and the use of inner classes for more specialized behaviors inside the same domain.

---

## 1. Fundamental Concepts of Inner Classes

Java allows declaring classes inside other classes, creating a logical grouping of related structures.
This feature:
- Keeps the code organized
- Increases encapsulation
- Allows direct access to outer class members
- Creates specialized behaviors without generating many loose classes in the package

The four types are:

### 1.1 Inner Class (Non-Static Inner Class)
- It is associated with an instance of the outer class.
- Can directly access all attributes of the outer class (including private ones).
- Ideal when the inner class only exists in the context of an object of the outer class.

### 1.2 Local Class
- Declared inside a method.
- Useful for auxiliary structures of restricted use.
- Has access to effectively final variables of the method.

### 1.3 Anonymous Class
- Has no name, created at instantiation time.
- Used to override or implement behaviors quickly.
- Commonly used with functional interfaces, callbacks, and events.

### 1.4 Static Nested Class
- Belongs to the outer class, not to its instance.
- Only accesses static members of the outer class.
- Good for utilities, DTOs, logical grouping of types.

---

## 2. Code Exercise: Inner Classes Module — University
### Description
A university has several students.

Each student is represented by a non-static inner class, because a Student only exists within the context of a University.

### Main Code
Classes:
- University
- University.Student
- Application/Main

--- 

## 3. Local Classes Module — Event & Ticket
### Description
The method generateTickets() creates an auxiliary class Ticket inside the method, because tickets exist only in the context of generation.

### Main Code
Classes:
- Event
- Local class Ticket inside generateTickets()
- Sum and print methods

---

## 4. Anonymous Classes Module — Notifications & Alerts
### Description
This part shows two typical applications:
- Quick implementation of interfaces via anonymous classes
- Overriding methods of abstract and concrete classes

### Main Code
Classes:
- Notifier (interface)
- Notification (base class)
- Alert (abstract class)
- Event
- Application/Main

---

## 5. Static Nested Classes Module — Library & Book
### Description
The library contains books (Book) and statistics (Statistics).
Both are static nested classes because they do not depend on a Library instance.

### Main Code
Classes:
- Library
- Library.Book
- Library.Statistics

---

## 6. Project Overview
| Module              | Inner Class Type        | Purpose                                      |
|---------------------|--------------------------|----------------------------------------------|
| InnerClasses        | Non-static inner class   | Strongly dependent related objects           |
| LocalClasses        | Local class              | Method-restricted scope                      |
| AnonymousClasses    | Anonymous class          | Quick, customized polymorphism               |
| StaticAlignedClasses| Static nested class      | Logical grouping + instance-independent types|

Each module has its own ProblemQuestion.txt containing the exercise description.

---

## 7. Conclusion

This module demonstrates:
- How to organize code by logical proximity
- When each type of inner class is applicable
- Advanced encapsulation
- Inner classes accessing outer members
- Polymorphism with anonymous classes
- Utility structures with static nested classes
- Correct use of scope and context

---