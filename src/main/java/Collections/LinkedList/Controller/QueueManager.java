package Collections.LinkedList.Controller;
import Collections.LinkedList.Domain.Customer;
import java.util.Iterator;
import java.util.LinkedList;

public class QueueManager {
    public static void addCustomer(LinkedList<Customer> queue, Customer c) {
        if (!queue.contains(c)) {
            queue.addLast(c);
            System.out.println("Added to queue: " + c);
        }
        System.out.println("The user already has an open support ticket.");
    }

    public static void serveCustomer(LinkedList<Customer> queue) {
        if (queue.isEmpty()) {
            System.out.println("No customers to serve.");
            return;
        }
        Customer served = queue.removeFirst();
        System.out.println("Serving customer: " + served);
    }

    public static void showNext(LinkedList<Customer> queue) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Next to be served: " + queue.peekFirst());
    }

    public static void showQueue(LinkedList<Customer> queue) {
        System.out.println("Current queue: " + queue);
    }

    public static void finishAll(LinkedList<Customer> queue){
        Iterator<Customer> iterator = queue.iterator();
        while (iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
        System.out.println("All services performed");
    }
}