# Streams

---

## Introduction

This repository contains practical exercises on **Java Streams**, covering different operations such as **filter**, **map**, **reduce**, **generate**, **summaryStatistics**, and more.  
The goal is to demonstrate how to use the Streams API to process collections of data in a declarative, expressive, and efficient way.

### What are Streams?

Streams in Java are an abstraction that allows data processing in a functional style.  
Key characteristics:

- They do not store data; they process flows.
- Support **intermediate operations** (e.g., `filter`, `map`, `sorted`) and **terminal operations** (e.g., `collect`, `reduce`, `forEach`).
- Facilitate parallelization and make code more readable and concise.

---

## Exercises Structure

Each exercise is accompanied by a `problemquestion.txt` file describing the problem statement.  
Below are the modules and their functionalities:

### 1. Generate and Summarizing

**Package:** `Streams.GenerateAndSummarizing`

- Generate arrays with `IntStream.range`.
- Use `Stream.generate` to create random values.
- Apply `DoubleSummaryStatistics` to summarize deposits in transactions:
  - Sum
  - Average
  - Maximum
  - Minimum
  - Count
- Extra challenge: summarize dynamically generated random numbers.

---

### 2. Reduce

**Package:** `Streams.Reduce`

- Entities: `Product`, `Item`, `Order`.
- Use of `reduce` to:
  - Calculate the total value of orders.
  - Find the largest request value.
- Grouping with `Collectors.groupingBy` and `Collectors.reducing` to identify the most ordered product.

---

### 3. Basic Streams

**Package:** `Streams.Streams`

- Create a list of `Employee`.
- Stream operations:
  - `filter`: select employees with salary above 5000.
  - `map`: transform into formatted strings.
  - `sorted`: sort alphabetically.
  - `distinct`: remove duplicates.
  - `count`: count filtered elements.
  - `max` and `min`: find highest and lowest salary.
  - `anyMatch`, `allMatch`, `noneMatch`: check conditions on age, salary, and names.

---

## Organization

Each exercise includes:
- Java source code.
- A `problemquestion.txt` file with the problem description.

---

## Objective

The purpose of these exercises is to consolidate understanding of the Java Streams API, exploring from basic operations to advanced reductions and summarizations.  
They serve as practice for writing more functional, expressive, and efficient Java code.

---
