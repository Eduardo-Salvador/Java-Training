<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# MySQL

MySQL is a relational database management system that organizes data in tables with rows and columns. It uses SQL (Structured Query Language) as its language for creating, querying, updating and deleting data. It is widely adopted, reliable, and integrates naturally with Java through JDBC.

## Technologies
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

</div>

---

## 1. Database and Tables

A database is a container that groups related tables. A table is a structured set of data organized in columns (fields) and rows (records).

```sql
CREATE DATABASE training;
USE training;

CREATE TABLE users (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    age  INT
);

DROP TABLE users;
DROP DATABASE training;
```

---

## 2. Main Data Types

| Type | Description |
|------|-------------|
| `INT` | Integer numbers |
| `BIGINT` | Large integer numbers |
| `DECIMAL(p,s)` | Decimal numbers with precision |
| `VARCHAR(n)` | Variable length text up to n characters |
| `TEXT` | Long text without size limit |
| `BOOLEAN` | True or false |
| `DATE` | Date (YYYY-MM-DD) |
| `DATETIME` | Date and time |
| `TIMESTAMP` | Date and time with timezone awareness |

---

## 3. Keys

**Primary Key** uniquely identifies each record in a table. No two rows can have the same value. `AUTO_INCREMENT` makes MySQL generate the value automatically.

```sql
id INT AUTO_INCREMENT PRIMARY KEY
```

**Foreign Key** references the primary key of another table, creating a relationship between them. It enforces referential integrity, preventing orphan records.

```sql
CREATE TABLE orders (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total   DECIMAL(10,2),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

**Unique** ensures no two rows have the same value in a column without making it the primary key.

```sql
email VARCHAR(150) NOT NULL UNIQUE
```

**NOT NULL** ensures a column cannot be left empty.

**DEFAULT** sets a fallback value when no value is provided on insert.

```sql
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
```

---

## 4. CRUD

CRUD stands for Create, Read, Update and Delete. These are the four fundamental operations on any database.

**Create — INSERT**

```sql
INSERT INTO users (name, email, age) VALUES ('Eduardo', 'edu@email.com', 25);
INSERT INTO users (name, email, age) VALUES
    ('Ana', 'ana@email.com', 30),
    ('Carlos', 'carlos@email.com', 22);
```

**Read — SELECT**

```sql
SELECT * FROM users;
SELECT name, email FROM users;
SELECT * FROM users WHERE age > 20;
SELECT * FROM users WHERE name = 'Eduardo';
SELECT * FROM users ORDER BY name ASC;
SELECT * FROM users LIMIT 10;
SELECT * FROM users WHERE age > 20 ORDER BY name ASC LIMIT 5;
```

**Update — UPDATE**

```sql
UPDATE users SET age = 26 WHERE id = 1;
UPDATE users SET name = 'Eduardo G', age = 26 WHERE id = 1;
```

Always use `WHERE` on UPDATE. Without it, every row in the table is updated.

**Delete — DELETE**

```sql
DELETE FROM users WHERE id = 1;
```

Always use `WHERE` on DELETE. Without it, every row in the table is deleted.

---

## 5. Filtering and Operators

```sql
WHERE age > 20
WHERE age >= 18 AND age <= 30
WHERE name = 'Eduardo' OR name = 'Ana'
WHERE name LIKE 'Edu%'       -- starts with Edu
WHERE name LIKE '%uardo'     -- ends with uardo
WHERE age IN (18, 25, 30)
WHERE age BETWEEN 18 AND 30
WHERE email IS NULL
WHERE email IS NOT NULL
```

---

## 6. Aggregate Functions

```sql
SELECT COUNT(*) FROM users;
SELECT AVG(age) FROM users;
SELECT SUM(total) FROM orders;
SELECT MAX(age) FROM users;
SELECT MIN(age) FROM users;
SELECT age, COUNT(*) FROM users GROUP BY age;
SELECT age, COUNT(*) FROM users GROUP BY age HAVING COUNT(*) > 1;
```

`GROUP BY` groups rows with the same value. `HAVING` filters after grouping, unlike `WHERE` which filters before.

---

## 7. Joins

Joins combine rows from two or more tables based on a related column.

```sql
-- INNER JOIN: only rows that match in both tables
SELECT users.name, orders.total
FROM users
INNER JOIN orders ON users.id = orders.user_id;

-- LEFT JOIN: all rows from the left table, matching rows from the right
SELECT users.name, orders.total
FROM users
LEFT JOIN orders ON users.id = orders.user_id;

-- RIGHT JOIN: all rows from the right table, matching rows from the left
SELECT users.name, orders.total
FROM users
RIGHT JOIN orders ON users.id = orders.user_id;
```

---

## 8. Indexes

Indexes speed up queries on columns that are frequently searched. They work like a book index — instead of reading every row, MySQL jumps directly to the relevant ones.

```sql
CREATE INDEX idx_email ON users(email);
CREATE INDEX idx_name_age ON users(name, age);
DROP INDEX idx_email ON users;
```

Primary keys and unique columns automatically have an index. Adding indexes on other frequently queried columns improves performance but increases storage and slows down inserts and updates.

---

## 9. Transactions

A transaction groups multiple operations into a single atomic unit. Either all operations succeed or none of them are applied.

```sql
START TRANSACTION;

UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;

COMMIT;    -- applies all changes
ROLLBACK;  -- undoes all changes if something goes wrong
```

---

## 10. Useful Commands

```sql
SHOW DATABASES;
SHOW TABLES;
DESCRIBE users;
SHOW CREATE TABLE users;
SHOW INDEXES FROM users;
```

---