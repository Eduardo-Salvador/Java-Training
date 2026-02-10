<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Streams

Este módulo contém uma série abrangente de exercícios demonstrando o uso de Streams em Java, uma funcionalidade introduzida no Java 8 que permite processar coleções de dados de forma declarativa e funcional.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Streams representam uma **sequência de elementos que suportam operações sequenciais e paralelas**. Em vez de escrever loops explícitos, você **descreve o que quer fazer com os dados** de forma mais legível e funcional.

**Importante:** Streams **não armazenam dados** - eles operam sobre uma fonte de dados (como coleções, arrays, arquivos). Uma Stream só pode ser percorrida uma vez, e após a operação terminal ela é fechada.

---

## Arquitetura

O projeto está organizado da seguinte forma:

- **Domain**: Contém as classes de modelo (`Product`, `Student`, `Order`, `Employee`, `Sale`, `Customer`, etc.)
- **Application**: Contém as classes `Main` com implementações práticas de cada exercício
- **13 Exercícios Progressivos**: Do básico ao avançado, cobrindo todas as operações de Streams

---

## 1. O que são Streams

Streams são uma funcionalidade que permite processar coleções de dados de forma declarativa. Eles constroem um **pipeline de operações** que só executam quando você "pede o resultado" (operação terminal).

### 1.1. Pipeline de Streams

```
1. CRIAR Stream → 2. INTERMEDIÁRIAS (opcional) → 3. TERMINAL (obrigatório)
```

```java
lista.stream()                          // Criar
    .filter(n -> n > 5)                 // Intermediária
    .map(n -> n * 2)                    // Intermediária
    .collect(Collectors.toList());      // Terminal
```

### 1.2. Criando Streams

**A partir de coleções:**
```java
List<String> nomes = Arrays.asList("Ana", "Bruno", "Carlos");
Stream<String> stream = nomes.stream();
```

**A partir de valores:**
```java
Stream<Integer> numeros = Stream.of(1, 2, 3, 4, 5);
```

**Streams infinitos:**
```java
Stream<Integer> infinito = Stream.iterate(0, n -> n + 2);
Stream<Double> randoms = Stream.generate(Math::random);
```

**Ranges numéricos:**
```java
IntStream.range(1, 10);         // 1 até 9
IntStream.rangeClosed(1, 10);   // 1 até 10
```

### 1.3. Operações Intermediárias

Retornam outro Stream e são **lazy** (só executam quando há operação terminal):

- `filter(predicate)` — filtra elementos
- `map(function)` — transforma elementos
- `flatMap(function)` — "achata" estruturas aninhadas
- `sorted()` — ordena elementos
- `sorted(comparator)` — ordena com critério personalizado
- `distinct()` — remove duplicatas
- `limit(n)` — limita quantidade
- `skip(n)` — pula elementos
- `peek(consumer)` — executa ação sem modificar stream

### 1.4. Operações Terminais

Encerram o Stream e produzem um resultado:

**Coleção:**
- `collect(collector)` — coleta em estrutura de dados
- `toArray()` — converte para array

**Redução:**
- `reduce(accumulator)` — combina elementos em um valor
- `count()` — retorna quantidade
- `sum()`, `average()`, `min()`, `max()` — operações numéricas

**Busca:**
- `findFirst()` — retorna primeiro elemento
- `findAny()` — retorna qualquer elemento

**Verificação:**
- `allMatch(predicate)` — todos atendem condição?
- `anyMatch(predicate)` — algum atende condição?
- `noneMatch(predicate)` — nenhum atende condição?

**Ação:**
- `forEach(consumer)` — executa ação para cada elemento

### 1.5. Vantagens da Lazy Evaluation

1. **Performance** — não processa dados desnecessários
2. **Otimização** — pode combinar operações
3. **Short-circuit** — para assim que encontra resultado em operações como `findFirst()`

### 1.6. Benefícios de Usar Streams

**Código mais legível** — expressivo e declarativo

**Menos código boilerplate** — elimina loops verbosos

**Programação funcional** — incentiva imutabilidade

**Paralelização fácil** — use `parallelStream()`

**Otimização automática** — lazy evaluation e short-circuit

---

## 2. Tabela de Referência Rápida

### 2.1. Operações Intermediárias

| Operação | Assinatura | Uso |
|----------|-----------|-----|
| `filter()` | `Predicate<T>` | Filtra elementos |
| `map()` | `Function<T,R>` | Transforma elementos |
| `flatMap()` | `Function<T,Stream<R>>` | Achata estruturas |
| `sorted()` | - ou `Comparator<T>` | Ordena |
| `distinct()` | - | Remove duplicatas |
| `limit()` | `long` | Limita quantidade |
| `skip()` | `long` | Pula elementos |
| `peek()` | `Consumer<T>` | Debug/efeitos |

### 2.2. Operações Terminais

| Operação | Retorno | Uso |
|----------|---------|-----|
| `collect()` | Coleção/Map/etc | Coleta resultado |
| `reduce()` | `Optional<T>` ou `T` | Combina elementos |
| `forEach()` | `void` | Executa ação |
| `count()` | `long` | Conta elementos |
| `findFirst()` | `Optional<T>` | Primeiro elemento |
| `findAny()` | `Optional<T>` | Qualquer elemento |
| `anyMatch()` | `boolean` | Algum atende? |
| `allMatch()` | `boolean` | Todos atendem? |
| `noneMatch()` | `boolean` | Nenhum atende? |
| `min()`/`max()` | `Optional<T>` | Mínimo/máximo |
| `sum()`/`average()` | número | Soma/média (primitivos) |

### 2.3. Collectors Principais

| Collector | Resultado | Uso |
|-----------|-----------|-----|
| `toList()` | `List<T>` | Coleta em lista |
| `toSet()` | `Set<T>` | Coleta em conjunto |
| `toMap()` | `Map<K,V>` | Cria mapa |
| `groupingBy()` | `Map<K,List<V>>` | Agrupa elementos |
| `counting()` | `Long` | Conta elementos |
| `summingDouble()` | `Double` | Soma valores |
| `averagingDouble()` | `Double` | Calcula média |
| `summarizingDouble()` | `DoubleSummaryStatistics` | Estatísticas |
| `joining()` | `String` | Concatena strings |
| `mapping()` | Variado | Transforma antes de coletar |

---

## 3. Exercícios

### 3.1. Operações Básicas (filter, map, collect)

**Filtragem de Produtos**

Cria uma classe `Product` e filtra produtos com preço acima de 100.

**Conceitos-chave:**
- Criação de Stream a partir de lista
- `filter()` com predicado
- `map()` para extrair atributos específicos
- `collect(Collectors.toList())` para coletar resultado

**Classe Product:**
- Atributos: `name`, `price`, `category`
- Lista com 8 produtos de categorias variadas

---

### 3.2. Sorted e Distinct

**Ordenação de Estudantes**

Remove duplicatas e ordena estudantes por nota.

**Conceitos-chave:**
- `distinct()` para remover duplicados
- `sorted()` com `Comparator.comparing()`
- Ordenação descendente com `reversed()`

**Classe Student:**
- Atributos: `name`, `grade`
- Lista com 10 estudantes (alguns nomes duplicados)

---

### 3.3. Finding e Matching

**Análise de Pedidos**

Demonstra operações de busca e verificação.

**Conceitos-chave:**
- `findFirst()` — primeiro elemento que atende condição
- `findAny()` — qualquer elemento (útil em paralelo)
- `anyMatch()` — existe algum?
- `allMatch()` — todos atendem?
- `noneMatch()` — nenhum atende?
- Trabalhar com `Optional<T>`

**Classe Order:**
- Atributos: `id`, `customerName`, `totalValue`, `status`
- Status: PENDING, SHIPPED, DELIVERED

---

### 3.4. Reduce

**Cálculo de Vendas**

Combina elementos usando `reduce()`.

**Conceitos-chave:**
- `reduce()` com valor inicial
- `reduce()` sem valor inicial (retorna Optional)
- Usar method reference (`Double::sum`, `Integer::max`)
- Encadeamento de operações

**Classe Sale:**
- Atributos: `product`, `quantity`, `unitPrice`
- Método: `getTotalValue()` retorna `quantity * unitPrice`

---

### 3.5. FlatMap

**Turmas e Alunos**

"Achata" listas aninhadas em uma única lista.

**Conceitos-chave:**
- `flatMap()` para achatar estruturas
- Diferença entre `map()` e `flatMap()`
- Combinar com `filter()` e `sorted()`

**Classe Classroom:**
- Atributos: `className`, `students` (List<String>)
- 4 turmas, cada uma com 3-5 alunos

---

### 3.6. Gerando Streams (generate, iterate, range)

**Geração de Números**

Cria Streams sem coleções pré-existentes.

**Conceitos-chave:**
- `Stream.generate()` — geração infinita com Supplier
- `Stream.iterate()` — sequências baseadas no anterior
- `IntStream.range()` e `rangeClosed()`
- Necessidade de `limit()` para streams infinitos
- Verificação de números primos

---

### 3.7. Collectors.summarizingDouble/Int/Long

**Estatísticas de Transações**

Coleta estatísticas resumidas em um único objeto.

**Conceitos-chave:**
- `summarizingDouble()` retorna `DoubleSummaryStatistics`
- Métodos disponíveis: `getCount()`, `getSum()`, `getMin()`, `getMax()`, `getAverage()`
- Combinar com `filter()` para estatísticas condicionais

**Classe Transaction:**
- Atributos: `type` (DEPOSIT/WITHDRAWAL), `amount`

---

### 3.8. Collectors.groupingBy (Básico)

**Agrupamento de Funcionários**

Agrupa elementos em um Map.

**Conceitos-chave:**
- `groupingBy()` retorna `Map<K, List<V>>`
- Agrupamento por atributo
- `groupingBy()` com `counting()` — conta elementos por grupo

**Classe Employee:**
- Atributos: `name`, `department`, `salary`
- 12 funcionários de 4 departamentos

---

### 3.9. Collectors.groupingBy (Avançado com downstream)

**Análise de Produtos por Categoria**

Usa downstream collectors para transformações nos grupos.

**Conceitos-chave:**
- `groupingBy()` com downstream collectors
- `summingDouble()` — soma valores por grupo
- `averagingDouble()` — média por grupo
- `summarizingDouble()` — estatísticas por grupo
- `mapping()` — extrai campos específicos

**Classe Product:**
- Atributos: `name`, `category`, `price`, `stock`
- 15 produtos de 3 categorias

---

### 3.10. Collectors.toMap

**Mapeamento de IDs de Estudantes**

Cria Maps personalizados a partir de Streams.

**Conceitos-chave:**
- `toMap(keyMapper, valueMapper)` — cria Map
- `toMap()` com merge function — resolve duplicatas
- Tipos de Map: HashMap, TreeMap, LinkedHashMap

**Classe Student:**
- Atributos: `id`, `name`, `course`

---

### 3.11. Agrupamento Multinível

**Agrupamento Multinível de Vendas**

Agrupa por múltiplos critérios aninhados.

**Conceitos-chave:**
- `groupingBy()` aninhado
- Map de Maps: `Map<String, Map<String, List<T>>>`
- Combinar com `counting()` e `summingDouble()`

**Classe Sale:**
- Atributos: `region`, `product`, `quantity`, `value`
- 20 vendas de diferentes regiões e produtos

---

### 3.12. Parallel Streams

**Comparação de Performance**

Processa elementos em paralelo usando múltiplos threads.

**Conceitos-chave:**
- `parallelStream()` vs `stream()`
- Quando usar: coleções grandes + operações pesadas
- Quando evitar: coleções pequenas, operações rápidas, ordem importante
- Thread-safety e estado compartilhado
- Medição de tempo de execução
- Speedup factor

**Classe Task:**
- Atributos: `id`, `processingTime`
- Método: `execute()` — simula trabalho com Thread.sleep()

---

### 2.13. Exercício Integrado Final

**Sistema de E-commerce**

Exercício completo integrando todos os conceitos.

**Conceitos-chave:**
- Todas as operações de Stream em um projeto real
- Relacionamento entre entidades (Customer ↔ Order)
- FlatMap para produtos
- GroupingBy multinível
- Estatísticas e agregações
- Matching e Finding
- ToMap para joins

**Classes:**

**Customer:**
- Atributos: `id`, `name`, `city`

**Order:**
- Atributos: `orderId`, `customerId`, `products`, `totalValue`, `status`
- Status: PENDING, PROCESSING, SHIPPED, DELIVERED

**Operações Realizadas:**

1. **FlatMap** — Extrai todos produtos únicos
2. **GroupingBy + Counting** — Conta pedidos por status
3. **GroupingBy + Summing** — Total de vendas por cidade (join com Customer)
4. **Filter + Map + Reduce** — Soma de pedidos DELIVERED
5. **Matching:**
  - anyMatch: pedidos > 1000
  - allMatch: todos têm produtos
  - noneMatch: sem nomes vazios
6. **Statistics** — Min, Max, Average dos valores
7. **ToMap** — customerId → customerName
8. **Multinível** — Cidade → Status → Pedidos

---

## 3. Resultados

**Domínio completo de criação de Streams**
- Streams a partir de coleções, valores, generate, iterate, ranges
- Quando usar cada método de criação

**Operações intermediárias**
- filter, map, flatMap, sorted, distinct, limit, skip
- Entendimento de lazy evaluation
- Encadeamento eficiente

**Operações terminais**
- collect, reduce, forEach, count, sum, min, max
- findFirst, findAny, anyMatch, allMatch, noneMatch
- Escolher a operação terminal apropriada

**Collectors avançados**
- groupingBy com downstream collectors
- toMap com merge functions
- summarizing para estatísticas
- Agrupamento multinível

**Programação funcional**
- Lambdas e method references
- Imutabilidade e side-effects
- Composição de funções

**Performance e paralelismo**
- Quando usar parallelStream()
- Thread-safety e estado compartilhado
- Medição e otimização

**Boas práticas**
- Código declarativo e legível
- Evitar side-effects em operações intermediárias
- Escolher estruturas de dados apropriadas
- Combinar Streams com Optional

**Aplicações do mundo real**
- Processamento de dados
- Relatórios e agregações
- Transformações complexas
- Análise de dados

---