package FinalProject.Server;
import FinalProject.Domain.Pet;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


@Log4j2
public class NotificationServer implements Runnable {
    private final int port;
    private final ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<>();
    private final List<SocketChannel> clients = new CopyOnWriteArrayList<>();
    private Selector selector;

    public NotificationServer(int port) {
        this.port = port;
    }

    public void start() {
        Thread serverThread = new Thread(this);
        serverThread.setDaemon(true);
        serverThread.start();
        log.info("NotificationServer started on port {}", port);
    }

    public void notify(Pet pet) {
        CompletableFuture.runAsync(() ->
                broadcast("Pet available: " + pet.getName() + " | Type: " + pet.getType())
        );
    }

    public void broadcast(String message) {
        messageQueue.add(message);
        if (selector != null) selector.wakeup();
    }

    @Override
    public void run() {
        try {
            selector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            log.debug("Server loop started on port {}", port);

            while (!Thread.currentThread().isInterrupted()) {
                selector.select(500);

                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) acceptClient(serverChannel);
                }
                selector.selectedKeys().clear();

                drainQueue();
            }

        } catch (IOException e) {
            log.error("NotificationServer error: {}", e.getMessage());
        }
    }

    private void acceptClient(ServerSocketChannel serverChannel) throws IOException {
        SocketChannel client = serverChannel.accept();
        if (client != null) {
            client.configureBlocking(false);
            clients.add(client);
            log.info("Client connected: {}", client.getRemoteAddress());
        }
    }

    private void drainQueue() {
        String message;
        while ((message = messageQueue.poll()) != null) {
            ByteBuffer buffer = ByteBuffer.wrap((message + "\n").getBytes(StandardCharsets.UTF_8));
            for (SocketChannel client : clients) {
                try {
                    buffer.rewind();
                    client.write(buffer);
                    log.debug("Broadcast sent: {}", message);
                } catch (IOException e) {
                    log.error("Client disconnected, removing: {}", e.getMessage());
                    clients.remove(client);
                }
            }
        }
    }
}
