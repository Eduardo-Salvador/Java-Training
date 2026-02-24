<div align="center">

![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-<COLOR>.svg)

# Sistema de Seminários

Version 2.0 - Refactored

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral
O Sistema de Seminários é um sistema simples para registrar e gerenciar seminários, estudantes e professores, desenvolvido em Java com foco em lógica de programação, programação orientada a objetos (OOP) e manipulação básica de dados em memória.  

O usuário interage com o sistema através de um menu textual, podendo registrar, modificar e listar seminários, estudantes e professores.

---

## Novidades da v2.0:
- **Uso de Herança.**
- **Melhor compreensão da arquitetura do sistema.**
- **Uso de Generics.**
- **Uso de ArrayLists.**
- **Econômia de Código e modularização das classes ajudando a manutenção e entendimento.**

---

## Arquitetura:
- **Domain** → Classes e Entidades.
- **Services** → Regras de negócio e manipulação dos dados.
- **Controller** → Entrada e saída dos dados para o usuário.
- **App** → Inicializa a aplicação.

---

## O Que Foi Utilizado

### 1. Classes e Objetos:
O sistema é dividido em entidades principais:
- **Location** → Implementado como um *record*, representando o endereço do seminário.
- **Seminar** → Possui id, título e localização.
- **Students** → Possui id, nome, idade e associação opcional a um seminário.
- **Teacher** → Possui id, nome, especialidade e um conjunto de seminários ministrados.  
- **BaseService** → Classe pai dos outros services, compartilham 3 métodos em comum que são implementados por cada classe.
- **Services (Seminar, Students e Teacher)** → Regras de negócio específico de cada classe.

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



### 4. Generics
Generics permitem criar classes, interfaces e métodos que trabalham com diferentes tipos de dados mantendo segurança de tipos em tempo de compilação.

```java
public class Caixa<T> {
    private T conteudo;
    
    public void guardar(T item) {
        this.conteudo = item;
    }
    
    public T pegar() {
        return conteudo;
    }
}
```

### 5. Herança
Herança permite que uma classe (subclasse/filha) herde atributos e métodos de outra classe (superclasse/pai), promovendo reuso de código e hierarquia.

```java
public class Animal {
    protected String nome;
    public void comer() {
        System.out.println("Comendo...");
    }
}

public class Cachorro extends Animal {
    public void latir() {
    System.out.println("Au au!");
    }
}
```

### 6. Associações (Associações entre Classes):
O projeto demonstra diferentes formas de associação:
- Seminar possui uma Location (1:1)
- Student pode estar relacionado a um Seminar (N:1)
- Teacher pode ministrar vários Seminars (1:N com array)
- ArraysLists são usados para armazenar múltiplas entidades no sistema

### 7. ArrayLists:
Anteriormente manipulamos com Array, porém agora foi utilizado ArrayLists

```java
ArrayList<Students> students = new ArrayList<>();
etc...
```

Esses ArraysLists funcionam como “bancos de dados” simples em memória.

### 8. Menu Interativo (Console):
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
- Capturar entradas com Scanner no Controller.
- Validar opções no Service.
- Exibir mensagens ao usuário via Controller.
- Manipular objetos nos arrays via Service.

### 9. Condicionais:
O projeto utiliza extensivamente:
- `if / else`
- `switch` (ex.: alterar dados de seminário/estudante/professor)
- Validações com condições aninhadas.

Exemplo:

```java
switch (changeOption) {
    case 1: // alterar nome
    case 2: // alterar idade
    case 3: // alterar seminário
    ...
}
```

### 10. Laços:
Para percorrer arrays e exibir elementos:
- `for (Object obj : list)`

### 11. Manipulação de Entrada:
Uso de Scanner para:
- Strings
- Inteiros
- Caracteres (Y/N)
- Opções do menu  
Tratamento de `nextLine()` para evitar quebras de linha indesejadas.

---

## Funcionalidades

#### Registrar Seminários
- Título  
- Localização  
- Armazenamento no ArrayList  

#### Modificar Seminários
- Alterar título  
- Alterar localização  
- Alterar ambos  

#### Listar Seminários
- Exibe todos os seminários cadastrados

#### Registrar Estudantes
- Nome  
- Idade  
- Pergunta se o estudante pertence a um seminário  

#### Alterar Estudantes
- Alterar Nome  
- Alterar Idade  
- Alterar Seminário  
- Combinações das opções acima  

#### Listar Estudantes
- Com informações completas.  

#### Registrar Professores
- Nome  
- Especialidade  
- Seleção de vários seminários para lecionar  

#### Alterar Professores
- Alterar Nome  
- Alterar Especialidade  
- Alterar Seminários ministrados  
- Combinações das opções acima

#### Listar Professores
- Com informações completas.  

## Aprendizados
- Programação orientada a objetos.  
- Herança.
- Arquitetura de Software.
- Construtores e sobrecarga.
- Encapsulamento e acesso a atributos.
- Generics.
- ArraysLists como estrutura de armazenamento.  
- Associações entre objetos.
- Manipulação de dados no console. 
- Estruturas condicionais e laços.
- Navegação por menu interativo.
- Entrada e validação de dados pelo usuário.

---