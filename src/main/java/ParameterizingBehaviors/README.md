# Parameterizing Behaviors

---

## Overview
This module contains an exercise demonstrating the use of **Parameterizing Behaviors** in Java, applying the Strategy pattern to represent different payment methods.  
The goal is to show how to parameterize behaviors dynamically, avoiding rigid conditionals and enabling greater flexibility in code design.

**Brief explanation:**  
The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable.  
In this exercise, different payment strategies are implemented as parameterized behaviors, applying specific discounts depending on the amount.

The exercise is organized into three main packages:
- **Domain** — contains the `PaymentStrategy` interface  
- **Controller** — contains the `PaymentProcessor` class responsible for processing payments  
- **Application** — contains the `Main` class demonstrating the use of payment strategies  
There is also a **ProblemQuestion.txt** file with the original problem statement.

---

## Folder Structure

### 1. ParameterizingBehaviors.Domain
Contains the interface that defines the contract for payment strategies.

**Interface: PaymentStrategy**
- Method: `void pay(double amount)`  
- Represents the payment behavior that can be parameterized dynamically

---

### 2. ParameterizingBehaviors.Controller
Contains the class responsible for processing payments using a provided strategy.

**Class: PaymentProcessor**
- Method: `processPayment(double amount, PaymentStrategy strategy)`  
  - Receives the amount and the payment strategy  
  - Calls `strategy.pay(amount)` to execute the parameterized behavior  

---

### 3. ParameterizingBehaviors.Application
Demonstrates multiple real-world scenarios using parameterized behaviors.

**Class: Main**
Executes different payment strategies:

- **Credit Card Strategy**
  - Applies a 2% discount if the amount > 3000  
  - Prints payment message via credit card  

- **Pix Strategy**
  - Applies a 10% discount if the amount > 3000  
  - Prints payment message via Pix  

- **Ticket Strategy**
  - Applies a 5% discount if the amount > 3000  
  - Prints payment message via ticket  

**Scenarios demonstrated:**
- Processing a single payment with `PaymentProcessor`  
- Iterating over a list of strategies (`List<PaymentStrategy>`)  
- Executing multiple payments with different parameterized behaviors  

This exercise shows how parameterizing behaviors avoids rigid conditionals and makes the code more flexible and extensible.

---

## Purpose
- Teach the use of parameterized behaviors with Strategy  
- Demonstrate best practices to avoid rigid conditionals  
- Show how to encapsulate different business logic into independent strategies  
- Encourage modular and extensible design  

---

## Files Included
- **Domain/PaymentStrategy.java**  
- **Controller/PaymentProcessor.java**  
- **Application/Main.java**  
- **ProblemQuestion.txt**

---

## How to Run
1. Navigate to the **ParameterizingBehaviors/Application** folder  
2. Run the **Main** class  
3. Observe the console output showing different payment behaviors and applied discounts  

---

## Requirements
- **Java 17+**  
- Basic knowledge of interfaces and OOP  
- Familiarity with lists and loops  

---

## Notes
- Consistent use of the Strategy pattern to parameterize behaviors  
- Avoids rigid and repetitive conditionals  
- Demonstrates how different strategies can be applied dynamically  
- Clearly shows how object-oriented design facilitates extensibility and maintainability  

---
