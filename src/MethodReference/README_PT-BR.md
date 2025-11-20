# Method Reference

## Visão Geral
Este módulo contém uma coleção de exercícios independentes que demonstram o uso de **Method References** em Java.

**Breve explicação:**  
Um *method reference* é uma forma abreviada de usar um método já existente no lugar de uma expressão lambda.  
Em vez de escrever uma lambda como `x -> obj.metodo(x)`, você pode simplesmente usar `obj::metodo`.  
Isso reduz código desnecessário e melhora a legibilidade quando a lambda apenas chama um método já existente.

Os exercícios seguem a mesma estrutura dos módulos anteriores, organizados nos pacotes **MethodReference.E1**, **E2** e **E3**.  
Cada exercício contém:
- Uma classe **Domain**, representando a entidade usada no exemplo
- Uma classe **Application**, demonstrando o uso de method references
- Um arquivo **ProblemQuestion.txt** com o enunciado original do exercício

---

## Estrutura de Pastas

### 1. MethodReference.E1
Demonstra method references com **métodos de instância**, **métodos estáticos** e utilitários de **Comparator**.

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
- Ordena alfabeticamente ignorando caixa usando  
  `Comparator.comparing(Product::getName, String::compareToIgnoreCase)`
- Imprime a lista com `System.out::println`
- Ordena pelo preço usando `Product::compareByPrice`
- Imprime novamente usando method reference

---

### 2. MethodReference.E2
Demonstra method references aplicados a **métodos de conversão** e uso de **Function**.

**Conceitos-chave:**
- Conversão de string para inteiro com `Integer::parseInt`
- Conversão de inteiro para string com `String::valueOf`
- Transformações aplicadas com `Function<T, R>`
- Iteração de coleções aplicando funções referenciadas

**Domain Class: Empty**
- Classe vazia usada apenas para manter o padrão estrutural

**Application:**
- Cria uma lista de strings contendo números
- Converte todas para inteiros usando `Integer::parseInt`
- Imprime os valores com `System.out::println`
- Converte os inteiros de volta para string usando `String::valueOf`
- Imprime a lista atualizada

---

### 3. MethodReference.E3
Demonstra o uso de method references para **métodos utilitários personalizados**.

**Conceitos-chave:**
- Referência a método estático personalizado (`Utility::printDecorated`)
- Uso de method reference com `forEach`
- Separação clara entre lógica de domínio e lógica de apresentação

**Domain Class: Utility**
- Método estático:  
  `printDecorated(String s)` — responsável por imprimir strings (pode ser expandido futuramente)

**Application:**
- Cria uma lista de nomes de cidades
- Imprime a lista usando `System.out::println`
- Imprime novamente usando o method reference `Utility::printDecorated`

---

## Propósito
- Introduzir method references como uma alternativa mais limpa a lambdas simples  
- Mostrar cenários reais onde eles aumentam a clareza do código  
- Demonstrar seu uso junto com interfaces funcionais e operações com coleções  

---

## Arquivos Incluídos
- Classes **Domain/**
- Classes **Application/**
- Arquivos **ProblemQuestion.txt**

---

## Como Executar
1. Navegue até qualquer pasta **Application**  
2. Execute a classe `Main`  
3. Observe a saída no console demonstrando o uso de method references  

---

## Requisitos
- **Java 17+**
- Entendimento básico de lambdas, coleções e interfaces funcionais  

---

## Notas
- Sintaxe limpa de method reference  
- Demonstra os quatro tipos de method reference:  
  - `objeto::métodoDeInstância`  
  - `Classe::métodoEstático`  
  - `Classe::métodoDeInstância`  
  - `Classe::new` (pode aparecer em exercícios futuros)  
- Facilita a remoção de lambdas redundantes  

---