<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Concurrent Threads: Executors, ThreadPool, Atomic, Locks and Conditions

This module covers the full concurrency toolkit in Java, from the Executor framework that manages thread pools efficiently, to atomic operations, explicit locks and condition-based synchronization. All tools come from the `java.util.concurrent` package introduced in Java 5.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

Instead of creating and destroying threads manually for each task, the Executor framework maintains a pool of reusable threads. When a task finishes, the thread returns to the pool and picks up the next one from the queue, eliminating the overhead of thread lifecycle management.

The pool shines when you have many parallel tasks. 4 threads can handle thousands of tasks sequentially without the cost of creating and destroying a thread per task.

The `synchronized` keyword is still your responsibility regardless of which tool you use. The pool only manages how many threads exist and how tasks are distributed — it does not protect shared resources for you.

---

## Architecture

Each folder contains the exercise class with its implementation and a `problemQuestion.txt` file with the exercise description.

- **NewCachedThreadPool**: `ImageProcessor`
- **NewFixedThreadPool**: `ReportGenerator`
- **NewScheduledThreadPool**: `TaskScheduler`
- **NewSingleThreadExecutor**: `DatabaseWriter`
- **NewSingleThreadScheduledExecutor**: `TaskScheduler`
- **NewWorkStealingPool**: `DataAnalyzer`
- **AtomicInteger**: `Counter`, `Main`
- **ReentrantLock**: `Counter`, `Workers`, `Main`
- **Conditions**: `SharedBuffer`, `Producer`, `Consumer`, `Main`
- **ReentrantReadWriteLock**: `ConfigCache`, `Main`

---

## 1. Executor Framework

### 1.1. How the Pool Works

```
Submitted tasks: [t1] [t2] [t3] [t4] [t5] [t6] ... [t100]
Pool threads:    [T1] [T2] [T3] [T4]
Waiting queue:   [t5] [t6] [t7] ... [t100]
```

The pool threads each pick up one task. When one finishes, it automatically picks up the next from the queue. The pool manages this distribution internally.

### 1.2. Runnable vs Callable

Every thread needs a task, either `Runnable` or `Callable`. What varies is where the task is defined: in a separate class, in a variable in main, or inline with a lambda. But there is always one.

| | Runnable | Callable |
|--|---------|---------|
| Return value | No | Yes, via `Future<T>` |
| Checked exceptions | No | Yes |
| Method | `run()` | `call()` |
| Used with | `Thread` or `ExecutorService` | `ExecutorService` only |

`Runnable` with `execute()` when no return is needed. `Callable` with `submit()` when a return is needed, and then `Future` makes sense.

### 1.3. Basic Usage

```java
ExecutorService pool = Executors.newFixedThreadPool(4);

pool.execute(new MyTask());                           // Runnable, no return
Future<Product> f = pool.submit(new FetchProduct());  // Callable, returns Future

pool.shutdown();
```

### 1.4. ExecutorService Core Methods

```java
pool.execute(runnable)            // submits Runnable, no return
pool.submit(task)                 // submits Runnable or Callable, returns Future
pool.shutdown()                   // stops accepting new tasks, waits for current ones
pool.shutdownNow()                // attempts to cancel everything immediately
pool.isShutdown()                 // true if shutdown was called
pool.isTerminated()               // true if all tasks finished after shutdown
pool.awaitTermination(n, TimeUnit) // blocks until all finish or time runs out
```

### 1.5. Available Executors

| Factory | What it does | Ideal for |
|---------|--------------|-----------|
| `newCachedThreadPool()` | Creates threads on demand, reuses idle ones | Many short unpredictable tasks |
| `newFixedThreadPool(n)` | Fixed number of threads, queues the excess | Controlled resource consumption |
| `newScheduledThreadPool(n)` | Schedules tasks after a delay or periodically | Recurring jobs, health checks |
| `newSingleThreadExecutor()` | Single thread, sequential execution | Guaranteed order, no parallelism |
| `newSingleThreadScheduledExecutor()` | Single thread with scheduling | Sequential scheduled execution |
| `newWorkStealingPool()` | Uses all available CPU cores, work-stealing | Heavy independent tasks, max parallelism |

---

## 2. Callable and Future

### 2.1. The Problem Callable Solves

`Runnable` has two limitations: `run()` returns no value and cannot throw checked exceptions. `Callable<T>` solves both using `call()` instead of `run()`.

`Callable` is a functional interface with a single method and only works with `ExecutorService`. The `Thread` class only accepts `Runnable`.

```java
public class FetchUser implements Callable<User> {
    private int id;

    public FetchUser(int id) {
        this.id = id;
    }

    @Override
    public User call() throws Exception {
        return database.fetch(id);
    }
}
```

### 2.2. How Future Works

The main thread has no direct access to what another thread returns. `Future` acts as a shared container: the pool thread fills it when it finishes, and the main thread reads from it when needed.

The execution is asynchronous. Obtaining the result is potentially synchronous. `get()` only blocks if the task has not finished yet. If it already finished, `get()` returns immediately with the value, throws `ExecutionException` if the task ended with error, or throws `CancellationException` if it was cancelled.

```java
Future<User> future = pool.submit(new FetchUser(1));

// main keeps executing here

User user = future.get(); // blocks only if task hasn't finished yet
```

### 2.3. Async Flow

```
1. main creates ExecutorService
2. main submits task with submit()
3. task starts running in a pool thread
4. main continues executing normally
5. only when main calls future.get() may there be blocking
```

`get()` is optional. You only use it when you need the return value to continue the program. If the task just processes something and you don't need the result, submit and forget, same as `execute()` with `Runnable`.

### 2.4. Callable Methods

| Method | Description |
|--------|-------------|
| `call()` | Executes the task, returns result of type `T`, may throw checked exception |

### 2.5. Future Methods

| Method | Return | Description |
|--------|--------|-------------|
| `get()` | `V` | Blocks until result is available |
| `get(timeout, unit)` | `V` | Blocks until timeout, throws `TimeoutException` if exceeded |
| `cancel(mayInterrupt)` | `boolean` | Attempts to cancel. `true` interrupts if already running |
| `isCancelled()` | `boolean` | True if cancelled before completing |
| `isDone()` | `boolean` | True if completed by any means |

### 2.6. Exceptions Thrown by `get()`

| Exception | When |
|-----------|------|
| `ExecutionException` | Task threw an exception internally |
| `InterruptedException` | The waiting thread was interrupted |
| `TimeoutException` | Time limit of `get(timeout, unit)` was reached |
| `CancellationException` | Task was cancelled before completing |

---

## 3. Executor Methods by Implementation

### `newCachedThreadPool`

Creates threads on demand, reuses idle ones. Instantiated as `ExecutorService`.

| Method | What it does |
|--------|--------------|
| `submit(Runnable)` | Submits task, creates new thread if none available |
| `submit(Callable<T>)` | Submits task with return, creates thread if needed |
| `execute(Runnable)` | Submits task with no return |
| `shutdown()` | Stops accepting tasks, finishes current ones |
| `shutdownNow()` | Attempts to cancel everything immediately |
| `awaitTermination(time, unit)` | Blocks until all finish or time runs out |

---

### `newFixedThreadPool(n)`

Maintains a fixed number of threads, queues the excess. Instantiated as `ExecutorService`.

| Method | What it does |
|--------|--------------|
| `submit(Runnable)` | Submits task, queues if all threads busy |
| `submit(Callable<T>)` | Submits task with return, queues if needed |
| `execute(Runnable)` | Submits task with no return |
| `shutdown()` | Stops accepting tasks, finishes current ones |
| `shutdownNow()` | Attempts to cancel everything immediately |
| `awaitTermination(time, unit)` | Blocks until all finish or time runs out |

---

### `newScheduledThreadPool(n)`

Schedules tasks after a delay or periodically. Instantiated as `ScheduledExecutorService`, returns `ScheduledFuture<?>`.

| Method | What it does |
|--------|--------------|
| `schedule(Runnable, delay, unit)` | Executes once after the delay |
| `schedule(Callable<T>, delay, unit)` | Executes once after the delay with return |
| `scheduleAtFixedRate(Runnable, initialDelay, period, unit)` | Repeats counting from the start of the previous execution |
| `scheduleWithFixedDelay(Runnable, initialDelay, delay, unit)` | Repeats counting from the end of the previous execution |
| `shutdown()` | Stops accepting tasks, finishes current ones |
| `shutdownNow()` | Cancels all scheduled and running tasks |

---

### `newSingleThreadExecutor`

Single thread, sequential execution. Instantiated as `ExecutorService`.

| Method | What it does |
|--------|--------------|
| `submit(Runnable)` | Submits task, executes sequentially on the single thread |
| `submit(Callable<T>)` | Submits task with return, executes sequentially |
| `execute(Runnable)` | Submits task with no return |
| `shutdown()` | Stops accepting tasks, finishes current one |
| `shutdownNow()` | Attempts to cancel current and pending tasks |
| `awaitTermination(time, unit)` | Blocks until finished or time runs out |

---

### `newSingleThreadScheduledExecutor`

Single thread with scheduling. Instantiated as `ScheduledExecutorService`.

| Method | What it does |
|--------|--------------|
| `schedule(Runnable, delay, unit)` | Schedules single execution after delay |
| `schedule(Callable<T>, delay, unit)` | Schedules single execution with return |
| `scheduleAtFixedRate(Runnable, initialDelay, period, unit)` | Schedules periodic execution on single thread |
| `scheduleWithFixedDelay(Runnable, initialDelay, delay, unit)` | Schedules periodic execution with fixed delay between runs |
| `shutdown()` | Stops accepting tasks, finishes current one |
| `shutdownNow()` | Cancels all scheduled tasks |

---

### `newWorkStealingPool`

Uses all available CPU cores with work-stealing algorithm. Instantiated as `ExecutorService`.

| Method | What it does |
|--------|--------------|
| `submit(Runnable)` | Submits task distributed among available threads |
| `submit(Callable<T>)` | Submits task with return, distributed by the pool |
| `execute(Runnable)` | Submits task with no return |
| `invoke(ForkJoinTask<T>)` | Executes ForkJoinTask and waits for result |
| `shutdown()` | Stops accepting tasks, finishes current ones |
| `shutdownNow()` | Attempts to cancel everything immediately |

---

## 4. Atomic Operations and CAS

### 4.1. The Problem

`counter++` is actually three steps: read, modify, write. If two threads execute this simultaneously, one increment can silently overwrite the other.

```
Thread 1: reads value = 5
Thread 2: reads value = 5
Thread 1: writes value = 6
Thread 2: writes value = 6  (overwrites Thread 1 result)
Final value: 6 instead of 7
```

### 4.2. CAS — Compare-And-Swap

CAS is an atomic hardware instruction that does three things in a single step: reads the current value, compares with the expected value, and if equal replaces with the new value. If different, fails and retries. Atomic classes are lock-free: instead of blocking, they retry on conflict.

```java
AtomicInteger counter = new AtomicInteger(0);

counter.incrementAndGet();           // atomic ++
counter.getAndAdd(5);                // returns old value, adds 5
counter.compareAndSet(10, 20);       // only changes to 20 if current value is 10

AtomicReference<String> ref = new AtomicReference<>("initial");
ref.compareAndSet("initial", "new"); // atomic reference swap
```

### 4.3. When to Use Atomic vs Locks

| Situation | Use |
|-----------|-----|
| Simple counter | `AtomicInteger` |
| Boolean flag | `AtomicBoolean` |
| Object reference | `AtomicReference` |
| Multiple variables together | `synchronized` or `ReentrantLock` |
| Complex conditional logic | `synchronized` or `ReentrantLock` |

Atomic classes work best with low contention and single-variable operations. When multiple variables must change together or logic is more complex, locks are the right choice.

---

## 5. ReentrantLock

### 5.1. Why Use It Over `synchronized`

The main advantages over `synchronized` are `tryLock()` without blocking, timeout support, interruptibility and `Condition` support for granular wait/signal control. If none of these are needed, `synchronized` is simpler and preferred.

### 5.2. Usage

```java
private final ReentrantLock lock = new ReentrantLock();

public void increment() {
    lock.lock();
    try {
        value++;
    } finally {
        lock.unlock(); // ALWAYS in finally block
    }
}

public boolean tryIncrement() {
    if (lock.tryLock()) {
        try {
            value++;
            return true;
        } finally {
            lock.unlock();
        }
    }
    return false; // lock was busy
}
```

The lock must always be released in a `finally` block. If an exception is thrown and unlock is not in `finally`, the lock will never be released, causing a deadlock.

### 5.3. Key Methods

| Method | Return | Description |
|--------|--------|-------------|
| `lock()` | `void` | Acquires the lock, blocks until available |
| `unlock()` | `void` | Releases the lock |
| `tryLock()` | `boolean` | Attempts to acquire without blocking |
| `tryLock(long timeout, TimeUnit unit)` | `boolean` | Attempts within a time limit |
| `lockInterruptibly()` | `void` | Acquires but can be interrupted |
| `newCondition()` | `Condition` | Creates a Condition associated with this lock |
| `isLocked()` | `boolean` | True if any thread holds the lock |
| `isHeldByCurrentThread()` | `boolean` | True if the current thread holds the lock |
| `getHoldCount()` | `int` | How many times the current thread acquired the lock |
| `hasQueuedThreads()` | `boolean` | True if threads are waiting for the lock |
| `getQueueLength()` | `int` | Number of threads waiting |
| `isFair()` | `boolean` | True if created in fair mode |

---

## 6. Conditions

### 6.1. The Problem with a Single Wait Queue

With `synchronized`, every object has a single wait set. When `notify()` is called, the JVM picks any waiting thread. In a producer-consumer scenario, a producer might wake another producer instead of a consumer, wasting the signal.

`Condition` allows multiple distinct wait queues for the same lock, giving complete control over which threads are woken up.

```
Lock: bufferLock

Condition notEmpty  ->  [ consumers waiting ]
Condition notFull   ->  [ producers waiting ]

notEmpty.signal()   ->  wakes only a consumer
notFull.signal()    ->  wakes only a producer
```

### 6.2. Usage Pattern

```java
private final ReentrantLock lock = new ReentrantLock();
private final Condition notFull  = lock.newCondition();
private final Condition notEmpty = lock.newCondition();

public void produce(int value) throws InterruptedException {
    lock.lock();
    try {
        while (isFull()) {      // always while, never if
            notFull.await();    // releases lock and waits
        }
        insert(value);
        notEmpty.signal();      // wakes one consumer
    } finally {
        lock.unlock();
    }
}

public int consume() throws InterruptedException {
    lock.lock();
    try {
        while (isEmpty()) {     // always while, never if
            notEmpty.await();   // releases lock and waits
        }
        int value = remove();
        notFull.signal();       // wakes one producer
        return value;
    } finally {
        lock.unlock();
    }
}
```

Always use `while` instead of `if` before `await()`. A thread can wake spuriously without being signaled, or another thread may have already consumed the item before this thread re-acquires the lock. The `while` re-checks the condition every time after waking up.

### 6.3. Key Methods

| Method | `synchronized` equivalent | Description |
|--------|--------------------------|-------------|
| `await()` | `wait()` | Suspends the thread and releases the lock |
| `signal()` | `notify()` | Wakes one waiting thread |
| `signalAll()` | `notifyAll()` | Wakes all waiting threads |
| `await(time, unit)` | `wait(timeout)` | Waits with a timeout |
| `awaitUninterruptibly()` | none | Waits without responding to interrupts |

---

## 7. ReentrantReadWriteLock

### 7.1. The Concept

In systems where reads are far more frequent than writes, a regular lock forces all threads to wait even for read-only operations. `ReentrantReadWriteLock` separates read and write access: multiple threads can hold the read lock simultaneously, but the write lock is exclusive — it blocks all readers and other writers.

```
Read lock:   Thread 1, Thread 2, Thread 3  (all concurrent)
Write lock:  Thread 4 only                 (exclusive, blocks all others)
```

### 7.2. Usage

```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();

public String get(String key) {
    rwLock.readLock().lock();
    try {
        return map.get(key);
    } finally {
        rwLock.readLock().unlock();
    }
}

public void put(String key, String value) {
    rwLock.writeLock().lock();
    try {
        map.put(key, value);
    } finally {
        rwLock.writeLock().unlock();
    }
}
```

### 7.3. Key Methods

| Method | Interface | Description |
|--------|-----------|-------------|
| `readLock()` | `ReadWriteLock` | Returns the read lock |
| `writeLock()` | `ReadWriteLock` | Returns the write lock |
| `lock()` | `Lock` | Acquires the lock |
| `unlock()` | `Lock` | Releases the lock |
| `tryLock()` | `Lock` | Attempts to acquire without blocking |
| `tryLock(long timeout, TimeUnit unit)` | `Lock` | Attempts within a time limit |
| `newCondition()` | `Lock` | Creates a Condition (write lock only) |
| `isWriteLocked()` | `ReentrantReadWriteLock` | True if the write lock is held |
| `isWriteLockedByCurrentThread()` | `ReentrantReadWriteLock` | True if the current thread holds the write lock |
| `getReadLockCount()` | `ReentrantReadWriteLock` | Total threads holding the read lock |
| `hasQueuedThreads()` | `ReentrantReadWriteLock` | True if threads are waiting |
| `isFair()` | `ReentrantReadWriteLock` | True if created in fair mode |

---

## 8. Fairness

Fairness means respecting the arrival order of threads (FIFO). Without it, the JVM may let a thread skip the queue. Most locks and concurrent structures support fair mode via their constructor.

```java
new ReentrantLock(true)                // fair
new ReentrantReadWriteLock(true)       // fair
new Semaphore(permits, true)           // fair
new ArrayBlockingQueue(capacity, true) // fair
new SynchronousQueue(true)             // fair
```

`synchronized`, `ConcurrentHashMap` and default Executors do not expose fairness configuration.

Fairness affects acquisition order, predictability and starvation prevention. It does not affect program correctness, thread safety or data consistency. It is a scheduling policy, not a synchronization guarantee.

| Mode | Characteristic |
|------|----------------|
| Non-fair (default) | Higher throughput, lower overhead, may cause starvation |
| Fair | More predictable, avoids starvation, slightly slower |

In high-performance production systems, non-fair is almost always preferred. Fair mode is used when predictability matters more than raw throughput.

---

## 9. Choosing the Right Tool

| Situation | Use |
|-----------|-----|
| Simple counter or flag | `AtomicInteger` / `AtomicBoolean` |
| Task with no return value | `Runnable` + `execute()` |
| Task with return value | `Callable` + `submit()` + `Future` |
| Many short unpredictable tasks | `newCachedThreadPool` |
| Controlled parallelism | `newFixedThreadPool` |
| Scheduled or periodic tasks | `newScheduledThreadPool` |
| Sequential guaranteed order | `newSingleThreadExecutor` |
| Maximum CPU parallelism | `newWorkStealingPool` |
| Critical section, complex logic | `synchronized` or `ReentrantLock` |
| Need `tryLock()` or timeout | `ReentrantLock` |
| Multiple wait queues | `ReentrantLock` + `Condition` |
| Many reads, few writes | `ReentrantReadWriteLock` |

---

## 10. Exercises

### 10.1. newCachedThreadPool — Image Processor

Processes images concurrently using a cached pool that creates threads on demand. Demonstrates how the pool reuses idle threads across submissions and why each `Callable` must be a separate instance to avoid shared state race conditions.

**Key concepts:** `newCachedThreadPool`, `Callable`, `Future`, independent instances per submission.

---

### 10.2. newFixedThreadPool — Report Generator

Generates reports with a fixed pool of 3 threads, showing how tasks beyond the pool size are queued and processed in controlled batches. Uses timestamps to make the batched execution visible.

**Key concepts:** `newFixedThreadPool`, task queuing, bounded parallelism, `Future.get()` with timeout.

---

### 10.3. newScheduledThreadPool — Task Scheduler

Demonstrates all three scheduling modes running in parallel: a one-time delayed task, a task repeating at a fixed rate, and a task repeating with a fixed delay between executions. Shows the behavioral difference between `scheduleAtFixedRate` and `scheduleWithFixedDelay` when tasks have processing time.

**Key concepts:** `schedule()`, `scheduleAtFixedRate()`, `scheduleWithFixedDelay()`, `ScheduledFuture`, cancellation.

---

### 10.4. newSingleThreadExecutor — Database Writer

Simulates sequential database record writing using a single-thread executor, guaranteeing that no two writes happen simultaneously. Contrasts directly with `newFixedThreadPool(3)` to show the difference between sequential and parallel execution.

**Key concepts:** `newSingleThreadExecutor`, guaranteed sequential order, comparison with fixed pool.

---

### 10.5. newWorkStealingPool — Data Analyzer

Processes data chunks in parallel using all available CPU cores and measures total execution time against a single-thread executor running the same workload. Shows the real performance impact of maximum parallelism on CPU-bound tasks.

**Key concepts:** `newWorkStealingPool`, `Runtime.getRuntime().availableProcessors()`, execution time comparison, ForkJoinPool workers.

---

### 10.6. AtomicInteger — Thread-Safe Counter

Demonstrates atomic increment using `AtomicInteger` across 5 concurrent threads each incrementing a shared counter 1000 times. Shows how CAS-based operations guarantee a deterministic result of 5000 without any explicit locking.

**Key concepts:** `AtomicInteger`, `incrementAndGet()`, lock-free thread safety, race condition prevention.

---

### 10.7. ReentrantLock — Log Counter

Builds a shared counter protected by `ReentrantLock` where 5 worker threads each perform 1000 increments. Also implements `tryIncrement()` using `tryLock()` to demonstrate non-blocking lock attempts and what happens when the lock is unavailable.

**Key concepts:** `lock()`, `unlock()` in `finally`, `tryLock()`, critical section isolation, deterministic result.

---

### 10.8. Conditions — Producer-Consumer Buffer

Implements a shared buffer coordinated by two `Condition` objects: `notFull` and `notEmpty`. The producer waits when the buffer is full and the consumer waits when it is empty, using `signal()` for precise wake-up control instead of waking all waiting threads indiscriminately.

**Key concepts:** `Condition`, `await()`, `signal()`, `while` before `await()`, multiple wait queues per lock, spurious wakeup protection.

---

### 10.9. ReentrantReadWriteLock — Config Cache

Builds a shared configuration cache backed by a `HashMap`, protected by `ReentrantReadWriteLock`. Multiple reader threads read concurrently while writer threads update values, demonstrating that reads proceed in parallel and writes block all other access.

**Key concepts:** `readLock()`, `writeLock()`, concurrent reads, exclusive writes, read-write separation.

---

## 11. Results

- Thread pool management with all six `Executors` factory methods
- Asynchronous task submission with `Callable` and result retrieval via `Future`
- Timeout control with `Future.get(n, TimeUnit)` and `TimeoutException` handling
- Scheduled and periodic task execution with `ScheduledExecutorService`
- Atomic operations with `AtomicInteger` and CAS hardware instructions
- Explicit lock control with `ReentrantLock` and `tryLock()`
- Always releasing locks in `finally` blocks
- Fine-grained thread coordination with `Condition` and multiple wait queues
- Always using `while` before `await()` to guard against spurious wakeups
- Concurrent read access with `ReentrantReadWriteLock`
- Fairness tradeoffs between throughput and starvation prevention
- Choosing the right tool based on the use case

---