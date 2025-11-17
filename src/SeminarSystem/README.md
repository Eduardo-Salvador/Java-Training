# Seminar System

---

## Overview
The Seminar System is a simple system for registering and managing seminars, students, and professors, developed in Java with a focus on programming logic, object-oriented programming (OOP), and basic in-memory data manipulation.

The user interacts with the system through a text menu, being able to register, modify, and list seminars, students, and professors.

---

## Requirements

All source code is organized into packages:

- SeminarSystem.Domain → Contains the domain classes (Location, Seminar, Students, Teacher).
- SeminarSystem.Controller → Contains the main menu, registration rules, modification rules, and listing rules.

The project does not use a database; everything is stored in arrays in memory.

---

## What Was Used

### 1. Classes and Objects
The system is divided into main entities:

- Location → Implemented as a record, representing the seminar's address.
- Seminar → Has a title and location.
- Students → Has a name, age, and optional association with a seminar.
- Teacher → Has a name, specialty, and a set of seminars taught.

These objects model the domain in a clear and object-oriented way.

---

### 2. Encapsulation
All classes (except the record) feature:

- Private attributes
- Getters and setters
- Overloaded constructors
- ToString() method when necessary

---

### 3. Records
The Location class uses records, which offer immutability and less verbosity:

    public record Location(String address) {}

---

### 4. Associations (Associations between Classes)
The project demonstrates different forms of association:

- Seminar has a Location (1:1)
- Student can be related to a Seminar (N:1)
- Teacher can teach multiple Seminars (1:N with array)
- Arrays are used to store multiple entities in the system

---

### 5. Arrays
Instead of dynamic lists, the system uses fixed arrays to store data:

    Students[] students = new Students[100];
    Seminar[] seminars = new Seminar[500];
    Teacher[] teachers = new Teacher[100];

These arrays function as simple in-memory "databases".

---

### 6. Interactive Menu (Console)
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

- Capturing inputs with Scanner
- Validating options
- Showing messages to the user
- Manipulating objects in arrays

---

### 7. Conditionals
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

---

### 8. Loops
To iterate through arrays and display elements:

- for (int i = 0; i < array.length; i++)

Used to list entities and find empty spaces.

---

### 9. Input Handling
Uses Scanner for:

- Strings
- Integers
- Characters (Y/N)
- Menu Options

Handling nextLine() to avoid unwanted line breaks.

---

## Features
### ✔ Register Seminars

- Title
- Location
- Incremental storage in the array

### ✔ Modify Seminars
- Modify title
- Modify location
- Modify both

### ✔ List Seminars
- Displays all registered seminars.
  
### ✔ Register Students

- Name
- Age
- Asks if the student belongs to a seminar

### ✔ Change Students
Allows changing:
- Name
- Age
- Seminar
- Combinations of the above

### ✔ List Students
With complete information.

### ✔ Register Professors
- Name
- Specialty
- Selection of several seminars to teach

### ✔ Change Professors
- Name
- Specialty
- Seminars taught
- Combinations

### ✔ List Professors
With complete information.

---

## Learning Outcomes

By developing or analyzing this project, you will practice:

- Modular organization (Domain and Controller packages)
- Object-oriented programming
- Constructors and overloading
- Encapsulation and attribute access
- Arrays as a storage structure
- Associations between objects
- Data manipulation in the console
- Conditional structures and loops
- Interactive menu navigation
- User input and validation

---