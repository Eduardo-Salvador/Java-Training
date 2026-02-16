<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Pet Adoption System (CLI) v.1.0

Complete animal registry management system for shelters, developed in Java with Command Line Interface (CLI).

See what as requested here: [Problem Question EN_US](https://github.com/Eduardo-Salvador/Java-Training/blob/main/src/main/java/StudyChallenges/RegistrationSystem/ProblemQuestion.md)

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Project Architecture](#-project-architecture)
- [Applied Knowledge](#-applied-knowledge)
- [Data Structure](#-data-structure)
- [Technical Details](#-technical-details)
- [Extra Implemented Features](#-extra-implemented-features)
- [How to Run](#-how-to-run)
- [Learning Outcomes](#-learning-outcomes)

---

## Overview

This project is a robust CLI application for managing oldPet records in animal shelters. The system allows shelter administrators to perform complete CRUD (Create, Read, Update, Delete) operations with rigorous validations and a customizable form system.

**Main Objective:** Facilitate the management of rescued animal information, enabling detailed registration, advanced search, editing, and data deletion in an organized and secure manner.

---

## Features

### Main Features

1. **Register a new oldPet**
  - Form with 7 base questions + customizable extra questions
  - Rigorous data validation (name, age, weight, breed, address)
  - Support for user-created additional questions

2. **Search pets**
  - Search by multiple criteria: name, sex, age, weight, breed, address, registration date
  - Combination of up to 2 criteria simultaneously
  - Partial, case-insensitive, and accent-insensitive search
  - Visual highlight of found terms (bold)

3. **Edit oldPet data**
  - Modification of any attribute (except Type and Sex)
  - Prior search for oldPet selection
  - History preservation with timestamp

4. **Delete a oldPet**
  - Secure deletion with confirmation
  - Prior search for selection

5. **List all pets**
  - Quick view of all records
  - Clear and numbered formatting

6. **Customizable Forms System** (Extra)
  - Create new questions
  - Edit extra questions (8+)
  - Delete extra questions with automatic renumbering

---

## 🏗️ Project Architecture

```
RegistrationSystem/
│
├── Application/
│   └── Main.java                 # Application entry point
│
├── Domain/
│   ├── Pet.java                  # Pet data model
│   ├── Address.java              # Address data model
│   ├── PetType.java              # Enum: DOG, CAT, NO_INFORMED
│   └── PetSex.java               # Enum: MALE, FEMALE, NO_INFORMED
│
├── Services/
│   └── PetSystem.java            # Business logic and menu control
│
├── RegisteredPets/               # Directory with oldPet files
│   └── [YYYYMMDDTHHMM-NAME.txt]
│
└── Forms.txt                     # Form questions file
```

### Architecture Pattern

The project follows a layered architecture:

- **Application Layer**: Application initialization
- **Domain Layer**: Domain models (entities and enums)
- **Service Layer**: Business logic, validations, and I/O operations

---

## Applied Knowledge

- **Object-Oriented Programming (OOP)**
  - Encapsulation
  - Classes and objects
  - Enumerations (ENUMs)
  - Static and instance methods

- **File Manipulation (Java I/O)**
  - `BufferedReader` and `BufferedWriter`
  - `FileReader` and `FileWriter`
  - `Files` and `Paths` (NIO)
  - `DirectoryStream`
  - File attributes (readonly)

- **Data Structures**
  - Arrays
  - Lists (ArrayList)
  - String manipulation

- **Validation and Regex**
  - `Pattern` and `Matcher`
  - Complex regular expressions
  - Text normalization (accent removal)

- **Exception Handling**
  - Try-catch blocks
  - IOException
  - User input validation

- **Date/Time Formatting**
  - `LocalDateTime`
  - `DateTimeFormatter`

- **Clean Code Principles**
  - Small and specific methods
  - Clear naming
  - Separation of concerns
  - Constants for fixed values

---

### Pet File Format

```
1 - [Pet Name]
2 - [Type: Dog/Cat/No informed]
3 - [Sex: Male/Female/No informed]
4 - Street: [Street] - Number: [Number] - City: [City]
5 - [Age] years
6 - [Weight] kgs
7 - [Breed]
8 - [Extra Answer] [EXTRA]
9 - [Extra Answer] [EXTRA]
...
```

**File Name:** `YYYYMMDDTHHMM-PETNAME.txt`  
**Example:** `20231101T1234-FLORZINHADASILVA.txt`

---

## Technical Details

### 1. Pet Registration (`Pet.createPet`)

**Execution Flow:**

1. Reads questions from `Forms.txt` file
2. Collects user answers with validation
3. Creates `Pet` object and fills attributes
4. Saves to `.txt` file with readonly protection
5. Dynamically processes extra questions

**Implemented Validations:**

| Field | Rules |
|-------|-------|
| **Name** | Required, letters and spaces only, no special characters |
| **Type** | ENUM: Dog, Cat, or No informed |
| **Sex** | ENUM: Male, Female, or No informed |
| **Address** | Three fields (street, number, city), accepts empty |
| **Age** | Numeric, ≤ 20 years, accepts comma or decimal point |
| **Weight** | Numeric, between 0.5kg and 60kg |
| **Breed** | Letters only, accepts empty |

**Technologies Used:**
- Regex for format validation
- Pattern/Matcher for parsing
- BufferedReader for file reading
- BufferedWriter for writing
- Files.setAttribute for readonly

### 2. Pet Search (`searchPet`)

**Search Criteria:**

- First or last name
- Sex
- Age
- Weight
- Breed
- Address (street, number, or city)
- Registration date (month/year)
- **Combination of 2 criteria**

**Advanced Features:**

- Text normalization (removes accents)
- Case-insensitive matching
- Partial search (substring)
- Visual highlight with ANSI codes (`\033[1m`)
- DirectoryStream for reading multiple files

**Output Example:**

```
1. **FLOR**ZINHA DA SILVA - CAT - FEMALE - Street: Street 456 - Number: 10 - City: Seilandia - 6.0 YEARS - 5.0 KGS - SIAMESE
```

### 3. Pet Editing (`changePet`)

**Flow:**

1. Searches for oldPet using `searchPet`
2. User selects which oldPet to edit
3. Edit options menu:
  - Name
  - Address
  - Age
  - Weight
  - Breed
4. Creates new file with updated timestamp
5. Deletes old file

**Non-Editable Fields:**
- Type (Dog/Cat)
- Sex (Male/Female)

**Extra Feature:**
- Versioning by timestamp in file name
- Implicit history preservation

### 4. Pet Deletion (`deletePet`)

**Security:**

- Mandatory confirmation (YES/NO)
- Prior search for selection
- Only one oldPet at a time

### 5. Forms System

#### Create Question (`createQuestionForms`)

- Reads last question from file
- Automatically increments numbering
- Adds with `[EXTRA - ...]` tag
- Limit of 19 total questions

#### Edit Question (`changeQuestionForms`)

- Only questions 8+ (extras)
- Preserves existing answers
- In-place file update

#### Delete Question (`deleteQuestionForms`)

- Only questions 8+ (extras)
- Mandatory confirmation
- **Automatic renumbering** of remaining questions

---

## Extra Implemented Features

Beyond base requirements, the following were implemented:

### 1. **File Protection System**
- Pet files are marked as **readonly** after creation
- Prevents accidental changes
- Modifications only through official methods

### 2. **Timestamp Versioning**
- Every edit generates new file with updated timestamp
- File name: `YYYYMMDDTHHMM-NAME.txt`
- Implicit history (old files deleted, but pattern allows tracking)

### 3. **Advanced Search with Visual Highlight**
- Found terms appear in **bold**
- Uses ANSI escape codes for terminal formatting

### 4. **Robust Input Validation**
- Exception handling on all inputs
- Descriptive error messages
- Retry loops for invalid inputs

### 5. **Text Normalization**
- Removes accents for search
- Case-insensitive comparisons
- Support for different date formats

---

## How to Run

### Prerequisites

- Java JDK 8 or higher
- Terminal with ANSI codes support (for visual highlight)

### Required File Structure

```
src/PROGRAMMING_Challenge_RegistrationSystem/
├── Forms.txt                    # Create with 7 base questions
└── RegisteredPets/              # Create empty directory
```

### `Forms.txt` Example

```
1 - What is the oldPet's first and last name?
2 - What type of animal is it? (Dog or Cat)
3 - What is the oldPet's sex? (Male or Female)
4 - Where was the oldPet found?
5 - What is the approximate age?
6 - What is the approximate weight? (in kg)
7 - What is the breed?
```

### Main Menu

```
What do you want to do?
1 - Start the system to register pets
2 - Start the system to modify the form
3 - Exit
```

---

## Learning Outcomes

Upon completing this project, the following knowledge was consolidated:

### Object-Oriented Programming
- Class design with clear responsibilities
- Appropriate use of ENUMs for fixed types
- Encapsulation and getters/setters
- Static vs. instance methods

### File Manipulation
- Reading and writing with BufferedReader/Writer
- Directory navigation with DirectoryStream
- File attribute management (readonly)
- Directory structure creation

### Data Validation and Processing
- Complex regular expressions
- Pattern matching with groups
- String normalization (accents, case)
- Parsing different numeric formats

### Exception Handling
- Try-catch in I/O operations
- User input validation
- Informative error messages
- Graceful error recovery

### User Experience (CLI)
- Clear interactive menus
- Confirmations for destructive actions
- Visual feedback (bold highlight)
- Appropriate success/error messages

### Best Practices
- Constants for fixed values
- Small and specific methods
- Descriptive naming
- Comments where necessary
- Layer separation (Domain/Service/Application)

---

## Technical Notes

### Known Limitations

- File-based system (doesn't use database)
- Sequential search (can be slow with many pets)
- ANSI codes may not work in all Windows terminals
- 
### Problems found during the review some time later:

- During the project review, bottlenecks from when I first created the system were identified.
- With improved knowledge, I know I need to refactor the system:
- Some architectural errors and classes with too many responsibilities.
- Magic strings and lack of best practices at times (too many try/catch blocks and many repeated checks).
- All of this will be corrected in v2.0, when we connect the system to a database with more functionalities.

---