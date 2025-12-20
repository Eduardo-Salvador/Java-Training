# Lambdas

---

## Visão Geral
Este módulo contém um conjunto de exercícios independentes que demonstram o uso de Lambdas em Java e das principais interfaces funcionais do pacote `java.util.function`.  
Os exercícios estão organizados em três categorias:

- **Consumer**
- **Function**
- **Predicate**

Cada categoria contém:

- Uma classe **Domain**, que modela a entidade usada no exercício
- Uma classe **Application**, com o método *main* demonstrando o uso de lambdas
- Um arquivo **ProblemQuestion.txt** com a descrição original do problema

---

## Estrutura de Pastas

### 1. Lambdas.Consumer
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

### 2. Lambdas.Function
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

### 3. Lambdas.Predicate
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

## Propósito
- Introduzir recursos de programação funcional em Java  
- Mostrar casos reais de uso de Lambdas em coleções  
- Demonstrar as principais interfaces funcionais usadas em Java corporativo  

---

## Arquivos Incluídos
- Classes **Domain/**
- Classes **Application/**
- Arquivo **ProblemQuestion.txt**

---

## Como Executar
1. Navegue até qualquer pasta **Application**.  
2. Execute a classe **Main**.  
3. Observe a saída no console, ilustrando as operações com lambdas.  

---

## Requisitos
- **Java 17+**
- Compreensão básica de coleções e POO

---

## Notas
- Sintaxe limpa de lambdas  
- Uso adequado de interfaces funcionais  
- Melhor controle de pipelines de processamento de dados em Java  

---