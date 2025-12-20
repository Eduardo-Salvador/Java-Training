# Treinamento de Lógica e Programação Orientada a Objetos

---

## Requisitos
Todas as descrições dos problemas estão em suas respectivas pastas.

---

## O Que Foi Utilizado

### 1. Tipos Primitivos
Tipos que armazenam um valor simples na memória.
- int
- double
- float
- char
- byte
- short
- long
- boolean

---

### 2. Operadores
#### 2.1. Aritméticos:
Operadores que permitem realizar cálculos matemáticos.
- Soma ( + )
- Subtração ( - )
- Divisão ( / )
- Multiplicação ( * )
- Resto da Divisão ( % )

#### 2.2. Relacionais:
Também chamados de variáveis lógicas, são usados para realizar comparações e sempre retornam valores booleanos (true ou false).
- ( < ) Menor que
- ( <= ) Menor ou igual a
- ( > ) Maior que
- ( >= ) Maior ou igual a
- ( == ) Igual a / Idêntico a
- ( != ) Diferente de

#### 2.3. Lógicos:
Funcionam em conjunto com estruturas condicionais, servindo para dar lógica às comparações.
- ( && ) (E) -> Duas condições verdadeiras para resultado True.
- ( || ) (Ou) -> Uma das condições verdadeira para resultado True.
- ( ! ) (Negação) -> Inverte a condição; se a condição anterior era verdadeira, retorna falso.

#### 2.4. Atribuição:
Atribui valores às variáveis.
- ( = ) Atribui algum valor.
- ( += ) Adiciona ao valor da variável.
- ( -= ) Subtrai do valor da variável.
- ( *= ) Multiplica o valor da variável.
- ( /= ) Divide o valor da variável.
- ( %= ) Adiciona o resto da divisão à variável.
- ( ++ ) Incremento (Adiciona 1).
- ( -- ) Decremento (Subtrai 1).

---

### 3. Estruturas Condicionais
São as estruturas mais utilizadas na programação; direta ou indiretamente, servem para dar ao código uma condição, uma regra.

    if (condição = true) {
        Executa algo no bloco if.
    } 
    else if (condição = true) {
        Se a primeira condição não for atendida, uma segunda condição é testada,
        e se for verdadeira, algo é executado no bloco else if.
    } 
    else {
        Se nenhuma condição for atendida, executa o bloco else.
    }

---

#### 3.1 Operador Ternário:
Criado para simplificar estruturas condicionais.
    
    tipoVariavel nomeVariavel = (condição) ? resultadoSeVerdadeiro : resultadoSeFalso

- Use-o dentro de uma variável.
- Encadear operadores ternários não é uma boa prática.
---

### 4. Estrutura Switch
É uma condição de seleção que evita o encadeamento de if e else; chamamos de palavra reservada.
    
    switch(valorEntrada) {
        case opção1:
            faça algo
            break;
        case opção2:
            faça algo
            break;
        default: (nenhuma opção foi atendida na entrada)
            faça algo
            break;
    }

---

### 5. Estruturas de Repetição
São usadas para repetir uma determinada ação várias vezes ou para iterar sobre algo.  
Quebram a lógica de cima para baixo, repetindo certas estruturas.

#### 5.1. While:
Enquanto uma determinada condição for verdadeira, o loop se repete.

    while (condição) {
        faça algo
    }

#### 5.2. Do While:
Mesma lógica do while, mas necessariamente executa o loop pelo menos uma vez; se a condição for falsa, terá sido executado ao menos uma vez.

    do {
        faça algo
    } while (condição)

#### 5.3. For:
Funciona com índices, o que é útil para iterar sobre coisas (contar elementos).

    for (início; condição; incremento/decremento) {
        faça algo
    }

#### 5.4. For Each:
Uma estrutura mais simples para percorrer elementos, semelhante ao for.

    for (TipoElemento var : Elemento) {
        faça algo com var
    }

---
### 6. Arrays
Arrays são tipos de estruturas de dados que armazenam valores do mesmo tipo (int, String, double, etc.).  
São vistos como objetos da classe Array do Java.

    int[] idades -> Tipo de referência.

    int[] idades = new int[3]; -> Array de int com 3 posições. (Objeto na memória)

---
### 7. Arrays Multidimensionais
Arrays multidimensionais são arrays com linhas e colunas.

    (linha - coluna)
    11 (0,0) - 32 (0,1) - 64 (0,2) - 128 (0,3)
    43 (1,0) - 86 (1,1) - 91 (1,2) - 102 (1,3)

    int[][] dias = new int[5][5];

---

### 8. POO - Classes, Objetos, Construtores, Getters e Setters, Métodos, Modificadores de Acesso, Encapsulamento, Referência a objetos, Sobrecargas, Associações, Scanners, Herança, toStrings, Enumerações, Interfaces, Classes Abstratas, Polimorfismo.

---

## Resultados de Aprendizagem

- Programação Orientada a Objetos (POO), Polimorfismo e outros conceitos
- Lógica de programação
- Manipulação de pacotes
- Resolução de problemas

---
