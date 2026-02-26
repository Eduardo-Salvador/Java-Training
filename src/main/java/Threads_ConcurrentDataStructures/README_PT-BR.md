<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Estruturas de Dados Concorrentes em Java

Este módulo contém uma série abrangente de exercícios demonstrando o uso de Estruturas de Dados Concorrentes em Java, disponíveis no pacote `java.util.concurrent` introduzido no Java 5. Essas estruturas foram construídas do zero para ambientes multi-thread, substituindo a abordagem antiga do `Collections.synchronized*` com soluções muito mais eficientes e escaláveis.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

Quando múltiplas threads acessam e modificam os mesmos dados simultaneamente, surgem **condições de corrida** situações onde o resultado depende da ordem imprevisível de execução das threads.

A "solução" clássica era envolver tudo em um único lock:

```java
List<String> lista = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> mapa = Collections.synchronizedMap(new HashMap<>());
```

Funciona mas coloca um **cadeado único em volta de tudo**. Cada thread espera a anterior terminar, mesmo que sejam só leituras. Em sistemas com muitas threads, isso vira um gargalo sério.

Por isso surgiram as estruturas de dados concorrentes.

**Importante:** Essas estruturas garantem thread-safety por operação, mas **sequências de operações não são automaticamente atômicas**. Se duas operações precisam depender uma da outra, sincronização adicional ainda pode ser necessária.

---

## Arquitetura

O projeto está organizado da seguinte forma:

- Contém as classes de caso de uso por estrutura (`EventManager`, `DownloadManager`, `TaskDispatcher`, `MetricsCollector`, `ScoreBoard`)
- **5 Exercícios Progressivos**: Cada um focado em uma estrutura concorrente específica

---

## 1. Estruturas de Dados Concorrentes

### 1.1. Por que Existem

Cada thread tem seu próprio cache de CPU. Sem sincronização, uma thread pode estar trabalhando com uma cópia desatualizada dos dados. As estruturas do `java.util.concurrent` resolvem isso usando três técnicas principais:

- **Segmentação de locks** em vez de um cadeado pra tudo, a estrutura é dividida em partes independentes. Threads em partes diferentes não se bloqueiam.
- **Algoritmos lock-free (CAS)** usando instruções atômicas do hardware (*Compare and Swap*), algumas estruturas eliminam locks completamente. A CPU garante a atomicidade, não o Java.
- **Separação de leitura e escrita** estruturas como `CopyOnWriteArrayList` deixam leituras completamente livres de sincronização, pagando o custo apenas nas escritas.

---

## 2. Tabela de Referência Rápida

### 2.1. Estruturas Disponíveis

| Estrutura | Capacidade | Bloqueante | Mecanismo | Melhor Para |
|-----------|------------|------------|-----------|-------------|
| `CopyOnWriteArrayList` | Ilimitada | Não | Cópia na escrita | Listas com escritas raras |
| `ArrayBlockingQueue` | Fixa | Sim | Lock único | Produtor-Consumidor com limite |
| `LinkedTransferQueue` | Ilimitada | Sim | Lock-free + transfer | Confirmação de entrega direta |
| `ConcurrentLinkedQueue` | Ilimitada | Não | CAS lock-free | Alta concorrência sem bloqueio |
| `ConcurrentHashMap` | Ilimitada | Não | Locks segmentados | Mapas compartilhados entre threads |

### 2.2. Comportamento dos Métodos Chave

| Método | Comportamento quando cheio/vazio |
|--------|----------------------------------|
| `put(e)` / `take()` | Bloqueia até ter espaço/elemento |
| `offer(e)` / `poll()` | Retorna false/null imediatamente |
| `add(e)` / `remove()` | Lança exception |
| `transfer(e)` | Bloqueia até consumidor pegar diretamente |
| `tryTransfer(e)` | Retorna false se não houver consumidor esperando |

---

## 3. Estruturas

### 3.1. CopyOnWriteArrayList

**A ideia:** A cada escrita, cria uma cópia completa do array interno. Leituras nunca bloqueiam iteradores operam sobre um snapshot do momento em que foram criados.

```java
CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<>();
lista.add("A");

// Iteração segura nunca lança ConcurrentModificationException
for (String s : lista) {
    lista.add("X"); // seguro, itera no snapshot antigo
}
```

**Métodos principais:**

| Método | O que faz |
|--------|-----------|
| `add(e)` | Adiciona no fim da lista |
| `add(index, e)` | Adiciona em posição específica |
| `set(index, e)` | Substitui elemento na posição |
| `remove(index)` | Remove elemento na posição |
| `get(index)` | Retorna elemento na posição |
| `contains(e)` | Verifica se elemento existe |
| `addIfAbsent(e)` | Adiciona apenas se elemento não existir |
| `iterator()` | Retorna iterador sobre snapshot atual |

**Quando usar:** Cenários com muitas leituras e poucas escritas listas de listeners, registros de observers. **Evite** quando escritas são frequentes ou a lista é grande.

---

### 3.2. ArrayBlockingQueue

**A ideia:** Uma fila com **capacidade limitada** que bloqueia threads automaticamente quando necessário. Se a fila está cheia, a thread que insere espera. Se vazia, a thread que consome espera. É a base do padrão **Produtor-Consumidor**.

```java
ArrayBlockingQueue<String> fila = new ArrayBlockingQueue<>(5);

// Produtor
fila.put("tarefa1");  // bloqueia se cheia

// Consumidor
String item = fila.take(); // bloqueia se vazia
```

**Métodos principais:**

| Método | O que faz | Comportamento quando cheio/vazio |
|--------|-----------|----------------------------------|
| `put(e)` | Insere elemento | Bloqueia até ter espaço |
| `take()` | Remove e retorna o primeiro | Bloqueia até ter elemento |
| `offer(e)` | Insere elemento | Retorna false se cheia |
| `offer(e, time, unit)` | Insere com timeout | Desiste após o tempo |
| `poll()` | Remove e retorna o primeiro | Retorna null se vazia |
| `poll(time, unit)` | Remove com timeout | Retorna null após o tempo |
| `add(e)` | Insere elemento | Lança exception se cheia |
| `peek()` | Retorna o primeiro sem remover | Retorna null se vazia |
| `remainingCapacity()` | Retorna espaço disponível | |

**Quando usar:** Sempre que precisar de fluxo controlado entre produtores e consumidores impede que produtores sobrecarreguem consumidores e estourem a memória.

**Desvantagens:** Tamanho fixo definido na criação. Lock único pode virar gargalo com muitas threads. Bloquear threads por completo pode ser problema em algumas arquiteturas.

---

### 3.3. LinkedTransferQueue

**A ideia:** Vai além do produtor-consumidor simples. O método `transfer()` faz o produtor **esperar até que um consumidor pegue o item diretamente** não apenas enfileirado, mas de fato recebido.

```
put()      → Produtor insere e segue a vida
             [ item, item, item ] → Consumidor pega quando quiser

transfer() → Produtor insere e ESPERA
             Produtor ✋ item → Consumidor pega → Produtor liberado
```

```java
LinkedTransferQueue<String> fila = new LinkedTransferQueue<>();

fila.transfer("tarefa");              // bloqueia até consumidor pegar
fila.tryTransfer("tarefa");           // retorna false se não houver consumidor esperando
fila.tryTransfer("tarefa", 2, TimeUnit.SECONDS); // tenta por 2 segundos
```

**Métodos principais:**

| Método | O que faz | Comportamento |
|--------|-----------|---------------|
| `transfer(e)` | Entrega diretamente a um consumidor | Bloqueia até consumidor pegar |
| `tryTransfer(e)` | Tenta entrega direta | Retorna false se não houver consumidor esperando |
| `tryTransfer(e, time, unit)` | Tenta com timeout | Retorna false se tempo esgotar |
| `put(e)` | Insere na fila | Nunca bloqueia (capacidade ilimitada) |
| `take()` | Remove e retorna o primeiro | Bloqueia se vazia |
| `hasWaitingConsumer()` | Verifica se há consumidor esperando | Retorna boolean |
| `getWaitingConsumerCount()` | Retorna quantos consumidores estão esperando | |

**Quando usar:** Quando precisa de **confirmação de entrega** não só que o item foi para a fila, mas que uma thread já está processando ele. Pipelines de tempo real, padrões de requisição-resposta entre threads.

**Importante:** `transfer()` só desbloqueia quando um consumidor entra no `take()` com a **fila vazia**. Se a fila tem itens, o consumidor pega imediatamente sem ficar "bloqueado esperando" e o `transfer()` continua congelado.

---

### 3.4. ConcurrentLinkedQueue

**A ideia:** Uma fila **lock-free** sem limite de capacidade. Em vez de locks, usa **CAS (Compare-And-Swap)** uma instrução atômica de hardware. As threads nunca bloqueiam esperando umas pelas outras; competem diretamente no hardware.

```
Instrução atômica de CPU:
  "Se o valor atual é X, troca por Y tudo em uma instrução"

Sem CAS:
  lê → modifica → escreve  (3 passos, pode ser interrompido)

Com CAS:
  compareAndSwap(esperado, novo)  (1 passo atômico de hardware)
```

```java
ConcurrentLinkedQueue<String> fila = new ConcurrentLinkedQueue<>();

fila.offer("tarefa1"); // nunca bloqueia
String item = fila.poll(); // retorna null se vazia, nunca bloqueia

if (item == null) {
    // fila vazia você decide o que fazer
}
```

**Métodos principais:**

| Método | O que faz | Comportamento |
|--------|-----------|---------------|
| `offer(e)` | Insere no fim | Sempre retorna true, nunca bloqueia |
| `poll()` | Remove e retorna o primeiro | Retorna null se vazia |
| `peek()` | Retorna o primeiro sem remover | Retorna null se vazia |
| `isEmpty()` | Verifica se está vazia | Mais eficiente que `size() == 0` |
| `size()` | Retorna tamanho atual | Percorre a fila inteira evite em loops |
| `contains(e)` | Verifica se elemento existe | Percorre a fila inteira |
| `iterator()` | Retorna iterador | Snapshot, nunca lança ConcurrentModificationException |

**Quando usar:** Muitas threads inserindo e removendo simultaneamente sem necessidade de bloqueio. Sistemas de logging, coleta de métricas, filas de eventos de alta frequência.

**Desvantagens:** Sem controle de fluxo se produtores são muito mais rápidos que consumidores, a fila cresce sem limite e pode estourar a memória. `size()` e `contains()` percorrem a fila inteira caros em loops. Sem `take()` você mesmo trata o `null`.

---

### 3.5. ConcurrentHashMap

**A ideia:** `HashMap` thread-safe que, ao contrário do `Collections.synchronizedMap()`, **trava apenas o segmento específico** sendo modificado. Leituras geralmente não travam nada.

```
synchronizedMap:
Thread 1 escreve → trava TUDO → Threads 2, 3, 4 esperam

ConcurrentHashMap:
Thread 1 escreve no segmento A →
Thread 2 escreve no segmento C → rodam ao mesmo tempo
Thread 3 lê qualquer coisa    →
```

O grande diferencial são as **operações compostas atômicas** sem elas você teria que sincronizar manualmente os blocos de leitura + modificação + escrita, abrindo espaço para race conditions:

```java
ConcurrentHashMap<String, Integer> mapa = new ConcurrentHashMap<>();

// ERRADO não é atômico, race condition aqui
if (!mapa.containsKey("jogador1")) {
    mapa.put("jogador1", 0); // outra thread pode inserir entre o if e o put
}

// CERTO atômico
mapa.putIfAbsent("jogador1", 0);

// ERRADO leitura e escrita separadas, race condition
int pontos = mapa.get("jogador1");
mapa.put("jogador1", pontos + 10);

// CERTO atômico
mapa.merge("jogador1", 10, Integer::sum);
```

**Métodos principais:**

| Método | O que faz |
|--------|-----------|
| `put(k, v)` | Insere ou substitui o valor |
| `get(k)` | Retorna o valor ou null |
| `putIfAbsent(k, v)` | Insere só se a chave não existir |
| `computeIfAbsent(k, fn)` | Calcula e insere se chave não existir |
| `computeIfPresent(k, fn)` | Atualiza valor só se chave existir |
| `compute(k, fn)` | Lê e escreve atomicamente, trata null |
| `merge(k, v, fn)` | Combina valor existente com novo atomicamente |
| `getOrDefault(k, default)` | Retorna valor ou default se não existir |
| `replace(k, v)` | Substitui valor só se chave existir |
| `replace(k, old, new)` | Substitui só se valor atual for o esperado |
| `forEach(fn)` | Itera sobre todos os pares chave-valor |
| `size()` | Retorna tamanho aproximado |

**Importante:** Não permite chaves ou valores `null` diferente do `HashMap`. Tentar inserir `null` lança `NullPointerException`.

**Quando usar:** Sempre que precisar de um mapa compartilhado entre threads caches, contadores de acesso, agrupamento de resultados de threads paralelas, configurações atualizáveis em runtime.

**Evite quando:** Precisar de consistência entre múltiplas chaves simultaneamente. O `ConcurrentHashMap` garante atomicidade por operação, não entre operações diferentes.

---

## 4. Exercícios

### 4.1. CopyOnWriteArrayList — Sistema de Notificação de Eventos

Constrói um sistema de notificação de eventos demonstrando iteração concorrente segura sobre uma lista de listeners enquanto novos listeners são adicionados simultaneamente. Mostra como a `CopyOnWriteArrayList` elimina `ConcurrentModificationException` e por que um `ArrayList` comum quebra nas mesmas condições.

**Conceitos chave:** iteração baseada em snapshot, comportamento de cópia na escrita, segurança de leitura sob escritas concorrentes.

---

### 4.2. ArrayBlockingQueue — Download Manager

Implementa uma fila de downloads com slots limitados compartilhados entre múltiplos solicitantes e um único downloader. Demonstra o bloqueio automático dos produtores quando a fila atinge a capacidade e como `put()` e `add()` se comportam diferente sob pressão.

**Conceitos chave:** fila bloqueante com capacidade limitada, controle de fluxo produtor-consumidor, coordenação de threads sem sincronização manual.

---

### 4.3. LinkedTransferQueue — Task Dispatcher

Constrói um despachante de tarefas onde o produtor aguarda confirmação de entrega antes de avançar para a próxima tarefa. Contrasta `transfer()` com `put()` para mostrar a diferença entre enfileirar e entregar diretamente a um consumidor esperando.

**Conceitos chave:** transferência direta, confirmação de entrega, `transfer()` vs `put()`, requisito de prontidão do consumidor.

---

### 4.4. ConcurrentLinkedQueue — Metrics Collector

Implementa um coletor de eventos de log de alta vazão com múltiplos produtores e consumidores operando sem nenhum bloqueio. Usa uma flag `volatile` combinada com `AtomicInteger` para sinalizar com segurança aos consumidores quando todos os produtores terminaram.

**Conceitos chave:** fila lock-free, loop de poll não bloqueante, tratamento de `null`, gerenciamento de ciclo de vida com `volatile` + `AtomicInteger`.

---

### 4.5. ConcurrentHashMap — Score Board

Constrói um placar concorrente onde múltiplas threads de jogadores se registram e atualizam suas pontuações simultaneamente enquanto uma thread de bonus dobra os valores ao mesmo tempo. Demonstra operações compostas atômicas para evitar race conditions em sequências de leitura-modificação-escrita.

**Conceitos chave:** `putIfAbsent`, `compute`, `computeIfPresent`, `merge`, operações compostas atômicas, evitando `get()` separado após escrita.

---

## 5. Resultados

**Entendimento completo de por que as estruturas concorrentes existem**
- O problema com `Collections.synchronized*`
- Contenção de lock e seu impacto na performance
- Como o `java.util.concurrent` resolve isso

**Domínio do comportamento de cada estrutura**
- Operações bloqueantes vs não-bloqueantes
- Mecanismo CAS lock-free
- Estratégia de cópia na escrita
- Padrão de transferência direta

**Padrões de uso corretos**
- `put()`/`take()` vs `offer()`/`poll()` vs `add()`/`remove()`
- `transfer()` vs `put()` e quando cada um faz sentido
- Operações compostas atômicas no `ConcurrentHashMap`
- Flag `volatile` + `AtomicInteger` para gerenciamento do ciclo de vida das threads

**Armadilhas comuns evitadas**
- Usar `add()` em vez de `put()` (lança exception quando cheia)
- Operações separadas de leitura + escrita em vez de `compute()`/`merge()` atômicos
- Imprimir valores de um `get()` separado em vez do retorno da operação
- Threads consumidoras morrendo antes dos produtores terminarem
- `size()` em loops em estruturas lock-free

---