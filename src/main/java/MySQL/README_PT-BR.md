<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# MySQL

MySQL é um sistema de gerenciamento de banco de dados relacional que organiza dados em tabelas com linhas e colunas. Utiliza SQL (Structured Query Language) como linguagem para criar, consultar, atualizar e deletar dados. É amplamente adotado, confiável e se integra naturalmente com Java através do JDBC.

## Tecnologias
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

</div>

---

## 1. Banco de Dados e Tabelas

Um banco de dados é um container que agrupa tabelas relacionadas. Uma tabela é um conjunto estruturado de dados organizado em colunas (campos) e linhas (registros).

```sql
CREATE DATABASE training;
USE training;

CREATE TABLE users (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    age   INT
);

DROP TABLE users;
DROP DATABASE training;
```

---

## 2. Principais Tipos de Dados

| Tipo | Descrição |
|------|-----------|
| `INT` | Números inteiros |
| `BIGINT` | Números inteiros grandes |
| `DECIMAL(p,s)` | Números decimais com precisão |
| `VARCHAR(n)` | Texto de tamanho variável até n caracteres |
| `TEXT` | Texto longo sem limite de tamanho |
| `BOOLEAN` | Verdadeiro ou falso |
| `DATE` | Data (YYYY-MM-DD) |
| `DATETIME` | Data e hora |
| `TIMESTAMP` | Data e hora com consciência de fuso horário |

---

## 3. Chaves e Constraints

**Primary Key** identifica de forma única cada registro na tabela. Duas linhas não podem ter o mesmo valor. `AUTO_INCREMENT` faz o MySQL gerar o valor automaticamente.

```sql
id INT AUTO_INCREMENT PRIMARY KEY
```

**Foreign Key** referencia a primary key de outra tabela, criando um relacionamento entre elas. Garante integridade referencial, impedindo registros órfãos.

```sql
CREATE TABLE orders (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total   DECIMAL(10,2),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

**Unique** garante que duas linhas não tenham o mesmo valor em uma coluna sem torná-la a chave primária.

```sql
email VARCHAR(150) NOT NULL UNIQUE
```

**NOT NULL** garante que uma coluna não possa ficar vazia.

**DEFAULT** define um valor padrão quando nenhum valor é fornecido no insert.

```sql
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
```

---

## 4. CRUD

CRUD representa as quatro operações fundamentais em qualquer banco de dados: Create, Read, Update e Delete.

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

Sempre utilizar `WHERE` no UPDATE. Sem ele, todas as linhas da tabela são atualizadas.

**Delete — DELETE**

```sql
DELETE FROM users WHERE id = 1;
```

Sempre utilizar `WHERE` no DELETE. Sem ele, todas as linhas da tabela são deletadas.

---

## 5. Filtros e Operadores

```sql
WHERE age > 20
WHERE age >= 18 AND age <= 30
WHERE name = 'Eduardo' OR name = 'Ana'
WHERE name LIKE 'Edu%'       -- começa com Edu
WHERE name LIKE '%uardo'     -- termina com uardo
WHERE age IN (18, 25, 30)
WHERE age BETWEEN 18 AND 30
WHERE email IS NULL
WHERE email IS NOT NULL
```

---

## 6. Funções de Agregação

```sql
SELECT COUNT(*) FROM users;
SELECT AVG(age) FROM users;
SELECT SUM(total) FROM orders;
SELECT MAX(age) FROM users;
SELECT MIN(age) FROM users;
SELECT age, COUNT(*) FROM users GROUP BY age;
SELECT age, COUNT(*) FROM users GROUP BY age HAVING COUNT(*) > 1;
```

`GROUP BY` agrupa linhas com o mesmo valor. `HAVING` filtra após o agrupamento, ao contrário do `WHERE` que filtra antes.

---

## 7. Joins

Joins combinam linhas de duas ou mais tabelas com base em uma coluna relacionada.

```sql
-- INNER JOIN: apenas linhas que correspondem em ambas as tabelas
SELECT users.name, orders.total
FROM users
INNER JOIN orders ON users.id = orders.user_id;

-- LEFT JOIN: todas as linhas da tabela esquerda, correspondências da direita
SELECT users.name, orders.total
FROM users
LEFT JOIN orders ON users.id = orders.user_id;

-- RIGHT JOIN: todas as linhas da tabela direita, correspondências da esquerda
SELECT users.name, orders.total
FROM users
RIGHT JOIN orders ON users.id = orders.user_id;
```

---

## 8. Índices

Índices aceleram consultas em colunas frequentemente pesquisadas. Funcionam como o índice de um livro: em vez de ler cada linha, o MySQL pula diretamente para as relevantes.

```sql
CREATE INDEX idx_email ON users(email);
CREATE INDEX idx_name_age ON users(name, age);
DROP INDEX idx_email ON users;
```

Chaves primárias e colunas únicas já possuem índice automaticamente. Adicionar índices em outras colunas frequentemente consultadas melhora a performance, mas aumenta o armazenamento e desacelera inserts e updates.

---

## 9. Transações

Uma transação agrupa múltiplas operações em uma única unidade atômica. Ou todas as operações são aplicadas ou nenhuma delas é.

```sql
START TRANSACTION;

UPDATE accounts SET balance = balance - 100 WHERE id = 1;
UPDATE accounts SET balance = balance + 100 WHERE id = 2;

COMMIT;    -- aplica todas as alterações
ROLLBACK;  -- desfaz todas as alterações caso algo dê errado
```

---

## 10. Comandos Úteis

```sql
SHOW DATABASES;
SHOW TABLES;
DESCRIBE users;
SHOW CREATE TABLE users;
SHOW INDEXES FROM users;
```

---