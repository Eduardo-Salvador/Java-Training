<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Concorrência de Threads: Executors, ThreadPool, Atomic, Locks e Conditions

Este módulo cobre o conjunto completo de ferramentas de concorrência em Java, desde o framework Executor que gerencia pools de threads de forma eficiente, até operações atômicas, locks explícitos e sincronização baseada em condições. Todas as ferramentas fazem parte do pacote `java.util.concurrent` introduzido no Java 5.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Em vez de criar e destruir threads manualmente para cada tarefa, o framework Executor mantém um pool de threads reutilizáveis. Quando uma tarefa termina, a thread volta ao pool e pega a próxima da fila, eliminando o overhead do ciclo de vida das threads.

O pool brilha quando você tem muitas tarefas paralelas. 4 threads podem atender milhares de tarefas sequencialmente sem o custo de criar e destruir uma thread por tarefa.

O `synchronized` continua sendo sua responsabilidade independente da ferramenta usada. O pool apenas gerencia quantas threads existem e distribui as tarefas — ele não protege recursos compartilhados por você.

---

## Arquitetura

Cada pasta contém a classe do exercício com sua implementação e um arquivo `problemQuestion.txt` com a descrição do exercício.

- **NewCachedThreadPool**: `ImageProcessor`
- **NewFixedThreadPool**: `ReportGenerator`
- **NewScheduledThreadPool**: `TaskScheduler`
- **NewSingleThreadExecutor**: `DatabaseWriter`
- **NewSingleThreadScheduledExecutor**: `TaskScheduler`
- **NewWorkStealingPool**: `DataAnalyzer`
- **AtomicInteger**: `Counter`, `Main`
- **ReentrantLock**: `Counter`, `Workers`, `Main`
- **Conditions**: `SharedBuffer`, `Producer`, `Consumer`, `Main`
- **ReentrantReadWriteLock**: `ConfigCache`, `Main`

---

## 1. Framework Executor

### 1.1. Como o Pool Funciona

```
Tarefas submetidas: [t1] [t2] [t3] [t4] [t5] [t6] ... [t100]
Threads do pool:    [T1] [T2] [T3] [T4]
Fila de espera:     [t5] [t6] [t7] ... [t100]
```

As threads do pool pegam uma tarefa cada. Quando uma termina, automaticamente pega a próxima da fila. O pool gerencia essa distribuição internamente.

### 1.2. Runnable vs Callable

Toda thread precisa de uma task, seja `Runnable` ou `Callable`. O que varia é onde a task é definida: em uma classe separada, em uma variável na main, ou inline com lambda. Mas sempre tem uma.

| | Runnable | Callable |
|--|---------|---------|
| Retorno | Não | Sim, via `Future<T>` |
| Checked exceptions | Não | Sim |
| Método | `run()` | `call()` |
| Usado com | `Thread` ou `ExecutorService` | Apenas `ExecutorService` |

`Runnable` com `execute()` quando não precisa de retorno. `Callable` com `submit()` quando precisa de retorno, aí o `Future` faz sentido.

### 1.3. Uso Básico

```java
ExecutorService pool = Executors.newFixedThreadPool(4);

pool.execute(new MyTask());                           // Runnable, sem retorno
Future<Product> f = pool.submit(new FetchProduct());  // Callable, retorna Future

pool.shutdown();
```

### 1.4. Métodos Principais do ExecutorService

```java
pool.execute(runnable)             // submete Runnable, sem retorno
pool.submit(task)                  // submete Runnable ou Callable, retorna Future
pool.shutdown()                    // para de aceitar novas tasks, espera as atuais terminarem
pool.shutdownNow()                 // tenta cancelar tudo imediatamente
pool.isShutdown()                  // true se shutdown foi chamado
pool.isTerminated()                // true se todas as tasks terminaram após shutdown
pool.awaitTermination(n, TimeUnit) // bloqueia até todas terminarem ou o tempo esgotar
```

### 1.5. Executors Disponíveis

| Factory | O que faz | Ideal para |
|---------|-----------|------------|
| `newCachedThreadPool()` | Cria threads sob demanda, reutiliza ociosas | Muitas tarefas curtas e imprevisíveis |
| `newFixedThreadPool(n)` | Número fixo de threads, enfileira o excesso | Consumo controlado de recursos |
| `newScheduledThreadPool(n)` | Agenda tarefas após delay ou periodicamente | Jobs recorrentes, health checks |
| `newSingleThreadExecutor()` | Thread única, execução sequencial | Ordem garantida, sem paralelismo |
| `newSingleThreadScheduledExecutor()` | Thread única com agendamento | Agendamento sequencial |
| `newWorkStealingPool()` | Usa todos os cores disponíveis, work-stealing | Tarefas pesadas, paralelismo máximo |

---

## 2. Callable e Future

### 2.1. O Problema que o Callable Resolve

`Runnable` tem duas limitações: `run()` não retorna valor e não lança checked exceptions. `Callable<T>` resolve os dois usando `call()` no lugar de `run()`.

`Callable` é uma interface funcional com um único método e só funciona com `ExecutorService`. A classe `Thread` aceita apenas `Runnable`.

```java
public class BuscarUsuario implements Callable<Usuario> {
    private int id;

    public BuscarUsuario(int id) {
        this.id = id;
    }

    @Override
    public Usuario call() throws Exception {
        return bancoDeDados.buscar(id);
    }
}
```

### 2.2. Como o Future Funciona

A main não tem acesso direto ao que outra thread retorna. O `Future` funciona como um intermediário: a thread do pool preenche quando termina, e a main busca quando precisar. É basicamente uma caixinha compartilhada entre as duas threads.

A execução é assíncrona. A obtenção do resultado é potencialmente síncrona. O `get()` só bloqueia se a tarefa ainda não terminou. Se já terminou, retorna imediatamente com o valor, lança `ExecutionException` se a tarefa terminou com erro, ou lança `CancellationException` se foi cancelada.

```java
Future<Usuario> future = pool.submit(new BuscarUsuario(1));

// main continua executando aqui

Usuario usuario = future.get(); // bloqueia apenas se a tarefa ainda não terminou
```

### 2.3. Fluxo Assíncrono

```
1. main cria o ExecutorService
2. main envia a tarefa com submit()
3. a tarefa começa a rodar em outra thread do pool
4. main continua executando normalmente
5. só quando main chama future.get() é que pode haver bloqueio
```

O `get()` é opcional. Você só usa quando precisa do retorno para continuar o programa. Se a tarefa é só processar algo e você não precisa do resultado, submete e esquece, igual ao `execute()` com `Runnable`.

### 2.4. Métodos do Callable

| Método | Descrição |
|--------|-----------|
| `call()` | Executa a tarefa, retorna resultado do tipo `T`, pode lançar checked exception |

### 2.5. Métodos do Future

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `get()` | `V` | Bloqueia até obter o resultado |
| `get(timeout, unit)` | `V` | Bloqueia até o timeout, lança `TimeoutException` se esgotar |
| `cancel(mayInterrupt)` | `boolean` | Tenta cancelar. `true` interrompe se já iniciou |
| `isCancelled()` | `boolean` | True se cancelada antes de concluir |
| `isDone()` | `boolean` | True se concluiu por qualquer motivo |

### 2.6. Exceções Lançadas pelo `get()`

| Exceção | Quando ocorre |
|---------|---------------|
| `ExecutionException` | A tarefa lançou uma exceção internamente |
| `InterruptedException` | A thread que espera foi interrompida |
| `TimeoutException` | O tempo limite de `get(timeout, unit)` foi atingido |
| `CancellationException` | A tarefa foi cancelada antes de concluir |

---

## 3. Métodos por Implementação de Executor

### `newCachedThreadPool`

Cria threads sob demanda e reutiliza as ociosas. Instanciado como `ExecutorService`.

| Método | O que faz |
|--------|-----------|
| `submit(Runnable)` | Envia tarefa, cria nova thread se nenhuma disponível |
| `submit(Callable<T>)` | Envia tarefa com retorno, cria thread se necessário |
| `execute(Runnable)` | Envia tarefa sem retorno |
| `shutdown()` | Para de aceitar tarefas, termina as em andamento |
| `shutdownNow()` | Tenta cancelar tudo imediatamente |
| `awaitTermination(time, unit)` | Bloqueia até todas terminarem ou tempo esgotar |

---

### `newFixedThreadPool(n)`

Mantém número fixo de threads, enfileira o excesso. Instanciado como `ExecutorService`.

| Método | O que faz |
|--------|-----------|
| `submit(Runnable)` | Envia tarefa, enfileira se todas as threads ocupadas |
| `submit(Callable<T>)` | Envia tarefa com retorno, enfileira se necessário |
| `execute(Runnable)` | Envia tarefa sem retorno |
| `shutdown()` | Para de aceitar tarefas, termina as em andamento |
| `shutdownNow()` | Tenta cancelar tudo imediatamente |
| `awaitTermination(time, unit)` | Bloqueia até todas terminarem ou tempo esgotar |

---

### `newScheduledThreadPool(n)`

Agenda tarefas após delay ou periodicamente. Instanciado como `ScheduledExecutorService`, retorna `ScheduledFuture<?>`.

| Método | O que faz |
|--------|-----------|
| `schedule(Runnable, delay, unit)` | Executa uma vez após o delay |
| `schedule(Callable<T>, delay, unit)` | Executa uma vez após o delay com retorno |
| `scheduleAtFixedRate(Runnable, initialDelay, period, unit)` | Repete contando do início da execução anterior |
| `scheduleWithFixedDelay(Runnable, initialDelay, delay, unit)` | Repete contando do fim da execução anterior |
| `shutdown()` | Para de aceitar tarefas, termina as em andamento |
| `shutdownNow()` | Cancela todas as tarefas agendadas e em execução |

---

### `newSingleThreadExecutor`

Thread única, execução sequencial. Instanciado como `ExecutorService`.

| Método | O que faz |
|--------|-----------|
| `submit(Runnable)` | Envia tarefa, executa sequencialmente na única thread |
| `submit(Callable<T>)` | Envia tarefa com retorno, executa sequencialmente |
| `execute(Runnable)` | Envia tarefa sem retorno |
| `shutdown()` | Para de aceitar tarefas, termina a em andamento |
| `shutdownNow()` | Tenta cancelar a tarefa atual e pendentes |
| `awaitTermination(time, unit)` | Bloqueia até terminar ou tempo esgotar |

---

### `newSingleThreadScheduledExecutor`

Thread única com agendamento. Instanciado como `ScheduledExecutorService`.

| Método | O que faz |
|--------|-----------|
| `schedule(Runnable, delay, unit)` | Agenda execução única após delay |
| `schedule(Callable<T>, delay, unit)` | Agenda execução única com retorno |
| `scheduleAtFixedRate(Runnable, initialDelay, period, unit)` | Agenda execução periódica em thread única |
| `scheduleWithFixedDelay(Runnable, initialDelay, delay, unit)` | Agenda execução periódica com delay fixo entre execuções |
| `shutdown()` | Para de aceitar tarefas, termina a em andamento |
| `shutdownNow()` | Cancela todas as tarefas agendadas |

---

### `newWorkStealingPool`

Usa todos os cores disponíveis com algoritmo de work-stealing. Instanciado como `ExecutorService`.

| Método | O que faz |
|--------|-----------|
| `submit(Runnable)` | Envia tarefa distribuída entre threads disponíveis |
| `submit(Callable<T>)` | Envia tarefa com retorno, distribuída pelo pool |
| `execute(Runnable)` | Envia tarefa sem retorno |
| `invoke(ForkJoinTask<T>)` | Executa ForkJoinTask e aguarda resultado |
| `shutdown()` | Para de aceitar tarefas, termina as em andamento |
| `shutdownNow()` | Tenta cancelar tudo imediatamente |

---

## 4. Operações Atômicas e CAS

### 4.1. O Problema

`counter++` são na verdade três passos: ler, modificar, escrever. Se duas threads executam isso simultaneamente, um incremento pode silenciosamente sobrescrever o outro.

```
Thread 1: lê valor = 5
Thread 2: lê valor = 5
Thread 1: escreve valor = 6
Thread 2: escreve valor = 6  (sobrescreve o resultado da Thread 1)
Valor final: 6 em vez de 7
```

### 4.2. CAS — Compare-And-Swap

CAS é uma instrução atômica de hardware que faz três coisas em um único passo: lê o valor atual, compara com o esperado, e se igual substitui pelo novo. Se diferente, falha e tenta novamente. As classes atômicas são lock-free: em vez de bloquear, tentam novamente em caso de conflito.

```java
AtomicInteger contador = new AtomicInteger(0);

contador.incrementAndGet();           // ++ atômico
contador.getAndAdd(5);                // retorna valor anterior, soma 5
contador.compareAndSet(10, 20);       // só muda para 20 se valor atual for 10

AtomicReference<String> ref = new AtomicReference<>("inicial");
ref.compareAndSet("inicial", "novo"); // troca atômica de referência
```

### 4.3. Quando Usar Atomic vs Locks

| Situação | Use |
|----------|-----|
| Contador simples | `AtomicInteger` |
| Flag booleana | `AtomicBoolean` |
| Referência a objeto | `AtomicReference` |
| Múltiplas variáveis juntas | `synchronized` ou `ReentrantLock` |
| Lógica condicional complexa | `synchronized` ou `ReentrantLock` |

Classes atômicas funcionam melhor com baixa contenção e operações em variável única. Quando múltiplas variáveis precisam mudar juntas ou a lógica é mais complexa, locks são a escolha certa.

---

## 5. ReentrantLock

### 5.1. Por que Usar em Vez do `synchronized`

As principais vantagens sobre `synchronized` são `tryLock()` sem bloqueio, suporte a timeout, interruptibilidade e suporte a `Condition` para controle granular de wait/signal. Se nenhuma dessas for necessária, `synchronized` é mais simples e preferível.

### 5.2. Uso

```java
private final ReentrantLock lock = new ReentrantLock();

public void incrementar() {
    lock.lock();
    try {
        valor++;
    } finally {
        lock.unlock(); // SEMPRE no bloco finally
    }
}

public boolean tryIncrementar() {
    if (lock.tryLock()) {
        try {
            valor++;
            return true;
        } finally {
            lock.unlock();
        }
    }
    return false;
}
```

O lock deve sempre ser liberado em um bloco `finally`. Se uma exceção for lançada e o unlock não estiver no `finally`, o lock nunca será liberado, causando deadlock.

### 5.3. Métodos Principais

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `lock()` | `void` | Adquire o lock, bloqueia até conseguir |
| `unlock()` | `void` | Libera o lock |
| `tryLock()` | `boolean` | Tenta adquirir sem bloquear |
| `tryLock(long timeout, TimeUnit unit)` | `boolean` | Tenta dentro de um tempo limite |
| `lockInterruptibly()` | `void` | Adquire mas pode ser interrompido |
| `newCondition()` | `Condition` | Cria uma Condition associada ao lock |
| `isLocked()` | `boolean` | True se alguma thread segura o lock |
| `isHeldByCurrentThread()` | `boolean` | True se a thread atual segura o lock |
| `getHoldCount()` | `int` | Quantas vezes a thread atual adquiriu o lock |
| `hasQueuedThreads()` | `boolean` | True se threads estão esperando pelo lock |
| `getQueueLength()` | `int` | Número de threads na fila de espera |
| `isFair()` | `boolean` | True se criado em modo fair |

---

## 6. Conditions

### 6.1. O Problema com uma Única Fila de Espera

Com `synchronized`, todo objeto tem um único conjunto de espera. Quando `notify()` é chamado, a JVM escolhe qualquer thread esperando. Em um cenário produtor-consumidor, um produtor pode acordar outro produtor em vez de um consumidor, desperdiçando o sinal.

`Condition` permite múltiplas filas de espera distintas para o mesmo lock, dando controle total sobre quais threads são acordadas.

```
Lock: bufferLock

Condition notEmpty  ->  [ consumidores esperando ]
Condition notFull   ->  [ produtores esperando   ]

notEmpty.signal()   ->  acorda apenas um consumidor
notFull.signal()    ->  acorda apenas um produtor
```

### 6.2. Padrão de Uso

```java
private final ReentrantLock lock = new ReentrantLock();
private final Condition notFull  = lock.newCondition();
private final Condition notEmpty = lock.newCondition();

public void produzir(int valor) throws InterruptedException {
    lock.lock();
    try {
        while (cheio()) {         // sempre while, nunca if
            notFull.await();      // libera o lock e espera
        }
        inserir(valor);
        notEmpty.signal();        // acorda um consumidor
    } finally {
        lock.unlock();
    }
}

public int consumir() throws InterruptedException {
    lock.lock();
    try {
        while (vazio()) {         // sempre while, nunca if
            notEmpty.await();     // libera o lock e espera
        }
        int valor = remover();
        notFull.signal();         // acorda um produtor
        return valor;
    } finally {
        lock.unlock();
    }
}
```

Sempre use `while` em vez de `if` antes do `await()`. Uma thread pode acordar espontaneamente sem ter sido sinalizada, ou outra thread pode ter consumido o item antes desta re-adquirir o lock. O `while` re-verifica a condição toda vez que a thread acorda.

### 6.3. Métodos Principais

| Método | Equivalente `synchronized` | Descrição |
|--------|---------------------------|-----------|
| `await()` | `wait()` | Suspende a thread e libera o lock |
| `signal()` | `notify()` | Acorda uma thread esperando |
| `signalAll()` | `notifyAll()` | Acorda todas as threads esperando |
| `await(time, unit)` | `wait(timeout)` | Espera com timeout |
| `awaitUninterruptibly()` | nenhum | Espera sem responder a interrupções |

---

## 7. ReentrantReadWriteLock

### 7.1. O Conceito

Em sistemas onde leituras são muito mais frequentes que escritas, um lock normal força todas as threads a esperar mesmo em operações somente de leitura. `ReentrantReadWriteLock` separa acesso de leitura e escrita: múltiplas threads podem segurar o lock de leitura simultaneamente, mas o lock de escrita é exclusivo.

```
Lock de leitura:  Thread 1, Thread 2, Thread 3  (todas concorrentes)
Lock de escrita:  Thread 4 apenas               (exclusivo, bloqueia todas)
```

### 7.2. Uso

```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();

public String get(String chave) {
    rwLock.readLock().lock();
    try {
        return mapa.get(chave);
    } finally {
        rwLock.readLock().unlock();
    }
}

public void put(String chave, String valor) {
    rwLock.writeLock().lock();
    try {
        mapa.put(chave, valor);
    } finally {
        rwLock.writeLock().unlock();
    }
}
```

### 7.3. Métodos Principais

| Método | Interface | Descrição |
|--------|-----------|-----------|
| `readLock()` | `ReadWriteLock` | Retorna o lock de leitura |
| `writeLock()` | `ReadWriteLock` | Retorna o lock de escrita |
| `lock()` | `Lock` | Adquire o lock |
| `unlock()` | `Lock` | Libera o lock |
| `tryLock()` | `Lock` | Tenta adquirir sem bloquear |
| `tryLock(long timeout, TimeUnit unit)` | `Lock` | Tenta dentro de um tempo limite |
| `newCondition()` | `Lock` | Cria uma Condition (apenas writeLock) |
| `isWriteLocked()` | `ReentrantReadWriteLock` | True se o lock de escrita está adquirido |
| `isWriteLockedByCurrentThread()` | `ReentrantReadWriteLock` | True se a thread atual segura o writeLock |
| `getReadLockCount()` | `ReentrantReadWriteLock` | Total de threads segurando o readLock |
| `hasQueuedThreads()` | `ReentrantReadWriteLock` | True se threads estão esperando |
| `isFair()` | `ReentrantReadWriteLock` | True se criado em modo fair |

---

## 8. Fairness

Fairness significa respeitar a ordem de chegada das threads (FIFO). Sem ela, a JVM pode deixar uma thread furar a fila. A maioria dos locks e estruturas concorrentes suportam modo fair via construtor.

```java
new ReentrantLock(true)                // fair
new ReentrantReadWriteLock(true)       // fair
new Semaphore(permits, true)           // fair
new ArrayBlockingQueue(capacity, true) // fair
new SynchronousQueue(true)             // fair
```

`synchronized`, `ConcurrentHashMap` e Executors padrão não expõem configuração de fairness.

Fairness afeta a ordem de aquisição, previsibilidade e prevenção de starvation. Não afeta a correção do programa, thread safety nem consistência de dados. É uma política de agendamento, não uma garantia de sincronização.

| Modo | Característica |
|------|----------------|
| Non-fair (padrão) | Maior throughput, menos overhead, pode causar starvation |
| Fair | Mais previsível, evita starvation, levemente mais lento |

Em sistemas de alta performance, non-fair é quase sempre preferido. Fair é usado quando previsibilidade importa mais que throughput bruto.

---

## 9. Escolhendo a Ferramenta Certa

| Situação | Use |
|----------|-----|
| Contador ou flag simples | `AtomicInteger` / `AtomicBoolean` |
| Tarefa sem retorno | `Runnable` + `execute()` |
| Tarefa com retorno | `Callable` + `submit()` + `Future` |
| Muitas tarefas curtas e imprevisíveis | `newCachedThreadPool` |
| Paralelismo controlado | `newFixedThreadPool` |
| Tarefas agendadas ou periódicas | `newScheduledThreadPool` |
| Ordem sequencial garantida | `newSingleThreadExecutor` |
| Máximo paralelismo de CPU | `newWorkStealingPool` |
| Seção crítica, lógica complexa | `synchronized` ou `ReentrantLock` |
| Precisa de `tryLock()` ou timeout | `ReentrantLock` |
| Múltiplas filas de espera | `ReentrantLock` + `Condition` |
| Muitas leituras, poucas escritas | `ReentrantReadWriteLock` |

---

## 10. Exercícios

### 10.1. newCachedThreadPool — Image Processor

Processa imagens de forma concorrente usando um pool que cria threads sob demanda. Demonstra como o pool reutiliza threads ociosas entre submissões e por que cada `Callable` deve ser uma instância separada para evitar race conditions em estado compartilhado.

**Conceitos chave:** `newCachedThreadPool`, `Callable`, `Future`, instâncias independentes por submissão.

---

### 10.2. newFixedThreadPool — Report Generator

Gera relatórios com um pool fixo de 3 threads, mostrando como tarefas além do tamanho do pool são enfileiradas e processadas em lotes controlados. Usa timestamps para tornar visível a execução em lotes.

**Conceitos chave:** `newFixedThreadPool`, enfileiramento de tarefas, paralelismo limitado, `Future.get()` com timeout.

---

### 10.3. newScheduledThreadPool — Task Scheduler

Demonstra os três modos de agendamento rodando em paralelo: tarefa única com delay, tarefa repetindo em taxa fixa, e tarefa repetindo com delay fixo entre execuções. Mostra a diferença comportamental entre `scheduleAtFixedRate` e `scheduleWithFixedDelay` quando as tarefas têm tempo de processamento.

**Conceitos chave:** `schedule()`, `scheduleAtFixedRate()`, `scheduleWithFixedDelay()`, `ScheduledFuture`, cancelamento.

---

### 10.4. newSingleThreadExecutor — Database Writer

Simula escrita sequencial de registros em banco usando executor de thread única, garantindo que duas escritas nunca aconteçam simultaneamente. Contrasta diretamente com `newFixedThreadPool(3)` para mostrar a diferença entre execução sequencial e paralela.

**Conceitos chave:** `newSingleThreadExecutor`, ordem sequencial garantida, comparação com pool fixo.

---

### 10.5. newWorkStealingPool — Data Analyzer

Processa chunks de dados em paralelo usando todos os cores de CPU disponíveis e mede o tempo total de execução contra um executor de thread única rodando a mesma carga. Mostra o impacto real de performance do paralelismo máximo em tarefas CPU-bound.

**Conceitos chave:** `newWorkStealingPool`, `Runtime.getRuntime().availableProcessors()`, comparação de tempo de execução, workers do ForkJoinPool.

---

### 10.6. AtomicInteger — Contador Thread-Safe

Demonstra incremento atômico usando `AtomicInteger` com 5 threads concorrentes cada uma incrementando um contador compartilhado 1000 vezes. Mostra como operações baseadas em CAS garantem um resultado determinístico de 5000 sem nenhum lock explícito.

**Conceitos chave:** `AtomicInteger`, `incrementAndGet()`, thread safety lock-free, prevenção de race condition.

---

### 10.7. ReentrantLock — Contador de Log

Constrói um contador compartilhado protegido por `ReentrantLock` onde 5 threads trabalhadoras executam 1000 incrementos cada. Também implementa `tryIncrement()` usando `tryLock()` para demonstrar tentativas de lock não bloqueantes e o que acontece quando o lock está indisponível.

**Conceitos chave:** `lock()`, `unlock()` no `finally`, `tryLock()`, isolamento da seção crítica, resultado determinístico.

---

### 10.8. Conditions — Buffer Produtor-Consumidor

Implementa um buffer compartilhado coordenado por dois objetos `Condition`: `notFull` e `notEmpty`. O produtor espera quando o buffer está cheio e o consumidor espera quando está vazio, usando `signal()` para controle preciso de wake-up em vez de acordar todas as threads indiscriminadamente.

**Conceitos chave:** `Condition`, `await()`, `signal()`, `while` antes do `await()`, múltiplas filas de espera por lock, proteção contra spurious wakeup.

---

### 10.9. ReentrantReadWriteLock — Config Cache

Constrói um cache de configuração compartilhado baseado em `HashMap`, protegido por `ReentrantReadWriteLock`. Múltiplas threads leitoras leem de forma concorrente enquanto threads escritoras atualizam valores, demonstrando que leituras ocorrem em paralelo e escritas bloqueiam todos os outros acessos.

**Conceitos chave:** `readLock()`, `writeLock()`, leituras concorrentes, escritas exclusivas, separação leitura-escrita.

---

## 11. Resultados

- Gerenciamento de pool de threads com os seis métodos factory do `Executors`
- Submissão assíncrona de tarefas com `Callable` e obtenção de resultado via `Future`
- Controle de timeout com `Future.get(n, TimeUnit)` e tratamento de `TimeoutException`
- Execução agendada e periódica com `ScheduledExecutorService`
- Operações atômicas com `AtomicInteger` e instruções CAS de hardware
- Controle explícito de lock com `ReentrantLock` e `tryLock()`
- Sempre liberar locks em blocos `finally`
- Coordenação granular de threads com `Condition` e múltiplas filas de espera
- Sempre usar `while` antes de `await()` para proteção contra spurious wakeups
- Acesso concorrente de leitura com `ReentrantReadWriteLock`
- Tradeoffs de fairness entre throughput e prevenção de starvation
- Escolha da ferramenta certa com base no caso de uso

---