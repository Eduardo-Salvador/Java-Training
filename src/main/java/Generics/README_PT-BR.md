<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)](https://shields.io/)

# Generics

Este módulo fornece uma introdução completa aos Generics em Java, com foco em parametrização de tipos, classes genéricas, métodos genéricos, wildcards e a interação entre generics e polimorfismo. Todos os exercícios incluem seu próprio arquivo **ProblemQuestion.txt** dentro do diretório correspondente.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral
- Generics **são recursos para trabalhar com tipos parametrizados. Eles tornam as coleções mais seguras e reutilizáveis.**
- Usa o **operador diamante para tipagem <>, sempre considerando um objeto, sendo Wrapper ou não.**
- **Type Esasure é o processo pelo qual o compilador Java remove as informações e tipo genérico durante a compilação**. Isso significa que os tipos genéricos são usados apenas para garantir segurança de tipo em tempo de compilação, mas nunca executados pela JVM. Assim mantendo compatibilidade com versões antigas.
  
**Eles possibilitam:**
- Segurança de tipos (validação em tempo de compilação)
- Prevenção de `ClassCastException`
- Reutilização de algoritmos e estruturas de dados.
- APIs mais expressivas.

**Notação típica:**
```java
class Box<T> { ... }
T value;
List<String> list;
List<? extends String>;
List<? super String>;
```

---

## Arquitetura:
- **Domain** → Classes e Entidades.
- **Services** → Regras de negócio e manipulação dos dados.
- **App** → Inicializa a aplicação.

---
### 1. Wildcards em Generics

Os coringas (`?`) permitem flexibilidade controlada ao trabalhar com tipos relacionados.

Também chamado de caractere curinga, ele utiliza conceitos como polimorfismo.

### 1.1 `? extends T` — Wildcard com Limite Superior
Usado para ler objetos do tipo `T` ou subclasses.

#### Exemplos:
```java
public static void printAnimalSounds(List<? extends Animal> animalList){
for (Animal a : animalList){
    System.out.println(a);
    a.makeSound();
    } // -> Tudo que estende (É um) Animal. (Dog dog = new Dog())
}
```

### 1.2 `? super T` — Lower-Bounded Wildcard
Usado quando se deseja inserir objetos.

#### Exemplo:
```java
public static void add(List<? super Animal> animals, Animal a){
  animals.add(a);
} // -> Tudo que é especificamente Animal (Animal a = new Dog()) ou acima dele.
```

#### Uso:
- Inserção de objetos do tipo `T`
- Suporte à contravariância.
- adicionar elementos do tipo Animal (subclasses também).
- ```? super Tipo``` **e Classes acima na hierárquia** (Garante que é o supertipo ou acima que é usado como referência)

---

### 2. Wildcards e Polimorfismo

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

#### Sem wildcards:
- Não seria possível passar `List<Dog>` para um método que espera `List<Animal>`
- Generics são invariantes.

#### Com `? extends Animal`:
- Qualquer lista de subtipos funciona.
- Cada animal executa seu próprio `makeSound()`

#### Demonstra:
- Segurança de tipos em compilação
- Polimorfismo em tempo de execução
- Padrões de covariância e contravariância.

### 3. Classes Genéricas

Uma classe genérica declara um ou mais parâmetros de tipo. Isso permite que a mesma classe armazene ou processe diferentes tipos sem reescrever código.

### Box
```java
Box<String> stringBox = new Box<>("Hello");
Box<Integer> numberBox = new Box<>(10);
Box<Animal> animalBox = new Box<>(new Animal("Bolt"));
```

#### Características:
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

### 4. Métodos Genéricos

Um método genérico declara seus próprios parâmetros de tipo independentemente da classe em que está.

#### Sintaxe:
```java
public static <T> void printArray(T[] array) { ... }
```

- `printArray(T[] array)`
- `maxValue(T a, T b, T c)` com `<T extends Comparable>`
- `containsElements(List list, T element)`
- `swap(T[] array, T e1, T e2)`
- `copyList(List<? extends T> from, List<? super T> to)`

#### Métodos genéricos oferecem:
- Lógica reutilizável independente de tipos concretos.
- Verificação forte em tempo de compilação.
- Utilidade em algoritmos e ferramentas.

---

### 5. Exercícios:

### 5.1 Wildcards e Polimorfismo
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
- Une objetos de subtipos em uma lista pai

Domínio:
- `Animal`, `Dog`, `Cat`, `Lion`
- `AnimalManager`

---

### 5.2 Classes Genéricas
#### Abrange:
- Criação de containers genéricos.
- Uso de parâmetros simples (`T`).
- Uso de múltiplos parâmetros (`K`, `V`).
- Sobrescrita de `toString()`.
- Uso com vários tipos (String, Integer, classes de domínio).

Exercícios:

#### Box
- Armazena e retorna conteúdo genérico.
- Aplicação: `Box`, `Main`

#### Pair<K, V>
- Armazena pares chave/valor.
- Usa múltiplos parâmetros de tipo.
- Aplicação: `Pair<K, V>`, `Main`

---

### 5.3 Métodos Genéricos
#### Abrange:
- Definição de parâmetros independentes.
- Processamento de arrays genéricos.
- Tipos limitados (`<T extends Comparable>`).
- Operação com listas.
- Funções utilitárias como swap, copy, max, contains.

Exercícios:
#### printArray()
- Identifica tipo do array em runtime
- Itera sobre arrays genéricos

#### maxValue()
- Determina maior elemento usando `Comparable`

#### containsElements()
- Verifica pertencimento em listas genericamente.

#### swap()
- Troca dois elementos de um array genérico.

#### copyList()
- Usa wildcards:
  - `? extends T` para leitura.
  - `? super T` para escrita.
- Demonstra o princípio PECS (Producer Extends, Consumer Super).

---

### 6. Aprendizado:

- Parametrização de tipos e segurança em tempo de compilação.
- Como criar e usar classes genéricas.
- Como implementar métodos genéricos com parâmetros limitados ou não.
- Como wildcards permitem polimorfismo controlado.
- Diferença entre `extends` e `super`.
- Criação de utilitários genéricos reutilizáveis.
- Manipulação prática de dados com generics e polimorfismo.

---