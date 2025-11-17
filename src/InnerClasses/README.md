# Inner Classes

---

## 1. Fundamental Concepts of Inner Classes

Java allows declaring classes inside other classes, providing logical
grouping, increased encapsulation, and access to external class members.
The four types of inner classes are:

### 1.1 Non‑static Inner Class

-   Associated with an instance of the outer class.
-   Can access all members of the outer class.
-   Used when the inner class exists only within the context of the
    outer object.

### 1.2 Local Class

-   Declared inside a method.
-   Useful for temporary, method-scoped structures.
-   Can access effectively final variables.

### 1.3 Anonymous Class

-   Declared and instantiated in a single expression.
-   Used to override behavior quickly without creating additional files.
-   Common in event handlers and callback logic.

### 1.4 Static Nested Class

-   Belongs to the outer class itself, not to its instance.
-   Can only access static members of the outer class.
-   Useful for grouping related utilities or domain types.

------------------------------------------------------------------------

## 2. Inner Classes Module --- University

### Description

A `University` contains multiple students. Each student is represented
by a **non-static inner class**, meaning each `Student` instance is tied
to a specific `University` object.

### Key Implementation Points

-   **Instantiating a non-static inner class:**

    ``` java
    University u1 = new University("Harvard", "Cambridge");
    University.Student s1 = u1.new Student("Eduardo", "A11C3", "Computer Engineering");
    ```

-   Inner class accesses outer class data via `University.this`.

-   Includes search, delete, and count operations on lists of `Student`.

------------------------------------------------------------------------

## 3. Local Classes Module --- Event & Ticket

### Description

Inside `generateTickets()`, a **local class** `Ticket` is created, as
tickets only exist during the ticket-generation process.

### Key Implementation Points

-   Local class declared inside a method:

    ``` java
    class Ticket { ... }
    ```

-   Includes a static method inside the local class (`sellAll`), which
    sums ticket prices.

-   Tickets cannot escape the method scope, ensuring strict
    encapsulation.

------------------------------------------------------------------------

## 4. Anonymous Classes Module --- Notifications & Alerts

### Description

Demonstrates two typical uses of anonymous classes: 1. **Implementing
interfaces quickly** (e.g., Notifier) 2. **Overriding behavior of
existing classes** (e.g., Notification, Alert)

### Key Implementation Points

-   Anonymous implementation of an interface:

    ``` java
    Notifier email = new Notifier() {
        @Override
        public void notifyUser(String message) { ... }
    };
    ```

-   Anonymous subclass overriding a concrete class method.

-   Anonymous class containing its own local class for specialized
    behavior.

------------------------------------------------------------------------

## 5. Static Nested Classes Module --- Library

### Description

`Library` contains two **static nested classes**: `Book` and
`Statistics`.

### Key Implementation Points

-   Static nested class does NOT require an outer instance:

    ``` java
    Library.Book b = new Library.Book("Dom Casmurro", "Machado de Assis", 1899);
    ```

-   `Statistics` processes lists of `Book` objects (count, average
    publication year).

-   Represents domain grouping without outer-instance dependency.

------------------------------------------------------------------------

## 6. Module Summary

| Module              | Inner Class Type        | Purpose                                      |
|---------------------|--------------------------|----------------------------------------------|
| InnerClasses        | Non-static inner class   | Strongly dependent related objects           |
| LocalClasses        | Local class              | Method-restricted scope                      |
| AnonymousClasses    | Anonymous class          | Quick, customized polymorphism               |
| StaticAlignedClasses| Static nested class      | Logical grouping + instance-independent types|


------------------------------------------------------------------------

## 7. Conclusion

- Proper organization using inner class types
- Enhanced encapsulation through scoped class definitions
- Dynamic polymorphism via anonymous classes
- Domain grouping and utilities via static nested classes
- Clear, structured patterns for real-world Java systems

Each directory includes its own `ProblemQuestion.txt` with the exercise
specification.

---