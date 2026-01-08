<div align="center">

![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-<COLOR>.svg)

# Collections

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

## Overview

This module consolidates a comprehensive set of exercises involving the
Java Collections Framework, focusing on lists, queues, sets, maps,
sorting mechanisms, search strategies, iteration patterns, and
object comparison. Each exercise is accompanied by a
**ProblemQuestion.txt** file containing the complete specifications.

Although this module focuses on Collections, brief conceptual references
on data structures, hashing, tree-based sorting
and algorithmic cost (Big-O) are also included. These details are
only superficially involved. A dedicated project for a more in-depth structural analysis is available at:
[Data Structures Project](https://github.com/Eduardo-Salvador/Data_Strutcture-in-Java)

---

## Architecture
- **Domain** → Classes and Entities.

- **Services** → Business rules and data manipulation.

- **App** → Initializes the application.

- Some exercises involve the app manipulating the data.

---

## What was used:

### 1. Overview of the Java Collections Framework

The Collections Framework unifies a set of interfaces and implementations designed to efficiently store, manipulate, and organize data. This module uses:

- **List** (ArrayList, LinkedList)
- **Set** (HashSet, TreeSet, NavigableSet)
- **Map** (HashMap, LinkedHashMap, TreeMap, NavigableMap)
- **Queue** (LinkedList, PriorityQueue)
- **Sorting and binary search utilities**
- **Iterators and advanced iteration patterns**

Each structure has its own internal behavior and performance characteristics.

---

### 2. Brief Notes on Data Structures

#### Lists:
Ordered collections that allow duplicate elements and index access. ArrayList offers O(1) for index access but O(n) for insertion/removal in the middle; LinkedList has O(n) for access but O(1) for insertion/removal at the ends. Choose ArrayList for frequent reading and LinkedList for many insertions/removals.
#### Maps:
Key-value structures that do not allow duplicate keys. HashMap offers O(1) for basic operations but without ordering; TreeMap maintains ordered keys with O(log n); LinkedHashMap preserves insertion order. Choice based on the need for ordering vs. performance.
#### Sets:
Collections that do not allow duplicate elements. HashSet offers O(1) for add/remove/contains without ordering; TreeSet maintains ordered elements with O(log n); LinkedHashSet preserves insertion order. Useful for checking for existence and eliminating duplicates.
### Queues:
FIFO (first-in-first-out) structures for ordered processing. LinkedList implements a simple queue; PriorityQueue orders by priority (heap) with O(log n) for add/poll; ArrayDeque is efficient for queue/stack. Ideal for sequential processing and algorithms with priorities.

---

### 3. Big-O Notation

This module does not perform in-depth algorithmic analysis. However, Big-O concepts help explain the performance of the following structures:

- **O(1)** constant time (e.g., hash searches)
- **O(log n)** logarithmic time (e.g., insertion/search in trees)
- **O(n)** linear time (e.g., iterating over lists or sets)
- **O(n log n)** common in sorting operations

These factors are mentioned only to contextualize how the chosen structure impacts the behavior of the exercises.

---

### 4. Object Comparison: Comparable and Comparator

#### Comparable:

Used when the class defines its natural ordering:

``` java
public interface Comparable<T> {
  int compareTo(T o);
}
```

Examples in the module:

- Students sorted by name
- Books sorted alphabetically by title

#### Comparator:

Used to define alternative sorting strategies.

``` java
public interface Comparator<T> {
  int compare(T o1, T o2);
}
```

Examples:

- Compare students by age
- Compare students by grade (descending)

These mechanisms allow flexible sorting in lists, sets, priority queues, and tree-based structures.

---

### 5. Structures

#### Array-based structures (ArrayList):

It **stores data in an ordered and linear way and defines the standard operations of the List data structure.**

- Allows duplicates.
- Two elements cannot be in the same position.
- The order criterion defines the element's position.
- Removal and Addition via index.

  | Operation | Complexity |
    | --- | --- |
  | `add(E)` | O(1) Amortized |
  | `add(index, E)` | O(n) |
  | `get(index)` | O(1) |
  | `set(index, E)` | O(1) |
  | `remove(index)` | O(n) |
  | `indexOf(Object)` | O(n) |
  | `contains(Object)` | O(n) |
  | `size()` | O(1) |
``` java
List<String> list = new ArrayList<>();
```

---

#### Linked Structures (LinkedList):

It **stores data in an ordered and linear way and defines the standard operations of the List data structure.**

- Allows duplicates.
- Two elements cannot be in the same position.
- The order criterion defines the element's position.
- Removal and Addition via index.

| Operation | Complexity |
  | --- | --- |
| `add(E)` | O(1) |
| `add(index, E)` | O(n) |
| `get(index)` | O(n) |
| `set(index, E)` | O(n) |
| `remove(index)` | O(n) |
| `indexOf(Object)` | O(n) |
| `contains(Object)` | O(n) |
| `size()` | O(1) |
``` java
List<String> list = new LinkedList<>();
```

---

#### Queue-based structures (Queue, PriorityQueue):

- Queue follows the FIFO (First-In-First-Out) principle for sequential processing.
- PriorityQueue is based on a binary heap (min-heap by default).
- Simple Queue has O(1) operations, while PriorityQueue has O(log n) for insertion/removal.
An in-depth analysis of heaps, load balancing, and queue structures is available in the aforementioned Data Structures project.

#### Queue (LinkedList):
| Operation | Complexity | Observations |
| --- | --- |-------------------------------------|
| **offer(element) / add(element)** | O(1) | Adds to the end of the queue |
| **poll() / remove()** | O(1) | Removes and returns the first element |
| **peek() / element()** | O(1) | Returns the first element without removing it |
| **size()** | O(1) | Keeps the internal counter |
| **isEmpty()** | O(1) | Checks if it is empty |
| **contains(element)** | O(n) | Requires traversing the queue |
| **Full iteration** | O(n) | Traverses all elements |
```java
Queue<String> queue = new LinkedList<>();
```

#### PriorityQueue:
| Operation | Complexity | Observations |
| --- | --- | --- |
| **offer(element) / add(element)** | O(log n) | Insert and reorganize the heap |
| **poll() / remove()** | O(log n) | Root removal and rebalancing |
| **peek() / element()** | O(1) | Returns the element with the highest priority |
| **remover(element)** | Over(n) | Requires searching and then rebalancing |
| **contains(element)** | Over(n) | Linear search in the heap |
| **size()** | O(1) | Keeps internal counter |
| **Full iteration** | Over(n) | Traverses but does NOT guarantee order |
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

---

#### Hash-based structures (HashMap, HashSet):

- They use hashing: a mathematical function transforms a key into an integer value.
- They offer, on average, **O(1)** for access, insertion, and removal.
- They do not guarantee order.

#### Equals:

Works with comparisons based on values ​​and not object references.
- **Rules:**

- **Reflexive**: `x.equals(x)` must be `true` for everything that is different from `null`.
- **Symmetric**: for `x and y ≠ null`, if `x.equals(y) == true`, then `y.equals(x) == true;`
- **Transitivity**: for `x, y, z ≠ null`, if `x.equals(y) == true`, `x.equals(z) == true`, `y.equals(z) == true.`
- **Consistent**: `x.equals(x)` is always `true` if `x ≠ null`

``` java
@Override
public boolean equals(Object obj) {
  if(obj == null) return false; 
  // If the object is not null.
  if(this == obj) return true; 
  //If the object is equal to this.
  if(this.getClass() != obj.getClass()) return false;
  // If the class of this is different from the class of the passed object.
  Smartphone smartphone = (Smartphone) obj;
  // Casting of this compared to the object.
  return serialNumber != null && serialNumber.equals(smartphone.serialNumber);
}
// Returns true if the serialNumber attribute is not null
// And the serialNumber of this is equal to the serialNumber of the object.
// I can have more attributes to consider in an equals.
// -> I define what I want the comparison value to be for
// -> one object to be equal to another (even with different addresses on the Heap)

```

#### HashCode:

**Maps values, generating numeric codes for each occurrence.**

*Think of several buckets, each with its own code, and inside these buckets we have values.
This is a summary of Hash; we index collections with numeric values for identification, helping with performance and direct value searching, without needing to iterate over large arrays.*

Basically, we say, **generate a specific Hash based on this attribute; if another object has the same attribute, it will have the same Hash. The implementation is useful mainly for Hash collections.**

NOTE: **HashCode must match the equals attribute.** That is, the attribute that generates a hash must be in the equals attribute and vice versa.

```java
@Override
public int hashCode() {
    return Objects.hash(cpf, nome); // Uses CPF and name
}

@Override
  public boolean equals(Object obj) {
  Pessoa outra = (Pessoa) obj;
  return cpf.equals(outra.cpf) && nome.equals(outra.nome); // Uses CPF and name
}

```

> **HashCode is like a house's ZIP code. The equals method checks the house number within that street!**

*If the HashCode is not overridden, it will automatically generate based on the address in memory, which can lead to undesirable behavior in HashSets, etc.*

#### Hash: Concept and Purpose:

Structures like **HashSet** and **HashMap** use hash tables:

- Each key (or element, in the case of HashSet) generates a **hashCode()**.
- The hash value is processed to determine the *bucket* where the object will be stored.
- The bucket can contain more than one entry due to collisions.
- Since searching, inserting, and removing operate on hash positions, the average performance is **O(1)**.
- The worst case becomes **O(log n)** when the buckets become balanced trees (Java 8+).
- Hash-based structures **do not maintain order**.
- They offer better performance.

Therefore, duplicates are avoided through comparison:

1. **Hash Code**
2. **equals()** (for confirmation)

#### HashSet:
| Operation | Complexity | Explanation |
| --- | --- | --- |
| `add(E)` | **O(1)** | Hash directly to the bucket |
| `remove(Object)` | **O(1)** | Hash directly to the bucket |
| `contains(Object)` | **O(1)** | Hash directly to the bucket |
| `size()` | **O(1)** | Counter attribute |
| `isEmpty()` | **O(1)** | Check size |
| `clear()` | **O(n)** | Clean all buckets |
| `iterator()` | **O(1)** | Create iterator |
| Iterar todos | **O(n)** | Cycle through n elements |
| `addAll(Collection)` | **O(m)** | m = collection size |
| `retainAll(Collection)` | **O(n)** | Cycle through current set |
| `removeAll(Collection)` | **O(n)** | Cycle through current set |
| `containsAll(Collection)` | **O(m)** | Check each element |

```java
Set<Manga> mangas = new HashSet<>();
```

#### LinkedHashSet:
| Operation | Complexity | Explanation |
| --- | --- | --- |
| `add(E)` | **O(1)** | Hash + pointer adjustment |
| `remove(Object)` | **O(1)** | Hash + pointer adjustment |
| `contains(Object)` | **O(1)** | Hash directly to the bucket |
| `size()` | **O(1)** | Counter attribute |
| `isEmpty()` | **O(1)** | Checks size |
| `clear()` | **O(n)** | Clears buckets and list |
| `iterator()` | **O(1)** | Creates iterator |
| Iterate all | **O(n)** | Traverses linked list |
| `addAll(Collection)` | **O(m)** | m = collection size |
| `retainAll(Collection)` | **O(n)** | Traverses current set |
| `removeAll(Collection)` | **O(n)** | Traverses current set |
| `containsAll(Collection)` | **O(m)** | Checks each element |

```java
Set<Manga> mangas = new LinkedHashSet<>();
```

#### HashMap:
| Operation | Average Case | Worst Case | Observation |
| --- | --- | --- |-------------------------|
| **get(key)** | O(1) | O(log n)* | *O(n) before Java 8 |
| **put(key, value)** | O(1) | O(log n)* | *O(n) before Java 8 |
| **remove(key)** | O(1) | O(log n)* | *O(n) before Java 8 |
| **containsKey(key)** | O(1) | O(log n)* | *O(n) before Java 8 |
| **containsValue(value)** | O(n) | O(n) | Needs to iterate through all |
| There are more methods |  |  |                         |

```java
Map<String, String> map = new HashMap<>();
```

#### LinkedHashMap:
| Operation | Average Case | Worst Case | Observations |
| --- | --- | --- | --- |
| **get(key)** | O(1) | O(log n) | Same as HashMap |
| **put(key, value)** | O(1) | O(log n) | + cost of adjusting list pointers |
| **remove(key)** | O(1) | O(log n) | + cost of adjusting list pointers |
| **containsKey(key)** | O(1) | O(log n) | Same as HashMap |
| **containsValue(value)** | O(n) | O(n) | Can iterate through the sorted list |
| **Iteration** | O(n) | O(n) | More efficient than HashMap! |
| There are more methods |  |  |  |

```java
Map<String, String> map = new LinkedHashMap<>();
```

Hash exercises:
- Collections.Set.HashSet
- Collections.Map.HashMap
- Implementations of equals() and hashCode()
- User System via Email

---

#### Tree-based structures (TreeMap, TreeSet):

- Based on balanced trees (such as Red-Black Tree).

- Maintain elements or keys in ascending order.

- Operations cost **O(log n)**.

An in-depth analysis of trees, hash functions, collision management, or chained representations is available in the aforementioned Data Structures project.

#### TreeMap:
| Operation | Complexity | Observations |
| --- | --- | --- |
| **get(key)** | O(log n) | Descends the tree comparing keys |
| **put(key, value)** | O(log n) | Inserts and rebalances |
| **remove(key)** | O(log n) | Removes and rebalances |
| **containsKey(key)** | O(log n) | Searches the tree |
| **containsValue(value)** | O(n) | Requires traversing all nodes |
| **firstKey() / lastKey()** | O(log n) | Goes to the end of the tree |
| **lowerKey() / higherKey()** | O(log n) | Navigates the tree |
| **subMap() / headMap() / tailMap()** | O(log n) | Creates a view, does not copy |
| **Full iteration** | O(n) | Traverses all elements in order |

```java
NavigableMap<String, String> map = new TreeMap<>();
```

#### TreeSet:
| Operation | Complexity | Observations |
| --- | --- | --- |
| **add(element)** | O(log n) | Inserts and rebalances the tree |
| **remove(element)** | O(log n) | Removes and rebalances the tree |
| **contains(element)** | O(log n) | Searches the tree |
| **first() / last()** | O(log n) | Goes to the end of the tree |
| **lower() / higher()** | O(log n) | Navigates the tree |
| **floor() / ceiling()** | O(log n) | Searches for the largest ≤ or smallest ≥ |
| **pollFirst() / pollLast()** | O(log n) | Removes and returns the end |
| **subSet() / headSet() / tailSet()** | O(log n) | Creates a view, does not copy |
| **size()** | O(1) | Maintains internal counter |
| **isEmpty()** | O(1) | Checks if size == 0 |
| **clear()** | O(n) | Removes all elements |
| **Full iteration** | O(n) | Iterates through all elements in ascending order |
| **descendingSet()** | O(1) | Returns reverse view |
| **descendingIterator()** | O(1) | Creates reverse iterator (O(n) iteration) |

```java
NavigableSet<Smartphone> set = new TreeSet<>();
```

---

### 6. Summary:

The Collections module provides a complete practical foundation for using the core structures, understanding performance, sorting, searching, object equality, navigation, and modeling real-world scenarios.

Understanding the data structures present in the Collections API is fundamental for developing scalable and high-performing applications.