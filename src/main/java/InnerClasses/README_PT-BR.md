# Inner Classes

---

## Visão Geral

Este projeto contém uma coleção de exercícios independentes focados em Classes Internas em Java, organizados nos seguintes pacotes:
- InnerClasses.InnerClasses — Classes Internas de Membro
- InnerClasses.LocalClasses — Classes Locais
- InnerClasses.AnonymousClasses — Classes Anônimas
- InnerClasses.StaticAlignedClasses — Classes Aninhadas Estáticas

### Cada exercício inclui:
- Uma estrutura mínima com camadas de Aplicação e Domínio
- Lógica independente demonstrando diferentes mecanismos de classes internas
- Um arquivo ProblemQuestion.txt descrevendo o exercício original
- Exemplos práticos de:
    - Classes Internas de Membro
    - Classes Locais
    - Classes Anônimas
    - Classes Aninhadas Estáticas
    - Encapsulamento e relações de escopo
    - Organização modular usando estruturas aninhadas

O objetivo é fornecer compreensão prática de como as classes internas funcionam, quando usá-las e como elas melhoram a organização, o encapsulamento e a expressividade do código.

---

## O que são Classes Internas?

Classes Internas são classes declaradas dentro de outras classes.  
Elas permitem:
- Forte agrupamento de classes relacionadas
- Acesso direto entre contextos interno e externo
- Maior encapsulamento
- Organização modular clara quando os objetos estão fortemente relacionados

---

## Tipos Principais

### 1. Classes Internas de Membro
- Declaradas dentro de outra classe (mas fora de métodos).
- Têm acesso total à instância da classe externa.

### 2. Classes Locais
- Classes declaradas dentro de um método.
- Só existem dentro do método onde são definidas.

### 3. Classes Anônimas
- Classes criadas sem nome, geralmente para sobrescrever métodos ou implementar interfaces inline.

### 4. Classes Aninhadas Estáticas
- Declaradas como estáticas.
- Não dependem de uma instância da classe externa.

---

## Conceitos Utilizados

### Os exercícios demonstram:
- Composição de objetos através de classes internas
- Acesso à classe externa via OuterClass.this
- Regras de escopo local
- Polimorfismo com classes anônimas
- Estruturas aninhadas estáticas e não estáticas
- Iteração sobre listas de objetos aninhados
- Organização modular orientada a domínio

---

## Resumo dos Exercícios

Abaixo está a descrição de cada módulo e o que ele demonstra.

---

### 1. Classes Internas de Membro — Universidade & Estudante

    Package: InnerClasses.InnerClasses

#### Tópicos:
- Classes Internas de Membro
- Forte acoplamento entre entidades
- Acesso a atributos da classe externa

#### Descrição:
A classe University contém uma classe interna Student.  
Cada estudante está intrinsecamente ligado à sua universidade, inclusive na saída de toString().

#### Funcionalidades:
- Buscar estudante pelo número de matrícula
- Remover estudante
- Contar total de estudantes
- Imprimir todos os estudantes com referência à universidade pai

#### Destaques Técnicos:
- Instanciação via u1.new Student(...)
- Uso de University.this.name dentro de Student
- Manipulação de listas de objetos de classe interna

---

### 2. Classes Locais — Evento & Ingresso

    Package: InnerClasses.LocalClasses

#### Tópicos:
- Classes Locais declaradas dentro de métodos
- Escopo restrito
- Métodos estáticos dentro de classes locais

#### Descrição:
Dentro de generateTickets(), uma classe local Ticket é declarada.  
Ela é usada apenas dentro desse método para criar e manipular objetos de ingresso.

#### Funcionalidades:
- Criação dinâmica de objetos de ingresso
- Impressão de ingressos
- Soma dos preços dos ingressos usando um método estático dentro da classe local

#### Destaques Técnicos:
- Classe local definida dentro de um método
- Lógica encapsulada restrita ao cálculo interno
- Manipulação de lista interna com objetos criados dinamicamente

---

### 3. Classes Anônimas — Eventos, Notificadores & Notificações

    Package: InnerClasses.AnonymousClasses

#### Tópicos:
- Criação de classes anônimas
- Sobrescrita de métodos inline
- Polimorfismo sem classes nomeadas
- Classes locais dentro de classes anônimas

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

### 4. Classes Aninhadas Estáticas — Biblioteca, Livro & Estatísticas

    Package: InnerClasses.StaticAlignedClasses

#### Tópicos:
- Classes Aninhadas Estáticas
- Agrupamento de lógica de domínio dentro de uma classe pai
- Classes utilitárias vivendo dentro de um contêiner de domínio

#### Descrição:
A classe Library contém:
- Book — representa entidades de livro
- Statistics — calcula análises sobre a lista de livros

Essas classes são estáticas e, portanto, não dependem de uma instância de Library.

#### Funcionalidades:
- Impressão de informações de livros
- Contagem total de livros
- Cálculo da média do ano de publicação

#### Destaques Técnicos:
- Instanciação via new Library.Book(...)
- Independência da instância da classe externa
- Útil para agrupar utilitários relacionados ao domínio

---

#### Objetivos de Aprendizagem

- Diferenças entre Classes Internas de Membro, Locais, Anônimas e Aninhadas Estáticas
- Quando e por que usar cada tipo
- Encapsulamento e regras de escopo em estruturas aninhadas
- Como classes internas melhoram o design modular
- Como sobrescrever comportamento dinamicamente usando classes anônimas
- Como organizar lógica de domínio dentro de estruturas compostas
- Interação entre classes internas e listas
- Arquitetura limpa usando organização Aplicação + Domínio

---
