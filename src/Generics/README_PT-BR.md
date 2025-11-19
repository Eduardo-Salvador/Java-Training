# Generics

Este módulo fornece uma introdução completa aos Generics em Java, com foco em parametrização de tipos, classes genéricas, métodos genéricos, wildcards e a interação entre generics e polimorfismo. Todos os exercícios incluem seu próprio arquivo **ProblemQuestion.txt** dentro do diretório correspondente.

---

## 1. Fundamentos: O que são Generics?

Generics permitem que tipos (classes e interfaces) sejam parâmetros ao definir classes, métodos e interfaces.

Você cria Classes, Métodos, etc; E não define qual tipo ele vai receber, sendo assim um tipo Genérico, conseguindo receber e trabalhar o mesmo comportamento para diversos tipos, evitando reescrever o mesmo código diversas vezes.

Eles possibilitam:
- Segurança de tipos (validação em tempo de compilação)
- Prevenção de `ClassCastException`
- Reutilização de algoritmos e estruturas de dados
- APIs mais expressivas

Em Java, generics são implementados via *type erasure*, mas a sintaxe permite modelar lógica de forma flexível e segura.

Notação típica:
```java
class Box<T> { ... }
T value;
List<String> list;
```

---

## 2. Classes Genéricas

Uma classe genérica declara um ou mais parâmetros de tipo. Isso permite que a mesma classe armazene ou processe diferentes tipos sem reescrever código.

Exemplo do módulo:

### Box
```java
Box<String> stringBox = new Box<>("Hello");
Box<Integer> numberBox = new Box<>(10);
Box<Animal> animalBox = new Box<>(new Animal("Bolt"));
```

Características:
- `T` é um placeholder para um tipo real.
- Cada instância associa `T` a um tipo específico.
- Elimina necessidade de casting.
- Aumenta a reutilização.

### Pair<K, V>

Uma classe genérica com dois parâmetros de tipo:
```java
Pair<String, Integer> age = new Pair<>("Age", 25);
Pair<String, String> name = new Pair<>("Name", "John");
```

Usada para modelar estruturas chave–valor ou associações duplas.

---

## 3. Métodos Genéricos

Um método genérico declara seus próprios parâmetros de tipo independentemente da classe em que está.

Sintaxe:
```java
public static <T> void printArray(T[] array) { ... }
```

Exemplos do módulo:
- `printArray(T[] array)`
- `maxValue(T a, T b, T c)` com `<T extends Comparable>`
- `containsElements(List list, T element)`
- `swap(T[] array, T e1, T e2)`
- `copyList(List<? extends T> from, List<? super T> to)`

Métodos genéricos oferecem:
- Lógica reutilizável independente de tipos concretos
- Verificação forte em tempo de compilação
- Utilidade em algoritmos e ferramentas

---

## 4. Wildcards em Generics

Wildcards (`?`) permitem flexibilidade controlada ao trabalhar com tipos relacionados.

### 4.1 `? extends T` — Upper-Bounded Wildcard
Usado para leitura de objetos do tipo `T` ou subclasses.

Exemplo:
```java
public static void printAnimalSounds(List<? extends Animal> animals)
```

Uso:
- Consumir valores
- Evita inserção insegura de subclasses

### 4.2 `? super T` — Lower-Bounded Wildcard
Usado quando se deseja inserir objetos.

Exemplo:
```java
public static void add(List<? super Animal> list, List<? extends Animal> animals)
```

Uso:
- Inserção de objetos do tipo `T`
- Suporte à contravariância

### 4.3 `?` — Unbounded Wildcard
Significa: “Não sei qual é o tipo.”

Uso:
- Apenas leitura como `Object`
- Tipo irrelevante para a operação

---

## 5. Wildcards e Polimorfismo

Wildcards permitem comportamento polimórfico dentro de estruturas genéricas.

Exemplo:
```java
List<Dog> dogs;
List<Cat> cats;
List<Lion> lions;

AnimalManager.printAnimalSounds(dogs);
AnimalManager.printAnimalSounds(cats);
AnimalManager.printAnimalSounds(lions);
```

Sem wildcards:
- Não seria possível passar `List<Dog>` para um método que espera `List<Animal>`
- Generics são invariantes

Com `? extends Animal`:
- Qualquer lista de subtipos funciona
- Cada animal executa seu próprio `makeSound()`

Demonstra:
- Segurança de tipos em compilação
- Polimorfismo em tempo de execução
- Padrões de covariância e contravariância

---

## 6. Estrutura do Módulo e Exercícios

Cada diretório contém um **ProblemQuestion.txt** com os requisitos de cada exercício.

### 6.1 Classes Genéricas
Abrange:
- Criação de containers genéricos
- Uso de parâmetros simples (`T`)
- Uso de múltiplos parâmetros (`K`, `V`)
- Sobrescrita de `toString()`
- Uso com vários tipos (String, Integer, classes de domínio)

Exercícios:

#### Box
- Armazena e retorna conteúdo genérico
- Demonstra binding para diferentes tipos
- Aplicação: `Box`, `Main`

#### Pair<K, V>
- Armazena pares chave/valor
- Usa múltiplos parâmetros de tipo
- Aplicação: `Pair<K, V>`, `Main`

---

### 6.2 Métodos Genéricos
Abrange:
- Definição de parâmetros independentes
- Processamento de arrays genéricos
- Tipos limitados (`<T extends Comparable>`)
- Operação com listas
- Funções utilitárias como swap, copy, max, contains

Exercícios:
#### printArray()
- Identifica tipo do array em runtime
- Itera sobre arrays genéricos

#### maxValue()
- Determina maior elemento usando `Comparable`

#### containsElements()
- Verifica pertencimento em listas genericamente

#### swap()
- Troca dois elementos de um array genérico

#### copyList()
- Usa wildcards:
  - `? extends T` para leitura
  - `? super T` para escrita
- Demonstra o princípio PECS (Producer Extends, Consumer Super)

---

### 6.3 Wildcards e Polimorfismo
Abrange:
- Listas covariantes (`? extends Animal`)
- Listas contravariantes (`? super Animal`)
- Polimorfismo dentro de generics
- Métodos que aceitam listas especializadas mantendo segurança

Exercícios:
#### printAnimalSounds()
- Aceita `List<? extends Animal>`
- Imprime cada animal
- Chama `makeSound()` sobrescrito

#### add()
- Demonstra inserção segura com `? super Animal`
- Une listas de subtipos em uma lista pai

Domínio:
- `Animal`, `Dog`, `Cat`, `Lion`
- `AnimalManager`

---

## 7. Resumo

- Parametrização de tipos e segurança em tempo de compilação
- Como criar e usar classes genéricas
- Como implementar métodos genéricos com parâmetros limitados ou não
- Como wildcards permitem polimorfismo controlado
- Diferença entre `extends` e `super`
- Criação de utilitários genéricos reutilizáveis
- Manipulação prática de dados com generics e polimorfismo

Todos os exercícios possuem um arquivo ProblemQuestion.txt garantindo prática orientada e estruturada.

---