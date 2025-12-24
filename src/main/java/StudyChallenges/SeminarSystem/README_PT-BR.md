<div align="center">

![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)

# Sistema de Seminários

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral
O Sistema de Seminários é um sistema simples para registrar e gerenciar seminários, estudantes e professores, desenvolvido em Java com foco em lógica de programação, programação orientada a objetos (OOP) e manipulação básica de dados em memória.  
O usuário interage com o sistema através de um menu textual, podendo registrar, modificar e listar seminários, estudantes e professores.

## Requisitos
Todo o código-fonte está organizado em pacotes:
- **SeminarSystem.Domain** → Contém as classes de domínio (Location, Seminar, Students, Teacher).
- **SeminarSystem.Controller** → Contém o menu principal, regras de cadastro, regras de modificação e regras de listagem.  
O projeto não utiliza banco de dados; tudo é armazenado em arrays na memória.

## O Que Foi Utilizado

### 1. Classes e Objetos:
O sistema é dividido em entidades principais:
- **Location** → Implementado como um *record*, representando o endereço do seminário.
- **Seminar** → Possui título e localização.
- **Students** → Possui nome, idade e associação opcional a um seminário.
- **Teacher** → Possui nome, especialidade e um conjunto de seminários ministrados.  
Esses objetos modelam o domínio de forma clara e orientada a objetos.

### 2. Encapsulamento:
Todas as classes (exceto o *record*) possuem:
- Atributos privados  
- Getters e setters  
- Construtores sobrecarregados  
- Método `toString()` quando necessário

### 3. Records:
A classe Location utiliza *records*, que oferecem imutabilidade e menos verbosidade:

```java
public record Location(String address) {}
```

### 4. Associações (Associações entre Classes):
O projeto demonstra diferentes formas de associação:
- Seminar possui uma Location (1:1)
- Student pode estar relacionado a um Seminar (N:1)
- Teacher pode ministrar vários Seminars (1:N com array)
- Arrays são usados para armazenar múltiplas entidades no sistema

### 5. Arrays:
Em vez de listas dinâmicas, o sistema usa arrays fixos para armazenar dados:

```java
Students[] students = new Students[100];
Seminar[] seminars = new Seminar[500];
Teacher[] teachers = new Teacher[100];
```

Esses arrays funcionam como “bancos de dados” simples em memória.

### 6. Menu Interativo (Console):
Toda a lógica do sistema é acessada por meio de um menu textual, exibido repetidamente ao usuário:

```
1. Registrar Seminário
2. Alterar Seminário
3. Listar Seminários
4. Registrar Estudante
5. Alterar Estudante
6. Listar Estudantes
7. Registrar Professor
8. Alterar Professor
9. Listar Professores
10. Sair
```

Cada opção chama métodos responsáveis por:
- Capturar entradas com Scanner
- Validar opções
- Exibir mensagens ao usuário
- Manipular objetos nos arrays

### 7. Condicionais:
O projeto utiliza extensivamente:
- `if / else`
- `switch` (ex.: alterar dados de seminário/estudante/professor)
- Validações com condições aninhadas

Exemplo:

```java
switch (changeOption) {
    case 1: // alterar nome
    case 2: // alterar idade
    case 3: // alterar seminário
    ...
}
```

### 8. Laços:
Para percorrer arrays e exibir elementos:
- `for (int i = 0; i < array.length; i++)`  
Usado para listar entidades e encontrar espaços vazios.

### 9. Manipulação de Entrada:
Uso de Scanner para:
- Strings
- Inteiros
- Caracteres (Y/N)
- Opções do menu  
Tratamento de `nextLine()` para evitar quebras de linha indesejadas.

---

## Funcionalidades

### ✔ Registrar Seminários
- Título  
- Localização  
- Armazenamento incremental no array  

### ✔ Modificar Seminários
- Alterar título  
- Alterar localização  
- Alterar ambos  

### ✔ Listar Seminários
- Exibe todos os seminários cadastrados.  

### ✔ Registrar Estudantes
- Nome  
- Idade  
- Pergunta se o estudante pertence a um seminário  

### ✔ Alterar Estudantes
Permite alterar:
- Nome  
- Idade  
- Seminário  
- Combinações das opções acima  

### ✔ Listar Estudantes
- Com informações completas.  

### ✔ Registrar Professores
- Nome  
- Especialidade  
- Seleção de vários seminários para lecionar  

### ✔ Alterar Professores
- Nome  
- Especialidade  
- Seminários ministrados  
- Combinações  

### ✔ Listar Professores
- Com informações completas.  

## Aprendizados
- Organização modular (pacotes Domain e Controller).
- Programação orientada a objetos.  
- Construtores e sobrecarga.
- Encapsulamento e acesso a atributos.
- Arrays como estrutura de armazenamento.  
- Associações entre objetos.
- Manipulação de dados no console. 
- Estruturas condicionais e laços.
- Navegação por menu interativo.
- Entrada e validação de dados pelo usuário.

---