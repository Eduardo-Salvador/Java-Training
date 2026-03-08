<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# JDBC

JDBC (Java Database Connectivity) é uma biblioteca padrão do Java para conectar e executar operações em bancos de dados relacionais. Fornece uma API unificada que funciona independentemente do banco de dados utilizado, ou seja, a mesma estrutura de código funciona para MySQL, PostgreSQL, Oracle e outros. Apenas o driver e a URL de conexão mudam.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

</div>

---

## Arquitetura

```
conn        Factory de conexão
domain      Classes de modelo de dados
service     Regras de negócio antes de chegar na camada repository
repository  Classes que interagem diretamente com o banco de dados
```

---

## 1. Dependência Maven

O driver do banco de dados deve ser adicionado ao `pom.xml`. O driver é a biblioteca que traduz as chamadas Java para o protocolo específico do banco. Sem ele, a JVM não sabe como se comunicar com o banco de dados.

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.6.0</version>
    <scope>compile</scope>
</dependency>
```

As dependências baixadas ficam em `~/.m2/repository` e visíveis na IDE em External Libraries.

---

## 2. Conexão

`DriverManager` é o ponto de entrada para obter uma `Connection`. A URL segue o formato `jdbc:vendor://host:porta/database`. Toda conexão deve ser fechada após o uso, caso contrário a aplicação vaza conexões com o banco. O uso de try-with-resources garante que a conexão seja fechada mesmo que uma exceção seja lançada.

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

## 3. Lombok e Log4J2

**Lombok** é um processador de anotações em tempo de compilação que gera código repetitivo como getters, setters, equals, hashCode, toString e builders. Não impacta a performance em runtime. O código gerado existe no bytecode mas não no arquivo fonte. A IDE precisa do plugin do Lombok para reconhecer os métodos gerados.

```java
@Value
@Builder
public class Producer {
    Integer id;
    String name;
}
```

`@Value` torna todos os campos private final. `@Builder` gera a classe interna builder. Métodos escritos manualmente coexistem com as anotações do Lombok. Se um método for escrito manualmente, o Lombok não o gera.

**Log4J2** é uma biblioteca de logging que substitui o `System.out.println`. Requer um arquivo XML de configuração em `src/main/resources`. Os níveis de log permitem controlar o que é registrado dependendo do ambiente.

```java
log.info("linhas afetadas {}", rowsAffected);
log.debug("detalhe para depuração");
log.warn("possível problema");
log.error("algo deu errado", e);
log.trace("rastreamento detalhado");
```

Em conformidade com a LGPD, os logs nunca devem conter informações pessoais identificáveis como CPF, senhas ou nomes completos.

---

## 4. Statement

`Statement` é o objeto básico para enviar SQL fixo ao banco de dados. Não aceita parâmetros e é vulnerável a SQL Injection. É adequado apenas para queries onde nenhuma entrada externa está envolvida.

`executeUpdate` é usado para INSERT, UPDATE e DELETE e retorna o número de linhas afetadas. `executeQuery` é usado para SELECT e retorna um `ResultSet`.

```java
try (Connection conn = ConnectionFactory.getConnection();
     Statement stmt = conn.createStatement()) {
    int rowsAffected = stmt.executeUpdate(sql);
    log.info("linhas afetadas {}", rowsAffected);
} catch (SQLException e) {
    log.error("Erro {}", e);
}
```

---

## 5. ResultSet

`ResultSet` é o objeto que contém o resultado de uma query SELECT. Começa posicionado antes da primeira linha. Cada chamada a `rs.next()` avança o cursor para a próxima linha e retorna true enquanto existirem linhas. Os valores são lidos pelo nome da coluna ou pelo índice.

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

`Optional` pode ser utilizado quando uma query deve retornar zero ou um resultado, tornando o tratamento de nulo explícito.

---

## 6. ResultSet Scrollable

Por padrão um `ResultSet` é somente para frente. Criando o statement com `TYPE_SCROLL_INSENSITIVE` é possível navegar em qualquer direção. `INSENSITIVE` significa que o ResultSet não reflete alterações feitas no banco após a execução da query.

```java
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_UPDATABLE
);
```

Métodos de navegação disponíveis com scroll:

```java
rs.last();          // move para a última linha
rs.first();         // move para a primeira linha
rs.absolute(2);     // move para a linha 2
rs.relative(-1);    // volta uma linha
rs.previous();      // move para a linha anterior
rs.getRow();        // retorna o número da linha atual
rs.isFirst();
rs.isLast();
rs.isBeforeFirst();
rs.isAfterLast();
```

Com `CONCUR_UPDATABLE`, o próprio ResultSet pode modificar o banco sem emitir uma nova query:

```java
rs.updateString("name", rs.getString("name").toUpperCase());
rs.updateRow();        // aplica a atualização
rs.cancelRowUpdates(); // cancela antes de updateRow ser chamado

rs.moveToInsertRow(); // move o cursor para uma nova linha temporária
rs.updateString("name", name);
rs.insertRow();        // insere a nova linha

rs.deleteRow();        // deleta a linha atual
```

---

## 7. ResultSetMetaData

`ResultSetMetaData` fornece informações estruturais sobre as colunas de um result set: nome da tabela, nomes das colunas, tipos e tamanhos.

```java
ResultSetMetaData meta = rs.getMetaData();
int columnCount = meta.getColumnCount();
for (int i = 1; i <= columnCount; i++) {
    log.info("Tabela: {}", meta.getTableName(i));
    log.info("Coluna: {}", meta.getColumnName(i));
    log.info("Tipo: {}", meta.getColumnTypeName(i));
    log.info("Tamanho: {}", meta.getColumnDisplaySize(i));
}
```

---

## 8. DatabaseMetaData

`DatabaseMetaData` fornece informações sobre as capacidades do driver e do próprio banco de dados. É utilizado para verificar quais tipos de ResultSet e modos de concorrência o driver suporta antes de utilizá-los.

```java
DatabaseMetaData meta = conn.getMetaData();
if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
    log.info("Suporta TYPE_SCROLL_INSENSITIVE");
}
```

---

## 9. PreparedStatement

`PreparedStatement` pré-compila o SQL antes da execução. Os parâmetros são passados usando `?` como placeholder e definidos individualmente. Essa abordagem protege contra SQL Injection porque a entrada do usuário nunca é concatenada diretamente na string da query e é sempre tratada como dado, nunca como código.

```java
String sql = "SELECT * FROM store.producer WHERE name LIKE ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, "%" + name + "%");
ResultSet rs = ps.executeQuery();
```

O padrão `%%%s%%` usado com `String.format` produz `%value%` porque `%%` representa um sinal de porcentagem literal em strings de formato. Com `PreparedStatement` o wildcard é definido diretamente: `"%" + name + "%"`.

`PreparedStatement` realiza todas as operações de CRUD e deve ser preferido em relação ao `Statement` sempre que entrada do usuário estiver envolvida.

---

## 10. SQL Injection

SQL Injection é uma vulnerabilidade de segurança onde código SQL malicioso é injetado através da entrada do usuário em uma query. Um exemplo clássico é o bypass de login onde digitar `' OR '1'='1` como senha torna a cláusula WHERE sempre verdadeira. Em casos mais destrutivos, um atacante pode injetar `'; DROP TABLE users;` e deletar uma tabela inteira. `PreparedStatement` previne isso tratando todos os parâmetros como valores literais, nunca como sintaxe SQL.

---

## 11. CallableStatement

`CallableStatement` é uma versão especializada do `PreparedStatement` usada para executar stored procedures e functions armazenadas no banco de dados.

Stored procedures são blocos SQL pré-definidos e armazenados no banco. Podem executar qualquer operação DML e podem ou não retornar valores. Functions sempre retornam um valor e podem ser chamadas dentro de queries SQL.

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

| Tipo | Retorna | Uso principal | Chamada |
|------|---------|---------------|---------|
| Stored Procedure | Não obrigatório | Operações e lógica | `CALL sp_name(...)` |
| Function | Sempre | Cálculo e retorno | `SELECT fn_name(...)` |

---

## 12. Comparação de Statements

| Tipo | Propósito | Aceita `?` | Seguro contra SQL Injection |
|------|-----------|------------|------------------------------|
| `Statement` | SQL fixo | Não | Não |
| `PreparedStatement` | SQL parametrizado | Sim (entrada) | Sim |
| `CallableStatement` | Stored procedures | Sim (entrada e saída) | Sim |

---

## 13. RowSet

`RowSet` é uma versão aprimorada do `ResultSet`. Estende `ResultSet` mas adiciona suporte a serialização, transferência via rede e scrollabilidade e atualizabilidade nativas.

**JdbcRowSet** mantém uma conexão ativa com o banco, similar a um `ResultSet` mas com API mais simples:

```java
JdbcRowSet jrs = ConnectionFactory.getJdbcRowSet();
jrs.setCommand("SELECT * FROM store.producer WHERE name LIKE ?");
jrs.setString(1, "%" + name + "%");
jrs.execute();
while (jrs.next()) {
    // leitura das linhas
}
```

`RowSetListener` permite reagir a eventos no RowSet sem precisar verificar manualmente. Três eventos estão disponíveis: `rowSetChanged` quando o comando é executado, `rowChanged` quando uma linha é inserida, atualizada ou deletada, e `cursorMoved` quando a posição do cursor muda.

**CachedRowSet** desconecta após carregar os dados em memória. As operações são realizadas offline e as alterações são sincronizadas de volta ao banco com `acceptChanges()`. É útil para passar dados entre camadas sem manter uma conexão aberta, mas requer cuidado com conflitos de dados.

---

## 14. Transações

Uma transação agrupa múltiplas operações em uma única unidade atômica. Ou todas são aplicadas ou nenhuma é. Por padrão o JDBC faz auto-commit de cada statement. Definir `autoCommit` como false coloca todas as operações subsequentes dentro de uma transação até que `commit()` ou `rollback()` seja chamado explicitamente.

```java
conn.setAutoCommit(false);
try {
    // múltiplas operações
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
}
```

Transações são essenciais quando múltiplas escritas precisam ter sucesso juntas, como transferir saldo entre duas contas ou inserir registros relacionados em tabelas diferentes.

---

## 15. Exercício

### 15.1. LibraryManager

Implementação de um CRUD completo via CLI para gerenciamento de uma biblioteca. A tabela `books` armazena id, title, author, year e status de disponibilidade. `DatabaseConnection` centraliza a conexão usando `DriverManager`. `BookRepository` implementa as cinco operações usando `PreparedStatement`: `create`, `findAll`, `findById` retornando `Optional`, `update` e `delete`. A entidade `Book` usa o padrão Builder. `LibraryMenu` executa um loop lendo a entrada do usuário via `Scanner` e despachando para o método correspondente do repositório, imprimindo os resultados no terminal.

**Conceitos chave:** `Connection`, `PreparedStatement`, `ResultSet`, `Optional`, Builder com JDBC, arquitetura em camadas, `Scanner` para entrada CLI, prevenção de SQL Injection.

---

## 16. Resultados

- JDBC como camada padrão entre Java e bancos de dados relacionais
- Gerenciamento do ciclo de vida da `Connection` com try-with-resources
- `Statement` para SQL fixo e `PreparedStatement` para queries parametrizadas
- Prevenção de SQL Injection através do binding de parâmetros no `PreparedStatement`
- Navegação do cursor do `ResultSet` e extração de dados por nome de coluna
- `ResultSet` scrollable e atualizável com `TYPE_SCROLL_INSENSITIVE`
- `ResultSetMetaData` para inspecionar a estrutura de tabelas e colunas em runtime
- `DatabaseMetaData` para consultar as capacidades do driver
- `CallableStatement` para stored procedures e functions
- `RowSet` como alternativa portável e serializável ao `ResultSet`
- `RowSetListener` para reações orientadas a eventos em alterações de dados
- `CachedRowSet` para manipulação offline desconectada de dados
- Transações com `setAutoCommit(false)`, `commit()` e `rollback()`
- Lombok e Log4J2 como ferramentas de suporte para entidades e logging mais limpos

---