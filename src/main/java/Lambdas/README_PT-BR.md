<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)](https://shields.io/)

# Lambdas

Este módulo contém um conjunto de exercícios independentes que demonstram o uso de Lambdas em Java e das principais interfaces funcionais do pacote `java.util.function`.  

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

A ideia é mostrar como que tratar código como dado, passando comportamentos de forma simples e direta, tornando o código muito mais enxuto e expressivo!

---

## Arquitetura:

Os exercícios estão organizados em três categorias:

- **Consumer**
- **Function**
- **Predicate**
- **Supplier**
- **Unary Operator**
- **Binary Operator**
- **BiConsumer**
- **BiFunction**
- **BiPredicate**

Cada categoria contém:

- Uma classe **Domain**, que modela a entidade usada no exercício
- Uma classe **Application**, com o método *main* demonstrando o uso de lambdas
- Um arquivo **ProblemQuestion.txt** com a descrição original do problema

---

## 1. O que são Lambdas:

Lambdas são funções anônimas (sem nome) que podem ser escritas de forma super concisa. São uma sintaxe curta para implementar métodos de interfaces funcionais.

**Substitui classes anônimas** de forma muito mais limpa e legível.

### 1.1 Principais usos:

**1. Com interfaces funcionais (Predicate, Consumer, Function, etc).**

2. Com **Streams** (processamento de coleções).

3. **Ordenação** customizada.

4. **Threads**.

### 1.2. Vantagens das Lambdas:

**Código mais limpo e legível** - menos boilerplate

**Menos linhas de código** - aumenta produtividade

**Facilita programação funcional** - passar comportamentos como parâmetros

**Trabalha bem com Streams** - processamento de dados eficiente

### 1.3. Limitações:

Só funciona com **interfaces funcionais** (1 método abstrato)

Não pode acessar variáveis locais não-finais

Pode dificultar debug em casos complexos

> **Em resumo:** Lambdas são a forma do Java permitir que você **trate código como dado**, passando comportamentos de forma simples e direta, tornando o código muito mais enxuto e expressivo!

---

## 2. Resumo de Operações e Sintaxe

### 2.1. Predicate\<T>
- `predicate.test(t)` - Testa uma condição e retorna boolean
- `predicate.and(other)` - Combina dois predicates com AND lógico
- `predicate.or(other)` - Combina dois predicates com OR lógico
- `predicate.negate()` - Inverte o resultado do predicate

### 2.2. BiPredicate\<T, U>
- `biPredicate.test(t, u)` - Testa uma condição com dois parâmetros
- `biPredicate.and(other)` - Combina com AND lógico
- `biPredicate.or(other)` - Combina com OR lógico
- `biPredicate.negate()` - Inverte o resultado

### 2.3. Consumer\<T>
- `consumer.accept(t)` - Executa uma ação sobre o parâmetro
- `consumer.andThen(after)` - Encadeia outro consumer para executar depois

### 2.4. BiConsumer\<T, U>
- `biConsumer.accept(t, u)` - Executa uma ação com dois parâmetros
- `biConsumer.andThen(after)` - Encadeia outro biConsumer

### 2.5. Function\<T, R>
- `function.apply(t)` - Transforma T em R
- `function.andThen(after)` - Encadeia outra function após esta
- `function.compose(before)` - Executa outra function antes desta
- `Function.identity()` - Retorna uma function que retorna o próprio argumento

### 2.6. BiFunction\<T, U, R>
- `biFunction.apply(t, u)` - Transforma T e U em R
- `biFunction.andThen(after)` - Encadeia uma function após esta

### 2.7. UnaryOperator\<T>
- `unaryOperator.apply(t)` - Transforma T em T (mesmo tipo)
- `unaryOperator.andThen(after)` - Encadeia outro operator
- `unaryOperator.compose(before)` - Executa outro operator antes
- `UnaryOperator.identity()` - Retorna um operator que retorna o próprio argumento

### 2.8. BinaryOperator\<T>
- `binaryOperator.apply(t1, t2)` - Combina dois T em T
- `BinaryOperator.minBy(comparator)` - Retorna o menor valor segundo o comparator
- `BinaryOperator.maxBy(comparator)` - Retorna o maior valor segundo o comparator

### 2.9. Supplier\<T>
- `supplier.get()` - Fornece/gera um valor do tipo T

---

## 3. Exercícios:

### 3.1. Lambdas.Consumer
Demonstra o uso da interface funcional **Consumer**.

**Conceitos-chave:**
- Processamento de listas com lambdas  
- Referências de método (`System.out::println`)  
- Encadeamento de consumers com `andThen`  
- Execução dinâmica de múltiplos consumers  

**Domain Class: Product**
- Atributos: `name`, `price`
- Método: `processProducts(List<Product>, Consumer<Product>)`

**Application:**
- Consumer padrão de impressão
- Consumer de desconto
- Consumer que converte o nome para letras maiúsculas
- Encadeamento de consumers
- Execução de uma lista de consumers

---

### 3.2. Lambdas.Function
Demonstra o uso de **Function<T, R>** e **Predicate**.

**Conceitos-chave:**
- Mapeamento de valores usando `Function`
- Filtragem usando `Predicate`
- Transformação de coleções (extração e conversão de nomes)
- Combinação de filtragem e transformação

**Domain Class: Product**
- Filtra produtos via `filterProducts(...)`
- Mapeia nomes de produtos via `mapProducts(...)`

**Application:**
- Filtragem por preço (maior que / menor que)
- Mapeamento de nomes para maiúsculas
- Aplicação de mapeamento após filtragem

---

### 3.3. Lambdas.Predicate
Demonstra o uso de **Predicate** para avaliação condicional.

**Conceitos-chave:**
- Filtragem de objetos com predicates personalizados
- Combinação de condições com `AND`
- Negação de condições com `predicate.negate()`

**Domain Class: Employee**
- Atributos: `name`, `age`, `salary`
- Método estático `filterEmployee(List<Employee>, Predicate<Employee>)` imprime os funcionários correspondentes

**Application:**
- Filtragem por salário, idade, iniciais do nome
- Condições compostas usando múltiplos critérios
- Negação de predicate para filtragem inversa

---

### 3.4. Lambdas.Supplier
Demonstra o uso da interface funcional **Supplier**.

**Conceitos-chave:**
- Geração de dados sob demanda (lazy evaluation)
- Criação de objetos com `Supplier`
- Geração de valores aleatórios e dinâmicos
- Uso de `Stream.generate()` para criar coleções

**Domain Class: Customer**
- Atributos: `customerId`, `name`, `registrationDate`
- Método: `generateCustomers(int count, Supplier<Customer>)`

**Application:**
- Supplier de IDs aleatórios
- Supplier de nomes sequenciais com AtomicInteger
- Supplier de datas atuais com `LocalDateTime.now()`
- Supplier de preços aleatórios

---

### 3.5. Lambdas.BiPredicate
Demonstra o uso de **BiPredicate<T, U>** para validação com dois parâmetros.

**Conceitos-chave:**
- Comparação entre objetos e valores de referência
- Validação com múltiplos critérios
- Filtragem baseada em condições duplas
- Uso em contextos de comparação

**Domain Class: Product**
- Atributos: `name`, `price`, `stock`
- Método: `compareProducts(List<Product>, Double threshold, BiPredicate<Product, Double>)`

**Application:**
- Validação de preço contra limite
- Comparação de estoque com valor mínimo
- Verificação de igualdade de preços
- BiPredicate entre dois produtos (comparação direta)

---

### 3.6. Lambdas.BiConsumer
Demonstra o uso de **BiConsumer<T, U>** para ações com dois parâmetros.

**Conceitos-chave:**
- Processamento de pares de valores
- Aplicação de operações em Maps (forEach)
- Atualização de objetos com valores adicionais

**Domain Class: Order**
- Atributos: `orderId`, `totalAmount`, `discount`
- Método: `processOrders(List<Order>, Double rate, BiConsumer<Order, Double>)`

**Application:**
- BiConsumer para aplicar desconto percentual
- BiConsumer para adicionar taxas
- BiConsumer para imprimir detalhes formatados

---

### 3.7. Lambdas.BiFunction
Demonstra o uso de **BiFunction<T, U, R>** para transformações com dois parâmetros.

**Conceitos-chave:**
- Cálculos combinando dois valores
- Transformação de dados com múltiplas entradas
- Retorno de resultado computado

**Domain Class: CartItem**
- Atributos: `productName`, `quantity`, `unitPrice`
- Método: `calculateTotal(List<CartItem>, Double taxRate, BiFunction<Double, Double, Double>)`

**Application:**
- BiFunction para cálculo de total com imposto
- BiFunction para aplicar taxa fixa
- BiFunction para aplicar desconto percentual
- Cálculo de preço com quantidade bônus

---

### 3.8. Lambdas.UnaryOperator
Demonstra o uso de **UnaryOperator<T>** para transformações do mesmo tipo.

**Conceitos-chave:**
- Transformação de valores mantendo o tipo
- Aplicação de ajustes e modificações
- Encadeamento de operações com `andThen` e `compose`
- Uso em pipelines de processamento

**Domain Class: Product**
- Atributos: `name`, `price`
- Método: `adjustPrices(List<Product>, UnaryOperator<Double>)`

**Application:**
- UnaryOperator para aumento percentual de preços
- UnaryOperator para adicionar valor fixo
- UnaryOperator para arredondamento
- Encadeamento: aumentar 10% e depois arredondar
- Uso de `compose()` para ordem reversa de operações

---

### 3.9. Lambdas.BinaryOperator
Demonstra o uso de **BinaryOperator<T>** para combinar dois valores do mesmo tipo.

**Conceitos-chave:**
- Operações de redução (soma, máximo, mínimo)
- Combinação de valores homogêneos
- Uso em operações de merge
- Integração com `Stream.reduce()`

**Domain Class: Warehouse**
- Atributos: `location`, `stock`
- Método: `mergeStock(List<Warehouse>, List<Warehouse>, BinaryOperator<Integer>)`

**Application:**
- BinaryOperator para soma de estoques
- BinaryOperator para máximo (`Math.max`)
- BinaryOperator para mínimo (`Math.min`)
- BinaryOperator para cálculo de média
- Mesclagem de inventários de múltiplos armazéns

---

## 4. Objetivos Alcançados

**Compreensão completa das principais interfaces funcionais do Java**
- Domínio de Predicate, Consumer, Function e Supplier
- Entendimento de suas variantes com dois parâmetros (Bi*)
- Conhecimento de operadores especializados (Unary/Binary)

**Aplicação prática de expressões lambda**
- Sintaxe de lambdas em diferentes contextos
- Uso de referências de método
- Criação de código mais conciso e legível

**Técnicas avançadas de programação funcional**
- Encadeamento de operações (`andThen`, `compose`)
- Combinação de predicates (`and`, `or`, `negate`)
- Redução de coleções com operadores binários
- Geração lazy de dados com Supplier

---