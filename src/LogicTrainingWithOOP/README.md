# Training Logic and Object-Oriented Programming

---

## Requirements
All problem descriptions are in their respective folders.

---

## What Was Used

### 1. Primitive types
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

### 2. Operators
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

### 3. Conditional Structures
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

### 4. Switch Statement
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

### 5. Repetition Structures
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
### 6. Arrays
Arrays are data structure types that store values of the same type (int, String, double, etc.).
They are viewed as objects of the Java Array class.

    int[] ages -> Reference type.

    int[] ages = new int[3]; -> int array of 3 positions. (Object in memory)

---
### 7. Multidimensional Arrays
Multidimensional arrays are arrays with rows and columns.

    (line - column)
    11 (0,0) - 32 (0,1) - 64 (0,2) - 128 (0,3)
    43 (1,0) - 86 (1,1) - 91 (1,2) - 102 (1,3)

    int[][] days = new int[5][5];

---

### 8. OOP - Classes, Objects, Constructors, Getters and Setters, Methods, Access Modifiers, Encapsulation, Reference to objects, Overloads, Associations, Scanners, Heritage, toStrings, Enumerations, Interfaces, Abstract Classes, Polymorphism.

---

## Learning Outcomes

- Object-Oriented Programming (OOP), Polymorphism, and other concepts
- Programming logic.
- Package handling.
- Problem solving.

---