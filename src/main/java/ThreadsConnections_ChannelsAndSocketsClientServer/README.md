<div align="center">

[![Generic badge](https://img.shields.io/badge/STATUS-FINISHED-success.svg)](https://shields.io/)

# Threads-Connections: Channels and Sockets: Client-Server:

This module covers network communication in Java, from the fundamentals of IP and TCP to building a functional multi-client chat application using SocketChannel, ServerSocketChannel, and ExecutorService.

## Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

</div>

---

## Overview

Network communication in Java is built on two concepts: knowing where the other machine is (IP address) and knowing which service on that machine to reach (TCP port). Together they form a socket, the exact point of communication between two applications that may run on different physical machines.

**IP address** is the unique identifier of a device on a network. **TCP port** is a 16-bit number that identifies which specific application on that device should receive the data. Ports 0 to 1023 are reserved for well-known services and should not be used arbitrarily.

---

## Architecture

- **Domain**: No model classes in this module; the core entities are the network channels themselves
- **Application**: `SimpleChatServer` and `SimpleChatClient`, two independent processes that communicate over localhost
- **1 Exercise**: A full-duplex multi-client chat where the server broadcasts every message to all connected clients

---

## 1. Fundamental Concepts

### 1.1. How a Connection Works

```
Client connects  ->  IP + Port  ->  Server accepts
Client reads     <-  SocketChannel  <-  Server writes
Client writes    ->  SocketChannel  ->  Server reads
```

A channel represents a connection between two applications. Both sides need to know the IP address and TCP port to establish it.

### 1.2. Key Classes

| Class | Role |
|-------|------|
| `InetSocketAddress` | Holds an IP address and port together |
| `SocketChannel` | Represents one end of a connection; used by both client and server |
| `ServerSocketChannel` | Listens on a port and accepts incoming client connections |
| `Channels.newReader()` | Bridges a byte-level channel to a character-level Reader |
| `Channels.newWriter()` | Bridges a byte-level channel to a character-level Writer |
| `BufferedReader` | Reads text line by line from the channel |
| `PrintWriter` | Writes text to the channel |

### 1.3. Connecting from the Client

```java
InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);
SocketChannel socketChannel = SocketChannel.open(serverAddress);

// Reading
Reader reader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
BufferedReader bufferedReader = new BufferedReader(reader);
String message = bufferedReader.readLine();

// Writing
Writer writer = Channels.newWriter(socketChannel, StandardCharsets.UTF_8);
PrintWriter printWriter = new PrintWriter(writer);
printWriter.println("message to send");
```

`127.0.0.1` is the loopback address (localhost), meaning the server is running on the same machine as the client.

### 1.4. Starting the Server

```java
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.bind(new InetSocketAddress(5000));

        while (serverChannel.isOpen()) {
SocketChannel clientChannel = serverChannel.accept(); // blocks until a client connects
// handle clientChannel
}
```

`accept()` is blocking: the thread pauses there until a client connects. When it does, `accept()` returns a `SocketChannel` dedicated to that client and the server can loop back to wait for the next one.

### 1.5. Why Threads Are Required

Reading from a channel is a blocking operation. If the server handled all clients in a single thread, one slow or idle client would freeze communication with every other client. Each connected client must be handled in its own thread so that blocking reads do not interfere with each other.

The same applies to the client: reading incoming messages from the server is blocking, so it must run in a background thread while the main thread manages the user interface.

### 1.6. Thread Safety with Multiple Clients

When multiple threads share a resource (such as the list of connected writers on the server), concurrent access without synchronization leads to data corruption. `CopyOnWriteArrayList` is one solution: it creates a fresh copy of the underlying array on every write, making reads always safe without explicit locking.

```java
private final List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();
```

---

## 2. ExecutorService for Client Handling

Rather than creating raw threads, the server uses a cached thread pool. When a client connects, the server submits a `ClientHandler` task to the pool, freeing the main thread to return to `accept()` immediately.

```java
ExecutorService threadPool = Executors.newCachedThreadPool();

while (serverChannel.isOpen()) {
SocketChannel clientSocket = serverChannel.accept();
    threadPool.submit(new ClientHandler(clientSocket));
        }
```

`newCachedThreadPool` creates new threads as needed and reuses idle ones, making it suitable for scenarios where the number of simultaneous clients is unpredictable.

---

## 3. Server vs Client: Why Two Separate Applications

The server and client have entirely different responsibilities and must run as independent processes.

| Aspect | Server | Client |
|--------|--------|--------|
| Interface | None | Swing GUI |
| Knowledge of others | Knows all clients | Knows only the server |
| Startup order | Must start first | Connects after server is running |
| Threading | Pool per client connection | Single background reader thread |
| Role | Receives and broadcasts messages | Sends and displays messages |

The client does not communicate directly with other clients. It sends messages to the server, and the server broadcasts them to everyone. This intermediary pattern means any number of client instances can join without knowing each other's addresses.

---

## 4. Exercise

### 4.1. Full-Duplex Chat Client

**Concepts:** `SocketChannel`, `ServerSocketChannel`, `BufferedReader`, `PrintWriter`, `ExecutorService`, `Runnable`, Swing GUI, thread-safe list

The reference material provides a one-way client that only sends messages. The exercise extends it into a full-duplex client capable of both sending and receiving messages simultaneously.

**Classes:**
- `SimpleChatServer`: binds to port 5000, accepts clients in a loop, dispatches each to a `ClientHandler` via thread pool, and broadcasts every received message with `tellEveryone()`
- `SimpleChatClient`: connects to the server, renders a scrollable incoming message area and a text field for outgoing messages, and runs an `IncomingReader` task in a background thread to continuously display messages from the server

**Flow:**
```
Client A types message -> writer sends to server -> ClientHandler reads it
-> tellEveryone() writes to all writers -> IncomingReader on each client reads it
-> appended to JTextArea
```

---

## 5. Results

![Chat client receiving messages broadcast by the server in real time](assets/chat-result.png)

The terminal on the left shows the server logging each message as it is read. The Swing window on the right shows the client's `JTextArea` displaying all messages received from the server via the `IncomingReader` background thread.

- Establishing TCP connections with `SocketChannel` and `ServerSocketChannel`
- Reading and writing text over channels with `BufferedReader` and `PrintWriter`
- Managing multiple simultaneous client connections with `ExecutorService`
- Separating blocking I/O into background threads to keep the UI responsive
- Sharing state safely across threads with `CopyOnWriteArrayList`
- Understanding the client-server intermediary model