<div align="center">

![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)

# Seminar System

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview
The Seminar System is a simple system for registering and managing seminars, students, and professors, developed in Java with a focus on programming logic, object-oriented programming (OOP), and basic in-memory data manipulation.

The user interacts with the system through a text menu, being able to register, modify, and list seminars, students, and professors.

---

## New features in v2.0:
- **Use of Inheritance.**
- **Improved understanding of the system architecture.**
- **Use of Generics.**
- **Use of ArrayLists.**
- **Code economy and modularization of classes aiding maintenance and understanding.**

---

## Architecture:

- **Domain** → Classes and Entities.

- **Services** → Business rules and data manipulation.

- **Controller** → Input and output of data for the user.

- **App** → Initializes the application.

---

## What Was Used

### 1. Classes and Objects:
The system is divided into main entities:
- **Location** → Implemented as a *record*, representing the seminar address.
- **Seminar** → Has an id, title, and location.
- **Students** → Has an id, name, age, and optional association with a seminar.
- **Teacher** → Has an id, name, specialty, and a set of seminars taught.

- **BaseService** → Parent class of the other services, sharing 3 common methods that are implemented by each class.

- **Services (Seminar, Students, and Teacher)** → Business rules specific to each class.

---

### 2. Encapsulation:
All classes (except the record) feature:

- Private attributes
- Getters and setters
- Overloaded constructors
- ToString() method when necessary

---

### 3. Records:
The Location class uses records, which offer immutability and less verbosity:

    public record Location(String address) {}

---

### 4. Generics
Generics allow you to create classes, interfaces, and methods that work with different data types while maintaining type safety at compile time.

```java
public class Box<T> {
    private T content;

    public void store(T item) {
        this.content = item;
    }

    public T get() {
        return content;
    }
}
```

### 5. Inheritance
Inheritance allows a class (subclass/child) to inherit attributes and methods from another class (superclass/parent), promoting code reuse and hierarchy.

```java
public class Animal {
    protected String name;
    public void eat() {
        System.out.println("Eating...");
    }
}

public class Dog extends Animal {
    public void bark() {
        System.out.println("Woof woof!");
    }
}
```


### 6. Associations (Associations between Classes):
The project demonstrates different forms of association:

- Seminar has a Location (1:1)
- Student can be related to a Seminar (N:1)
- Teacher can teach multiple Seminars (1:N with array)
- ArraysLists are used to store multiple entities in the system


### 7. ArrayLists:
Previously we manipulated arrays, but now we've used ArrayLists.

```java
ArrayList<Students> students = new ArrayList<>();
etc...
```

These ArraysLists function as simple in-memory "databases".


### 8. Interactive Menu (Console):
All system logic is accessed through a text menu, displayed repeatedly to the user:

    1. Register Seminar
    2. Change Seminar
    3. List Seminars
    4. Register Student
    5. Change Student
    6. List Students
    7. Register Teacher
    8. Change Teacher
    9. List Teachers
    10. Exit

Each option calls methods responsible for:

- Capturing inputs with Scanner in the Controller.
- Validating options in the Service.
- Displaying messages to the user via the Controller.
- Manipulating objects in arrays via the Service.

### 9. Conditionals:
The project uses extensively:

- if / else
- switch (e.g., change seminar/student/teacher data)
- Validations with nested conditions

Example:

    switch (changeOption) {
        case 1: // change name
        case 2: // change age
        case 3: // change seminar
        ...
    }

### 10. Loops:
To iterate through arrays and display elements:

- `for (Object obj : list)`


### 11. Input Handling:
Uses Scanner for:

- Strings
- Integers
- Characters (Y/N)
- Menu Options

Handling nextLine() to avoid unwanted line breaks.

---

## Features
#### Register Seminars
- Title
- Location
- Incremental storage in the ArrayList

#### Modify Seminars
- Modify title
- Modify location
- Modify both

#### List Seminars
- Displays all registered seminars.
  
#### Register Students
- Name
- Age
- Asks if the student belongs to a seminar

#### Change Students
- Modify Name
- Modify Age
- Modify Seminar
- Combinations of the above

#### List Students
With complete information.

#### Register Professors
- Name
- Specialty
- Selection of several seminars to teach

#### Change Professors
- Modify Name
- Modify Specialty
- Modify Seminars taught
- Combinations of the above

#### List Professors
With complete information.

---

## Learning Outcomes
- Object-oriented programming.
- Inheritance.
- Software architecture.
- Constructors and overloading.
- Encapsulation and attribute access.
- Generics.
- ArrayLists as a storage structure.
- Associations between objects.
- Data manipulation in the console.
- Conditional structures and loops.
- Interactive menu navigation.
- User input and data validation.

---