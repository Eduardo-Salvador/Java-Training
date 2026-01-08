package Collections.Queues.Queue.Controller;
import Collections.Queues.Queue.Domain.Client;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ClientManager implements Queue<Client> {
    private final Queue<Client> queue = new LinkedList<>();

    public static void serveCustomer(Queue<Client> queue){
        System.out.println("Actual Queue: ");
        queue.forEach(System.out::println);
        System.out.println("----------------------");
        System.out.println("Providing service and releasing");
        System.out.println(queue.poll());
        System.out.println("Completed");
        System.out.println("----------------------");
        System.out.println("New Queue: ");
        queue.forEach(System.out::println);
    }

    public static void viewNextCustomer(Queue<Client> queue){
        System.out.println("Next Customer: ");
        System.out.println(queue.peek());
    }

    public static void clearQueue(Queue<Client> queue){
        System.out.println("Cleaning Queue");
        queue.clear();
        System.out.println("Done!");
        System.out.println("Queue:");
        queue.forEach(System.out::println);
    }

    @Override
    public boolean add(Client client) {
        return queue.add(client);
    }

    @Override
    public boolean offer(Client client) {
        return queue.offer(client);
    }

    @Override
    public Client remove() {
        return queue.remove();
    }

    @Override
    public Client poll() {
        return queue.poll();
    }

    @Override
    public Client element() {
        return queue.element();
    }

    @Override
    public Client peek() {
        return queue.peek();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public Iterator<Client> iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Client> c) {
        return queue.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }
}