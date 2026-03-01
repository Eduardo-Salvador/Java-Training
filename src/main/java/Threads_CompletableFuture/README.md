<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# CompletableFuture

This module covers asynchronous programming in Java using `CompletableFuture`, part of the `java.util.concurrent` package introduced in Java 8. It enables fluent chaining, parallel task combination, error handling, and integration with the `Stream` API — all without blocking the main thread.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Architecture

Each folder contains the exercise class with its implementation and a `problemQuestion.txt` file with the exercise description.

- **SimpleAsyncChaining**: `OrderProcessor`, `Order`
- **CombiningMultipleTasks**: `ProductSearch`
- **ErrorHandlingDifferenceAndFallback**: `PaymentService`
- **ThreadFactoryAndPool**: `ReportService`
- **StreamsWithCompletableFuture**: `DataPipeline`

---

## 1. Overview

`CompletableFuture` uses modern functional interfaces from Java 8. `supplyAsync()` receives a `Supplier<T>` and `runAsync()` receives a `Runnable`, in practice you always pass a lambda. No class to instantiate, no interface to implement, no pool to manage manually.

By default it uses `ForkJoinPool.commonPool()`, a global pool managed by the JVM. No `shutdown()` needed. If you want controlled threads, pass a custom pool with a `ThreadFactory` as the second argument to `supplyAsync()`.

`get()` and `join()` are optional, only use them when you need the return value to continue the program. `join()` is preferred because it throws `CompletionException` (unchecked) instead of `ExecutionException` (checked).

---

## 2. Creation

```java
// With return value
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "result");

// Without return value
CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> System.out.println("running"));

// Already completed (useful for tests and fallbacks)
CompletableFuture<String> cf = CompletableFuture.completedFuture("immediate value");

// With custom pool
ExecutorService pool = Executors.newFixedThreadPool(4, myFactory);
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> fetch(), pool);
```

---

## 3. Transformation

`thenApply()` transforms the value and continues the chain with the new value, the `map` of streams. `thenAccept()` consumes the final value without returning anything. `thenRun()` does not even receive the value, just executes an action when the previous stage finishes.

```java
CompletableFuture.supplyAsync(() -> "eduardo")
    .thenApply(name -> name.toUpperCase())
    .thenApply(name -> "Hello, " + name)
    .thenAccept(System.out::println); // "Hello, EDUARDO"
```

---

## 4. Combining Multiple Tasks

`thenCombine()` waits for two `CompletableFuture` to finish and combines both results. `allOf()` waits for all tasks and returns `CompletableFuture<Void>`, so you fetch values manually after. `anyOf()` returns the result of the first that finishes.

```java
store1.thenCombine(store2, Integer::min)
      .thenCombine(store3, Integer::min)
      .thenAccept(min -> System.out.println("Lowest: $" + min));

CompletableFuture.allOf(cf1, cf2, cf3)
    .thenRun(() -> System.out.println("All done"));
```

---

## 5. Error Handling

`exceptionally()` catches the exception and returns a default value so the chain continues, only runs on error. `handle()` receives both the result and the exception and always runs, with or without error.

```java
// exceptionally — fallback only on error
.exceptionally(ex -> "default value")

// handle — always runs
.handle((result, ex) -> {
    if (ex != null) return "error: " + ex.getCause().getMessage();
    return result;
})
```

Always use `ex.getCause().getMessage()`, the received exception is a `CompletionException` wrapping the original.

---

## 6. Stream inside CompletableFuture

Inside the lambda you write normal Java, including streams. `CompletableFuture` only manages when that block runs and what to do with the result.

```java
CompletableFuture.supplyAsync(() -> fetchList())
    .thenApply(list -> list.stream()
        .filter(x -> x > 0)
        .map(x -> x * 3)
        .collect(Collectors.toList()))
    .thenApply(list -> list.stream()
        .reduce(0, Integer::sum))
    .thenAccept(System.out::println);
```

Avoid `parallelStream()` with the default pool, both use `ForkJoinPool.commonPool()` and will compete for the same resources.

---

## 7. ThreadFactory

`ThreadFactory` defines how threads are created. Use it to give descriptive names for easier debugging, set daemon threads, or configure priority.

```java
ThreadFactory factory = new ThreadFactory() {
    private int count = 1;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("report-worker-" + count++);
        t.setDaemon(true);
        return t;
    }
};

ExecutorService pool = Executors.newFixedThreadPool(3, factory);
CompletableFuture.supplyAsync(() -> generateReport(), pool)
    .thenAccept(System.out::println);
```

| Method | Return | Description |
|--------|--------|-------------|
| `newThread(Runnable r)` | `Thread` | Creates and returns a new Thread to execute the given task |

---

## 8. Key Methods

| Method | Receives | Returns | When to use |
|--------|----------|---------|-------------|
| `supplyAsync(Supplier)` | `Supplier<T>` | `CompletableFuture<T>` | Task with return value |
| `runAsync(Runnable)` | `Runnable` | `CompletableFuture<Void>` | Task without return value |
| `completedFuture(value)` | value | `CompletableFuture<T>` | Value already available |
| `thenApply(Function)` | `Function<T,U>` | `CompletableFuture<U>` | Transform result |
| `thenAccept(Consumer)` | `Consumer<T>` | `CompletableFuture<Void>` | Consume result |
| `thenRun(Runnable)` | `Runnable` | `CompletableFuture<Void>` | Action without using result |
| `thenCombine(cf, BiFunction)` | CF + BiFunction | `CompletableFuture<V>` | Combine two results |
| `allOf(cf...)` | multiple CF | `CompletableFuture<Void>` | Wait for all |
| `anyOf(cf...)` | multiple CF | `CompletableFuture<Object>` | Get the fastest |
| `exceptionally(Function)` | `Function<Throwable,T>` | `CompletableFuture<T>` | Error fallback |
| `handle(BiFunction)` | `BiFunction<T,Throwable,U>` | `CompletableFuture<U>` | Handle result or error |
| `get()` | none | `T` | Block and get value (checked exception) |
| `join()` | none | `T` | Same as get() without checked exception |
| `isDone()` | none | `boolean` | Check without blocking |
| `cancel(bool)` | none | `boolean` | Attempt to cancel |

---

## 9. Exercises

### 9.1. SimpleAsyncChaining — Order Processor

Builds an e-commerce order pipeline that fetches orders asynchronously, applies a 10% discount in a `thenApply` stage, and prints results via `thenAccept`. Runs 3 independent chains concurrently and uses `allOf().join()` to wait for all before the main thread exits.

**Key concepts:** `supplyAsync`, `thenApply`, `thenAccept`, `allOf`, independent instances per chain.

---

### 9.2. CombiningMultipleTasks — Product Search

Simulates searching for the cheapest product price across 3 stores running in parallel. Chains two `thenCombine()` calls using `Integer::min` to find the lowest price. Also demonstrates `allOf()` with 5 independent tasks printing a summary only after all complete.

**Key concepts:** `thenCombine`, `allOf`, `Integer::min` as method reference, parallel independent tasks.

---

### 9.3. ErrorHandlingDifferenceAndFallback — Payment Service

Processes payments through a validation pipeline that throws different exceptions for invalid and over-limit amounts. Implements the same pipeline twice, once with `exceptionally()` for a simple fallback, and once with `handle()` to print status before returning the result.

**Key concepts:** `exceptionally`, `handle`, `ex.getCause().getMessage()`, error-only vs always-runs behavior.

---

### 9.4. ThreadFactoryAndPool — Report Service

Generates 6 reports using a fixed pool of 3 threads built with a custom `ThreadFactory` that names threads `report-worker-N` and marks them as daemon. Passes the pool as the second argument to `supplyAsync()`, making thread names visible in the output. Measures and prints total execution time.

**Key concepts:** `ThreadFactory`, `setName()`, `setDaemon()`, custom pool with `supplyAsync`, `allOf`, execution time measurement.

---

### 9.5. StreamsWithCompletableFuture — Data Pipeline

Fetches a list of 10 random integers asynchronously and processes it through two chained `thenApply` stages: the first filters positives and multiplies by 3, the second reduces to a sum. Runs two independent pipelines concurrently and combines their final sums with `thenCombine(Integer::sum)`.

**Key concepts:** stream inside `thenApply`, `filter`, `map`, `reduce`, `thenCombine`, `exceptionally` with default value.

---

## 10. Results

- Asynchronous task execution with `supplyAsync` and `runAsync` without Runnable or Callable
- Fluent chaining with `thenApply`, `thenAccept` and `thenRun`
- Parallel task combination with `thenCombine`, `allOf` and `anyOf`
- Error handling with `exceptionally` for fallback and `handle` for full control
- Stream processing inside `CompletableFuture` chains
- Custom thread control with `ThreadFactory` and named daemon threads
- Passing custom pools to `supplyAsync` for production-level thread management
- Using `join()` over `get()` to avoid checked exceptions in async chains

---