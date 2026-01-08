package Collections.Maps.NavigableMap_TreeMap.Services;
import Collections.Maps.NavigableMap_TreeMap.Domain.Contact;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class ContactManager {
    public static void updateContact(NavigableMap<String, Contact> map){
        Scanner input = new Scanner(System.in);
        System.out.println("Chose one contact for edit:");
        map.forEach((key, value) -> System.out.println(key + ": " + value));
        String name = input.nextLine();
        if (!map.containsKey(name)) {
            System.out.println("Contact not found!");
            return;
        }
        System.out.println("Chose one option to change:");
        System.out.println("""
                1. Name
                2. Phone Number
                3. Email
                """);
        try {
            int option = input.nextInt();
            String nAtribute;
            input.nextLine();
            switch (option){
                case 1:
                    System.out.println("Enter a new name:");
                    nAtribute = input.nextLine();
                    if (map.containsKey(name)) {
                        Contact c = map.remove(name);
                        c.setName(nAtribute);
                        map.put(nAtribute, c);
                        map.forEach((key, value) -> System.out.println(key + " - " + value));
                        break;
                    }
                    System.out.println("Contact not found.");
                    break;
                case 2:
                    System.out.println("Enter a new Phone Number");
                    nAtribute = input.nextLine();
                    if (map.containsKey(name)){
                        map.get(name).setPhoneNumber(nAtribute);
                        map.forEach((key, value) -> System.out.println(key + " - " + value));
                        break;
                    }
                    System.out.println("Contact not found");
                    break;
                case 3:
                    System.out.println("Enter a new email");
                    nAtribute = input.nextLine();
                    if (map.containsKey(name)){
                        map.get(name).setEmail(nAtribute);
                        map.forEach((key, value) -> System.out.println(key + " - " + value));
                        break;
                    }
                    System.out.println("Contact not found");
                    break;
                default:
                    System.out.println("Incorrect Option");
            }
        } catch (InputMismatchException e){
            System.out.println("Input type incorrect");
        }
    }
}