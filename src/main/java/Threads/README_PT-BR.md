<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Threads

Este módulo cobre o uso de Threads em Java, desde criação e ciclo de vida até sincronização avançada, comunicação entre threads e paralelismo com ExecutorService.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Uma thread é a menor unidade de processamento gerenciada pelo sistema operacional. Todo programa Java inicia com a thread principal (main), a partir da qual novas threads podem ser criadas para executar tarefas de forma concorrente ou paralela.

**Concorrência** é quando múltiplas tarefas progridem no mesmo período de tempo, alternando em um único core. **Paralelismo** é quando executam simultaneamente em núcleos diferentes.

---

## Arquitetura

- **Domain**: Classes de modelo (`Account`, `Product`, `Member`, etc.)
- **Application**: Classes `Main` com a implementação de cada exercício
- **2 Exercícios Progressivos**: Sincronização com wait/notify e paralelismo com ExecutorService

---

## 1. Conceitos Fundamentais

### 1.1. Estados de uma Thread

```
New -> Runnable <-> Running -> Dead
                      |
                Waiting/Blocked
```

| Estado | Descrição |
|--------|-----------|
| New | Thread instanciada, ainda não iniciada |
| Runnable | `start()` chamado, pronta para executar |
| Running | Sendo executada pelo Scheduler |
| Waiting/Blocked | Aguardando recurso, lock ou sinal |
| Dead | Execução concluída |

### 1.2. Criando Threads

**Implementando Runnable** (forma recomendada):
```java
class MinhaTask implements Runnable {
    @Override
    public void run() {
        // tarefa aqui
    }
}

Thread t1 = new Thread(new MinhaTask());
t1.start();
```

**Com lambda:**
```java
new Thread(() -> System.out.println("rodando")).start();
```

`implements Runnable` é preferível a `extends Thread` pois Java não possui herança múltipla, e estender Thread impede herdar de qualquer outra classe. A classe define uma tarefa, não uma thread.

### 1.3. Métodos Principais

| Método | Descrição |
|--------|-----------|
| `start()` | Inicia nova thread |
| `run()` | Executa na thread main, não cria nova thread |
| `sleep(ms)` | Pausa a thread atual e mantém o lock |
| `join()` | Bloqueia a thread atual até outra terminar |
| `yield()` | Sugere ao Scheduler pausar a thread atual |
| `setPriority(1-10)` | Define prioridade de execução |

### 1.4. Sincronização e Race Condition

Race Condition ocorre quando duas ou mais threads acessam e modificam dados compartilhados ao mesmo tempo, gerando resultados imprevisíveis. `synchronized` garante que apenas uma thread por vez execute o trecho protegido:

```java
public synchronized void saque(int valor) {
    if (conta.getSaldo() >= valor) {
        conta.debitar(valor);
    }
}
```

Pode ser aplicado no método inteiro ou em um bloco específico para maior granularidade. O lock pertence ao objeto. Para compartilhar o lock entre todas as instâncias, usa-se `static synchronized`.

`sleep` mantém o lock. `wait()` libera o lock e aguarda notificação.

### 1.5. Wait, Notify e NotifyAll

Mecanismo de comunicação entre threads. Devem ser chamados dentro de um bloco `synchronized`:

| Método | Comportamento |
|--------|--------------|
| `wait()` | Libera o lock e aguarda notificação |
| `notify()` | Acorda uma thread em wait (JVM decide qual) |
| `notifyAll()` | Acorda todas as threads em wait |

### 1.6. Volatile

Cada core do processador possui seu próprio cache. Uma thread pode modificar uma variável sem que as demais enxerguem a mudança. `volatile` força leitura e escrita direto na memória principal, garantindo visibilidade entre threads:

```java
private volatile boolean running = true;
```

`synchronized` resolve atomicidade. `volatile` resolve visibilidade. Os dois problemas são distintos e um não substitui o outro.

### 1.7. Deadlock

Ocorre quando duas threads aguardam indefinidamente uma pela outra para liberar locks:

```
Thread 1 segura Obj A e espera Obj B
Thread 2 segura Obj B e espera Obj A
```

---

## 2. ExecutorService e ThreadPool

O pool mantém um conjunto fixo de threads reutilizáveis. Quando uma tarefa termina, a thread volta ao pool e pega a próxima da fila, eliminando o custo de criar e destruir threads a cada operação:

```java
ExecutorService pool = Executors.newFixedThreadPool(4);

pool.execute(new MinhaTask());
Future<Produto> f = pool.submit(new BuscarProduto());

pool.shutdown();
```

| Método | Aceita | Retorno |
|--------|--------|---------|
| `execute()` | Runnable | void |
| `submit()` | Runnable ou Callable | Future |

### 2.1. Callable e Future

`Callable<T>` executa uma tarefa e retorna valor, diferente do `Runnable` que não retorna nada:

```java
public class BuscarProduto implements Callable<Produto> {
    @Override
    public Produto call() throws Exception {
        return repositorio.buscar();
    }
}
```

`Future<T>` carrega o resultado da tarefa. A thread do pool preenche o Future ao terminar, e a main recupera o valor quando necessário:

```java
Future<Produto> futuro = pool.submit(new BuscarProduto());

Produto p = futuro.get();
Produto p = futuro.get(5, TimeUnit.SECONDS);
boolean pronto = futuro.isDone();
futuro.cancel(true);
```

---

## 3. Thread Manual vs ExecutorService

| Situação | Abordagem |
|----------|-----------|
| Tarefa simples sem retorno | Thread + Runnable |
| Tarefa com retorno | ExecutorService + Callable + Future |
| Múltiplas tarefas em paralelo | ExecutorService + pool fixo |
| Controle de timeout | Future.get(n, TimeUnit) |
| Projetos reais e APIs modernas | ExecutorService ou Spring @Async |

Thread manual com Runnable é o fundamento da concorrência em Java. Em produção: ExecutorService, que oferece controle de pool, retorno de valores via Future e gerenciamento de timeout.

---

## 4. Exercícios

### 4.1. Sistema de Entrega de E-mails

**Conceitos:** `wait()`, `notifyAll()`, `synchronized`, Thread Safety

Fila compartilhada de e-mails processada por múltiplas threads. As threads aguardam em modo de espera quando a fila está vazia e são notificadas ao receber novos e-mails.

**Classes:**
- `Members`: gerencia a fila com `wait/notifyAll`
- `EmailDeliveryService`: task que implementa `Runnable`

**Fluxo:**
```
main adiciona email -> notifyAll() -> threads acordam -> uma entrega -> outras voltam a wait()
```

### 4.2. Buscador de Produtos Concorrente

**Conceitos:** `ExecutorService`, `Callable`, `Future` com timeout, `volatile`

Busca simultânea do produto mais barato em 3 lojas. Cada loja é uma task independente com timeout de 5 segundos. Uma variável `volatile` controla o estado do sistema e é visível entre todas as threads.

**Classes:**
- `Product`: entidade de domínio
- `StoreA`, `StoreB`, `StoreC`: tasks que implementam `Callable<Product>`
- `Main`: orquestra o pool, coleta os Futures e exibe o menor preço

**Fluxo:**
```
pool submete 3 tasks -> executam em paralelo -> Future.get(5s) coleta resultados -> exibe menor preco
```

---

## 5. Resultados

- Criação e ciclo de vida de threads com Runnable e Thread
- Sincronização com `synchronized`, locks por objeto e por classe
- Comunicação entre threads com `wait`, `notify` e `notifyAll`
- Visibilidade de variáveis compartilhadas com `volatile`
- Gerenciamento de pool com `ExecutorService`
- Tarefas com retorno usando `Callable` e `Future`
- Controle de timeout com `Future.get(n, TimeUnit)`
- Race Condition e Deadlock: identificação e prevenção