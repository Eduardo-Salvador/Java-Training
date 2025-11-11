package Collections.Challange.Queue.PriorityQueue.Controller;
import Collections.Challange.Queue.PriorityQueue.Domain.Patient;
import Collections.Queue.Domain.Client;
import java.util.*;

public class PatientManager implements Queue<Client> {
    private final Queue<Client> queue = new LinkedList<>();

    public static void service(Queue<Patient> normalQueue, PriorityQueue<Patient> priorityQueue){
        System.out.println("Actual Queue:");
        if (!priorityQueue.isEmpty()){
            priorityQueue.forEach(System.out::println);
        }
        normalQueue.forEach(System.out::println);
        System.out.println("-----------------------------");
        if (priorityQueue.isEmpty()){
            System.out.println("Performing normal service: ");
            System.out.println(normalQueue.poll());
            System.out.println("Service provided");
            System.out.println("-----------------------------");
            return;
        }
        System.out.println("Performing priority service: ");
        System.out.println(priorityQueue.poll());
        System.out.println("Service provided");
        System.out.println("-----------------------------");
    }

    public static void addPatient(Queue<Patient> normalQueue, PriorityQueue<Patient> priorityQueue, Patient p){
        if(p.getPriority() > 0){
            priorityQueue.add(p);
            System.out.println("Add in priority!");
            return;
        }
        System.out.println("Add in normal!");
        normalQueue.add(p);
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