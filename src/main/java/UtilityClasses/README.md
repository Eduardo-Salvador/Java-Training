<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Utility Classes

This module contains a comprehensive series of exercises demonstrating the use of Java Utility Classes, essential tools that improve code quality, performance, and maintainability through pre-built, optimized functionalities.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

Utility Classes are **classes containing only static methods and constants**, serving as **collections of related functionalities**. They **should not be instantiated and generally solve common programming problems**.

**Important:** Utility classes **help improve application performance and code quality** by providing well-tested, optimized implementations. Java includes many native utility classes, and you can create custom ones following best practices.

---

## Architecture

The project is organized as follows:

- **ProblemQuestion**: Contains the exercise implementations
- **Main**: Entry point for running each exercise
- **Player** (Serialization only): Model class for demonstrating object persistence
- **18 Progressive Exercises**: From basic to advanced, covering all major utility class categories

---

## 1. What are Utility Classes

Utility classes contain **static methods and constants** that provide reusable functionality without requiring object instantiation. They follow a specific structure:

### 1.1. Typical Structure
```java
public final class StringUtils {
    // Private constructor prevents instantiation
    private StringUtils() {
        throw new AssertionError("Cannot instantiate utility class");
    }
    
    // All methods are static
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
```

### 1.2. Key Characteristics

- All methods are `static`
- Private constructor to prevent instantiation
- Usually marked as `final` (optional)
- Contain only helper logic, no state
- Promote code reuse and maintainability

### 1.3. Native Java Utility Classes

**Collections & Arrays:**
- `java.util.Arrays` — array manipulation
- `java.util.Collections` — collection operations
- `java.util.Objects` — null checks, equals, hash

**Math & Numbers:**
- `java.lang.Math` — mathematical functions
- Wrapper classes — Integer, Double, Boolean, etc.

**Text & Strings:**
- `java.lang.String` — text manipulation
- `StringBuilder/StringBuffer` — efficient string building

**Date & Time:**
- Legacy: `Date`, `Calendar`, `DateFormat`, `SimpleDateFormat`
- Modern: `LocalDate`, `LocalTime`, `LocalDateTime`, `Instant`
- Formatting: `DateTimeFormatter`
- Calculations: `Period`, `ChronoUnit`, `TemporalAdjusters`

**I/O & Files:**
- Legacy: `File`, `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`
- Modern: `Path`, `Paths`, `Files` (NIO)

**Internationalization:**
- `Locale` — region/language settings
- `ResourceBundle` — multilingual messages

**Pattern Matching:**
- `Pattern` — regex compilation
- `Matcher` — pattern matching operations

**Input:**
- `Scanner` — text parsing with delimiters

**Serialization:**
- `ObjectOutputStream` — save objects to bytes
- `ObjectInputStream` — restore objects from bytes

### 1.4. Best Practices

1. **Descriptive naming** — usually ends with "Utils" or "Helper"
2. **Cohesion** — group only related methods
3. **Pure methods** — avoid side effects when possible
4. **Clear documentation** — JavaDoc for all public methods
5. **Parameter validation** — check nulls and invalid values

### 1.5. When to Use

Utility classes are **ideal for functionalities that don't naturally belong to any domain class**, such as conversions, validations, or specific mathematical operations. Avoid creating "God Classes" that do too much — keep each utility class focused on a specific purpose.

---

## 2. Quick Reference Table

### 2.1. Wrapper Classes

| Primitive | Wrapper | Key Methods |
|-----------|---------|-------------|
| `byte` | `Byte` | `parseByte()`, `valueOf()` |
| `short` | `Short` | `parseShort()`, `valueOf()` |
| `int` | `Integer` | `parseInt()`, `valueOf()`, `compare()`, `toBinaryString()` |
| `long` | `Long` | `parseLong()`, `valueOf()`, `toHexString()` |
| `float` | `Float` | `parseFloat()`, `valueOf()`, `isNaN()` |
| `double` | `Double` | `parseDouble()`, `valueOf()`, `isInfinite()` |
| `char` | `Character` | `isDigit()`, `isLetter()`, `toUpperCase()` |
| `boolean` | `Boolean` | `parseBoolean()`, `valueOf()` |

### 2.2. String Operations

| Operation | Method | Use |
|-----------|--------|-----|
| **Query** | `length()`, `charAt()`, `isEmpty()` | Get info |
| **Compare** | `equals()`, `equalsIgnoreCase()`, `compareTo()` | Compare strings |
| **Search** | `indexOf()`, `contains()`, `startsWith()` | Find text |
| **Modify** | `substring()`, `replace()`, `toLowerCase()` | Transform |
| **Split/Join** | `split()`, `String.join()` | Divide/combine |
| **Format** | `format()`, `formatted()` | Template strings |

### 2.3. Date & Time (Modern API)

| Class | Represents | Use |
|-------|------------|-----|
| `LocalDate` | Date only | Birthdays, deadlines |
| `LocalTime` | Time only | Schedules, appointments |
| `LocalDateTime` | Date + Time | Events, logs |
| `Instant` | Timestamp (UTC) | Backend, databases |
| `ZonedDateTime` | Date + Time + Zone | International apps |
| `Period` | Date-based duration | Years, months, days |
| `ChronoUnit` | Time units | Calculate differences |

### 2.4. File Operations

| API | Classes | Use |
|-----|---------|-----|
| **Legacy** | `File`, `FileReader`, `FileWriter` | Old systems |
| **Buffered** | `BufferedReader`, `BufferedWriter` | Efficient I/O |
| **NIO (Modern)** | `Path`, `Paths`, `Files` | Recommended |

### 2.5. Pattern Matching

| Class | Purpose | Key Methods |
|-------|---------|-------------|
| `Pattern` | Compile regex | `compile()` |
| `Matcher` | Match text | `find()`, `matches()`, `group()`, `replaceAll()` |

---

## 3. Exercises

### 3.1. Wrappers & Autoboxing

**Number Processing with Collections**

Convert comma-separated numbers and perform operations.

**Key concepts:**
- `Integer.parseInt()` for String → int conversion
- Autoboxing (int → Integer) and Unboxing (Integer → int)
- `ArrayList<Integer>` requires wrapper, not primitive
- `Integer.compare()` for comparison
- NullPointerException risks with unboxing

**Input:** "10,20,30,40"  
**Output:** Sum: 100, Max: 40

---

### 3.2. Strings

**CPF Validator**

Validate Brazilian CPF format using String methods.

**Key concepts:**
- `replace()` to remove formatting characters
- `length()` to validate size
- `substring()` to extract parts
- `equals()` vs `==` for comparison
- String immutability

**Input:** "123.456.789-10"  
**Process:** "12345678910"  
**Validate:** Length and format

---

### 3.3. StringBuilder

**Report Generator**

Generate 1000-line report efficiently.

**Key concepts:**
- `StringBuilder` vs String concatenation
- Mutability and performance
- `append()` for building strings
- `String.format()` for padding
- Converting to String with `toString()`

**Comment:** Without `StringBuilder`, 1000 concatenations create 1000 temporary objects! `StringBuilder` modifies the same object, being much more efficient.

---

### 3.4. Date (Legacy)

**Date Arithmetic**

Work with timestamps and date comparison.

**Key concepts:**
- `Date` works with milliseconds since 1970
- `getTime()` returns timestamp
- `setTime()` modifies date (mutable!)
- `before()` and `after()` for comparison
- Why Date is deprecated

**Comment:** Date is legacy but still appears in old systems. It's mutable (can cause bugs!) and has confusing deprecated methods.

---

### 3.5. Calendar (Legacy)

**Age Calculator**

Calculate age using Calendar API.

**Key concepts:**
- `Calendar.getInstance()` for current date
- `set()` to define specific dates
- `get(Calendar.YEAR)`, `get(Calendar.MONTH)`
- `add()` to modify dates
- Month starts at 0 (January=0, December=11)

**Comment:** Calendar tried to fix Date's problems but is still legacy. The month starting at 0 is very confusing and was fixed in `java.time`.

---

### 3.6. DateFormat & SimpleDateFormat

**Date Format Converter**

Convert between date formats and locales.

**Key concepts:**
- `SimpleDateFormat` for custom patterns
- `parse()` converts String → Date
- `format()` converts Date → String
- `setLenient(false)` to reject invalid dates
- Pattern symbols: dd, MM, yyyy, HH, mm, ss

**Comment:** `SimpleDateFormat` is NOT thread-safe! Sharing the same instance between threads can cause wrong results. Always create new instances or use `DateTimeFormatter` (Java 8+).

---

### 3.7. Locale

**International Price Formatter**

Format currency for different countries.

**Key concepts:**
- `Locale.of("pt", "BR")` for Portuguese/Brazil
- `NumberFormat.getCurrencyInstance(locale)`
- Different decimal separators (. vs ,)
- Different currency symbols (R$, $, ¥, €)
- Date and number formatting by region

**Comment:** Locale is essential for international applications. Each country has its own rules for formatting dates, numbers, and currency. Java has this ready!

**Input:** 1234.56  
**Outputs:**
- Brazil: R$ 1.234,56
- USA: $1,234.56
- Japan: ¥1,235
- Germany: 1.234,56 €

---

### 3.8. LocalDate, LocalTime, LocalDateTime

**Appointment Scheduler**

Combine dates and times using modern API.

**Key concepts:**
- `LocalDate.of()` for specific dates
- `LocalTime.of()` for specific times
- `LocalDateTime.of()` combining both
- `plusWeeks()`, `plusDays()` for calculations
- `getDayOfWeek()` to check weekday
- `format()` with `DateTimeFormatter`

---

### 3.9. Period & ChronoUnit

**Company Time Calculator**

Calculate employment duration.

**Key concepts:**
- `Period.between()` for human-readable difference
- Returns years, months, days separately
- `ChronoUnit.DAYS.between()` for total days
- When to use Period vs ChronoUnit

**Comment:** `Period` gives you the "human" difference (5 years, 2 months, 10 days), while `ChronoUnit` gives you the absolute total (1868 days). Both are useful depending on context!

---

### 3.10. TemporalAdjusters

**Payroll Date Generator**

Find business-critical dates automatically.

**Key concepts:**
- `TemporalAdjusters.lastDayOfMonth()`
- `TemporalAdjusters.next(DayOfWeek.MONDAY)`
- `firstDayOfMonth()`, `firstDayOfYear()`
- Avoiding manual calendar calculations
- Business rule implementations

**Comment:** `TemporalAdjusters` prevents you from having to do complex manual calendar calculations. Very useful for business rules!

---

### 3.11. ZonedDateTime & ZoneId

**Time Zone Converter**

Convert times across different time zones.

**Key concepts:**
- `ZoneId.of("America/Sao_Paulo")`
- `ZonedDateTime` stores complete zone information
- `withZoneSameInstant()` for conversion
- Difference between `ZonedDateTime` and `OffsetDateTime`
- UTC vs local time

**Comment:** `ZonedDateTime` stores the complete zone (America/Sao_Paulo), while `OffsetDateTime` stores only the offset (-03:00). Use `ZonedDateTime` when you need correct time zone conversions!

---

### 3.12. DateTimeFormatter

**Log Parser**

Extract and reformat dates from log entries.

**Key concepts:**
- `DateTimeFormatter.ofPattern()`
- Custom patterns: "yyyy-MM-dd HH:mm:ss"
- `parse()` converts String → LocalDateTime
- `format()` converts LocalDateTime → String
- Locale-aware formatting

**Input:** "2026-02-11 14:30:45 - System error"  
**Output:** "11/02/2026 at 2:30 PM"

---

### 3.13. ResourceBundle

**Multilingual System**

Load messages based on user locale.

**Key concepts:**
- `.properties` files for each language
- `messages_pt_BR.properties`, `messages_en_US.properties`
- `ResourceBundle.getBundle("messages", locale)`
- `getString(key)` to retrieve messages
- Fallback to default properties file

**Comment:** ResourceBundle is fundamental for internationalization (i18n). In production, you would have hundreds of keys to translate the entire application!

**Files:**
- `messages_pt_BR.properties`: welcome=Bem-vindo
- `messages_en_US.properties`: welcome=Welcome

---

### 3.14. Regex - Pattern & Matcher

**Email Extractor**

Find and extract email addresses from text.

**Key concepts:**
- `Pattern.compile(regex)` to compile pattern
- `Matcher.find()` to search in loop
- `group()` to extract matched text
- `start()` and `end()` for positions
- `replaceAll()` for substitution
- Common patterns: `\d`, `\w`, `\.`, `+`, `*`

**Comment:** Regex is powerful but complex. Start simple and evolve. `find()` searches for patterns in text, while `matches()` validates if the ENTIRE text matches the pattern.

**Input:** "Contact: john@email.com or mary@company.com"  
**Regex:** `[\w.-]+@[\w.-]+\.\w+`

---

### 3.15. Scanner with Delimiters

**CSV Reader**

Parse delimited text efficiently.

**Key concepts:**
- `useDelimiter()` to change token separator
- Default delimiter is whitespace
- `next()` reads next token
- `hasNext()` checks for more tokens
- `nextInt()`, `nextDouble()` for typed reading
- Custom delimiters with regex

**Comment:** Scanner is great for simple parsing. The delimiter defines where to "break" the text. By default it's space, but you can change it to any regex!

**Input:** "John;25;New York|Mary;30;Los Angeles"  
**Delimiter:** `\\|` for person, `;` for fields

---

### 3.16. File & Basic IO

**Simple Log System**

Create, write, and read log files.

**Key concepts:**
- `File.exists()` to check existence
- `mkdir()` to create directory
- `BufferedWriter` for efficient writing
- Append mode with `FileWriter(file, true)`
- `BufferedReader` with `readLine()` for reading
- `File.length()` for file size
- Try-with-resources for automatic closing

**Comment:** Always use `BufferedReader/Writer` in production! Reading/writing character by character with `FileReader/Writer` is very slow. The buffer accumulates data in memory before writing to disk.

---

### 3.17. NIO - Path, Paths, Files

**File Organizer**

Modern file operations with NIO API.

**Key concepts:**
- `Paths.get()` to create Path
- `Files.write()` to create and write
- `Files.readAllLines()` to read all content
- `Files.list()` to list directory
- `Path.resolve()` to combine paths
- `Files.copy()` to copy files
- NIO is the modern, recommended API

**Comment:** NIO is the modern API! `Files` has methods for EVERYTHING: create, delete, copy, move, read, write. Much simpler than `File` from the old API.

**Operations:**
- Create 3 text files
- List all .txt files
- Read content
- Create backup directory
- Copy files to backup

---

### 3.18. Serialization

**Game Save System**

Persist objects to disk and restore them.

**Key concepts:**
- `implements Serializable` marker interface
- `serialVersionUID` for version control
- `ObjectOutputStream.writeObject()` to save
- `ObjectInputStream.readObject()` to restore
- `transient` keyword excludes fields
- Try-with-resources for stream management
- Cast required when deserializing

**Comment:** Serialization transforms objects into bytes. Nowadays, JSON is more common, but Java serialization still appears in cache, sessions, and legacy systems. `transient` is important to not save sensitive data like passwords!

**Player Class:**
- Serialized: name, level, points
- NOT serialized (transient): password

---

## 4. Results

**Complete mastery of wrapper classes**
- Autoboxing and unboxing mechanisms
- Converting between primitives and objects
- Performance considerations
- NullPointerException awareness

**String manipulation expertise**
- Immutability understanding
- Efficient string building with StringBuilder
- Format and parse operations
- Regular expressions for pattern matching

**Date and time proficiency**
- Legacy API (Date, Calendar) for maintenance
- Modern API (java.time) for new development
- Time zone handling
- Period calculations and adjustments
- Locale-aware formatting

**File I/O mastery**
- Legacy File API understanding
- Modern NIO for production code
- Buffered operations for performance
- Resource management with try-with-resources

**Internationalization skills**
- Locale for region-specific formatting
- ResourceBundle for multilingual apps
- Currency and number formatting
- Date formatting across cultures

**Object persistence**
- Serialization mechanism
- Version control with serialVersionUID
- Protecting sensitive data with transient
- Stream management

**Best practices**
- Choosing appropriate utility classes
- Performance optimization
- Code readability and maintainability
- Thread-safety awareness
- Resource management

**Real-world applications**
- Data validation and conversion
- Report generation
- Log processing
- File management
- International applications
- Legacy system maintenance

---