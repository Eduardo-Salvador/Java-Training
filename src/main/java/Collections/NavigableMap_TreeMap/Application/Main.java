package Collections.NavigableMap_TreeMap.Application;
import Collections.NavigableMap_TreeMap.Controller.ContactManager;
import Collections.NavigableMap_TreeMap.Domain.Contact;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Contact c1 = new Contact("Alice Johnson", "555-1234", "alice@gmail.com");
        Contact c2 = new Contact("Bruno Silva", "555-5678", "bruno@hotmail.com");
        Contact c3 = new Contact("Carla Mendes", "555-9012", "carla@yahoo.com");
        Contact c4 = new Contact("Daniel Souza", "555-3456", "daniel@gmail.com");
        Contact c5 = new Contact("Eva Martins", "555-7890", "eva@outlook.com");

        NavigableMap<String, Contact> contactNavigableMap = new TreeMap<>();
        contactNavigableMap.put(c1.getName(), c1);
        contactNavigableMap.put(c2.getName(), c2);
        contactNavigableMap.put(c3.getName(), c3);
        contactNavigableMap.put(c4.getName(), c4);
        contactNavigableMap.put(c5.getName(), c5);
        contactNavigableMap.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("---------------------");

        System.out.println("First contact:");
        System.out.println(contactNavigableMap.firstEntry());
        System.out.println("Contact Higher:");
        System.out.println(contactNavigableMap.higherEntry(contactNavigableMap.firstKey()));
        System.out.println("---------------------");
        System.out.println("Last contact:");
        System.out.println(contactNavigableMap.lastEntry());
        System.out.println("Contact Lower:");
        System.out.println(contactNavigableMap.lowerEntry(contactNavigableMap.lastKey()));
        System.out.println("---------------------");

        contactNavigableMap.descendingKeySet().forEach(c -> System.out.println(c + " - " + contactNavigableMap.get(c)));
        System.out.println("---------------------");

        ContactManager.removeContact(contactNavigableMap, c4.getName());
        contactNavigableMap.forEach((key, value) -> System.out.println(key + " - " + value));
        System.out.println("---------------------");

        Scanner input = new Scanner(System.in);
        ContactManager.updateContact(contactNavigableMap, input);
        contactNavigableMap.forEach((key, value) -> System.out.println(key + " - " + value));
        System.out.println("---------------------");
    }
}