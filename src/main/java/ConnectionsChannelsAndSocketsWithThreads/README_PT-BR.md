<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINALIZADO-success.svg)](https://shields.io/)

# Conexões: Canais e Sockets: Cliente-Servidor com Threads:

Este módulo cobre comunicação em rede com Java, desde os fundamentos de IP e TCP até a construção de uma aplicação de chat multi-cliente funcional usando SocketChannel, ServerSocketChannel e ExecutorService.

## Tecnologias
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Visão Geral

A comunicação em rede em Java se apoia em dois conceitos: saber onde a outra máquina está (endereço IP) e saber qual serviço naquela máquina deve receber os dados (porta TCP). Juntos, formam um socket, o ponto exato de comunicação entre duas aplicações que podem estar em máquinas físicas distintas.

**Endereço IP** é o identificador único de um dispositivo na rede. **Porta TCP** é um número de 16 bits que identifica qual aplicação específica naquele dispositivo deve receber os dados. As portas de 0 a 1023 são reservadas para serviços conhecidos e não devem ser usadas arbitrariamente.

---

## Arquitetura

- **Domain**: Nenhuma classe de modelo neste módulo; as entidades centrais são os próprios canais de rede
- **Application**: `SimpleChatServer` e `SimpleChatClient`, dois processos independentes que se comunicam via localhost
- **1 Exercício**: Um chat multi-cliente full-duplex em que o servidor transmite cada mensagem para todos os clientes conectados

---

## 1. Conceitos Fundamentais

### 1.1. Como uma Conexão Funciona

```
Cliente conecta  ->  IP + Porta  ->  Servidor aceita
Cliente lê       <-  SocketChannel  <-  Servidor escreve
Cliente escreve  ->  SocketChannel  ->  Servidor lê
```

Um canal representa uma conexão entre duas aplicações. Os dois lados precisam conhecer o endereço IP e a porta TCP para estabelecê-la.

### 1.2. Classes Principais

| Classe | Papel |
|--------|-------|
| `InetSocketAddress` | Agrupa um endereço IP e uma porta |
| `SocketChannel` | Representa uma ponta da conexão; usado tanto pelo cliente quanto pelo servidor |
| `ServerSocketChannel` | Escuta em uma porta e aceita conexões de clientes |
| `Channels.newReader()` | Faz a ponte entre o canal de bytes e um Reader de caracteres |
| `Channels.newWriter()` | Faz a ponte entre o canal de bytes e um Writer de caracteres |
| `BufferedReader` | Lê texto linha a linha do canal |
| `PrintWriter` | Escreve texto no canal |

### 1.3. Conectando pelo Cliente

```java
InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);
SocketChannel socketChannel = SocketChannel.open(serverAddress);

// Leitura
Reader reader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
BufferedReader bufferedReader = new BufferedReader(reader);
String message = bufferedReader.readLine();

// Escrita
Writer writer = Channels.newWriter(socketChannel, StandardCharsets.UTF_8);
PrintWriter printWriter = new PrintWriter(writer);
printWriter.println("mensagem a enviar");
```

`127.0.0.1` é o endereço de loopback (localhost), indicando que o servidor está na mesma máquina que o cliente.

### 1.4. Iniciando o Servidor

```java
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.bind(new InetSocketAddress(5000));

        while (serverChannel.isOpen()) {
SocketChannel clientChannel = serverChannel.accept(); // bloqueia até um cliente conectar
// tratar clientChannel
}
```

`accept()` é bloqueante: a thread pausa ali até que um cliente se conecte. Quando isso acontece, `accept()` retorna um `SocketChannel` dedicado àquele cliente e o servidor volta a aguardar o próximo.

### 1.5. Por que Threads são Necessárias

A leitura de um canal é uma operação bloqueante. Se o servidor atendesse todos os clientes em uma única thread, um cliente lento ou ocioso travaria a comunicação com todos os demais. Cada cliente conectado precisa ser tratado em sua própria thread para que leituras bloqueantes não interfiram entre si.

O mesmo vale para o cliente: ler mensagens recebidas do servidor é bloqueante, por isso essa leitura deve rodar em uma thread de fundo enquanto a thread principal gerencia a interface gráfica.

### 1.6. Thread Safety com Múltiplos Clientes

Quando múltiplas threads compartilham um recurso (como a lista de writers conectados no servidor), o acesso concorrente sem sincronização leva à corrupção de dados. `CopyOnWriteArrayList` é uma solução: ela cria uma cópia do array interno a cada escrita, tornando as leituras sempre seguras sem necessidade de lock explícito.

```java
private final List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();
```

---

## 2. ExecutorService para Gerenciar Clientes

Em vez de criar threads manualmente, o servidor usa um pool de threads com cache. Quando um cliente conecta, o servidor submete uma tarefa `ClientHandler` ao pool, liberando a thread principal para voltar ao `accept()` imediatamente.

```java
ExecutorService threadPool = Executors.newCachedThreadPool();

while (serverChannel.isOpen()) {
SocketChannel clientSocket = serverChannel.accept();
    threadPool.submit(new ClientHandler(clientSocket));
        }
```

`newCachedThreadPool` cria novas threads conforme necessário e reutiliza as ociosas, sendo adequado para cenários onde o número de clientes simultâneos é imprevisível.

---

## 3. Servidor e Cliente: Por que São Aplicações Separadas

O servidor e o cliente têm responsabilidades completamente diferentes e precisam rodar como processos independentes.

| Aspecto | Servidor | Cliente |
|---------|----------|---------|
| Interface | Nenhuma | GUI Swing |
| Conhecimento dos outros | Conhece todos os clientes | Conhece apenas o servidor |
| Ordem de inicialização | Deve iniciar primeiro | Conecta após o servidor estar no ar |
| Threading | Pool por conexão de cliente | Uma thread de leitura em fundo |
| Papel | Recebe e transmite mensagens | Envia e exibe mensagens |

O cliente não se comunica diretamente com outros clientes. Ele envia mensagens ao servidor, e o servidor as distribui para todos. Esse padrão intermediário permite que qualquer número de instâncias do cliente entre no chat sem precisar conhecer os endereços dos demais.

---

## 4. Exercício

### 4.1. Cliente de Chat Full-Duplex

**Conceitos:** `SocketChannel`, `ServerSocketChannel`, `BufferedReader`, `PrintWriter`, `ExecutorService`, `Runnable`, GUI Swing, lista thread-safe

O material de referência fornece um cliente unidirecional que apenas envia mensagens. O exercício o estende para um cliente full-duplex capaz de enviar e receber mensagens simultaneamente.

**Classes:**
- `SimpleChatServer`: faz o bind na porta 5000, aceita clientes em loop, despacha cada um para um `ClientHandler` via pool de threads e transmite cada mensagem recebida com `tellEveryone()`
- `SimpleChatClient`: conecta ao servidor, exibe uma área de mensagens recebidas com scroll e um campo de texto para envio, e executa uma tarefa `IncomingReader` em thread de fundo para exibir continuamente as mensagens vindas do servidor

**Fluxo:**
```
Cliente A digita mensagem -> writer envia ao servidor -> ClientHandler lê
-> tellEveryone() escreve para todos os writers -> IncomingReader de cada cliente lê
-> appended na JTextArea
```

---

## 5. Resultados

![Cliente de chat recebendo mensagens transmitidas pelo servidor em tempo real](assets/chat-result.png)

O terminal à esquerda mostra o servidor registrando cada mensagem conforme ela é lida. A janela Swing à direita mostra a `JTextArea` do cliente exibindo todas as mensagens recebidas do servidor via thread de fundo `IncomingReader`.

- Estabelecimento de conexões TCP com `SocketChannel` e `ServerSocketChannel`
- Leitura e escrita de texto em canais com `BufferedReader` e `PrintWriter`
- Gerenciamento de múltiplas conexões simultâneas com `ExecutorService`
- Separação de I/O bloqueante em threads de fundo para manter a interface responsiva
- Compartilhamento seguro de estado entre threads com `CopyOnWriteArrayList`
- Compreensão do modelo cliente-servidor com intermediário