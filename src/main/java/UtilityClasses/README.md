# Utility Classes

---

## 1. Overview
This module consolidates multiple exercises designed to demonstrate the practical use of Java’s core utility classes. The content covers date and time APIs, text manipulation, internationalization, numerical formatting, file operations (IO and NIO), and their integration within small domain-driven systems.

---

## 2. Scope
This documentation applies to all exercises contained in:
- Challenge 1 (E1–E15)
- Challenge 2 (E1–E4)
- 
Each exercise includes:
- Source code
-  ProblemQuestion.txt describing the specification
- Demonstrations of the related Java utility class or API

---

## 3. Objectives

The module focuses:
1. Parse, format, compare, and manipulate dates and times.
2. Format numerical values and currencies in various locales.
3. Perform text normalization and string processing.
4. Execute file reading and writing operations using Java IO and NIO APIs.
5. Integrate these utilities within domain-oriented components such as invoices, calendars, and consumption reports.

---

## 4. Architecture Overview
The module is divided into two major segments:

### 4.1 Challenge 1 (Standalone Exercises)

Each exercise contains a single Main class and a ProblemQuestion.txt defining the requirements.

No inter-file dependencies exist.

### 4.2 Challenge 2 (Layered Architecture)
Each exercise follows a minimal Layered Architecture:
- **Application Layer**
  - Initiates execution (Main class).
- **Controller Layer**
  - Handles user interaction, command routing, and validation.
- **Domain Layer**
  - Contains core business logic and data structures.
- **ProblemQuestion.txt**
  - Defines functional requirements.

This architecture promotes separation of concerns, maintainability, and clarity.

---

## 5. Technologies and Utility Classes Used
### 5.1 Date and Time API (java.time)
- LocalDate
- LocalTime
- LocalDateTime
- Instant
- ZonedDateTime
- ZoneId
- DateTimeFormatter
- Duration
- Period
- ChronoUnit
### 5.2 Text Manipulation
- StringBuilder
- Normalizer
- Character classification utilities
### 5.3 Internationalization and Formatting
- Locale
- NumberFormat
- DateFormat
### 5.4 File Handling (IO and NIO)
Used in selected exercises for text persistence and file operations.
#### IO (java.io)
- FileReader
- FileWriter
- BufferedReader
- BufferedWriter
#### NIO and Files (java.nio.file)
- Path
- Paths
- Files

Used for:
  - Path manipulation
  - Reading/writing entire files
  - Checking file existence
  - Handling OS-independent paths

---

## 6. Challenge 1 – Functional Summary (E1–E15)
- **E1:** LocalDate formatting
- **E2:** String-to-date parsing
- **E3:** Day difference (ChronoUnit)
- **E4:** Period-based difference
- **E5:** Date arithmetic (addition/subtraction)
- **E6:** Duration calculations
- **E7:** Numeric formatting
- **E8:** Locale-dependent formatting
- **E9:** StringBuilder operations
- **E10:** Text normalization
- **E11:** Date sorting
- **E12:** Deadline comparison
- **E13:** Currency formatting across locales
- **E14:** Remaining time calculation
- **E15:** String registry using StringBuilder

---

## 7. Challenge 2 – Functional Summary (E1–E4)
### E1 – Invoice Management
Implements an invoice lifecycle using date comparison, formatting, and fine calculation.

### E2 – Event Calendar
Manages events with date and time parsing, validation, and listing operations.

### E3 – Client Consumption Registry
Tracks consumption by category and aggregates results for a given year.

### E4 – Loan Simulation
Simulates compound interest and early payment discount calculations.

---

## 8. Validation and Input Handling
Throughout the module:
- Date formats are validated before parsing.
- Numerical inputs are checked for format compatibility.
- Domain methods enforce constraints (e.g., nonnegative values, nonexpired dates).
Exception usage is limited to the technical necessity of enforcing these constraints.

---

## 9. Known Limitations

- No persistence mechanism (database, JSON files, etc.) is included.
- Input validation assumes console use and synchronous input.

---

## 10. Directory Structure
    UtilityClasses/
    ├── Challenge1/
    │    ├── E1/
    │    ├── E2/
    │    ├── E3/
    │    └── ...
    ├── Challenge2/
    │    ├── E1/
    │    ├── E2/
    │    ├── E3/
    │    └── E4/
Each folder contains:
- Main.java
- Subpackages (Application, Controller, Domain) for Challenge 2
- ProblemQuestion.txt

---

## 13. Conclusion
This module demonstrates effective use of Java’s standard utility classes within both isolated exercises and structured multi-class systems. It serves as a foundation for developers who require reliable date, time, formatting, text manipulation, and file handling operations in enterprise environments.

---