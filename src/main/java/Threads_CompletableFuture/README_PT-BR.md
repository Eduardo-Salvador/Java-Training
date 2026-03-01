<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# CompletableFuture

Este módulo cobre programação assíncrona em Java usando `CompletableFuture`, parte do pacote `java.util.concurrent` introduzido no Java 8. Permite encadeamento fluente, combinação de tarefas paralelas, tratamento de erros e integração com a API de `Stream`, tudo sem bloquear a thread principal.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Arquitetura

Cada pasta contém a classe do exercício com sua implementação e um arquivo `problemQuestion.txt` com a descrição do exercício.

- **SimpleAsyncChaining**: `OrderProcessor`, `Order`
- **CombiningMultipleTasks**: `ProductSearch`
- **ErrorHandlingDifferenceAndFallback**: `PaymentService`
- **ThreadFactoryAndPool**: `ReportService`
- **StreamsWithCompletableFuture**: `DataPipeline`

---

## 1. Visão Geral

`CompletableFuture` usa interfaces funcionais modernas do Java 8. `supplyAsync()` recebe um `Supplier<T>` e `runAsync()` recebe um `Runnable` e na prática você sempre passa lambda. Sem classe para instanciar, sem interface para implementar, sem pool para gerenciar manualmente.

Por padrão usa o `ForkJoinPool.commonPool()`, um pool global gerenciado pela JVM. Sem necessidade de `shutdown()`. Se quiser threads controladas, passe um pool customizado com `ThreadFactory` como segundo argumento do `supplyAsync()`.

`get()` e `join()` são opcionais, use apenas quando precisar do retorno para continuar o programa. `join()` é preferido porque lança `CompletionException` (unchecked) em vez de `ExecutionException` (checked).

---

## 2. Criação

```java
// Com retorno
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "resultado");

// Sem retorno
CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> System.out.println("rodando"));

// Já com valor pronto (útil para testes e fallbacks)
CompletableFuture<String> cf = CompletableFuture.completedFuture("valor imediato");

// Com pool customizado
ExecutorService pool = Executors.newFixedThreadPool(4, minhaFactory);
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> buscar(), pool);
```

---

## 3. Transformação

`thenApply()` transforma o valor e continua a cadeia com o novo valor, o `map` dos streams. `thenAccept()` consome o valor final sem retornar nada. `thenRun()` nem recebe o valor, apenas executa uma ação quando a etapa anterior terminar.

```java
CompletableFuture.supplyAsync(() -> "eduardo")
    .thenApply(nome -> nome.toUpperCase())
    .thenApply(nome -> "Olá, " + nome)
    .thenAccept(System.out::println); // "Olá, EDUARDO"
```

---

## 4. Combinação de Múltiplas Tarefas

`thenCombine()` espera dois `CompletableFuture` terminarem e combina os dois resultados. `allOf()` espera todas as tarefas e retorna `CompletableFuture<Void>`, então você busca os valores manualmente depois. `anyOf()` retorna o resultado do primeiro que terminar.

```java
loja1.thenCombine(loja2, Integer::min)
     .thenCombine(loja3, Integer::min)
     .thenAccept(min -> System.out.println("Menor: $" + min));

CompletableFuture.allOf(cf1, cf2, cf3)
    .thenRun(() -> System.out.println("Todos concluídos"));
```

---

## 5. Tratamento de Erro

`exceptionally()` captura a exceção e retorna um valor padrão para a cadeia continuar, só executa quando há erro. `handle()` recebe o resultado e a exceção e sempre executa, com ou sem erro.

```java
// exceptionally — fallback só no erro
.exceptionally(ex -> "valor padrão")

// handle — sempre executa
.handle((resultado, ex) -> {
    if (ex != null) return "erro: " + ex.getCause().getMessage();
    return resultado;
})
```

Sempre use `ex.getCause().getMessage()`, a exceção recebida é uma `CompletionException` que envolve a original.

---

## 6. Stream dentro de CompletableFuture

Dentro do lambda você escreve Java normal, incluindo streams. O `CompletableFuture` só gerencia quando aquele bloco vai rodar e o que fazer com o resultado.

```java
CompletableFuture.supplyAsync(() -> buscarLista())
    .thenApply(lista -> lista.stream()
        .filter(x -> x > 0)
        .map(x -> x * 3)
        .collect(Collectors.toList()))
    .thenApply(lista -> lista.stream()
        .reduce(0, Integer::sum))
    .thenAccept(System.out::println);
```

Evite `parallelStream()` com o pool padrão, os dois usam `ForkJoinPool.commonPool()` e vão competir pelos mesmos recursos.

---

## 7. ThreadFactory

`ThreadFactory` define como as threads são criadas. Use para dar nomes descritivos para facilitar o debug, configurar daemon threads ou definir prioridade.

```java
ThreadFactory factory = new ThreadFactory() {
    private int count = 1;

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("report-worker-" + count++);
        t.setDaemon(true);
        return t;
    }
};

ExecutorService pool = Executors.newFixedThreadPool(3, factory);
CompletableFuture.supplyAsync(() -> gerarRelatorio(), pool)
    .thenAccept(System.out::println);
```

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `newThread(Runnable r)` | `Thread` | Cria e retorna uma nova Thread para executar a tarefa fornecida |

---

## 8. Tabela de Métodos Principais

| Método | Recebe | Retorna | Quando usar |
|--------|--------|---------|-------------|
| `supplyAsync(Supplier)` | `Supplier<T>` | `CompletableFuture<T>` | Tarefa com retorno |
| `runAsync(Runnable)` | `Runnable` | `CompletableFuture<Void>` | Tarefa sem retorno |
| `completedFuture(valor)` | valor | `CompletableFuture<T>` | Valor já disponível |
| `thenApply(Function)` | `Function<T,U>` | `CompletableFuture<U>` | Transformar resultado |
| `thenAccept(Consumer)` | `Consumer<T>` | `CompletableFuture<Void>` | Consumir resultado |
| `thenRun(Runnable)` | `Runnable` | `CompletableFuture<Void>` | Ação sem usar resultado |
| `thenCombine(cf, BiFunction)` | CF + BiFunction | `CompletableFuture<V>` | Combinar dois resultados |
| `allOf(cf...)` | vários CF | `CompletableFuture<Void>` | Esperar todos terminarem |
| `anyOf(cf...)` | vários CF | `CompletableFuture<Object>` | Pegar o mais rápido |
| `exceptionally(Function)` | `Function<Throwable,T>` | `CompletableFuture<T>` | Fallback em caso de erro |
| `handle(BiFunction)` | `BiFunction<T,Throwable,U>` | `CompletableFuture<U>` | Tratar resultado ou erro |
| `get()` | nenhum | `T` | Bloquear e pegar valor (checked exception) |
| `join()` | nenhum | `T` | Igual get() sem checked exception |
| `isDone()` | nenhum | `boolean` | Verificar sem bloquear |
| `cancel(bool)` | nenhum | `boolean` | Tentar cancelar a tarefa |

---

## 9. Exercícios

### 9.1. SimpleAsyncChaining — Order Processor

Constrói um pipeline de pedidos de e-commerce que busca pedidos de forma assíncrona, aplica 10% de desconto em um estágio `thenApply` e imprime os resultados via `thenAccept`. Roda 3 cadeias independentes de forma concorrente e usa `allOf().join()` para esperar todas antes que a main thread encerre.

**Conceitos chave:** `supplyAsync`, `thenApply`, `thenAccept`, `allOf`, instâncias independentes por cadeia.

---

### 9.2. CombiningMultipleTasks — Product Search

Simula a busca pelo menor preço de um produto em 3 lojas rodando em paralelo. Encadeia duas chamadas `thenCombine()` usando `Integer::min` para encontrar o menor preço. Também demonstra `allOf()` com 5 tarefas independentes imprimindo um resumo somente após todas concluírem.

**Conceitos chave:** `thenCombine`, `allOf`, `Integer::min` como method reference, tarefas paralelas independentes.

---

### 9.3. ErrorHandlingDifferenceAndFallback — Payment Service

Processa pagamentos em um pipeline de validação que lança exceções diferentes para valores inválidos e acima do limite. Implementa o mesmo pipeline duas vezes — uma com `exceptionally()` para fallback simples, e outra com `handle()` para imprimir o status antes de retornar o resultado.

**Conceitos chave:** `exceptionally`, `handle`, `ex.getCause().getMessage()`, comportamento só no erro vs sempre executa.

---

### 9.4. ThreadFactoryAndPool — Report Service

Gera 6 relatórios usando um pool fixo de 3 threads construído com `ThreadFactory` customizada que nomeia as threads `report-worker-N` e as marca como daemon. Passa o pool como segundo argumento do `supplyAsync()`, tornando os nomes das threads visíveis no output. Mede e imprime o tempo total de execução.

**Conceitos chave:** `ThreadFactory`, `setName()`, `setDaemon()`, pool customizado com `supplyAsync`, `allOf`, medição de tempo de execução.

---

### 9.5. StreamsWithCompletableFuture — Data Pipeline

Busca uma lista de 10 inteiros aleatórios de forma assíncrona e a processa em dois estágios `thenApply` encadeados: o primeiro filtra positivos e multiplica por 3, o segundo reduz para uma soma. Roda dois pipelines independentes de forma concorrente e combina as somas finais com `thenCombine(Integer::sum)`.

**Conceitos chave:** stream dentro de `thenApply`, `filter`, `map`, `reduce`, `thenCombine`, `exceptionally` com valor padrão.

---

## 10. Resultados

- Execução assíncrona de tarefas com `supplyAsync` e `runAsync` sem Runnable nem Callable
- Encadeamento fluente com `thenApply`, `thenAccept` e `thenRun`
- Combinação de tarefas paralelas com `thenCombine`, `allOf` e `anyOf`
- Tratamento de erro com `exceptionally` para fallback e `handle` para controle total
- Processamento com streams dentro de cadeias de `CompletableFuture`
- Controle customizado de threads com `ThreadFactory` e daemon threads nomeadas
- Passagem de pool customizado para `supplyAsync` para gerenciamento de threads em produção
- Uso de `join()` em vez de `get()` para evitar checked exceptions em cadeias assíncronas

---