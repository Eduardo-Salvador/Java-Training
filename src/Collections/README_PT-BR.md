# Collections

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

**Projeto de Estruturas de Dados:**
https://github.com/Eduardo-Salvador/Data_Strutcture-in-Java

------------------------------------------------------------------------

# 1. Fundamentos: Collections e Estruturas de Dados

## 1.1 Visão Geral do Java Collections Framework

O Collections Framework unifica um conjunto de interfaces e
implementações projetadas para armazenar, manipular e organizar dados de
forma eficiente. Este módulo utiliza:

-   **List** (ArrayList, LinkedList)\
-   **Set** (HashSet, TreeSet, NavigableSet)\
-   **Map** (HashMap, LinkedHashMap, TreeMap, NavigableMap)\
-   **Queue** (LinkedList, PriorityQueue)\
-   **Utilitários de ordenação e busca binária**\
-   **Iterators e padrões de iteração avançada**

Cada estrutura possui seu próprio comportamento interno e
características de desempenho.

------------------------------------------------------------------------

# 2. Notas Breves sobre Estruturas de Dados (Visão Leve)

Este módulo aborda algumas estruturas subjacentes:

### Estruturas baseadas em arrays (ex.: ArrayList)

-   Acesso rápido por índice (**O(1)**)\
-   Inserções/remoções mais lentas no meio (**O(n)**)

### Estruturas encadeadas (ex.: LinkedList)

-   Inserções/remoções rápidas nas extremidades (**O(1)**)\
-   Acesso aleatório lento (**O(n)**)

### Estruturas baseadas em hash (HashMap, HashSet)

-   Utilizam hashing: uma função matemática transforma uma chave em um
    valor inteiro.\
-   Oferecem, em média, **O(1)** para acesso, inserção e remoção.\
-   Não garantem ordem.

### Estruturas baseadas em árvores (TreeMap, TreeSet)

-   Baseadas em árvores balanceadas (como Red-Black Tree).\
-   Mantêm os elementos ou chaves em ordem crescente.\
-   Operações custam **O(log n)**.

Uma análise aprofundada sobre árvores, funções de hash, gerenciamento de
colisões ou representações encadeadas está disponível no projeto de
Estruturas de Dados mencionado.

------------------------------------------------------------------------

# Big-O Notation (Visão Superficial)

Este módulo não realiza análise algorítmica profunda. No entanto,
conceitos de Big-O ajudam a explicar o desempenho das estruturas:

-   **O(1)** tempo constante (ex.: buscas por hash)\
-   **O(log n)** tempo logarítmico (ex.: inserção/busca em árvores)\
-   **O(n)** tempo linear (ex.: iterar sobre listas ou conjuntos)\
-   **O(n log n)** comum em operações de ordenação

Esses fatores são mencionados apenas para contextualizar como a
estrutura escolhida impacta o comportamento dos exercícios.

------------------------------------------------------------------------

# 4. Hash vs. Tree (Visão Didática)

## 4.1 Hash: Conceito e Propósito

Estruturas como **HashSet** e **HashMap** utilizam tabelas hash:

-   Cada chave (ou elemento, no caso de HashSet) gera um
    **hashCode()**.\
-   O valor de hash é processado para determinar o *bucket* onde o
    objeto será armazenado.\
-   O bucket pode conter mais de uma entrada devido a colisões.\
-   Como busca, inserção e remoção operam sobre posições hash, o
    desempenho médio é **O(1)**.\
-   O pior caso torna-se **O(log n)** quando os buckets viram árvores
    balanceadas (Java 8+).\
-   Estruturas baseadas em hash **não mantêm ordem**.\
-   Oferecem melhor desempenho.

Portanto, duplicatas são evitadas através da comparação:

1.  **Hash Code**\
2.  **equals()** (para confirmação)

Exercícios com hash:\
- Collections.Set.HashSet\
- Collections.Map.HashMap\
- Implementações de equals() e hashCode()\
- Sistema de Usuários via Email

------------------------------------------------------------------------

## 4.2 Trees (Árvores Balanceadas)

**TreeSet** e **TreeMap** usam uma árvore (geralmente Red-Black Tree):

-   Armazenam dados sempre ordenados\
-   Todas as operações custam **O(log n)**\
-   Permitem operações navegáveis: higher(), lower(), first(), last(),
    subMap(), etc.\
-   Oferecem mais métodos, mas perdem um pouco em performance.

Exercícios com árvores:\
- TreeSet de livros\
- TreeMap de contatos\
- Navegação em ordem crescente/decrescente\
- Atualizações mantendo a ordenação

------------------------------------------------------------------------

# 5. Comparação de Objetos: Comparable e Comparator

### Comparable

Usado quando a classe define sua ordenação natural:

``` java
public int compareTo(T o)
```

Exemplos no módulo:\
- Alunos ordenados por nome\
- Livros ordenados alfabeticamente pelo título

### Comparator

Usado para definir estratégias de ordenação alternativas.

Exemplos:\
- Comparar alunos por idade\
- Comparar alunos por nota (decrescente)

Esses mecanismos permitem ordenação flexível em listas, conjuntos, filas
de prioridade e estruturas baseadas em árvores.

------------------------------------------------------------------------

# 6. Sintaxe Básica de Listas (Usada em Todo o Módulo)

``` java
List<Product> products = new ArrayList<>();
products.add(new Product("Mouse", 120.0, 10));
products.get(0);
products.removeIf(p -> p.getName().equals("Mouse"));
products.sort(Comparator.comparing(Product::getPrice));
```

Características: - Ordenadas\
- Permitem duplicatas\
- Acesso por índice

------------------------------------------------------------------------

# 7. Visão Geral do Módulo e Exercícios

## 7.1 Lists -- ArrayList e LinkedList

### Tópicos:

-   CRUD\
-   Iteração\
-   Ordenação\
-   Conversões\
-   Equals/hashCode\
-   LinkedList como fila

### Exercícios:

1.  Inventário\
2.  Fila de suporte\
3.  Conversões\
4.  Cadastro de funcionários

------------------------------------------------------------------------

## 7.2 Sets -- HashSet e TreeSet

1.  Cadastro de usuários\
2.  Catálogo de livros

------------------------------------------------------------------------

## 7.3 Maps -- HashMap, LinkedHashMap, TreeMap

1.  Mapa de produtos\
2.  Agenda de contatos

------------------------------------------------------------------------

## 7.4 Queues -- LinkedList e PriorityQueue

1.  Fila básica\
2.  Fila médica prioritária\
3.  PriorityQueue isolada

------------------------------------------------------------------------

## 7.5 Ordenação e Busca Binária

1.  Ordenação de alunos\
2.  Busca binária em livros

------------------------------------------------------------------------

# 8. Resumo

O módulo Collections fornece uma base prática completa para o uso das
estruturas centrais, compreensão de desempenho, ordenação, busca,
igualdade de objetos, navegação e modelagem de cenários reais.