<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Records

Este módulo cobre Records, uma forma compacta de declarar classes de dados imutáveis introduzida no Java 16. O compilador gera automaticamente o construtor, getters, `equals()`, `hashCode()` e `toString()` com base nos campos declarados.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Arquitetura

Cada pasta contém a classe do exercício com sua implementação e um arquivo `problemQuestion.txt` com a descrição do exercício.

- **Exercise**: `TransactionRecord`, `Main`

---

## 1. Visão Geral

Um record substitui uma classe que apenas carrega dados. Uma linha substitui construtor, getters, `equals()`, `hashCode()` e `toString()`.

Todos os campos são implicitamente `final`. Você não pode alterar o estado de um record depois de criado, o que o torna naturalmente thread-safe sem nenhuma sincronização.

---

## 2. Records em Contextos Concorrentes

Por serem imutáveis, records são ideais para transportar dados entre threads sem risco de race condition. Em cadeias de `CompletableFuture` você frequentemente precisa passar resultados de uma etapa para a próxima, um record é uma escolha natural.

A imutabilidade força transformação em vez de mutação. Quando uma etapa precisa alterar um valor, ela cria um novo record em vez de modificar o existente, tornando o fluxo de dados explícito e seguro.

---

## 3. Prós e Contras

**Prós:** elimina boilerplate, imutabilidade garantida pelo compilador, thread-safe por natureza, semântica de valor, dois records com os mesmos dados são iguais, nomenclatura expressiva que documenta a intenção.

**Contras:** não pode herdar de outra classe, apenas implementar interfaces. Todos os campos são obrigatoriamente `final`, se estado mutável for necessário, record não serve. Não é adequado para entidades que exigem construtor sem argumentos e campos mutáveis. Não é possível adicionar campos de instância além dos declarados no header.

---

## 4. Quando Usar e Quando Não Usar

Use quando o objeto é puramente um portador de dados, quando imutabilidade é desejada, quando os dados vão trafegar entre threads, quando precisar de um DTO simples em uma API, ou quando substituir classes que só têm campos e getters.

Não use quando herança for necessária, quando a entidade tem estado mutável, quando é uma entidade de banco de dados, ou quando a classe exige lógica de negócio complexa.

---

## 5. Exercício

### 5.1. Exercise — Transaction Record

Constrói um `TransactionRecord` imutável com validação no construtor compacto para amount, sender e receiver. Implementa um construtor alternativo que gera um `transactionId` aleatório via `UUID.randomUUID()` e define o status inicial como `"PENDING"`. Processa 3 transações em um pipeline de `CompletableFuture` que avalia cada uma e cria um novo record com status `"APPROVED"` ou `"REQUIRES_REVIEW"` com base no valor da transação.

**Conceitos chave:** construtor compacto, validação de campos, construtor alternativo, método próprio, imutabilidade forçando transformação no `thenApply`, integração com `CompletableFuture`.

---

## 6. Resultados

- Substituição de classes de dados com boilerplate por records
- Construtor compacto para validação e transformação de campos antes da atribuição
- Construtores alternativos delegando para o canônico
- Métodos próprios dentro de records
- Imutabilidade como garantia natural de thread safety
- Records como portadores de dados em cadeias de `CompletableFuture`
- Padrão de transformação: criando novos records em vez de mutar os existentes

---