package Collections.Queues.Queue.Application;
import Collections.Queues.Queue.Controller.ClientManager;
import Collections.Queues.Queue.Domain.Client;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Client c1 = new Client("Ana");
        Client c2 = new Client("Eduardo");
        Client c3 = new Client("Bruno");
        Client c4 = new Client("Laura");
        Client c5 = new Client("Luana");

        Queue<Client> clientQueue = new ClientManager();
        clientQueue.add(c1);
        clientQueue.add(c2);
        clientQueue.add(c3);
        clientQueue.add(c4);
        clientQueue.add(c5);

        ClientManager.viewNextCustomer(clientQueue);
        System.out.println("----------------------");
        ClientManager.serveCustomer(clientQueue);
        System.out.println("----------------------");
        ClientManager.clearQueue(clientQueue);
    }
}