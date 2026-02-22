package ConnectionsChannelsAndSocketsWithThreads.SimpleChat;
import java.io.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;
import java.net.InetSocketAddress;

public class SimpleChatServer {
    private final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        new SimpleChatServer().go();
    }

    public void go(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(5000));

            while (serverSocketChannel.isOpen()){
                SocketChannel clientSocket = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, StandardCharsets.UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket));
                System.out.println("got a connection");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void tellEveryone(String message){
        for (PrintWriter writer : clientWriters){
            writer.println(message);
            writer.flush();
        }
    }

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        SocketChannel socket;

        public ClientHandler(SocketChannel clientSocket){
            socket = clientSocket;
            reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
        }

        @Override
        public void run(){
            String message;
            try {
                while ((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}