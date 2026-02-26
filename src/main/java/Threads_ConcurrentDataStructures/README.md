<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Concurrent Data Structures in Java

This module contains a comprehensive series of exercises demonstrating the use of Concurrent Data Structures in Java, available in the `java.util.concurrent` package introduced in Java 5. These structures were built from the ground up for multi-threaded environments, replacing the old `Collections.synchronized*` approach with far more efficient and scalable solutions.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

When multiple threads access and modify the same data simultaneously, **race conditions** occur situations where the outcome depends on the unpredictable execution order of threads.

The classic "solution" was to wrap everything in a single lock:

```java
List<String> list = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
```

This works but puts a **single lock around everything**. Every thread waits for the previous one to finish, even for reads. In systems with many threads, this becomes a serious bottleneck.

This is why concurrent data structures emerged.

**Important:** These structures guarantee thread-safety per operation, but **sequences of operations are not automatically atomic**. If two operations must depend on each other, additional synchronization may still be required.

---

## Architecture

The project is organized as follows:

- Contains use-case classes per structure (`EventManager`, `DownloadManager`, `TaskDispatcher`, `MetricsCollector`, `ScoreBoard`)
- **5 Progressive Exercises**: Each one focused on a specific concurrent structure

---

## 1. Concurrent Data Structures

### 1.1. Why They Exist

Each thread has its own CPU cache. Without synchronization, a thread may be working with an outdated copy of data. The `java.util.concurrent` structures solve this using three main techniques:

- **Lock segmentation** instead of one lock for everything, the structure is divided into independent parts. Threads in different parts don't block each other.
- **Lock-free algorithms (CAS)** using atomic hardware instructions (*Compare and Swap*), some structures eliminate locks entirely. The CPU guarantees atomicity, not Java.
- **Read/write separation** structures like `CopyOnWriteArrayList` leave reads completely free of synchronization, paying the cost only on writes.

---

## 2. Quick Reference Table

### 2.1. Available Structures

| Structure | Capacity | Blocking | Mechanism | Best For |
|-----------|----------|----------|-----------|----------|
| `CopyOnWriteArrayList` | Unlimited | No | Copy on write | Lists with rare writes |
| `ArrayBlockingQueue` | Fixed | Yes | Single lock | Producer-Consumer with limit |
| `LinkedTransferQueue` | Unlimited | Yes | Lock-free + transfer | Direct delivery confirmation |
| `ConcurrentLinkedQueue` | Unlimited | No | CAS lock-free | High concurrency, no blocking |
| `ConcurrentHashMap` | Unlimited | No | Segmented locks | Shared maps between threads |

### 2.2. Key Method Behaviors

| Method | Behavior when full/empty |
|--------|--------------------------|
| `put(e)` / `take()` | Blocks until space/element available |
| `offer(e)` / `poll()` | Returns false/null immediately |
| `add(e)` / `remove()` | Throws exception |
| `transfer(e)` | Blocks until a consumer takes directly |
| `tryTransfer(e)` | Returns false if no waiting consumer |

---

## 3. Structures

### 3.1. CopyOnWriteArrayList

**The idea:** On every write, creates a complete copy of the internal array. Reads never block iterators work on a snapshot of the moment they were created.

```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("A");

// Safe iteration never throws ConcurrentModificationException
for (String s : list) {
    list.add("X"); // safe, iterates on old snapshot
}
```

**Key methods:**

| Method | What it does |
|--------|--------------|
| `add(e)` | Adds to end of list |
| `add(index, e)` | Adds at specific position |
| `set(index, e)` | Replaces element at position |
| `remove(index)` | Removes element at position |
| `get(index)` | Returns element at position |
| `contains(e)` | Checks if element exists |
| `addIfAbsent(e)` | Adds only if element doesn't exist |
| `iterator()` | Returns iterator over current snapshot |

**When to use:** Read-heavy scenarios with rare writes listener lists, observer registries. **Avoid** when writes are frequent or the list is large.

---

### 3.2. ArrayBlockingQueue

**The idea:** A **capacity-limited** queue that automatically blocks threads when needed. If the queue is full, the inserting thread waits. If empty, the consuming thread waits. This is the backbone of the **Producer-Consumer** pattern.

```java
ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

// Producer
queue.put("task1");  // blocks if full

// Consumer
String item = queue.take(); // blocks if empty
```

**Key methods:**

| Method | What it does | Behavior when full/empty |
|--------|--------------|--------------------------|
| `put(e)` | Inserts element | Blocks until space available |
| `take()` | Removes and returns first | Blocks until element available |
| `offer(e)` | Inserts element | Returns false if full |
| `offer(e, time, unit)` | Inserts with timeout | Gives up after time |
| `poll()` | Removes and returns first | Returns null if empty |
| `poll(time, unit)` | Removes with timeout | Returns null after time |
| `add(e)` | Inserts element | Throws exception if full |
| `peek()` | Returns first without removing | Returns null if empty |
| `remainingCapacity()` | Returns available space | |

**When to use:** Whenever you need controlled flow between producers and consumers prevents producers from overwhelming consumers and blowing up memory.

**Disadvantages:** Fixed size defined at creation. Single lock means high contention with many threads. Blocking threads entirely may be a problem in some architectures.

---

### 3.3. LinkedTransferQueue

**The idea:** Goes beyond simple producer-consumer. The `transfer()` method makes the producer **wait until a consumer picks up the item directly** not just enqueued, but actually received.

```
put()      → Producer inserts and moves on
             [ item, item, item ] → Consumer picks up when ready

transfer() → Producer inserts and WAITS
             Producer ✋ item → Consumer picks up → Producer released
```

```java
LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

queue.transfer("task");              // blocks until consumer takes it
queue.tryTransfer("task");           // returns false if no waiting consumer
queue.tryTransfer("task", 2, TimeUnit.SECONDS); // tries for 2 seconds
```

**Key methods:**

| Method | What it does | Behavior |
|--------|--------------|----------|
| `transfer(e)` | Delivers directly to a consumer | Blocks until consumer takes |
| `tryTransfer(e)` | Attempts direct delivery | Returns false if no waiting consumer |
| `tryTransfer(e, time, unit)` | Attempts with timeout | Returns false if time expires |
| `put(e)` | Inserts in queue | Never blocks (unlimited capacity) |
| `take()` | Removes and returns first | Blocks if empty |
| `hasWaitingConsumer()` | Checks if consumer is waiting | Returns boolean |
| `getWaitingConsumerCount()` | Returns number of waiting consumers | |

**When to use:** When you need **delivery confirmation** not just that the item was queued, but that a thread is already processing it. Real-time pipelines, request-response patterns between threads.

**Important:** `transfer()` only unblocks when a consumer enters `take()` with an **empty queue**. If the queue has items, the consumer takes immediately without being "blocked waiting" and `transfer()` stays frozen.

---

### 3.4. ConcurrentLinkedQueue

**The idea:** A **lock-free** queue with no capacity limit. Instead of locks, it uses **CAS (Compare-And-Swap)** an atomic hardware instruction. Threads never block waiting for each other; they compete directly at the hardware level.

```
CPU atomic instruction:
  "If current value is X, swap for Y all in one instruction"

Without CAS:
  read → modify → write  (3 steps, can be interrupted)

With CAS:
  compareAndSwap(expected, new)  (1 atomic hardware step)
```

```java
ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

queue.offer("task1"); // never blocks
String item = queue.poll(); // returns null if empty, never blocks

if (item == null) {
    // empty queue you decide what to do
}
```

**Key methods:**

| Method | What it does | Behavior |
|--------|--------------|----------|
| `offer(e)` | Inserts at end | Always returns true, never blocks |
| `poll()` | Removes and returns first | Returns null if empty |
| `peek()` | Returns first without removing | Returns null if empty |
| `isEmpty()` | Checks if empty | More efficient than `size() == 0` |
| `size()` | Returns current size | Traverses entire queue avoid in loops |
| `contains(e)` | Checks if element exists | Traverses entire queue |
| `iterator()` | Returns iterator | Snapshot, never throws ConcurrentModificationException |

**When to use:** Many threads inserting and removing simultaneously with no need to block. Logging systems, metrics collection, high-frequency event queues.

**Disadvantages:** No flow control if producers are much faster than consumers, the queue grows without limit and can blow up memory. `size()` and `contains()` traverse the entire queue expensive in loops. No `take()` you must handle `null` yourself.

---

### 3.5. ConcurrentHashMap

**The idea:** Thread-safe `HashMap` that, unlike `Collections.synchronizedMap()`, **only locks the specific segment** being modified. Reads generally don't lock at all.

```
synchronizedMap:
Thread 1 writes → locks EVERYTHING → Threads 2, 3, 4 wait

ConcurrentHashMap:
Thread 1 writes in segment A →
Thread 2 writes in segment C → run simultaneously
Thread 3 reads anything     →
```

The big differentiator is **atomic composite operations** without them you'd have to manually synchronize read + modify + write blocks, opening the door to race conditions:

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

// WRONG not atomic, race condition here
if (!map.containsKey("player1")) {
    map.put("player1", 0); // another thread might insert between the if and put
}

// CORRECT atomic
map.putIfAbsent("player1", 0);

// WRONG separate read and write, race condition
int score = map.get("player1");
map.put("player1", score + 10);

// CORRECT atomic
map.merge("player1", 10, Integer::sum);
```

**Key methods:**

| Method | What it does |
|--------|--------------|
| `put(k, v)` | Inserts or replaces value |
| `get(k)` | Returns value or null |
| `putIfAbsent(k, v)` | Inserts only if key doesn't exist |
| `computeIfAbsent(k, fn)` | Computes and inserts if key doesn't exist |
| `computeIfPresent(k, fn)` | Updates value only if key exists |
| `compute(k, fn)` | Reads and writes atomically, handles null |
| `merge(k, v, fn)` | Combines existing value with new one atomically |
| `getOrDefault(k, default)` | Returns value or default if not found |
| `replace(k, v)` | Replaces value only if key exists |
| `replace(k, old, new)` | Replaces only if current value matches expected |
| `forEach(fn)` | Iterates over all key-value pairs |
| `size()` | Returns approximate size |

**Important:** Does not allow `null` keys or values unlike `HashMap`. Attempting to insert `null` throws `NullPointerException`.

**When to use:** Whenever you need a shared map between threads caches, access counters, grouping results from parallel threads, runtime-updatable configuration.

**Avoid when:** You need consistency across multiple keys simultaneously. `ConcurrentHashMap` guarantees atomicity per operation, not across different operations.

---

## 4. Exercises

### 4.1. CopyOnWriteArrayList — Event Notification System

Builds an event notification system demonstrating safe concurrent iteration over a listener list while new listeners are being added simultaneously. Shows how `CopyOnWriteArrayList` eliminates `ConcurrentModificationException` and why a regular `ArrayList` breaks under the same conditions.

**Key concepts:** snapshot-based iteration, copy-on-write behavior, read safety under concurrent writes.

---

### 4.2. ArrayBlockingQueue — Download Manager

Implements a download queue with limited slots shared between multiple requesters and a single downloader. Demonstrates automatic producer blocking when the queue reaches capacity and how `put()` vs `add()` behave differently under pressure.

**Key concepts:** bounded blocking queue, producer-consumer flow control, thread coordination without manual synchronization.

---

### 4.3. LinkedTransferQueue — Task Dispatcher

Builds a task dispatcher where the producer waits for delivery confirmation before moving to the next task. Contrasts `transfer()` with `put()` to show the difference between enqueuing and direct handoff to a waiting consumer.

**Key concepts:** direct transfer, delivery confirmation, `transfer()` vs `put()`, consumer readiness requirement.

---

### 4.4. ConcurrentLinkedQueue — Metrics Collector

Implements a high-throughput log event collector with multiple producers and consumers operating without any blocking. Uses a `volatile` flag combined with `AtomicInteger` to safely signal consumers when all producers have finished.

**Key concepts:** lock-free queue, non-blocking poll loop, `null` handling, `volatile` + `AtomicInteger` lifecycle management.

---

### 4.5. ConcurrentHashMap — Score Board

Builds a concurrent scoreboard where multiple player threads register and update their scores simultaneously while a bonus thread doubles values at the same time. Demonstrates atomic composite operations to avoid race conditions on read-modify-write sequences.

**Key concepts:** `putIfAbsent`, `compute`, `computeIfPresent`, `merge`, atomic composite operations, avoiding separate `get()` after write.

---

## 5. Results

**Complete understanding of why concurrent structures exist**
- The problem with `Collections.synchronized*`
- Lock contention and its impact on performance
- How `java.util.concurrent` solves it

**Mastery of each structure's behavior**
- Blocking vs non-blocking operations
- CAS lock-free mechanism
- Copy-on-write strategy
- Direct transfer pattern

**Correct usage patterns**
- `put()`/`take()` vs `offer()`/`poll()` vs `add()`/`remove()`
- `transfer()` vs `put()` and when each makes sense
- Atomic composite operations on `ConcurrentHashMap`
- `volatile` flag + `AtomicInteger` for thread lifecycle management

**Common pitfalls avoided**
- Using `add()` instead of `put()` (throws exception when full)
- Separate read + write operations instead of atomic `compute()`/`merge()`
- Printing values from a separate `get()` instead of operation return
- Consumer threads dying before producers finish
- `size()` in loops on lock-free structures

---