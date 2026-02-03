<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)](https://shields.io/)

# Lambdas

This module contains a set of independent exercises demonstrating the use of Lambdas in Java and the main functional interfaces from the `java.util.function` package.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

The goal is to show how to treat code as data, passing behaviors in a simple and direct way, making the code much more concise and expressive!

---

## Architecture:

The exercises are organized into nine categories:

- **Consumer**
- **Function**
- **Predicate**
- **Supplier**
- **Unary Operator**
- **Binary Operator**
- **BiConsumer**
- **BiFunction**
- **BiPredicate**

Each category contains:

- A **Domain** class, which models the entity used in the exercise
- An **Application** class, with the *main* method demonstrating lambda usage
- A **ProblemQuestion.txt** file with the original problem description

---

## 1. What are Lambdas:

Lambdas are anonymous functions (without a name) that can be written in a super concise way. They are a short syntax for implementing methods of functional interfaces.

**Replaces anonymous classes** in a much cleaner and more readable way.

### 1.1 Main Uses:

**1. With functional interfaces (Predicate, Consumer, Function, etc).**

2. With **Streams** (collection processing).

3. Custom **sorting**.

4. **Threads**.

### 1.2. Lambda Advantages:

**Cleaner and more readable code** - less boilerplate

**Fewer lines of code** - increases productivity

**Facilitates functional programming** - pass behaviors as parameters

**Works well with Streams** - efficient data processing

### 1.3. Limitations:

Only works with **functional interfaces** (1 abstract method)

Cannot access non-final local variables

Can make debugging difficult in complex cases

> **In summary:** Lambdas are Java's way of allowing you to **treat code as data**, passing behaviors in a simple and direct way, making the code much more concise and expressive!

---

## 2. Operations and Syntax Summary

### 2.1. Predicate\<T>
- `predicate.test(t)` - Tests a condition and returns boolean
- `predicate.and(other)` - Combines two predicates with logical AND
- `predicate.or(other)` - Combines two predicates with logical OR
- `predicate.negate()` - Inverts the predicate result

### 2.2. BiPredicate\<T, U>
- `biPredicate.test(t, u)` - Tests a condition with two parameters
- `biPredicate.and(other)` - Combines with logical AND
- `biPredicate.or(other)` - Combines with logical OR
- `biPredicate.negate()` - Inverts the result

### 2.3. Consumer\<T>
- `consumer.accept(t)` - Performs an action on the parameter
- `consumer.andThen(after)` - Chains another consumer to execute after

### 2.4. BiConsumer\<T, U>
- `biConsumer.accept(t, u)` - Performs an action with two parameters
- `biConsumer.andThen(after)` - Chains another biConsumer

### 2.5. Function\<T, R>
- `function.apply(t)` - Transforms T into R
- `function.andThen(after)` - Chains another function after this one
- `function.compose(before)` - Executes another function before this one
- `Function.identity()` - Returns a function that returns the argument itself

### 2.6. BiFunction\<T, U, R>
- `biFunction.apply(t, u)` - Transforms T and U into R
- `biFunction.andThen(after)` - Chains a function after this one

### 2.7. UnaryOperator\<T>
- `unaryOperator.apply(t)` - Transforms T into T (same type)
- `unaryOperator.andThen(after)` - Chains another operator
- `unaryOperator.compose(before)` - Executes another operator before
- `UnaryOperator.identity()` - Returns an operator that returns the argument itself

### 2.8. BinaryOperator\<T>
- `binaryOperator.apply(t1, t2)` - Combines two T into T
- `BinaryOperator.minBy(comparator)` - Returns the minimum value according to the comparator
- `BinaryOperator.maxBy(comparator)` - Returns the maximum value according to the comparator

### 2.9. Supplier\<T>
- `supplier.get()` - Provides/generates a value of type T

---

## 3. Exercises:

### 3.1. Lambdas.Consumer
Demonstrates the use of the **Consumer** functional interface.

**Key concepts:**
- List processing with lambdas
- Method references (`System.out::println`)
- Consumer chaining with `andThen`
- Dynamic execution of multiple consumers

**Domain Class: Product**
- Attributes: `name`, `price`
- Method: `processProducts(List<Product>, Consumer<Product>)`

**Application:**
- Standard printing consumer
- Discount consumer
- Consumer that converts name to uppercase
- Consumer chaining
- Execution of a list of consumers

---

### 3.2. Lambdas.Function
Demonstrates the use of **Function<T, R>** and **Predicate**.

**Key concepts:**
- Value mapping using `Function`
- Filtering using `Predicate`
- Collection transformation (extraction and conversion of names)
- Combination of filtering and transformation

**Domain Class: Product**
- Filters products via `filterProducts(...)`
- Maps product names via `mapProducts(...)`

**Application:**
- Filtering by price (greater than / less than)
- Mapping names to uppercase
- Applying mapping after filtering

---

### 3.3. Lambdas.Predicate
Demonstrates the use of **Predicate** for conditional evaluation.

**Key concepts:**
- Object filtering with custom predicates
- Condition combination with `AND`
- Condition negation with `predicate.negate()`

**Domain Class: Employee**
- Attributes: `name`, `age`, `salary`
- Static method `filterEmployee(List<Employee>, Predicate<Employee>)` prints matching employees

**Application:**
- Filtering by salary, age, name initials
- Compound conditions using multiple criteria
- Predicate negation for inverse filtering

---

### 3.4. Lambdas.Supplier
Demonstrates the use of the **Supplier** functional interface.

**Key concepts:**
- On-demand data generation (lazy evaluation)
- Object creation with `Supplier`
- Random and dynamic value generation
- Using `Stream.generate()` to create collections

**Domain Class: Customer**
- Attributes: `customerId`, `name`, `registrationDate`
- Method: `generateCustomers(int count, Supplier<Customer>)`

**Application:**
- Random ID supplier
- Sequential name supplier with AtomicInteger
- Current date supplier with `LocalDateTime.now()`
- Random price supplier

---

### 3.5. Lambdas.BiPredicate
Demonstrates the use of **BiPredicate<T, U>** for validation with two parameters.

**Key concepts:**
- Comparison between objects and reference values
- Validation with multiple criteria
- Filtering based on dual conditions
- Use in comparison contexts

**Domain Class: Product**
- Attributes: `name`, `price`, `stock`
- Method: `compareProducts(List<Product>, Double threshold, BiPredicate<Product, Double>)`

**Application:**
- Price validation against limit
- Stock comparison with minimum value
- Price equality verification
- BiPredicate between two products (direct comparison)

---

### 3.6. Lambdas.BiConsumer
Demonstrates the use of **BiConsumer<T, U>** for actions with two parameters.

**Key concepts:**
- Processing value pairs
- Applying operations on Maps (forEach)
- Updating objects with additional values

**Domain Class: Order**
- Attributes: `orderId`, `totalAmount`, `discount`
- Method: `processOrders(List<Order>, Double rate, BiConsumer<Order, Double>)`

**Application:**
- BiConsumer to apply percentage discount
- BiConsumer to add fees
- BiConsumer to print formatted details

---

### 3.7. Lambdas.BiFunction
Demonstrates the use of **BiFunction<T, U, R>** for transformations with two parameters.

**Key concepts:**
- Calculations combining two values
- Data transformation with multiple inputs
- Computed result return

**Domain Class: CartItem**
- Attributes: `productName`, `quantity`, `unitPrice`
- Method: `calculateTotal(List<CartItem>, Double taxRate, BiFunction<Double, Double, Double>)`

**Application:**
- BiFunction for total calculation with tax
- BiFunction to apply fixed fee
- BiFunction to apply percentage discount
- Price calculation with bonus quantity

---

### 3.8. Lambdas.UnaryOperator
Demonstrates the use of **UnaryOperator<T>** for same-type transformations.

**Key concepts:**
- Value transformation maintaining type
- Application of adjustments and modifications
- Operation chaining with `andThen` and `compose`
- Use in processing pipelines

**Domain Class: Product**
- Attributes: `name`, `price`
- Method: `adjustPrices(List<Product>, UnaryOperator<Double>)`

**Application:**
- UnaryOperator for percentage price increase
- UnaryOperator to add fixed value
- UnaryOperator for rounding
- Chaining: increase 10% and then round
- Using `compose()` for reverse operation order

---

### 3.9. Lambdas.BinaryOperator
Demonstrates the use of **BinaryOperator<T>** to combine two values of the same type.

**Key concepts:**
- Reduction operations (sum, maximum, minimum)
- Combination of homogeneous values
- Use in merge operations
- Integration with `Stream.reduce()`

**Domain Class: Warehouse**
- Attributes: `location`, `stock`
- Method: `mergeStock(List<Warehouse>, List<Warehouse>, BinaryOperator<Integer>)`

**Application:**
- BinaryOperator for stock sum
- BinaryOperator for maximum (`Math.max`)
- BinaryOperator for minimum (`Math.min`)
- BinaryOperator for average calculation
- Merging inventories from multiple warehouses

---

## 4. Objectives Achieved

**Complete understanding of Java's main functional interfaces**
- Mastery of Predicate, Consumer, Function, and Supplier
- Understanding of their two-parameter variants (Bi*)
- Knowledge of specialized operators (Unary/Binary)

**Practical application of lambda expressions**
- Lambda syntax in different contexts
- Use of method references
- Creation of more concise and readable code

**Advanced functional programming techniques**
- Operation chaining (`andThen`, `compose`)
- Predicate combination (`and`, `or`, `negate`)
- Collection reduction with binary operators
- Lazy data generation with Supplier

---