<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Threads

This module covers the use of Threads in Java, from creation and lifecycle to advanced synchronization, inter-thread communication, and parallelism with ExecutorService.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

A thread is the smallest unit of processing managed by the operating system. Every Java program starts with the main thread, from which new threads can be created to execute tasks concurrently or in parallel.

**Concurrency** is when multiple tasks progress in the same time period, alternating on a single core. **Parallelism** is when they execute simultaneously on different cores.

---

## Architecture

- **Domain**: Model classes (`Account`, `Product`, `Member`, etc.)
- **Application**: `Main` classes with the implementation of each exercise
- **2 Progressive Exercises**: Synchronization with wait/notify and parallelism with ExecutorService

---

## 1. Fundamental Concepts

### 1.1. Thread States

```
New -> Runnable <-> Running -> Dead
                      |
                Waiting/Blocked
```

| State | Description |
|-------|-------------|
| New | Thread instantiated, not yet started |
| Runnable | `start()` called, ready to execute |
| Running | Being executed by the Scheduler |
| Waiting/Blocked | Waiting for resource, lock, or signal |
| Dead | Execution completed |

### 1.2. Creating Threads

**Implementing Runnable** (recommended):
```java
class MyTask implements Runnable {
    @Override
    public void run() {
        // task here
    }
}

Thread t1 = new Thread(new MyTask());
t1.start();
```

**With lambda:**
```java
new Thread(() -> System.out.println("running")).start();
```

`implements Runnable` is preferred over `extends Thread` because Java has no multiple inheritance, and extending Thread prevents inheriting from any other class. The class defines a task, not a thread.

### 1.3. Main Methods

| Method | Description |
|--------|-------------|
| `start()` | Starts a new thread |
| `run()` | Executes on the main thread, does not create a new thread |
| `sleep(ms)` | Pauses the current thread and holds the lock |
| `join()` | Blocks the current thread until another finishes |
| `yield()` | Suggests the Scheduler to pause the current thread |
| `setPriority(1-10)` | Sets execution priority |

### 1.4. Synchronization and Race Condition

Race Condition occurs when two or more threads access and modify shared data at the same time, generating unpredictable results. `synchronized` ensures only one thread at a time executes the protected section:

```java
public synchronized void withdrawal(int amount) {
    if (account.getBalance() >= amount) {
        account.debit(amount);
    }
}
```

Can be applied to the entire method or a specific block for greater granularity. The lock belongs to the object. To share the lock across all instances, use `static synchronized`.

`sleep` holds the lock. `wait()` releases the lock and waits for notification.

### 1.5. Wait, Notify and NotifyAll

Inter-thread communication mechanism. Must be called inside a `synchronized` block:

| Method | Behavior |
|--------|----------|
| `wait()` | Releases the lock and waits for notification |
| `notify()` | Wakes one thread in wait (JVM decides which) |
| `notifyAll()` | Wakes all threads in wait |

### 1.6. Volatile

Each processor core has its own cache. A thread can modify a variable without other threads seeing the change. `volatile` forces reads and writes directly to main memory, guaranteeing visibility across threads:

```java
private volatile boolean running = true;
```

`synchronized` solves atomicity. `volatile` solves visibility. The two problems are distinct and one does not replace the other.

### 1.7. Deadlock

Occurs when two threads wait indefinitely for each other to release locks:

```
Thread 1 holds Obj A and waits for Obj B
Thread 2 holds Obj B and waits for Obj A
```

---

## 2. ExecutorService and ThreadPool

The pool maintains a fixed set of reusable threads. When a task finishes, the thread returns to the pool and picks up the next one from the queue, eliminating the cost of creating and destroying threads per operation:

```java
ExecutorService pool = Executors.newFixedThreadPool(4);

pool.execute(new MyTask());
Future<Product> f = pool.submit(new FetchProduct());

pool.shutdown();
```

| Method | Accepts | Returns |
|--------|---------|---------|
| `execute()` | Runnable | void |
| `submit()` | Runnable or Callable | Future |

### 2.1. Callable and Future

`Callable<T>` executes a task and returns a value, unlike `Runnable` which returns nothing:

```java
public class FetchProduct implements Callable<Product> {
    @Override
    public Product call() throws Exception {
        return repository.fetch();
    }
}
```

`Future<T>` carries the task result. The pool thread fills the Future when done, and the main thread retrieves the value when needed:

```java
Future<Product> future = pool.submit(new FetchProduct());

Product p = future.get();
Product p = future.get(5, TimeUnit.SECONDS);
boolean done = future.isDone();
future.cancel(true);
```

---

## 3. Manual Thread vs ExecutorService

| Situation | Approach |
|-----------|----------|
| Simple task with no return | Thread + Runnable |
| Task with return value | ExecutorService + Callable + Future |
| Multiple parallel tasks | ExecutorService + fixed pool |
| Timeout control | Future.get(n, TimeUnit) |
| Real projects and modern APIs | ExecutorService or Spring @Async |

Manual Thread with Runnable is the foundation of concurrency in Java. In production: ExecutorService, which provides pool control, return values via Future, and timeout management.

---

## 4. Exercises

### 4.1. Email Delivery System

**Concepts:** `wait()`, `notifyAll()`, `synchronized`, Thread Safety

Shared email queue processed by multiple threads. Threads wait when the queue is empty and are notified when new emails arrive.

**Classes:**
- `Members`: manages the queue with `wait/notifyAll`
- `EmailDeliveryService`: task implementing `Runnable`

**Flow:**
```
main adds email -> notifyAll() -> threads wake up -> one delivers -> others return to wait()
```

### 4.2. Concurrent Product Search

**Concepts:** `ExecutorService`, `Callable`, `Future` with timeout, `volatile`

Simultaneous search for the cheapest product across 3 stores. Each store is an independent task with a 5-second timeout. A `volatile` variable controls the system state and is visible across all threads.

**Classes:**
- `Product`: domain entity
- `StoreA`, `StoreB`, `StoreC`: tasks implementing `Callable<Product>`
- `Main`: orchestrates the pool, collects Futures, and displays the lowest price

**Flow:**
```
pool submits 3 tasks -> run in parallel -> Future.get(5s) collects results -> displays lowest price
```

---

## 5. Results

- Thread creation and lifecycle with Runnable and Thread
- Synchronization with `synchronized`, locks per object and per class
- Inter-thread communication with `wait`, `notify`, and `notifyAll`
- Shared variable visibility with `volatile`
- Pool management with `ExecutorService`
- Tasks with return values using `Callable` and `Future`
- Timeout control with `Future.get(n, TimeUnit)`
- Race Condition and Deadlock: identification and prevention