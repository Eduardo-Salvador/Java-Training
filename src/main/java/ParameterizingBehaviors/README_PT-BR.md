<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)](https://shields.io/)

# Parametrizando Comportamentos:

Este módulo oferece uma introdução abrangente às Comportamentos Parametrizando.
O objetivo é proporcionar uma compreensão prática de como os comportamentos parametrizados tornam o código mais flexível, permitindo que você passe comportamentos (lógica/ação) como parâmetro para métodos.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral
Este módulo contém um exercício demonstrando o uso de **Comportamentos Parametrizados** em Java.  
A ideia é mostrar como parametrizar comportamentos dinamicamente, evitando condicionais rígidas e permitindo maior flexibilidade no código.

---

## Arquitetura
O exercício está organizado em três pacotes principais:
- **Domain** — contém a interface `PaymentStrategy`  
- **Services** — contém a classe `PaymentProcessor` responsável por processar pagamentos  
- **Application** — contém a classe `Main` demonstrando o uso das estratégias de pagamento  
Há também um arquivo **ProblemQuestion.txt** com o enunciado original do problema.

---

## 1. O que são Comportamentos Parametrizados
É a **base de programação funcional e das Streams do Java 8+**

Parametrização de comportamentos serve para ***tornar código mais flexível, permitindo que você passe comportamentos (lógica/ação) como parâmetro para métodos***, ao invés de apenas dados.

### 1.1. Principais utilidades:

**Reutilização de código** - Um único método pode ter comportamentos diferentes sem duplicação de código.

**Flexibilidade** - Você define o "o quê fazer" no momento da chamada, não quando escreve o método.

**Elimina condicionais** - Ao invés de vários `if/else` para diferentes comportamentos, você passa a lógica diretamente.

### 1.2. Usa-se interfaces funcionais para fazer a parametrização de comportamentos:

```java
// Predicate<T> - recebe T, retorna boolean (testes/filtros)
Predicate<Integer> ehPar = n -> n % 2 == 0;
boolean resultado = ehPar.test(4); // true

// Consumer<T> - recebe T, não retorna nada (ações)
Consumer<String> imprimir = s -> System.out.println(s);
imprimir.accept("Olá"); // imprime "Olá"

// Function<T, R> - recebe T, retorna R (transformações)
Function<String, Integer> tamanho = s -> s.length();
int tam = tamanho.apply("Java"); // 4

// Supplier<T> - não recebe nada, retorna T (geração de valores)
Supplier<Double> aleatorio = () -> Math.random();
double valor = aleatorio.get(); // número aleatório

// BiFunction<T, U, R> - recebe T e U, retorna R
BiFunction<Integer, Integer, Integer> somar = (a, b) -> a + b;
int soma = somar.apply(5, 3); // 8

// Comparator<T> - compara dois objetos
Comparator<Produto> porPreco = (p1, p2) -> p1.getPreco().compareTo(p2.getPreco());
```

### Você também pode criar suas próprias interfaces funcionais:

```java
@FunctionalInterface
public interface Calculadora {
    double calcular(double a, double b);
}

// Uso
public double executar(double x, double y, Calculadora calc) {
    return calc.calcular(x, y);
}

executar(10, 5, (a, b) -> a + b); // soma = 15
executar(10, 5, (a, b) -> a * b); // multiplicação = 50
executar(10, 5, (a, b) -> a - b); // subtração = 5
```

> Você define uma **interface com um método (parâmetro)**, e passa a **implementação desse método como lambda, classe anônima ou referência** no momento que precisa. 

- `Predicate`  e outras são só uma das interfaces prontas que o Java oferece!

---

## 2. Estrutura do Exercício:

### 2.1. ParameterizingBehaviors.Domain
Contém a interface que define o contrato para as estratégias de pagamento.

**Interface: PaymentStrategy**
- Método: `void pay(double amount)`  
- Representa o comportamento de pagamento que pode ser parametrizado dinamicamente

---

### 2.2. ParameterizingBehaviors.Services
Contém a classe responsável por processar pagamentos usando uma estratégia fornecida.

**Class: PaymentProcessor**
- Método: `processPayment(double amount, PaymentStrategy strategy)`  
  - Recebe o valor e a estratégia de pagamento  
  - Chama `strategy.pay(amount)` para executar o comportamento parametrizado  

---

### 2.3. ParameterizingBehaviors.Application
Demonstra múltiplos cenários reais de uso de comportamentos parametrizados.

**Class: Main**
Executa diferentes estratégias de pagamento:

- **Estratégia de Cartão de Crédito**
  - Aplica 2% de desconto se o valor > 3000  
  - Exibe mensagem de pagamento via cartão  

- **Estratégia de Pix**
  - Aplica 10% de desconto se o valor > 3000  
  - Exibe mensagem de pagamento via Pix  

- **Estratégia de Boleto**
  - Aplica 5% de desconto se o valor > 3000  
  - Exibe mensagem de pagamento via boleto  

**Cenários demonstrados:**
- Processamento de um pagamento único com `PaymentProcessor`  
- Iteração sobre uma lista de estratégias (`List<PaymentStrategy>`)  
- Execução de múltiplos pagamentos com diferentes comportamentos parametrizados  

> Foi aplicado as duas formas de usar os comportamentos: via Lambdas (próximo tópico do projeto no Github) e via Classes Anônimas.

---

## 3. Resumo: 
- Uso de diferentes formas de parametro de comportamento.
- Demonstrar boas práticas para evitar condicionais rígidas.
- Mostrar como encapsular diferentes lógicas de negócio em estratégias independentes. 
- Incentivar design modular e extensível.
- Evita condicionais fixas e repetitivas. 
- Demonstra como diferentes estratégias podem ser aplicadas dinamicamente.
- Mostra claramente como o design orientado a objetos facilita extensibilidade e manutenção.

---