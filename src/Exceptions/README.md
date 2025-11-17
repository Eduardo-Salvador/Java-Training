# Java Exceptions

---

## Overview
This project contains a collection of independent exercises on exception handling in Java, organized into packages Exceptions.E1, Exceptions.E2, … Exceptions.E7.

Each exercise includes:

- A minimal structure with Application, Controller, and Domain
- Specific logic to demonstrate different uses of exceptions
- A ProblemQuestion.txt file containing the original exercise statement
- Practical examples of:
  - try/catch
  - throws
  - Exception hierarchy
  - Custom exceptions
  - Finally block
  - Use of AutoCloseable
  - File handling (Reader/Writer)
  
The goal is to allow students to understand, in practice, how exceptions work and how they should be used to ensure safety, clarity, and robustness in the code.

---

## What Are Exceptions?

**Exceptions** are unexpected events that occur during program execution.

When something prevents normal execution, such as dividing by zero, reading a non-existent file, or validating incorrect data, Java throws an exception.

### Benefits:
- Prevent abrupt program termination
- Allow error handling locally (with try/catch)
- Help write safer programs
- Clear separation between normal flow and error flow

---

## Two main types:

### Checked Exceptions
Must be handled or declared with throws.

Examples:
- IOException
- FileNotFoundException
- Custom exceptions that extend Exception

### Unchecked Exceptions
Do not require mandatory handling.

Examples:
- ArithmeticException
- InputMismatchException
- NullPointerException

---

## Utility Classes Used
The exercises make use of important utility classes:

### Scanner (java.util.Scanner)
Reads data from the keyboard (user input).

### FileReader / BufferedReader
Reads text files.

### FileWriter / BufferedWriter=
Writes to text files.

### AutoCloseable
Allows objects to be closed automatically by try-with-resources.

### Custom Exceptions
Created in exercises E3, E4, and E7.

General example:

    public class InvalidAgeException extends Exception {
        public InvalidAgeException(String msg) {
            super(msg);
        }
    }

---

## Exercises Summary

Below, each exercise (E1 to E7) is described along with what it demonstrates.

**All exercises have a ProblemQuestion.txt file containing the complete problem statement.**

---

### E1 — Division Calculator (ArithmeticException + InputMismatchException)
#### Topic
- Division by zero validation
- Use of try/catch
- Unchecked exceptions

#### Description
- The program prompts the user for two numbers and attempts to perform a division.
- If the divisor is 0, an ArithmeticException is thrown.
- If the user enters something invalid, an InputMismatchException occurs.

#### Technical content:
- Method that explicitly throws an exception with throw
- Input scanner
- Joint handling (catch (ArithmeticException | InputMismatchException e))

---

### E2 — File Reader (Checked Exceptions + Multiple Catches)
#### Topic
- Reading text files
- Handling FileNotFoundException, IOException
- Using BufferedReader + FileReader
- Try-with-resources structure

#### Description
- The program attempts to open and read a file line by line.
- If the file does not exist, it catches FileNotFoundException.

#### Technical content:
- Try-with-resources blocks
- Reading files
- Handling multiple checked exceptions

--- 

### E3 — Custom Exception (InvalidAgeException)
#### Topic
- Custom Exceptions (extends Exception)
- Age Validation
- Required Checked Exception

#### Description
The user enters an age.

If it is less than 0 or greater than 120, the system throws an InvalidAgeException.

#### Technical Content:
- Domain class that validates data in the constructor
- Custom exception handling

---

### E4 — Finally Block + Custom Exception
#### Topic
- Difference between:
  - try
  - catch
  - finally
- Manually ensuring resource release

#### Description
Similar to E3, but with the explicit use of the finally block, which closes the Scanner manually.

#### Technical content:
- Resource closure
- Guaranteed execution of the finally block

---

### E5 — File Writer (IOException + Finally + BufferedWriter)
#### Topic
- Writing to text files
- Handling IOException
- finally to ensure closure

#### Description
The system writes two lines to a .txt file. Any write failure generates an exception.

#### Technical content:
- BufferedWriter + FileWriter
- Line-by-line writing
- Exception handling with finally for manual close()

---

### E6 — AutoCloseable + try-with-resources
#### Topic
- AutoCloseable Implementation
- Automatic resource closing
- Scanner and MockConnection in the same block

#### Description
Creation of a mock class MockConnection that simulates opening and closing a connection.

Shows how the close() method is called automatically.

#### Technical content:
- try(resource1; resource2; ...)
- Automatic execution of .close()

---

### E7 — Bank Account (Custom Exception + Business Rules)
#### Topic
- Custom exceptions linked to business rules
- Insufficient balance validation
- Interactive menu structure

#### Description
The user starts an account with an initial deposit.
Then choose:

    1. Deposit
    2. Withdraw
    3. View balance
    4. Logout

Withdrawal attempts exceeding the balance will cause a BalanceInsufficientException.

#### Technical content:
- Object-oriented programming
- Business rules + custom exception
- Interactive menu
- Scanner + flow control

---

## Learning Objectives
- Difference between checked and unchecked exceptions
- try, catch, finally
- throw vs throws
- Creating custom exceptions
- Reading and writing files
- Manual and automatic resource closing
- Implementation of AutoCloseable
- Data and business rule validation
- Interactive menu with Scanner
- Modular organization into Application, Controller, and Domain

---