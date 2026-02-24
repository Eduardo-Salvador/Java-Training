<div align="center">

![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)

# Lógica de Programação Orientada a Objetos

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Requisitos
Todas as descrições dos problemas estão em suas respectivas pastas (ProblemQuestion.txt).

Existe uma classe para gerar objetos numéricos, embora existam variáveis, o objetivo é usá-la como um objeto auxiliar, já que pode ser reutilizada em todos os exercícios envolvidos (Conceito de OOP).

---

## O Que Foi Utilizado

### 1. Tipos Primitivos:
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

### 2. Operadores:
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

### 3. Estruturas Condicionais:
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

### 4. Estrutura Switch:
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

### 5. Estruturas de Repetição:
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
### 6. Arrays:
Arrays são tipos de estruturas de dados que armazenam valores do mesmo tipo (int, String, double, etc.).  
São vistos como objetos da classe Array do Java.

    int[] idades -> Tipo de referência.

    int[] idades = new int[3]; -> Array de int com 3 posições. (Objeto na memória)

---
### 7. Arrays Multidimensionais:
Arrays multidimensionais são arrays com linhas e colunas.

    (linha - coluna)
    11 (0,0) - 32 (0,1) - 64 (0,2) - 128 (0,3)
    43 (1,0) - 86 (1,1) - 91 (1,2) - 102 (1,3)

    int[][] dias = new int[5][5];

---

### 8. Programação Orientada a Objetos e outros conceitos abordados:
- #### Classes:
  Estruturas que definem modelos para criar objetos, contendo atributos (variáveis) e comportamentos (métodos). São como "moldes" que especificam as características e ações que os objetos daquele tipo terão.

- #### Objetos:
  Instâncias concretas de uma classe, criadas a partir do molde definido por ela. Cada objeto possui seu próprio conjunto de valores para os atributos, mas compartilha os métodos da classe.

- #### Construtores:
  Métodos especiais chamados automaticamente ao criar um objeto, usados para inicializar seus atributos. Têm o mesmo nome da classe e não possuem tipo de retorno.

- #### Modificadores de Acesso:
  Palavras-chave (public, private, protected) que controlam a visibilidade de atributos e métodos. Definem quais partes do código podem acessar determinados membros da classe.

- #### Getters e Setters:
  Métodos de acesso (get) e modificação (set) de atributos privados de uma classe. Permitem controlar como os dados são lidos e alterados, aplicando validações quando necessário.

- #### Métodos:
  Funções definidas dentro de uma classe que representam os comportamentos dos objetos. Podem receber parâmetros, processar dados e retornar valores.

- #### Encapsulamento:
  Princípio de ocultar detalhes internos de implementação e expor apenas interfaces públicas necessárias. Protege os dados e garante que sejam manipulados apenas por métodos controlados.

- #### Referência a objetos, Sobrecargas:
  Variáveis que armazenam endereços de memória onde objetos estão localizados, não os objetos em si. Sobrecarga permite criar múltiplos métodos/construtores com o mesmo nome mas parâmetros diferentes.

- #### Associações:
  Relacionamentos entre classes onde objetos de uma classe se conectam com objetos de outra. Podem ser unidirecionais ou bidirecionais, representando dependências entre entidades.

- #### toStrings:
  Método que retorna uma representação textual de um objeto, facilitando sua exibição e depuração. Sobrescreve o método toString() herdado da classe Object em Java.

- #### Enumerações:
  Tipo especial que define um conjunto fixo de constantes nomeadas, representando valores predefinidos. Úteis para representar opções limitadas como dias da semana, status, categorias etc.

- #### Scanners:
  Classe Java que facilita a leitura de entrada de dados de diferentes fontes (teclado, arquivos). Oferece métodos para ler strings, números e outros tipos de dados formatados.

- #### LocalDate:
  Classe Java que facilita a manipulação de Datas.
---

## Resultados de Aprendizagem

- Programação Orientada a Objetos (POO) básica.
- Lógica de programação.
- Manipulação de pacotes.
- Resolução de problemas.

---
