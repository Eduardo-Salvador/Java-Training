<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)
# Programming Logic with Object-Oriented Programming

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Requirements
All problem descriptions are in their respective folders (ProblemQuestion.txt).

There is a class for generating numeric objects; although primitive variables exist, the goal is to use it as an auxiliary object, since it can be reused in all the exercises involved (OOP concept).

---

## What Was Used

### 1. Primitive types:
Types that will store a simple value in memory.
- int 
- double
- float
- char
- byte
- short
- long
- boolean    

---

### 2. Operators:
#### 2.1. Arithmetic:
Operators that allow you to perform mathematical calculations.
- Sum ( + )
- Subtraction ( - )
- Division ( / )
- Multiplication ( * )
- Rest of Division ( % )

#### 2.2. Relational:
Also called logical variables, they are used to perform comparisons and always return boolean values (true or false).
- ( < ) Less than
- ( <= ) Less than or equal to
- ( > ) Greater than
- ( >= ) Greater than or equal to
- ( == ) Equal to / Identical to
- ( != ) Not equal to

#### 2.3. Logics:
They work in conjunction with conditional structures, serving to provide logic to comparisons.
- ( && ) (And) -> Two true conditions for True result.
- ( || ) (Or) -> One of the conditions true for True result.
- ( ! ) (Negative) -> Inverts the condition; if the previous condition was true, it returns false.

#### 2.4. Attribution:
Assigns values to variables.
- ( = ) Assign some value.
- ( += ) Adds to the value of the variable.
- ( -= ) Subtract from the value of the variable.
- ( *= ) Multiply the value of the variable.
- ( /= ) Divide the value of the variable.
- ( %= ) Add the remainder of the division to the variable.
- ( ++ ) Increment (Add 1).
- ( -- ) Decrement (Subtract 1).

---

### 3. Conditional Structures:
These are the most commonly used structures in programming; directly or indirectly, they serve to give the code a condition, a rule.

    if (condition = true) {
        Execute something in the block if.
    } 
    else if (condition = true) {
        If the first condition is not met, a second condition is tested, 
        and if it is true, something is executed in the if else block.
    } 
    else {
        If no condition is met, execute the else block.
    }

#### 3.1 Ternary Operator:
Created to simplify conditional structures.
    
    varType varName = (condition) ? resultIfTrue : resultIfFalse

- Use it within a variable.
- Chaining ternary strings is not good practice.
---

### 4. Switch Statement:
It's a selection condition that avoids chaining up if and else statements; we call it a reserved word.
    
    switch(input value) {
        case option1:
            do something
            break;
        case option 2:
            do something
            break;
        default: (no option was met in the input)
            do something
            break;
    }

---

### 5. Repetition Structures:
They are used to repeat a certain action a number of times, or to iterate over something.
It breaks the logic of top-down, repeating certain structures.

#### 5.1. While:
While a certain condition is true, the loop repeats.

    while (condition) {
        do something
    }

#### 5.2. Do While:
Same logic as the while loop, but it will necessarily run the loop a certain amount of time; if the while condition is false, it will have executed at least once.

    do {
        do something
    } while (condition)

#### 5.3. For:
It works with indexes, which is useful for iterating through things (counting things).

    for (start; while condition; increment/decrement) {
        do something
    }

#### 5.4. For Each:
A simpler structure for traversing things, similar to a for loop.

    for (TypeElement var : Element) {
        do something with var
    }

---
### 6. Arrays:
Arrays are data structure types that store values of the same type (int, String, double, etc.).
They are viewed as objects of the Java Array class.

    int[] ages -> Reference type.

    int[] ages = new int[3]; -> int array of 3 positions. (Object in memory)

---
### 7. Multidimensional Arrays:
Multidimensional arrays are arrays with rows and columns.

    (line - column)
    11 (0,0) - 32 (0,1) - 64 (0,2) - 128 (0,3)
    43 (1,0) - 86 (1,1) - 91 (1,2) - 102 (1,3)

    int[][] days = new int[5][5];

---

### 8. Object-Oriented Programming and other concepts covered:

- #### Classes:
  Structures that define templates for creating objects, containing attributes (variables) and behaviors (methods). They are like "molds" that specify the characteristics and actions that objects of that type will have.

- #### Objects:
  Concrete instances of a class, created from the template defined by it. Each object has its own set of values for attributes, but shares the class methods.

- #### Constructors:
  Special methods called automatically when creating an object, used to initialize its attributes. They have the same name as the class and have no return type.

- #### Access Modifiers:
  Keywords (public, private, protected) that control the visibility of attributes and methods. They define which parts of the code can access certain class members.

- #### Getters and Setters:
  Methods for accessing (get) and modifying (set) private attributes of a class. They allow controlling how data is read and changed, applying validations when necessary.

- #### Methods:
  Functions defined inside a class that represent the behaviors of objects. They can receive parameters, process data and return values.

- #### Encapsulation:
  Principle of hiding internal implementation details and exposing only necessary public interfaces. It protects data and ensures it is manipulated only through controlled methods.

- #### Object References, Overloading:
  Variables that store memory addresses where objects are located, not the objects themselves. Overloading allows creating multiple methods/constructors with the same name but different parameters.

- #### Associations:
  Relationships between classes where objects of one class connect with objects of another. They can be unidirectional or bidirectional, representing dependencies between entities.

- #### Inheritance:
  Mechanism that allows creating derived classes (children) from existing classes (parents), reusing code. The child class inherits attributes and methods from the parent class, being able to add or modify behaviors.

- ####  toStrings:
  Method that returns a textual representation of an object, facilitating its display and debugging. It overrides the toString() method inherited from the Object class in Java.

- ####  Enumerations:
  Special type that defines a fixed set of named constants, representing predefined values. Useful for representing limited options like days of the week, status, categories, etc.

- ####  Interfaces:
  Contracts that define methods that classes must implement, without specifying how. They enable polymorphism and ensure that different classes follow a common behavior pattern.

- #### Abstract Classes:
  Classes that cannot be instantiated directly and serve as a base for other classes. They can contain abstract methods (without implementation) and concrete methods (with implementation).

- #### Polymorphism:
  Ability of objects from different classes to be treated through a common interface. It allows the same code to work with different types of objects, deciding the behavior at runtime.

- #### Scanners:
  Java class that facilitates reading input data from different sources (keyboard, files). It offers methods to read strings, numbers and other types of formatted data.

---

## Learning Outcomes

- Object-Oriented Programming (OOP), Polymorphism, and other concepts
- Programming logic.
- Package handling.
- Problem solving.

---