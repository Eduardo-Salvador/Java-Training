# Streams

---

## Introdução

Este módulo contém exercícios práticos sobre **Java Streams**, abordando diferentes operações como **filter**, **map**, **reduce**, **generate**, **summaryStatistics**, entre outras.  
O objetivo é demonstrar como utilizar a API de Streams para manipular coleções de dados de forma declarativa, expressiva e eficiente.

---

### O que são Streams?

Streams em Java são uma abstração que permite processar coleções de dados de maneira funcional.  
Principais características:

- Não armazenam dados, apenas processam fluxos.
- Permitem operações **intermediárias** (como `filter`, `map`, `sorted`) e **terminais** (como `collect`, `reduce`, `forEach`).
- Facilitam paralelização e tornam o código mais legível e conciso.

---

## Estrutura dos Exercícios

Cada exercício está acompanhado de um arquivo `problemquestion.txt` que descreve o enunciado do problema.  
Abaixo estão os módulos e suas respectivas funcionalidades:

### 1. Generate and Summarizing

**Pacote:** `Streams.GenerateAndSummarizing`

- Geração de arrays com `IntStream.range`.
- Uso de `Stream.generate` para criar valores aleatórios.
- Aplicação de `DoubleSummaryStatistics` para sumarizar depósitos em transações:
  - Soma
  - Média
  - Máximo
  - Mínimo
  - Contagem
- Desafio extra: sumarizar números aleatórios gerados dinamicamente.

---

### 2. Reduce

**Pacote:** `Streams.Reduce`

- Criação de entidades: `Product`, `Item`, `Order`.
- Uso de `reduce` para:
  - Calcular o valor total de pedidos.
  - Encontrar o maior valor de requisição.
- Agrupamento com `Collectors.groupingBy` e `Collectors.reducing` para identificar o produto mais pedido.

---

### 3. Streams Básico

**Pacote:** `Streams.Streams`

- Criação de lista de `Employee`.
- Operações com Streams:
  - `filter`: selecionar funcionários com salário acima de 5000.
  - `map`: transformar em strings formatadas.
  - `sorted`: ordenar alfabeticamente.
  - `distinct`: remover duplicados.
  - `count`: contar elementos filtrados.
  - `max` e `min`: encontrar maior e menor salário.
  - `anyMatch`, `allMatch`, `noneMatch`: verificar condições sobre idade, salário e nomes.

---

## Organização

Cada exercício possui:
- Código fonte em Java.
- Arquivo `problemquestion.txt` com a descrição do problema.

---

## Objetivo

O objetivo destes exercícios é consolidar o entendimento da API de Streams em Java, explorando desde operações básicas até reduções e sumarizações mais avançadas.  
Eles servem como prática para quem deseja escrever código mais funcional, expressivo e eficiente em Java.

---
