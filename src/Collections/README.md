# Collections
This module consolidates a comprehensive set of exercises involving the Java Collections Framework, focusing on lists, queues, sets, maps, sorting mechanisms, searching strategies, iteration patterns, and object comparison. Each exercise is accompanied by a ProblemQuestion.txt file containing the full specifications.

Although this module is centered on Collections, brief conceptual references to data structures, hashing, tree-based ordering, and algorithmic cost (Big-O) are included. These topics are addressed only at a high level.
A dedicated project for deeper structural analysis is available at:

**Data Structures Project:**
https://github.com/Eduardo-Salvador/Data_Strutcture-in-Java

---

## 1. Foundations: Collections and Data Structures
### 1.1 Java Collections Framework Overview
The Collections Framework unifies a set of interfaces and implementations designed to store, manipulate, and organize data efficiently. This module uses:
- List (ArrayList, LinkedList)
- Set (HashSet, TreeSet, NavigableSet)
- Map (HashMap, LinkedHashMap, TreeMap, NavigableMap)
- Queue (LinkedList, PriorityQueue)
- Sorting utilities and binary search
- Iterators and enhanced iteration patterns

Each structure has its own internal behavior and performance characteristics.

---

## 2. Brief Notes on Data Structures
This module touches on several underlying structures:

### Array-backed structures (e.g., ArrayList)
- Fast index access (O(1))
- bSlower insertions/removals in the middle (O(n))

### Linked structures (e.g., LinkedList)
- Fast insertions/removals at ends (O(1))
- Slow random access (O(n))

#### Hash-based structures (HashMap, HashSet)
- Use hashing: a mathematical function transforms a key into a hashed integer.
- Provide average O(1) for access, insertion, and removal.
- No ordering guarantee.

### Tree-based structures (TreeMap, TreeSet)
- Backed by balanced trees (e.g., Red-Black Tree).
- Maintain sorted order of elements or keys.
- Operations run in O(log n).

A complete deep-dive into trees, hashing functions, collision management, or linked representations is provided in the referenced Data Structures project.

---

## Big-O Notation
This module does not perform algorithmic analysis in depth. However, Big-O concepts help explain structural performance:
- O(1) constant time (e.g., hash lookups)
- O(log n) logarithmic time (e.g., tree insertion/search)
- O(n) linear time (e.g., iterating over lists or sets)
- O(n log n) typical for sorting operations

These performance factors are mentioned only to contextualize how the chosen structure impacts behavior in the provided exercises.

---

## 4. Hash vs. Tree 
### 4.1 Hash: Concept and Purpose
Structures like **HashSet** and **HashMap** use hash tables:
- Each key (or element, in the case of a HashSet) generates a hashCode().
- This hash value is processed (usually by spreading/mixing bits) to determine the bucket where the object will be stored.
- The bucket may contain one or more entries due to collisions, handled internally (Java uses linked lists or balanced trees depending on the case).
- Because lookup, insertion and removal operate on hashed bucket positions, they provide average time complexity O(1).
- Worst-case time becomes O(log n) when collisions form tree-based buckets in modern Java (since Java 8).
- Hash-based structures do not maintain order — elements are stored based on hash distribution.
- It offers more **performance.** 

Therefore, duplicates are avoided by comparing:
1. Hash Code
2. Equal (to confirm)

Exercises with hash:
- Collections.Set.HashSet
- Collections.Map.HashMap
- Implementations of equals() and hashCode()
- Email User System

### 4.2 Trees
TreeSet and TreeMap use a tree structure (usually Red-Black Tree):
- Always store ordered data
- All operations cost O(log n)
- Allow navigable operations:
  - higher()
  - lowest()
  - first()
  - last()
  - subMap(), etc.
- It offers **more methods**, but **loses a little in performance**.

Exercises with trees:
- TreeSet of books
- TreeMap of contacts
- Navigation in ascending/descending order
- Updates while maintaining the order

---

## 5. Object Comparison: Comparable and Comparator
### Comparable
Used when a class defines its default natural ordering, implementing:

    public int compareTo(T o)

Example from the module:
- Students sorted by name
- Books sorted alphabetically by title

### Comparator
Used to define custom or alternative ordering strategies.

Examples:
- Compare Students by age
- Compare Students by grade (including reverse sorting)

These mechanisms enable flexible sorting while interacting with lists, sets, priority queues, and tree-based structures.

---

## 6. Basic Syntax for Lists (Used Throughout the Module)
Lists are the starting point for most collection operations. This module uses:

    List<Product> products = new ArrayList<>();
    products.add(new Product("Mouse", 120.0, 10));
    products.get(0);
    products.removeIf(p -> p.getName().equals("Mouse"));
    products.sort(Comparator.comparing(Product::getPrice));

Key characteristics:
- Ordered
- Allow duplicates
- Index-based access

This foundation enables transitions into more advanced structures presented in other sections.

---

## 7. Module Overview and Exercise Documentation
Each group of exercises is organized by topic.
Every exercise includes its own ProblemQuestion.txt inside the corresponding directory.

---

### 7.1 Lists – ArrayList and LinkedList
#### Topics covered:
- Adding, removing, updating objects
- Iteration and enhanced for-loop
- Sorting using Comparator
- Natural ordering with Comparable
- Conversion between lists and arrays
- Object equality (equals / hashCode)
- LinkedList as a double-ended queue
- Practical business scenarios (inventory management, customer queues)
#### Exercises Included:
**1. Inventory Management (ArrayList)**
- CRUD operations
- Sorting by price
- Preventing duplicates using equals and hashCode
- Domain: Product, Controller: InventoryManager, Application: Main

**2. Technical Support Queue (LinkedList)**
- peek, poll, removeFirst, addLast
- Queue operations
- Iterator usage
- Avoiding duplicate customer tickets

**3. List/Array Conversion**
- Removing items based on conditions
- Converting list → array → list
- Preventing re-insertion of duplicates

**4. Employee Registration Using Equals**
- Detecting employees with duplicate CPF
- Building an additional filtered employee list

---

### 7.2 Sets – HashSet and TreeSet
#### Topics covered:
- Eliminating duplicates through hashing
- Understanding unpredictability of ordering in HashSet
- Using TreeSet for ordered sequences
- Navigating ordered sets through lower, higher, first, last

#### Exercises Included:
**1. User Registration with HashSet**
- Duplicate suppression via email hashing
- Removing based on user input
- Practical demonstration of hash-based semantics

**2. Book Catalog with TreeSet**
- Ordering books by title via natural ordering
- Navigating neighbors in the sorted set
- Reversing set order with descendingSet()

---

### 7.3 Maps – HashMap, LinkedHashMap, TreeMap (NavigableMap)
#### Topics covered:
- Key/value associations
- Duplicate key suppression
- Updating mapped objects
- Maintaining insertion order (LinkedHashMap)
- Natural ordering of keys (TreeMap)
- Navigable operations: higherEntry, lowerEntry, firstEntry, lastEntry
- Editing map entries

### Exercises Included:
**1. Product Map (HashMap + LinkedHashMap)**
- Keyed insertion
- Updating values by key
- Removing entries
- Demonstration of classic map behaviors

**2. Contact Directory (TreeMap)**
- Ordered contact management
- Updating attributes using user input
- Demonstrating key-based navigation

---

### 7.4 Queues – LinkedList, Queue Interface, PriorityQueue
#### Topics covered:
- FIFO operations with LinkedList
- Serving customers in order
- Clearing and monitoring queues
- Priority-based queues (min-heap behavior)
- Custom comparable implementation for priority models

#### Exercises Included:
**1. Basic Queue Operations**
- Viewing next customer
- Serving customers
- Clearing the queue

**2. Priority-Based Medical Queue**
- Normal queue + priority queue acting together
- Serving based on severity level
- Using Comparable<Patient> for priority comparison

**3. PriorityQueue Standalone**
- Demonstration of built-in priority behavior
- Polling in ascending priority order

### 7.5 Sorting Lists and Binary Search
#### Topics covered:
- Using Collections.sort() with Comparable
- Using custom Comparators
- Reversing order
- Binary search on sorted lists

#### Exercises Included:
**1. Sorting Students**
- Natural ordering (name)
- Sorting by age
- Sorting by grade (descending)

**2. Library Search (Binary Search)**
- Sorting books
- Searching using Collections.binarySearch()
- Returning the located object

---

## 8. Summary
- Using core collections (List, Set, Map, Queue)
- Understanding structural behaviors (hashing, trees, arrays, links)
- Performing sorting and searching
- Handling equality and object identity
- Navigating ordered structures
- Modeling realistic scenarios with domain objects
- Transitioning into advanced data-structure reasoning

All exercises are paired with a ProblemQuestion.txt, and more advanced structural concepts can be explored in the dedicated Data Structures project.

---