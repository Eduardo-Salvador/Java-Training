<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)](https://shields.io/)

# Method References

Este módulo contém um conjunto de exercícios independentes que demonstram o uso de Method References em Java, uma sintaxe concisa para referenciar métodos existentes no lugar de expressões lambda.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Method Reference é uma forma abreviada de usar um método já existente no lugar de uma expressão lambda. 

Em vez de escrever uma lambda como `x -> obj.metodo(x)`, você pode simplesmente usar `obj::metodo`.

Isso reduz código desnecessário e melhora a legibilidade quando a lambda apenas chama um método já existente, tornando o código mais limpo e expressivo!

---

## Arquitetura:

Os exercícios estão organizados em cinco categorias:

- **E1 - Sorting with Method Reference**
- **E2 - Type Conversion with Method Reference**
- **E3 - Utility Methods with Method Reference**
- **E4 - Instance Method Reference**
- **E5 - Constructor Reference**

Cada categoria contém:

- Uma classe **Domain**, que modela a entidade usada no exercício
- Uma classe **Application**, com o método *main* demonstrando o uso de method references
- Um arquivo **ProblemQuestion.txt** com a descrição original do problema

---

## 1. O que são Method References:

Method References são uma forma concisa de referenciar métodos sem executá-los, introduzida no Java 8 junto com expressões lambda. É essencialmente uma **sintaxe simplificada para lambdas que apenas chamam um método existente**.

### 1.1. Sintaxe Básica:

A sintaxe usa o **operador `::` (dois pontos duplos)**:
```java
NomeClasse::nomeMetodo
```

### 1.2. Tipos de Method Reference:

**1.2.1. Referência a método estático**
```java
// Lambda
Function<String, Integer> parser = s -> Integer.parseInt(s);

// Method Reference
Function<String, Integer> parser = Integer::parseInt;
```

**1.2.2. Referência a método de instância de um objeto específico**
```java
String texto = "Olá";

// Lambda
Supplier<Integer> tamanho = () -> texto.length();

// Method Reference
Supplier<Integer> tamanho = texto::length;
```

**1.2.3. Referência a método de instância de um tipo arbitrário**
```java
// Lambda
Function<String, String> upperCase = s -> s.toUpperCase();

// Method Reference
Function<String, String> upperCase = String::toUpperCase;
```

**1.2.4. Referência a construtor**
```java
// Lambda
Supplier<ArrayList<String>> lista = () -> new ArrayList<>();

// Method Reference
Supplier<ArrayList<String>> lista = ArrayList::new;
```

### 1.3. Vantagens das Method References:

**Código mais limpo** - menos verbosidade que lambdas

**Reutilização** - aproveitamento de métodos já existentes

**Legibilidade** - intenção do código mais clara

**Manutenibilidade** - mudanças no método são refletidas automaticamente

### 1.4. Compatibilidade de Assinatura:

Para um method reference funcionar, é necessário que:

1. **Número de parâmetros coincida**
2. **Tipos dos parâmetros sejam compatíveis**
3. **Tipo de retorno seja compatível**

> **Em resumo:** Method References **NÃO estão limitados às interfaces do Java**. Funcionam com qualquer interface funcional (incluindo as suas), desde que a assinatura do método seja compatível com o método abstrato da interface!

---

## 2. Exercícios:

### 2.1. MethodReference.E1 - Sorting with Method Reference
Demonstra method references com métodos de instância, métodos estáticos e utilitários de **Comparator**.

**Conceitos-chave:**
- Referência a método de instância de uma classe (`Product::getName`)
- Referência a método estático (`Product::compareByPrice`)
- Combinação de method reference com `Comparator.comparing`
- Ordenação de listas usando method references
- Impressão de elementos usando `System.out::println`

**Domain Class: Product**
- Atributos: `name`, `price`
- Métodos:
  - `getName()` e `getPrice()`
  - `static compareByPrice(Product p1, Product p2)` — usado como comparador
  - `toString()` sobrescrito para impressão formatada

**Application:**
- Cria uma lista de produtos
- Ordena alfabeticamente ignorando caixa usando `Comparator.comparing(Product::getName, String::compareToIgnoreCase)`
- Imprime a lista com `System.out::println`
- Ordena pelo preço usando `Product::compareByPrice`
- Imprime novamente usando method reference

---

### 2.2. MethodReference.E2 - Type Conversion with Method Reference
Demonstra method references aplicados a métodos de **conversão** e uso de **Function**.

**Conceitos-chave:**
- Conversão de string para inteiro com `Integer::parseInt`
- Conversão de inteiro para string com `String::valueOf`
- Transformações aplicadas com `Function<T, R>`
- Iteração de coleções aplicando funções referenciadas

**Domain Class: N/A**

**Application:**
- Cria uma lista de strings contendo números
- Converte todas para inteiros usando `Integer::parseInt`
- Imprime os valores com `System.out::println`
- Converte os inteiros de volta para string usando `String::valueOf`
- Imprime a lista atualizada

---

### 2.3. MethodReference.E3 - Utility Methods with Method Reference
Demonstra o uso de method references para **métodos utilitários personalizados**.

**Conceitos-chave:**
- Referência a método estático personalizado (`Utility::printDecorated`)
- Uso de method reference com `forEach`
- Separação clara entre lógica de domínio e lógica de apresentação

**Domain Class: Utility**
- Método estático: `printDecorated(String s)` — responsável por imprimir strings com formatação personalizada

**Application:**
- Cria uma lista de nomes de cidades
- Imprime a lista usando `System.out::println`
- Imprime novamente usando o method reference `Utility::printDecorated`

---

### 2.4. MethodReference.E4 - Instance Method Reference
Demonstra referência a método de **instância de um objeto específico**.

**Conceitos-chave:**
- Referência a método de instância (`objeto::métodoDeInstância`)
- Uso de `Consumer<T>` com method reference
- Composição de method references com transformações
- Reutilização de referências de método

**Domain Class: MessagePrinter**
- Método: `printMessage(String message)` — imprime a mensagem com formatação adicional

**Application:**
- Cria uma instância de `MessagePrinter`
- Cria uma lista de mensagens
- Utiliza `Consumer<String>` com method reference `messagePrinter::printMessage`
- Itera pela lista aplicando o consumer
- Combina method reference com `String::toUpperCase` para transformação
- Imprime mensagens em letras maiúsculas usando composição

---

### 2.5. MethodReference.E5 - Constructor Reference
Demonstra referência a **construtor** usando `Classe::new`.

**Conceitos-chave:**
- Referência a construtor (`User::new`)
- Uso de `BiFunction<T, U, R>` com constructor reference
- Criação de interface funcional personalizada (`UserFactory`)
- Construção dinâmica de objetos a partir de múltiplas fontes de dados

**Domain Class: User**
- Atributos: `name`, `age`
- Construtor: `User(String name, Integer age)`

**Domain Class: UserFactory**
- Interface funcional personalizada
- Método abstrato: `User create(String name, Integer age)`

**Application:**
- Cria listas separadas de nomes e idades
- Utiliza `BiFunction<String, Integer, User>` com `User::new`
- Combina dados das listas para criar objetos `User`
- Demonstra o uso de interface funcional personalizada
- Utiliza `UserFactory` com a mesma constructor reference
- Imprime todos os usuários criados

---

## 3. Objetivos Alcançados

**Domínio completo dos quatro tipos de method reference**
- Referência a método estático
- Referência a método de instância de objeto específico
- Referência a método de instância de tipo arbitrário
- Referência a construtor

**Aplicação prática de method references**
- Substituição de lambdas simples por method references
- Código mais limpo e legível
- Reutilização de métodos existentes

**Integração com interfaces funcionais**
- Uso com `Consumer`, `Function`, `BiFunction`
- Criação de interfaces funcionais personalizadas
- Composição e encadeamento de operações

**Técnicas avançadas**
- Ordenação com `Comparator` e method references
- Transformação de dados com conversões de tipo
- Construção dinâmica de objetos
- Separação de responsabilidades (domínio vs apresentação)

---