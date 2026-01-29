<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)](https://shields.io/)

# Classes Internas

Este módulo oferece uma introdução abrangente às Classes Internas.

O objetivo é proporcionar uma compreensão prática de como as classes internas funcionam, quando usá-las e como elas melhoram a organização, o encapsulamento e a expressividade do código.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Este projeto contém uma coleção de exercícios independentes focados em classes internas em Java, organizados nos seguintes pacotes:
- InnerClasses.InnerClasses — Classes internas de membros
- InnerClasses.LocalClasses — Classes locais
- InnerClasses.AnonymousClasses — Classes anônimas
- InnerClasses.StaticNestedClasses — Classes aninhadas estáticas

---

## Arquitetura

- Uma estrutura mínima com camadas de aplicação e domínio
- Lógica independente demonstrando diferentes mecanismos de classes internas
- Um arquivo ProblemQuestion.txt descrevendo o exercício original
- Exemplos práticos de:
  - Classes internas de membros
  - Classes locais
  - Classes anônimas
  - Classes aninhadas estáticas
  - Encapsulamento e relações de escopo
  - Organização modular usando estruturas aninhadas

---

### 1. O que são classes internas?

Classes internas são classes declaradas dentro de outras classes.

Elas permitem:

- Agrupamento forte de classes relacionadas

- Acesso direto entre contextos internos e externos

- Maior encapsulamento

- Organização modular clara quando os objetos estão intimamente relacionados

---

### 2. Tipos principais

#### 2.1. Classes internas (Não-estáticas)
- Declaradas dentro de outra classe (mas fora dos métodos).
- Possuem acesso total à instância da classe externa.

#### 2.2. Classes locais
- Classes declaradas dentro de um método.
- Elas existem apenas dentro do método onde são definidas.

#### 2.3. Classes anônimas
- Classes criadas sem nome, geralmente para sobrescrever métodos ou implementar interfaces inline.

#### 2.4. Classes aninhadas estáticas
- Declaradas como estáticas.
- Não dependem de uma instância da classe externa.

---

### 3. Conceitos Utilizados

#### 3.1. Os exercícios demonstram:
- Composição de objetos por meio de classes internas
- Acesso à classe externa via `OuterClass.this`
- Regras de escopo local
- Polimorfismo com classes anônimas
- Estruturas aninhadas estáticas e não estáticas
- Iteração sobre listas de objetos aninhados
- Organização modular orientada a domínio

---

### 4. Visão Geral de Classes:

#### 4.1. Classes Internas (não estáticas)
- Classes dentro de outras classes que dependem de uma instância da classe externa para existir.
- Acessa todos os membros (incluindo os privados) da classe externa.
- Útil quando duas classes estão fortemente acopladas.
- Sintaxe:
``` java
Outer outer = new Outer();
Inner interno = Outer.new Inner();
```

#### 4.2. Classes Locais
- Classes declaradas dentro de métodos ou blocos.
- Elas existem apenas dentro do escopo desse método e podem acessar variáveis locais que são, em última instância, finais.
- Raramente usadas hoje em dia, substituídas por lambdas na maioria dos casos.
- São úteis quando você precisa de múltiplos métodos, mas a classe só faz sentido naquele contexto específico.

#### 4.3. Classes Anônimas
- Classes nomeadas criadas e instanciadas em uma única expressão.
- São usadas para implementações pontuais e temporárias, como sobrescrever métodos seletivamente ou implementar interfaces rapidamente.
- Não podem ter construtores.
- Foram amplamente substituídas por lambdas no Java 8+, mas ainda são úteis quando você precisa implementar múltiplos métodos.

#### 4.4. Classes Aninhadas Estáticas
- Classes dentro de outras classes marcadas como estáticas.
- Não dependem de uma instância da classe externa para serem criadas.
- Usadas para agrupamento lógico, padrões como Builder ou estruturas auxiliares (Node, etc.). - Sintaxe:
``` java
Nested nested = new Nested();
```

---

## 5. Resumo dos Exercícios

Abaixo, segue uma descrição de cada módulo e o que ele demonstra.

---

### 5.1. Classes Internas Membro — Universidade e Aluno

Pacote: InnerClasses.InnerClasses

#### Tópicos:
- Classes Internas Membro
- Acoplamento forte entre entidades
- Acesso a atributos da classe externa

#### Descrição:
A classe Universidade contém uma classe interna Aluno.

Cada aluno está inerentemente vinculado à sua universidade, inclusive na saída de toString().

#### Funcionalidades:
- Buscar aluno por número de matrícula
- Remover aluno
- Contar o total de alunos
- Imprimir todos os alunos com referência à universidade à qual pertencem

#### Destaques Técnicos:
- Instanciação via u1.new Aluno(...)
- Uso de University.this.name dentro de Aluno
- Manipulação de listas de objetos de classes internas

---

### 5.2. Classes Locais — Evento e Ingresso

Pacote: InnerClasses.LocalClasses

#### Tópicos:
- Classes locais declaradas dentro de métodos
- Escopo restrito
- Métodos estáticos dentro de classes locais

#### Descrição:
Dentro do método `generateTickets()`, uma classe local `Ticket` é declarada.

Ela é usada exclusivamente dentro desse método para criar e manipular objetos de ingresso.

#### Recursos:
- Criação dinâmica de objetos de ingresso
- Impressão de ingressos
- Soma dos preços dos ingressos usando um método estático dentro da classe local

#### Destaques Técnicos:
- Classe local definida dentro de um método
- Lógica encapsulada restrita a cálculos internos
- Manipulação interna de listas com objetos criados dinamicamente

---

### 5.3. Classes Anônimas — Eventos, Notificadores e Notificações

Pacote: InnerClasses.AnonymousClasses

#### Tópicos:
- Criação de classes anônimas
- Sobrescrita de métodos inline
- Polimorfismo sem classes nomeadas
- Classes anônimas contendo classes locais

#### Descrição:
Este módulo demonstra:
- Implementações anônimas da interface Notifier (Email, SMS, Push).

- Subclasses anônimas de Notification sobrescrevendo o método send().

- Uma classe local dentro de uma classe anônima.

- Implementações anônimas da classe abstrata Alert.

#### Destaques Técnicos:
- new Interface() { ... }
- new Class() { @Override ... }
- Polimorfismo sem declarações de classe

- Comportamento encapsulado criado inline

---

### 5.4. Classes Aninhadas Estáticas — Biblioteca, Livro e Estatísticas

Pacote: InnerClasses.StaticNestedClasses

#### Tópicos:
- Classes Aninhadas Estáticas
- Agrupamento da lógica de domínio dentro de uma classe pai
- Classes utilitárias dentro de um contêiner de domínio

#### Descrição:
A classe Biblioteca contém:
- Livro — representa entidades de livro
- Estatísticas — calcula análises da lista de livros

Essas classes são estáticas e, portanto, não dependem de uma instância de Biblioteca.

#### Funcionalidades:
- Impressão de informações do livro
- Contagem do número total de livros
- Cálculo do ano médio de publicação

#### Destaques Técnicos:
- Instanciação via `new Book(...)`
- Independente da instância da classe externa
- Útil para agrupar utilitários relacionados ao domínio

---

#### 6. Objetivos de Aprendizagem

- Diferenças entre classes internas, locais, anônimas e estáticas aninhadas
- Quando e por que usar cada tipo
- Regras de encapsulamento e escopo em estruturas aninhadas
- Como as classes internas melhoram o design modular
- Como sobrescrever o comportamento dinamicamente usando classes anônimas
- Como organizar a lógica de domínio dentro de estruturas compostas
- Interação entre classes internas e listas
- Arquitetura limpa usando organização de Aplicação + Domínio

---