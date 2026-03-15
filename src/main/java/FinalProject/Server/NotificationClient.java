package FinalProject.Server;
import lombok.extern.log4j.Log4j2;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Log4j2
public class NotificationClient implements Runnable {
    private final String host;
    private final int port;
    private final Consumer<String> onMessage;

    public NotificationClient(String host, int port, Consumer<String> onMessage) {
        this.host = host;
        this.port = port;
        this.onMessage = onMessage;
    }

    public void start() {
        Thread clientThread = new Thread(this);
        clientThread.setDaemon(true);
        clientThread.start();
        log.info("NotificationClient started, connecting to {}:{}", host, port);
    }

    @Override
    public void run() {
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress(host, port));
             BufferedReader reader = new BufferedReader(
                     Channels.newReader(channel, StandardCharsets.UTF_8))) {

            log.info("Connected to server {}:{}", host, port);

            String line;
            while ((line = reader.readLine()) != null) {
                final String message = line;
                SwingUtilities.invokeLater(() -> onMessage.accept(message));
                log.debug("Message received: {}", message);
            }

        } catch (IOException e) {
            log.error("NotificationClient error: {}", e.getMessage());
        }
    }
}