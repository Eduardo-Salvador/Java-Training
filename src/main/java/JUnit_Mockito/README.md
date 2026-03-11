<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# JUnit_Mockito 5 and Mockito

This module covers unit testing in Java using JUnit_Mockito 5 and Mockito. Unit testing validates the behavior of isolated units of code without depending on databases, networks, or any external resource.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)

</div>

---

## Architecture

- **JUnit_Mockito**: `CalculatorRepository`, `CalculatorService`, `CalculatorTest`

---

## 1. Why Unit Tests

Without tests, the only way to verify that code works is to run the application manually and test it through the terminal or interface. That approach does not scale. As the project grows, a change in one place can silently break another, and the problem only surfaces in production.

Unit tests solve this in three ways. First, they guarantee that expected behavior continues to work after any modification, which is called regression prevention. Second, they force code to be written in a testable way, which naturally leads to smaller classes, methods with single responsibility, and less coupling. Third, they document behavior in an executable form: a well-written test is more reliable than any comment.

---

## 2. Test Structure

The universal structure for any test is the AAA pattern: Arrange, Act and Assert.

```java
@Test
void shouldReturnCorrectResultOnSum() {
    // Arrange — set up the scenario
    int a = 2;
    int b = 3;

    // Act — execute what is being tested
    int result = service.sum(a, b);

    // Assert — verify the result
    assertEquals(5, result);
}
```

---

## 3. Main Annotations

`@Test` marks the method as a test. `@BeforeEach` runs before each test, used to set up the initial state. `@AfterEach` runs after each test, used to clean up resources. `@BeforeAll` runs once before all tests in the class. `@AfterAll` runs once after all tests. `@DisplayName` defines a readable name for the test. `@Disabled` temporarily disables a test.

---

## 4. Main Assertions

```java
assertEquals(expected, actual);
assertNotEquals(expected, actual);
assertTrue(condition);
assertFalse(condition);
assertNull(object);
assertNotNull(object);
assertThrows(Exception.class, () -> method());
assertAll(
    () -> assertEquals(1, result.getId()),
    () -> assertEquals("name", result.getName())
);
```

`assertThrows` validates that the code throws the correct exception in error scenarios. `assertAll` groups multiple assertions and executes all of them even if one fails, instead of stopping at the first failure.

---

## 5. Good Tests vs Bad Tests

A bad test passes even when the code is wrong. The most classic example:

```java
@Test
void shouldSave() {
    service.save(book);
    assertTrue(true); // always passes, tests nothing
}
```

Coverage of 100% only means that each line was executed, not that behavior was validated. A test can execute a method and make no meaningful assertion while still counting as coverage.

A good test validates behavior, not execution. Each test must have a single reason to fail. If a test validates three different behaviors and fails, it is unclear which of the three broke. One behavior per test.

Tests must not depend on other tests. Each test must be able to run in isolation in any order and produce the same result.

The test name is the first documentation. When a test fails in CI, the name `shouldThrowExceptionWhenIdIsNegative` immediately explains what broke without reading the code.

---

## 6. Cases That Coverage Does Not Catch

Coverage measures executed lines, not combinations of state. The cases that escape most often are boundary values, null versus blank strings, empty lists versus populated lists, and compound conditions.

```java
// the code has this logic
if (id == null || id <= 0) throw new IllegalArgumentException();

// bad test — covers the line but not both conditions
@Test
void shouldThrowWhenIdIsInvalid() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(-1));
}

// good — tests each condition separately
@Test
void shouldThrowWhenIdIsNull() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(null));
}

@Test
void shouldThrowWhenIdIsZero() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(0));
}

@Test
void shouldThrowWhenIdIsNegative() {
    assertThrows(IllegalArgumentException.class,
        () -> service.delete(-1));
}
```

---

## 7. @BeforeEach and Instance Variables

`@BeforeEach` runs before each test and is used to build the initial state that repeats across multiple tests. The rule is simple: if three or more tests need the same object in the same state, it belongs in `@BeforeEach`.

```java
class CalculatorTest {

    CalculatorRepository repository;
    CalculatorService service;

    @BeforeEach
    void setUp() {
        repository = new CalculatorRepository();
        service = new CalculatorService(repository);
    }

    @Test
    void shouldReturnCorrectResultOnSum() {
        assertEquals(5, service.sum(2, 3));
    }
}
```

JUnit_Mockito creates a new instance of the test class before each test, so instance variables do not carry state between tests even without `@BeforeEach`.

---

## 8. Mockito

Tests must be isolated. When the method under test depends on another class, such as a repository that accesses a database, that dependency needs to be simulated. Mockito is the standard library for this.

A mock is an empty object that executes nothing. When `repository.save(book)` is called on a mock, it saves nothing anywhere. Mockito intercepts the call and allows the test to configure what the mock returns and to verify that specific methods were called.

```java
@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    @Mock
    private CalculatorRepository repository;

    @InjectMocks
    private CalculatorService service;

    @Test
    @DisplayName("Should call repository with correct result on sum")
    void shouldCallRepositoryWithCorrectResultOnSum() {
        service.sum(2, 3);
        verify(repository, times(1)).save(5);
    }
}
```

`@Mock` creates a simulated version of the dependency. `@InjectMocks` creates the class under test and injects the mocks automatically. `verify()` confirms that the method was called the expected number of times. `when().thenReturn()` configures what the mock returns for a given call.

Mockito works by creating a subclass of the repository at runtime and overriding its methods to intercept calls. This only works with instance methods. Static methods belong to the class, not to an object, so Mockito has no instance to substitute. This is why code that uses static calls to dependencies cannot be tested with Mockito, and why dependency injection via constructor is the standard practice for testable code.

---

## 9. Test Naming Convention

The test name must describe the expected behavior, not the method name. The most widely used pattern is `should[Result]When[Condition]`:

```
shouldReturnCorrectResultOnSum
shouldThrowExceptionWhenDivisorIsZero
shouldCallRepositoryWithCorrectResult
shouldReturnEmptyWhenIdDoesNotExist
```

---

## 10. Maven Dependencies

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.14.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.14.0</version>
    <scope>test</scope>
</dependency>
```

Surefire plugin required to run tests with Maven:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <argLine>-XX:+EnableDynamicAgentLoading</argLine>
    </configuration>
</plugin>
```

---

## 11. Exercise

### 11.1. CalculatorService

Implementation of a calculator service demonstrating unit testing with JUnit_Mockito 5. The `CalculatorRepository` simulates persistence by printing each result. The `CalculatorService` receives the repository via constructor and exposes four operations: `sum`, `subtract`, `multiply` and `divide`. Each operation calls `repository.save()` with the result before returning it. `sum` throws `IllegalArgumentException` for negative values and `divide` throws `IllegalArgumentException` when the divisor is zero.

The `CalculatorTest` class covers ten scenarios validating both correct results and exception behavior across all operations.

**Key concepts:** AAA pattern, `assertEquals`, `assertThrows`, `@BeforeEach`, instance variables, one behavior per test, naming convention, dependency injection via constructor.

---

## 12. Results

- AAA pattern as the universal structure for any test
- Distinction between coverage and meaningful validation
- Boundary and compound condition testing beyond line coverage
- `@BeforeEach` for shared setup without state leakage between tests
- Mockito for isolating dependencies from the unit under test
- Static methods as incompatible with Mockito injection
- Dependency injection via constructor as the prerequisite for testable code
- Naming convention that describes behavior rather than method names

---