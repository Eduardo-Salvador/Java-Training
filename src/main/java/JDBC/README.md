<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# JDBC

JDBC (Java Database Connectivity) is a standard Java library for connecting and executing operations on relational databases. It provides a unified API that works regardless of the database vendor, meaning the same code structure works for MySQL, PostgreSQL, Oracle, and others. Only the driver and the connection URL change.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

</div>

---

## Architecture

```
conn        Connection factory
domain      Data model classes
service     Business rules before reaching the repository layer
repository  Classes that interact directly with the database
```

---

## 1. Maven Dependency

The database driver must be added to `pom.xml`. The driver is the library that translates Java calls into database-specific protocol. Without it, the JVM does not know how to communicate with the database.

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.6.0</version>
    <scope>compile</scope>
</dependency>
```

Downloaded dependencies are stored in `~/.m2/repository` and visible in the IDE under External Libraries.

---

## 2. Connection

`DriverManager` is the entry point for obtaining a `Connection`. The URL follows the format `jdbc:vendor://host:port/database`. Every connection must be closed after use, otherwise the application leaks database connections. Using try-with-resources guarantees the connection is closed even if an exception is thrown.

```java
public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/store";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
}
```

---

## 3. Lombok and Log4J2

**Lombok** is a compile-time annotation processor that generates boilerplate code such as getters, setters, equals, hashCode, toString, and builders. It does not affect runtime performance. The generated code exists in the bytecode but not in the source file. The IDE requires the Lombok plugin to recognize the generated methods.

```java
@Value
@Builder
public class Producer {
    Integer id;
    String name;
}
```

`@Value` makes all fields private and final. `@Builder` generates the inner builder class. Manual methods can coexist with Lombok annotations. If a method is written manually, Lombok skips generating it.

**Log4J2** is a logging library that replaces `System.out.println`. It requires an XML configuration file placed in `src/main/resources`. Log levels allow controlling what is recorded depending on the environment.

```java
log.info("rows affected {}", rowsAffected);
log.debug("detail for debugging");
log.warn("potential issue");
log.error("something went wrong", e);
log.trace("very detailed trace");
```

LGPD compliance requires that logs never contain personally identifiable information such as CPF, passwords, or full names.

---

## 4. Statement

`Statement` is the basic object for sending fixed SQL to the database. It does not accept parameters and is vulnerable to SQL Injection. It is appropriate only for queries where no external input is involved.

`executeUpdate` is used for INSERT, UPDATE and DELETE and returns the number of rows affected. `executeQuery` is used for SELECT and returns a `ResultSet`.

```java
try (Connection conn = ConnectionFactory.getConnection();
     Statement stmt = conn.createStatement()) {
    int rowsAffected = stmt.executeUpdate(sql);
    log.info("rows affected {}", rowsAffected);
} catch (SQLException e) {
    log.error("Error {}", e);
}
```

---

## 5. ResultSet

`ResultSet` is the object that holds the result of a SELECT query. It starts positioned before the first row. Each call to `rs.next()` advances the cursor to the next row and returns true while rows exist. Values are read by column name or index.

```java
try (Connection conn = ConnectionFactory.getConnection();
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(sql)) {
    while (rs.next()) {
        var id = rs.getInt("id");
        var name = rs.getString("name");
        producers.add(Producer.builder().id(id).name(name).build());
    }
}
```

`Optional` can be used when a query is expected to return zero or one result, making null handling explicit.

---

## 6. Scrollable ResultSet

By default a `ResultSet` is forward-only. Creating the statement with `TYPE_SCROLL_INSENSITIVE` allows navigating in any direction. `INSENSITIVE` means the ResultSet does not reflect changes made to the database after the query was executed.

```java
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE
);
```

Navigation methods available with scroll:

```java
rs.last();          // moves to last row
rs.first();         // moves to first row
rs.absolute(2);     // moves to row 2
rs.relative(-1);    // moves back one row
rs.previous();      // moves to previous row
rs.getRow();        // returns current row number
rs.isFirst();
rs.isLast();
rs.isBeforeFirst();
rs.isAfterLast();
```

With `CONCUR_UPDATABLE`, the ResultSet itself can modify the database without issuing a new query:

```java
rs.updateString("name", rs.getString("name").toUpperCase());
rs.updateRow();      // applies the update
rs.cancelRowUpdates(); // cancels before updateRow is called

rs.moveToInsertRow(); // moves cursor to a temporary new row
rs.updateString("name", name);
rs.insertRow();       // inserts the new row

rs.deleteRow();       // deletes the current row
```

---

## 7. ResultSetMetaData

`ResultSetMetaData` provides structural information about the columns in a result set: table name, column names, types, and sizes.

```java
ResultSetMetaData meta = rs.getMetaData();
int columnCount = meta.getColumnCount();
for (int i = 1; i <= columnCount; i++) {
    log.info("Table: {}", meta.getTableName(i));
    log.info("Column: {}", meta.getColumnName(i));
    log.info("Type: {}", meta.getColumnTypeName(i));
    log.info("Size: {}", meta.getColumnDisplaySize(i));
}
```

---

## 8. DatabaseMetaData

`DatabaseMetaData` provides information about the capabilities of the driver and the database itself. It is used to verify which ResultSet types and concurrency modes the driver supports before using them.

```java
DatabaseMetaData meta = conn.getMetaData();
if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
    log.info("Supports TYPE_SCROLL_INSENSITIVE");
}
```

---

## 9. PreparedStatement

`PreparedStatement` pre-compiles the SQL before execution. Parameters are passed using `?` placeholders and set individually. This approach protects against SQL Injection because user input is never concatenated directly into the query string and is always treated as data, not code.

```java
String sql = "SELECT * FROM store.producer WHERE name LIKE ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, "%" + name + "%");
ResultSet rs = ps.executeQuery();
```

The `%%%s%%` pattern used with `String.format` produces `%value%` because `%%` is a literal percent sign in format strings. With `PreparedStatement` the wildcard is set directly: `"%" + name + "%"`.

`PreparedStatement` handles all CRUD operations and should be preferred over `Statement` whenever user input is involved.

---

## 10. SQL Injection

SQL Injection is a security vulnerability where malicious SQL code is injected through user input into a query. A classic example is a login bypass where entering `' OR '1'='1` as a password turns the WHERE clause always true. In more destructive cases, an attacker can inject `'; DROP TABLE users;` and delete an entire table. `PreparedStatement` prevents this by treating all parameters as literal values, never as SQL syntax.

---

## 11. CallableStatement

`CallableStatement` is a specialized version of `PreparedStatement` used for executing stored procedures and functions stored in the database.

Stored procedures are SQL blocks pre-defined and stored in the database. They can execute any DML operation and may or may not return values. Functions always return a value and can be called inside SQL queries.

```sql
DELIMITER //
CREATE PROCEDURE sp_get_producer_by_name(IN p_name VARCHAR(100))
BEGIN
    SELECT * FROM producer WHERE name LIKE p_name;
END //
DELIMITER ;
```

```java
String sql = "{CALL sp_get_producer_by_name(?)}";
CallableStatement cs = conn.prepareCall(sql);
cs.setString(1, "%" + name + "%");
ResultSet rs = cs.executeQuery();
```

| Type | Returns | Main use | Called with |
|------|---------|----------|-------------|
| Stored Procedure | Not required | Operations and logic | `CALL sp_name(...)` |
| Function | Always | Calculation and return | `SELECT fn_name(...)` |

---

## 12. Statement Comparison

| Type | Purpose | Accepts `?` | SQL Injection safe |
|------|---------|-------------|---------------------|
| `Statement` | Fixed SQL | No | No |
| `PreparedStatement` | Parameterized SQL | Yes (input) | Yes |
| `CallableStatement` | Stored procedures | Yes (input and output) | Yes |

---

## 13. RowSet

`RowSet` is an enhanced version of `ResultSet`. It extends `ResultSet` but adds serialization support, network transfer capability, and built-in scrollability and updatability.

**JdbcRowSet** maintains an active connection to the database, similar to a `ResultSet` but with a simpler API:

```java
JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet();
jrs.setCommand("SELECT * FROM store.producer WHERE name LIKE ?");
jrs.setString(1, "%" + name + "%");
jrs.execute();
while (jrs.next()) {
    // read rows
}
```

`RowSetListener` allows reacting to events on the RowSet without manually polling. Three events are available: `rowSetChanged` when the command is executed, `rowChanged` when a row is inserted, updated or deleted, and `cursorMoved` when the cursor position changes.

**CachedRowSet** disconnects after loading data into memory. Operations are performed offline and changes are synchronized back to the database with `acceptChanges()`. It is useful when passing data between layers without keeping a connection open, but requires caution around data conflicts.

---

## 14. Transactions

A transaction groups multiple operations into a single atomic unit. Either all succeed or none are applied. By default JDBC auto-commits every statement. Setting `autoCommit` to false puts all subsequent operations inside a transaction until `commit()` or `rollback()` is called explicitly.

```java
conn.setAutoCommit(false);
try {
    // multiple operations
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
}
```

Transactions are essential when multiple writes must succeed together, such as transferring a balance between two accounts or inserting related records across tables.

---

## 15. Exercise

### 15.1. LibraryManager

Implementation of a complete CRUD application via CLI for managing a library. The `books` table stores id, title, author, year and available status. `DatabaseConnection` centralizes the connection using `DriverManager`. `BookRepository` implements all five operations using `PreparedStatement`: `create`, `findAll`, `findById` returning `Optional`, `update` and `delete`. The `Book` entity uses the Builder pattern. `LibraryMenu` runs a loop reading user input through `Scanner` and dispatching to the appropriate repository method, printing results to the terminal.

**Key concepts:** `Connection`, `PreparedStatement`, `ResultSet`, `Optional`, Builder with JDBC, layered architecture, `Scanner` for CLI input, SQL Injection prevention.

---

## 16. Results

- JDBC as the standard layer between Java and relational databases
- `Connection` lifecycle management with try-with-resources
- `Statement` for fixed SQL and `PreparedStatement` for parameterized queries
- SQL Injection prevention through `PreparedStatement` parameter binding
- `ResultSet` cursor navigation and data extraction by column name
- Scrollable and updatable `ResultSet` with `TYPE_SCROLL_INSENSITIVE`
- `ResultSetMetaData` for inspecting table and column structure at runtime
- `DatabaseMetaData` for querying driver capabilities
- `CallableStatement` for stored procedures and functions
- `RowSet` as a portable and serializable alternative to `ResultSet`
- `RowSetListener` for event-driven reactions to data changes
- `CachedRowSet` for disconnected offline data manipulation
- Transactions with `setAutoCommit(false)`, `commit()` and `rollback()`
- Lombok and Log4J2 as supporting tools for cleaner entity and logging code

---