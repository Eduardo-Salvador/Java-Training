package Collections.LinkedList.Application;

import Collections.LinkedList.Services.QueueManager;
import Collections.LinkedList.Domain.Customer;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer("Alice Johnson", "Computer not turning on");
        Customer c2 = new Customer("Bruno Silva", "Internet connection dropping frequently");
        Customer c3 = new Customer("Carla Mendes", "Email account locked");
        Customer c4 = new Customer("Daniel Souza", "Printer not responding");
        Customer c5 = new Customer("Eva Martins", "System running very slowly");
        LinkedList<Customer> customerList = new LinkedList<>(List.of(c1, c2, c3, c4, c5));
        System.out.println("Service queue: " + customerList.size());
        customerList.forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("First: " + customerList.peekFirst());
        System.out.println("Complete service!");
        customerList.removeFirst();
        System.out.println("--------------------------");
        System.out.println("Service queue: " + customerList.size());
        customerList.forEach(System.out::println);
        System.out.println("--------------------------");
        System.out.println("First: " + customerList.peek());
        System.out.println("Complete service!");
        customerList.remove();
        System.out.println("--------------------------");
        System.out.println("Service queue: " + customerList.size());
        customerList.forEach(System.out::println);
        System.out.println("--------------------------");
        QueueManager.addCustomer(customerList, c4);
        System.out.println("--------------------------");
        QueueManager.serveCustomer(customerList);
        System.out.println("--------------------------");
        System.out.println("Service queue: " + customerList.size());
        customerList.forEach(System.out::println);
        System.out.println("--------------------------");
        QueueManager.showNext(customerList);
        System.out.println("--------------------------");
        QueueManager.showQueue(customerList);
        System.out.println("--------------------------");
        QueueManager.finishAll(customerList);
        System.out.println("--------------------------");
        System.out.println("Service queue: " + customerList.size());
        customerList.forEach(System.out::println);
        System.out.println("--------------------------");
    }
}