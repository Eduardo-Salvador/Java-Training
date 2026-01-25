<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)

# Exceptions

This module provides a comprehensive introduction to exception handling in Java, focusing on the features offered by exceptions and their types.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

This project contains:

- A collection of independent exercises on exception handling in Java, organized into the packages Exceptions.E1, Exceptions.E2, ... Exceptions.E7.
- Specific logic to demonstrate different uses of exceptions.
- Practical examples of:
- Try/catch
- Throws
- Exception hierarchy
- Custom exceptions
- Finally block
- Use of AutoCloseable
- File handling (Reader/Writer)

The goal is to allow you to understand how exceptions work and how they should be used to ensure safety, clarity, and robustness in the code.

---

## Architecture

- A minimal structure with Application, Services, and Domain
- A **ProblemQuestion.txt** file containing the original exercise statement

---

### 1. What are Exceptions?

Exceptions are abnormal program conditions that occur during a program's runtime.

When something prevents the normal flow, such as dividing by zero, reading a non-existent file, or validating incorrect data, Java throws an exception.

Exceptions come from the **Throwable** class, which are instantiated in Exceptions.

### 1.1. Benefits:

- They prevent abrupt program termination
- They allow local error handling (via try/catch)
- They help write safer programs
- They clearly separate the normal flow from the error flow

---

### 2. There are two main types:

#### 2.1. Checked Exceptions

Children of the Exception class are checked exceptions:

They must be handled or declared with **throws**.

Examples:

- IOException
- FileNotFoundException
- Custom exceptions that extend Exception

#### 2.2. Unchecked Exceptions

They do not require mandatory handling, but it can be done.

They are linked to code errors, so handling them is optional, as they shouldn't even be there.

It consists of a class called RunTimeException and its subclasses, when the program does not even compile due to the exception being thrown, when something was done in the code that should not be done, either in the syntax or in the logic.

Examples:

- ArithmeticException
- InputMismatchException
- NullPointerException

---

### 3. Throwing an exception:

``` java
private void withdraw(double amount) throws InvalidInputException {
  if(amount > 1000) {
    throw new InvalidInputException("The value is greater than the maximum");
  }
}
```

The `throws` statement in the method indicates that whoever uses it will have to handle a possible exception with `tryCatch`.

---

### 4. Finally:

A block that will always be executed inside `tryCatch`, regardless of whether an exception is thrown or not.

Used to close resources or perform some necessary operation.

``` java
try {
  System.out.println("Opening File");
} catch (Exception e) {
  e.printStackTrace();
} finally {
  System.out.println("Closing File");
}
```

---

### 5. Multiple Catches:

I can catch multiple Exceptions in a single block:

``` java
try {
  throw new ArrayIndexOutOfBoundException();
} catch (ArrayIndexOutOfBoundException e) {
} catch (IndexOutOfBoundException e) {
} catch (IllegalArgumentException e) {
} catch (ArithmeticException | IllegalArgumentException e) {
}
```

**Rules:**

1. *We cannot place the most generic type of Exception before a more specific one.*
2. *It must start from the least generic to the most generic.*
3. *To use |, the Exceptions must be on the same inheritance line (they cannot have a direct inheritance relationship (one cannot be a child of the other)).*

---

### 6. Try With Resources:

Try with resources: Used to close connections, requests, etc.

``` java
try(Reader reader = new BufferedReader(new FileReader("teste.txt")){
} catch (IOException e) {
}
// -> The try block itself closes the buffer.
// -> I can declare multiple references separated by semicolons (;) as long as they implement closeable.
```

---

### 7. AutoCloseable

Allows objects to be closed automatically (Objects that implement AutoClosable require opening and closing) via
**try-with-resources**.

---

### 8. Custom Exceptions:

Create a class ending in nameException and define whether it is Checked or Unchecked:

``` java
public class InsufficientBalanceException extends Exception {

    // Default constructor
    public InsufficientBalanceException() {
      super("Insufficient funds to perform this operation.");
   
    }

    // Constructor that accepts a custom message
    public InsufficientBalanceException(String message) {
      super(message);
    }
}
//-> Checked (mandatory handling) (Exception)

// BankAccount.java
public class BankAccount {
  private double balance;
  
  public BankAccount(double balance) {
    this.balance = balance;
  } 

  public void withdraw(double amount) throws InsufficientBalanceException { 
    if (amount > balance) { 
      throw new InsufficientBalanceException("Attempted withdrawal of R$" + amount + " with a balance of R$" + balance); 
  } 
    balance -= amount; 
    System.out.println("Withdrawal made. New balance: R$" + balance); 
   }
 }

// Main.java
public class Main { 
  public static void main(String[] args) { 
    BankAccount account = new BankAccount(100.0); 
    try { 
      account.withdraw(150.0); 
    } catch (InsufficientBalanceException e) { 
      System.err.println("Error: " + e.getMessage()); 
    } 
  }
}
```

### 9. Exceptions are Polymorphic:

*Exceptions are objects, nothing special about them, except that they can be thrown*. **Therefore, they can be polymorphically referenced.** The advantage is that **a method doesn't need to explicitly declare all possible exceptions; it can declare only a superclass of exceptions.**

The same applies to **catch; you don't need a catch for each exception, as long as the declared catch is a superclass that can handle all thrown exceptions.**

```java
  public void doLandry() throws ClothingException{}
  //-> Every subclass of Clothing can be thrown.

  try {
  } catch (ClothingException e) {}
  //-> Every subclass of Clothing that is in the method can be recovered.

  ClothingException e = new SubClasseException();
  //-> Basically, this is what we do with throw new and catch.
```
If your code handles a `TeeShirtException` differently than a `LingerieException`, write a catch block for each. But if you handle all other types of `ClothingException` the same way, add a `ClothingException` catch block to handle the rest.

---

### 10. Utility Classes Used

The exercises make use of important utility classes:

#### 10.1. Scanner (java.util.Scanner)

Reads data from the keyboard (user input).

#### 10.2. FileReader / BufferedReader

Reads text files.

#### 10.3. FileWriter / BufferedWriter

Writes text files.

---

### 11. Summary of Exercises

Below, each exercise (E1 to E7) is described along with what it demonstrates.

All have a **ProblemQuestion.txt** file with the complete problem statement.

#### 11.1. **E1 - Division Calculator (ArithmeticException + InputMismatchException)**

#### Topic

- Validation of division by zero
- Use of try/catch
- Unchecked exceptions

#### Description

- The program asks the user for two numbers and attempts to perform a division.

- If the divisor is 0, an ArithmeticException is thrown.

- If the user enters something invalid, an InputMismatchException occurs.

#### Technical Content:

- Method that explicitly throws an exception with **throw**
- Use of Scanner
- Joint handling with `catch (ArithmeticException | InputMismatchException e)`

#### 11.2. **E2 - File Reader (Checked Exceptions + Multiple Catches)**

#### Topic

- Reading text files
- Handling FileNotFoundException and IOException
- Using BufferedReader + FileReader
- Try-with-resources structure

#### Description

- The program attempts to open and read a file line by line.

- If the file does not exist, it catches FileNotFoundException.

#### Technical content:

- Try-with-resources blocks
- Reading files
- Handling multiple checked exceptions

### 11.3. **E3 - Custom Exception (InvalidAgeException)**

#### Topic

- Custom exceptions (extends Exception)
- Age validation
- Mandatory (checked) exception

#### Description

The user enters an age.

If it is less than 0 or greater than 120, the system throws an InvalidAgeException.

#### Technical content:

- Domain class validating data in the constructor
- Custom exception handling

#### 11.4. **E4 - Finally Block + Custom Exception**

#### Topic

- Difference between:

- try

- catch

- finally
- Guaranteed manual resource release

#### Description

Similar to E3, but with explicit use of finally, which closes the Scanner manually.

#### Technical content:

- Resource closure
- Guaranteed execution of finally

#### 11.5. **E5 - File Writer (IOException + Finally + BufferedWriter)**

#### Topic

- Writing to text files
- Handling IOException
- Using finally for manual closing

#### Description

The system writes two lines to a .txt file.

Any write failure generates an exception.

Technical Content:

- BufferedWriter + FileWriter
- Line-by-line writing

- Handling with finally and close()

#### 11.6. **E6 - AutoCloseable + try-with-resources**

#### Topic

- Implementation of AutoCloseable
- Automatic resource closing
- Scanner and MockConnection in the same block

#### Description

Creation of the mock class **MockConnection** that simulates the opening and closing of a connection.

Demonstrates how the close() method is called automatically.

#### Technical content:

- try(resource1; resource2; ...) structure
- Automatic execution of close()

#### 11.7. **E7 - Bank Account (Custom Exception + Business Rules)**

#### Topic

- Custom exceptions linked to business rules
- Insufficient balance validation
- Interactive menu

#### Description

The user opens an account with an initial deposit and chooses:

1. Deposit

2. Withdraw

3. View balance

4. Exit

Withdrawal attempts exceeding the balance throw a **BalanceInsufficientException**.

### Technical content:

- Object-oriented programming
- Business rules + custom exception
- Interactive menu
- Scanner + flow control

---

### 12. Summary:

- Difference between checked and unchecked exceptions
- Try, catch, finally
- Throw vs. throws
- Creation of custom exceptions
- Reading and writing files
- Manual and automatic closing of resources
- Implementation of AutoCloseable
- Data and business rule validation
- Interactive menu with Scanner
- Modular organization into Application, Services, and Domain

---