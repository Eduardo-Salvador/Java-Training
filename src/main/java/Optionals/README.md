# Optional

---

## Overview
This module contains a single exercise demonstrating the use of **Optional** in Java to represent values that may or may not exist, helping avoid `NullPointerException` and encouraging safer, more expressive code.

**Brief explanation:**  
`Optional<T>` is a container that may hold a value of type `T` or be empty.  
It provides safe methods to handle the absence of values, replacing manual `null` checks.

The exercise is organized into three main packages:
- **Domain** — contains the `User` entity  
- **Controller** — contains the repository responsible for data storage and lookup  
- **Application** — contains the `Main` class showing Optional in action  
There is also a **ProblemQuestion.txt** file with the original problem statement.

---

## Folder Structure

### 1. Optionals.Domain
Contains the main entity used in the exercise.

**Class: User**
- Fields: `id`, `name`, `email`
- Methods:
  - Getters (`getId`, `getName`, `getEmail`)
  - Overridden `toString()` for formatted display
- Represents a user stored in a simple in-memory list

---

### 2. Optionals.Controller
Contains the class responsible for storage and lookup logic.

**Class: UserRepository**
- Fields:
  - `userList` — internal list of users
- Methods:
  - `add(User user)` — adds a user, verifying null  
  - `findByEmail(String email)` — returns `Optional<User>`  
    - `Optional.of(u)` if found  
    - `Optional.empty()` if not found  

Shows clearly how Optional should be returned in operations that may or may not succeed.

---

### 3. Optionals.Application
Demonstrates multiple real-world scenarios using `Optional`.

**Class: Main**
Executes several Optional operations:

- **Initial user list creation**
- **Adding new users**
- Demonstrates usage of:
  - `Optional.of`
  - `Optional.empty`
  - `ifPresent(System.out::println)`
  - `ifPresentOrElse`
  - `orElse` — provides a default value
  - `orElseGet` — generates fallback value using a function
  - `orElseThrow` — throws exception when value is missing

**Scenarios demonstrated:**
- Successful lookup by email and direct printing of the Optional
- Conditional printing when value exists (`ifPresent`)
- Alternative action when value does not exist (`ifPresentOrElse`)
- Default user fallback using `orElse`
- Dynamically created fallback using `orElseGet`
- Exception thrown for missing values using `orElseThrow`

This exercise shows how Optional replaces manual null-check logic and makes the intention of the code more explicit and safer.

---

## Purpose
- Teach proper usage of `Optional` in lookups, returns, and validations  
- Demonstrate best practices that prevent `NullPointerException`  
- Show essential Optional methods and their real-world application  
- Encourage more declarative and functional programming style  

---

## Files Included
- **Domain/User.java**
- **Controller/UserRepository.java**
- **Application/Main.java**
- **ProblemQuestion.txt**

---

## How to Run
1. Navigate to the **Optionals/Application** folder  
2. Run the **Main** class  
3. Observe the console output demonstrating each Optional operation  

---

## Requirements
- **Java 17+**
- Basic understanding of collections and OOP
- Optional familiarity is helpful but not required

---

## Notes
- Consistent usage of Optional in lookup methods  
- Eliminates manual `null` checks  
- Demonstrates the most commonly used Optional methods:  
  - `of`, `empty`, `ifPresent`, `ifPresentOrElse`  
  - `orElse`, `orElseGet`, `orElseThrow`  
- Provides clear examples of fallback values and absence handling  

---