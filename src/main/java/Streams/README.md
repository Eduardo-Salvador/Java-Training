<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Streams

This module contains a comprehensive series of exercises demonstrating the use of Streams in Java, a feature introduced in Java 8 that enables processing data collections in a declarative and functional manner.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

Streams represent a **sequence of elements that support sequential and parallel operations**. Instead of writing explicit loops, you **describe what you want to do with the data** in a more readable and functional way.

**Important:** Streams **do not store data** - they operate on a data source (such as collections, arrays, files). A Stream can only be traversed once, and after a terminal operation it is closed.

---

## Architecture

The project is organized as follows:

- **Domain**: Contains model classes (`Product`, `Student`, `Order`, `Employee`, `Sale`, `Customer`, etc.)
- **Application**: Contains `Main` classes with practical implementations of each exercise
- **13 Progressive Exercises**: From basic to advanced, covering all Stream operations

---

## 1. What are Streams

Streams are a feature that allows processing data collections declaratively. They build an **operation pipeline** that only executes when you "request the result" (terminal operation).

### 1.1. Stream Pipeline

```
1. CREATE Stream → 2. INTERMEDIATE (optional) → 3. TERMINAL (required)
```

```java
list.stream()                           // Create
    .filter(n -> n > 5)                 // Intermediate
    .map(n -> n * 2)                    // Intermediate
    .collect(Collectors.toList());      // Terminal
```

### 1.2. Creating Streams

**From collections:**
```java
List<String> names = Arrays.asList("Ana", "Bruno", "Carlos");
Stream<String> stream = names.stream();
```

**From values:**
```java
Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
```

**Infinite streams:**
```java
Stream<Integer> infinite = Stream.iterate(0, n -> n + 2);
Stream<Double> randoms = Stream.generate(Math::random);
```

**Numeric ranges:**
```java
IntStream.range(1, 10);         // 1 to 9
IntStream.rangeClosed(1, 10);   // 1 to 10
```

### 1.3. Intermediate Operations

Return another Stream and are **lazy** (only execute when there's a terminal operation):

- `filter(predicate)` — filters elements
- `map(function)` — transforms elements
- `flatMap(function)` — "flattens" nested structures
- `sorted()` — sorts elements
- `sorted(comparator)` — sorts with custom criteria
- `distinct()` — removes duplicates
- `limit(n)` — limits quantity
- `skip(n)` — skips elements
- `peek(consumer)` — executes action without modifying stream

### 1.4. Terminal Operations

Close the Stream and produce a result:

**Collection:**
- `collect(collector)` — collects into data structure
- `toArray()` — converts to array

**Reduction:**
- `reduce(accumulator)` — combines elements into one value
- `count()` — returns quantity
- `sum()`, `average()`, `min()`, `max()` — numeric operations

**Search:**
- `findFirst()` — returns first element
- `findAny()` — returns any element

**Verification:**
- `allMatch(predicate)` — do all meet condition?
- `anyMatch(predicate)` — does any meet condition?
- `noneMatch(predicate)` — does none meet condition?

**Action:**
- `forEach(consumer)` — executes action for each element

### 1.5. Advantages of Lazy Evaluation

1. **Performance** — doesn't process unnecessary data
2. **Optimization** — can combine operations
3. **Short-circuit** — stops as soon as it finds result in operations like `findFirst()`

### 1.6. Benefits of Using Streams

**More readable code** — expressive and declarative

**Less boilerplate** — eliminates verbose loops

**Functional programming** — encourages immutability

**Easy parallelization** — use `parallelStream()`

**Automatic optimization** — lazy evaluation and short-circuit

---

## 2. Quick Reference Table

### 2.1. Intermediate Operations

| Operation | Signature | Use |
|-----------|-----------|-----|
| `filter()` | `Predicate<T>` | Filters elements |
| `map()` | `Function<T,R>` | Transforms elements |
| `flatMap()` | `Function<T,Stream<R>>` | Flattens structures |
| `sorted()` | - or `Comparator<T>` | Sorts |
| `distinct()` | - | Removes duplicates |
| `limit()` | `long` | Limits quantity |
| `skip()` | `long` | Skips elements |
| `peek()` | `Consumer<T>` | Debug/effects |

### 2.2. Terminal Operations

| Operation | Return | Use |
|-----------|--------|-----|
| `collect()` | Collection/Map/etc | Collects result |
| `reduce()` | `Optional<T>` or `T` | Combines elements |
| `forEach()` | `void` | Executes action |
| `count()` | `long` | Counts elements |
| `findFirst()` | `Optional<T>` | First element |
| `findAny()` | `Optional<T>` | Any element |
| `anyMatch()` | `boolean` | Any meets condition? |
| `allMatch()` | `boolean` | All meet condition? |
| `noneMatch()` | `boolean` | None meets condition? |
| `min()`/`max()` | `Optional<T>` | Minimum/maximum |
| `sum()`/`average()` | number | Sum/average (primitives) |

### 2.3. Main Collectors

| Collector | Result | Use |
|-----------|--------|-----|
| `toList()` | `List<T>` | Collects to list |
| `toSet()` | `Set<T>` | Collects to set |
| `toMap()` | `Map<K,V>` | Creates map |
| `groupingBy()` | `Map<K,List<V>>` | Groups elements |
| `counting()` | `Long` | Counts elements |
| `summingDouble()` | `Double` | Sums values |
| `averagingDouble()` | `Double` | Calculates average |
| `summarizingDouble()` | `DoubleSummaryStatistics` | Statistics |
| `joining()` | `String` | Concatenates strings |
| `mapping()` | Varied | Transforms before collecting |

---

## 3. Exercises

### 3.1. Basic Operations (filter, map, collect)

**Product Filtering**

Creates a `Product` class and filters products with price above 100.

**Key concepts:**
- Stream creation from list
- `filter()` with predicate
- `map()` to extract specific attributes
- `collect(Collectors.toList())` to collect result

**Product Class:**
- Attributes: `name`, `price`, `category`
- List with 8 products from various categories

---

### 3.2. Sorted and Distinct

**Student Sorting**

Removes duplicates and sorts students by grade.

**Key concepts:**
- `distinct()` to remove duplicates
- `sorted()` with `Comparator.comparing()`
- Descending order with `reversed()`

**Student Class:**
- Attributes: `name`, `grade`
- List with 10 students (some duplicate names)

---

### 3.3. Finding and Matching

**Order Analysis**

Demonstrates search and verification operations.

**Key concepts:**
- `findFirst()` — first element meeting condition
- `findAny()` — any element (useful in parallel)
- `anyMatch()` — is there any?
- `allMatch()` — do all meet condition?
- `noneMatch()` — does none meet condition?
- Working with `Optional<T>`

**Order Class:**
- Attributes: `id`, `customerName`, `totalValue`, `status`
- Status: PENDING, SHIPPED, DELIVERED

---

### 3.4. Reduce

**Sales Calculation**

Combines elements using `reduce()`.

**Key concepts:**
- `reduce()` with initial value
- `reduce()` without initial value (returns Optional)
- Using method reference (`Double::sum`, `Integer::max`)
- Operation chaining

**Sale Class:**
- Attributes: `product`, `quantity`, `unitPrice`
- Method: `getTotalValue()` returns `quantity * unitPrice`

---

### 3.5. FlatMap

**Classrooms and Students**

"Flattens" nested lists into a single list.

**Key concepts:**
- `flatMap()` to flatten structures
- Difference between `map()` and `flatMap()`
- Combining with `filter()` and `sorted()`

**Classroom Class:**
- Attributes: `className`, `students` (List<String>)
- 4 classrooms, each with 3-5 students

---

### 3.6. Generating Streams (generate, iterate, range)

**Number Generation**

Creates Streams without pre-existing collections.

**Key concepts:**
- `Stream.generate()` — infinite generation with Supplier
- `Stream.iterate()` — sequences based on previous value
- `IntStream.range()` and `rangeClosed()`
- Need for `limit()` with infinite streams
- Prime number verification

---

### 3.7. Collectors.summarizingDouble/Int/Long

**Transaction Statistics**

Collects summary statistics in a single object.

**Key concepts:**
- `summarizingDouble()` returns `DoubleSummaryStatistics`
- Available methods: `getCount()`, `getSum()`, `getMin()`, `getMax()`, `getAverage()`
- Combining with `filter()` for conditional statistics

**Transaction Class:**
- Attributes: `type` (DEPOSIT/WITHDRAWAL), `amount`

---

### 3.8. Collectors.groupingBy (Basic)

**Employee Grouping**

Groups elements into a Map.

**Key concepts:**
- `groupingBy()` returns `Map<K, List<V>>`
- Grouping by attribute
- `groupingBy()` with `counting()` — counts elements per group

**Employee Class:**
- Attributes: `name`, `department`, `salary`
- 12 employees from 4 departments

---

### 3.9. Collectors.groupingBy (Advanced with downstream)

**Product Analysis by Category**

Uses downstream collectors for transformations on groups.

**Key concepts:**
- `groupingBy()` with downstream collectors
- `summingDouble()` — sums values per group
- `averagingDouble()` — average per group
- `summarizingDouble()` — statistics per group
- `mapping()` — extracts specific fields

**Product Class:**
- Attributes: `name`, `category`, `price`, `stock`
- 15 products from 3 categories

---

### 3.10. Collectors.toMap

**Student ID Mapping**

Creates custom Maps from Streams.

**Key concepts:**
- `toMap(keyMapper, valueMapper)` — creates Map
- `toMap()` with merge function — resolves duplicates
- Map types: HashMap, TreeMap, LinkedHashMap

**Student Class:**
- Attributes: `id`, `name`, `course`

---

### 3.11. Multi-Level Grouping

**Multi-Level Sales Grouping**

Groups by multiple nested criteria.

**Key concepts:**
- Nested `groupingBy()`
- Map of Maps: `Map<String, Map<String, List<T>>>`
- Combining with `counting()` and `summingDouble()`

**Sale Class:**
- Attributes: `region`, `product`, `quantity`, `value`
- 20 sales from different regions and products

---

### 3.12. Parallel Streams

**Performance Comparison**

Processes elements in parallel using multiple threads.

**Key concepts:**
- `parallelStream()` vs `stream()`
- When to use: large collections + heavy operations
- When to avoid: small collections, fast operations, order matters
- Thread-safety and shared state
- Execution time measurement
- Speedup factor

**Task Class:**
- Attributes: `id`, `processingTime`
- Method: `execute()` — simulates work with Thread.sleep()

---

### 2.13. Final Integrated Exercise

**E-commerce System**

Complete exercise integrating all concepts.

**Key concepts:**
- All Stream operations in a real project
- Relationship between entities (Customer ↔ Order)
- FlatMap for products
- Multi-level GroupingBy
- Statistics and aggregations
- Matching and Finding
- ToMap for joins

**Classes:**

**Customer:**
- Attributes: `id`, `name`, `city`

**Order:**
- Attributes: `orderId`, `customerId`, `products`, `totalValue`, `status`
- Status: PENDING, PROCESSING, SHIPPED, DELIVERED

**Operations Performed:**

1. **FlatMap** — Extract all unique products
2. **GroupingBy + Counting** — Count orders by status
3. **GroupingBy + Summing** — Total sales by city (join with Customer)
4. **Filter + Map + Reduce** — Sum of DELIVERED orders
5. **Matching:**
- anyMatch: orders > 1000
- allMatch: all have products
- noneMatch: no empty names
6. **Statistics** — Min, Max, Average of values
7. **ToMap** — customerId → customerName
8. **Multi-level** — City → Status → Orders

---

## 3. Results

**Complete mastery of Stream creation**
- Streams from collections, values, generate, iterate, ranges
- When to use each creation method

**Intermediate operations**
- filter, map, flatMap, sorted, distinct, limit, skip
- Understanding lazy evaluation
- Efficient chaining

**Terminal operations**
- collect, reduce, forEach, count, sum, min, max
- findFirst, findAny, anyMatch, allMatch, noneMatch
- Choosing the appropriate terminal operation

**Advanced collectors**
- groupingBy with downstream collectors
- toMap with merge functions
- summarizing for statistics
- Multi-level grouping

**Functional programming**
- Lambdas and method references
- Immutability and side-effects
- Function composition

**Performance and parallelism**
- When to use parallelStream()
- Thread-safety and shared state
- Measurement and optimization

**Best practices**
- Declarative and readable code
- Avoiding side-effects in intermediate operations
- Choosing appropriate data structures
- Combining Streams with Optional

**Real-world applications**
- Data processing
- Reports and aggregations
- Complex transformations
- Data analysis

---