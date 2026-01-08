<div align="center">

![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)

# Collections

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

## Visão Geral

Este módulo consolida um conjunto abrangente de exercícios envolvendo o
Java Collections Framework, com foco em listas, filas, conjuntos, mapas,
mecanismos de ordenação, estratégias de busca, padrões de iteração e
comparação de objetos. Cada exercício é acompanhado por um arquivo
**ProblemQuestion.txt** contendo as especificações completas.

Embora este módulo seja centrado em Collections, referências conceituais
breves sobre estruturas de dados, hashing, ordenação baseada em árvores
e custo algorítmico (Big-O) também são incluídas. Esses tópicos são
abordados apenas em nível superficial. Um projeto dedicado para uma
análise estrutural mais profunda está disponível em:
[Projeto de Estruturas de Dados](https://github.com/Eduardo-Salvador/Data_Strutcture-in-Java)

---

## Arquitetura
- **Domain** → Classes e Entidades.
- **Services** → Regras de negócio e manipulação dos dados.
- **App** → Inicializa a aplicação.
- Alguns exercícios contam com o app manipulando os dados.

---

## O que foi utilizado:

### 1. Visão Geral do Java Collections Framework

O Collections Framework unifica um conjunto de interfaces e
implementações projetadas para armazenar, manipular e organizar dados de
forma eficiente. Este módulo utiliza:

-   **List** (ArrayList, LinkedList)
-   **Set** (HashSet, TreeSet, NavigableSet)
-   **Map** (HashMap, LinkedHashMap, TreeMap, NavigableMap)
-   **Queue** (LinkedList, PriorityQueue)
-   **Utilitários de ordenação e busca binária**
-   **Iterators e padrões de iteração avançada**

Cada estrutura possui seu próprio comportamento interno e
características de desempenho.

---

### 2. Notas Breves sobre Estruturas de Dados

#### Lists:
Coleções ordenadas que permitem elementos duplicados e acesso por índice. ArrayList oferece O(1) para acesso por índice mas O(n) para inserção/remoção no meio; LinkedList tem O(n) para acesso mas O(1) para inserção/remoção nas extremidades. Escolha ArrayList para leitura frequente e LinkedList para muitas inserções/remoções.
#### Maps:
Estruturas chave-valor que não permitem chaves duplicadas. HashMap oferece O(1) para operações básicas mas sem ordenação; TreeMap mantém chaves ordenadas com O(log n); LinkedHashMap preserva ordem de inserção. Escolha baseada em necessidade de ordenação vs performance.
#### Sets:
Coleções que não permitem elementos duplicados. HashSet oferece O(1) para add/remove/contains sem ordenação; TreeSet mantém elementos ordenados com O(log n); LinkedHashSet preserva ordem de inserção. Úteis para verificar existência e eliminar duplicatas.
#### Queues:
Estruturas FIFO (first-in-first-out) para processamento ordenado. LinkedList implementa fila simples; PriorityQueue ordena por prioridade (heap) com O(log n) para add/poll; ArrayDeque é eficiente para fila/pilha. Ideal para processamento sequencial e algoritmos com prioridades.

---

### 3. Big-O Notation

Este módulo não realiza análise algorítmica profunda. No entanto,
conceitos de Big-O ajudam a explicar o desempenho das estruturas:

-   **O(1)** tempo constante (ex.: buscas por hash)
-   **O(log n)** tempo logarítmico (ex.: inserção/busca em árvores)
-   **O(n)** tempo linear (ex.: iterar sobre listas ou conjuntos)
-   **O(n log n)** comum em operações de ordenação

Esses fatores são mencionados apenas para contextualizar como a
estrutura escolhida impacta o comportamento dos exercícios.

---

### 4. Comparação de Objetos: Comparable e Comparator

#### Comparable:

Usado quando a classe define sua ordenação natural:

``` java
public interface Comparable<T> {
	int compareTo(T o);
}
```

Exemplos no módulo:
- Alunos ordenados por nome
- Livros ordenados alfabeticamente pelo título

#### Comparator:

Usado para definir estratégias de ordenação alternativas.

``` java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

Exemplos:
- Comparar alunos por idade
- Comparar alunos por nota (decrescente)

Esses mecanismos permitem ordenação flexível em listas, conjuntos, filas
de prioridade e estruturas baseadas em árvores.

---

### 5. Estruturas

#### Estruturas baseadas em arrays (ex.: ArrayList):

Ela **armazena dados de forma ordenada e linear e define as operações padrões da estrutura de dados Lista.**

- Permite duplicadas.
- Dois elementos não podem estar na mesma posição.
- O critério da ordem define a posição do elemento.
- Remoção e Adição via indíce.

  | Operação | Complexidade |
  | --- | --- |
  | `add(E)` | O(1) amortizado |
  | `add(index, E)` | O(n) |
  | `get(index)` | O(1) |
  | `set(index, E)` | O(1) |
  | `remove(index)` | O(n) |
  | `indexOf(Object)` | O(n) |
  | `contains(Object)` | O(n) |
  | `size()` | O(1) |

``` java
List<String> lista = new ArrayList<>();
```

---

#### Estruturas encadeadas (ex.: LinkedList):

Ela **armazena dados de forma ordenada e linear e define as operações padrões da estrutura de dados Lista.**

- Permite duplicadas.
- Dois elementos não podem estar na mesma posição.
- O critério da ordem define a posição do elemento.
- Remoção e Adição via indíce.

  | Operação | Complexidade |
  | --- | --- |
  | `add(E)` | O(1) |
  | `add(index, E)` | O(n) |
  | `get(index)` | O(n) |
  | `set(index, E)` | O(n) |
  | `remove(index)` | O(n) |
  | `indexOf(Object)` | O(n) |
  | `contains(Object)` | O(n) |
  | `size()` | O(1) |

``` java
List<String> lista = new LinkedList<>();
```

---

#### Estruturas baseadas em fila (Queue, PriorityQueue):

- Queue segue o princípio FIFO (First-In-First-Out) para processamento sequencial.
- PriorityQueue é baseada em heap binário (min-heap por padrão).
- Queue simples tem operações O(1), enquanto PriorityQueue tem O(log n) para inserção/remoção.

Uma análise aprofundada sobre heaps, balanceamento e estruturas de fila está
disponível no projeto de Estruturas de Dados mencionado.

#### Queue (LinkedList):
| Operação | Complexidade | Observações |
| --- | --- | --- |
| **offer(element) / add(element)** | O(1) | Adiciona no final da fila |
| **poll() / remove()** | O(1) | Remove e retorna o primeiro elemento |
| **peek() / element()** | O(1) | Retorna o primeiro sem remover |
| **size()** | O(1) | Mantém contador interno |
| **isEmpty()** | O(1) | Verifica se está vazia |
| **contains(element)** | O(n) | Precisa percorrer a fila |
| **Iteração completa** | O(n) | Percorre todos os elementos |
```java
Queue<String> queue = new LinkedList<>();
```

#### PriorityQueue:
| Operação | Complexidade | Observações |
| --- | --- | --- |
| **offer(element) / add(element)** | O(log n) | Insere e reorganiza o heap |
| **poll() / remove()** | O(log n) | Remove a raiz e rebalanceia |
| **peek() / element()** | O(1) | Retorna o elemento de maior prioridade |
| **remove(element)** | O(n) | Precisa buscar e depois rebalancear |
| **contains(element)** | O(n) | Busca linear no heap |
| **size()** | O(1) | Mantém contador interno |
| **Iteração completa** | O(n) | Percorre mas NÃO garante ordem |
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

---

#### Estruturas baseadas em hash (HashMap, HashSet):

-   Utilizam hashing: uma função matemática transforma uma chave em um
    valor inteiro.
-   Oferecem, em média, **O(1)** para acesso, inserção e remoção.
-   Não garantem ordem.

#### Equals:

Trabalha com comparações baseado em valores e não referências de objeto
- **Regras:**
    - **Reflexivo**: `x.equals(x)` tem que ser `true` para tudo que for diferente de `null`.
    - **Simétrico**: para `x e y ≠ null`, se `x.equals(y) == true`, logo `y.equals(x) == true;`
    - **Transisitividade**: para `x, y, z ≠ null`, se `x.equals(y) == true`, `x.equals(z) == true`, `y.equals(z) == true.`
    - **Consistente**: `x.equals(x)` sempre `true` se `x ≠ null`
``` java
@Override
public boolean equals(Object obj) {
    if(obj == null) return false; 
    // Se o objeto nao é nulo.
    if(this == obj) return true; 
    // Se o objeto é igual ao this.
    if(this.getClass() != obj.getClass()) return false;
    // Se a classe do this for diferente da classe do objeto passado.
    Smartphone smartphone = (Smartphone) obj;
    // Casting do this comparado para o objeto.
    return serialNumber != null && serialNumber.equals(smartphone.serialNumber);
}
// Retorna true se o atributo serialNumber for diferente de nulo
// E o serialNumber do this for igual o serialNumber do objeto.
// Posso ter mais atributos para considerar um equals.```
// -> Defino o que quero que seja o valor de comparação para 
// -> um Objeto ser igual a outro (Mesmo com endereços diferentes na Heap)
```

#### HashCode:
**Mapeia valores, gerando código númericos para cada ocorrência.**

*Pense em diversos baldes, cada um com seu código, e dentro desses baldes temos valores.
Isso é um resumo de Hash, indexamos coleções com valores numéricos para identificação, ajudando na performance e busca direta de valores, sem precisar iterar grandes arrays.*

Basicamente falamos, **gere um Hash específico baseado neste atributo, se outro objeto tiver o atributo igual, ele terá o mesmo Hash, a implementação é útil para coleções Hash principalmente.**

OBS: **HashCode tem que dar match com o equals**. Ou seja, o atriubuto que gera um hash tem que estar no equals e vice-versa.

```java
@Override
public int hashCode() {
    return Objects.hash(cpf, nome);  // usa cpf E nome
}

@Override
public boolean equals(Object obj) {
    Pessoa outra = (Pessoa) obj;
    return cpf.equals(outra.cpf) && nome.equals(outra.nome);  // usa cpf E nome
}
```

> **HashCode é como o CEP de uma casa. O equals é verificar o número da casa dentro daquela rua!**

*Se o HashCode não for sobreescrito, irá gerar automáticamente baseado no endereço na memória, o que pode gerar comportamentos indesejados em HashSets, etc.*

#### Hash: Conceito e Propósito:

Estruturas como **HashSet** e **HashMap** utilizam tabelas hash:

-   Cada chave (ou elemento, no caso de HashSet) gera um
    **hashCode()**.
-   O valor de hash é processado para determinar o *bucket* onde o
    objeto será armazenado.
-   O bucket pode conter mais de uma entrada devido a colisões.
-   Como busca, inserção e remoção operam sobre posições hash, o
    desempenho médio é **O(1)**.
-   O pior caso torna-se **O(log n)** quando os buckets viram árvores
    balanceadas (Java 8+).
-   Estruturas baseadas em hash **não mantêm ordem**.
-   Oferecem melhor desempenho.

Portanto, duplicatas são evitadas através da comparação:

1.  **Hash Code**
2.  **equals()** (para confirmação)

#### HashSet:
| Operação | Complexidade | Explicação |
| --- | --- | --- |
| `add(E)` | **O(1)** | Hash direto ao bucket |
| `remove(Object)` | **O(1)** | Hash direto ao bucket |
| `contains(Object)` | **O(1)** | Hash direto ao bucket |
| `size()` | **O(1)** | Atributo contador |
| `isEmpty()` | **O(1)** | Verifica size |
| `clear()` | **O(n)** | Limpa todos buckets |
| `iterator()` | **O(1)** | Cria iterador |
| Iterar todos | **O(n)** | Percorre n elementos |
| `addAll(Collection)` | **O(m)** | m = tamanho da coleção |
| `retainAll(Collection)` | **O(n)** | Percorre set atual |
| `removeAll(Collection)` | **O(n)** | Percorre set atual |
| `containsAll(Collection)` | **O(m)** | Verifica cada elemento |

```java
Set<Manga> mangas = new HashSet<>();
```

#### LinkedHashSet:
| Operação | Complexidade | Explicação |
| --- | --- | --- |
| `add(E)` | **O(1)** | Hash + ajuste de ponteiros |
| `remove(Object)` | **O(1)** | Hash + ajuste de ponteiros |
| `contains(Object)` | **O(1)** | Hash direto ao bucket |
| `size()` | **O(1)** | Atributo contador |
| `isEmpty()` | **O(1)** | Verifica size |
| `clear()` | **O(n)** | Limpa buckets e lista |
| `iterator()` | **O(1)** | Cria iterador |
| Iterar todos | **O(n)** | Percorre lista encadeada |
| `addAll(Collection)` | **O(m)** | m = tamanho da coleção |
| `retainAll(Collection)` | **O(n)** | Percorre set atual |
| `removeAll(Collection)` | **O(n)** | Percorre set atual |
| `containsAll(Collection)` | **O(m)** | Verifica cada elemento |

```java
Set<Manga> mangas = new LinkedHashSet<>();
```

#### HashMap:
| Operação | Caso Médio | Pior Caso | Observações |
| --- | --- | --- | --- |
| **get(key)** | O(1) | O(log n)* | *O(n) antes do Java 8 |
| **put(key, value)** | O(1) | O(log n)* | *O(n) antes do Java 8 |
| **remove(key)** | O(1) | O(log n)* | *O(n) antes do Java 8 |
| **containsKey(key)** | O(1) | O(log n)* | *O(n) antes do Java 8 |
| **containsValue(value)** | O(n) | O(n) | Precisa percorrer todos |
| Existem mais métodos |  |  |  |

```java
Map<String, String> map = new HashMap<>();
```

#### LinkedHashMap:
| Operação | Caso Médio | Pior Caso | Observações |
| --- | --- | --- | --- |
| **get(key)** | O(1) | O(log n) | Mesmo do HashMap |
| **put(key, value)** | O(1) | O(log n) | + custo de ajustar ponteiros da lista |
| **remove(key)** | O(1) | O(log n) | + custo de ajustar ponteiros da lista |
| **containsKey(key)** | O(1) | O(log n) | Mesmo do HashMap |
| **containsValue(value)** | O(n) | O(n) | Pode percorrer pela lista ordenada |
| **Iteração** | O(n) | O(n) | Mais eficiente que HashMap! |
| Existem mais métodos |  |  |  |

```java
Map<String, String> map = new LinkedHashMap<>();
```

Exercícios com hash:
- Collections.Set.HashSet
- Collections.Map.HashMap
- Implementações de equals() e hashCode()
- Sistema de Usuários via Email

---

#### Estruturas baseadas em árvores (TreeMap, TreeSet):

-   Baseadas em árvores balanceadas (como Red-Black Tree).
-   Mantêm os elementos ou chaves em ordem crescente.
-   Operações custam **O(log n)**.

Uma análise aprofundada sobre árvores, funções de hash, gerenciamento de
colisões ou representações encadeadas está disponível no projeto de
Estruturas de Dados mencionado.

#### TreeMap:
| Operação | Complexidade | Observações |
| --- | --- | --- |
| **get(key)** | O(log n) | Desce pela árvore comparando chaves |
| **put(key, value)** | O(log n) | Insere e rebalancea |
| **remove(key)** | O(log n) | Remove e rebalancea |
| **containsKey(key)** | O(log n) | Busca na árvore |
| **containsValue(value)** | O(n) | Precisa percorrer todos os nós |
| **firstKey() / lastKey()** | O(log n) | Vai até a extremidade da árvore |
| **lowerKey() / higherKey()** | O(log n) | Navega pela árvore |
| **subMap() / headMap() / tailMap()** | O(log n) | Cria visão, não copia |
| **Iteração completa** | O(n) | Percorre todos os elementos em ordem |

```java
NavigableMap<String, String> map = new TreeMap<>();
```

#### TreeSet:
| Operação | Complexidade | Observações |
| --- | --- | --- |
| **add(element)** | O(log n) | Insere e rebalancea a árvore |
| **remove(element)** | O(log n) | Remove e rebalancea a árvore |
| **contains(element)** | O(log n) | Busca na árvore |
| **first() / last()** | O(log n) | Vai até a extremidade da árvore |
| **lower() / higher()** | O(log n) | Navega pela árvore |
| **floor() / ceiling()** | O(log n) | Busca o maior ≤ ou menor ≥ |
| **pollFirst() / pollLast()** | O(log n) | Remove e retorna extremidade |
| **subSet() / headSet() / tailSet()** | O(log n) | Cria visão, não copia |
| **size()** | O(1) | Mantém contador interno |
| **isEmpty()** | O(1) | Verifica se size == 0 |
| **clear()** | O(n) | Remove todos os elementos |
| **Iteração completa** | O(n) | Percorre todos em ordem crescente |
| **descendingSet()** | O(1) | Retorna visão reversa |
| **descendingIterator()** | O(1) | Cria iterador reverso (iteração O(n)) |

```java
NavigableSet<Smartphone> set = new TreeSet<>();
```

---

### 6. Resumo:

O módulo Collections fornece uma base prática completa para o uso das
estruturas centrais, compreensão de desempenho, ordenação, busca,
igualdade de objetos, navegação e modelagem de cenários reais.

Entender as estruturas de dados presentes na API Collections é fundamental para o desenvolvimento de aplicações escaláveis e perfomáticas.