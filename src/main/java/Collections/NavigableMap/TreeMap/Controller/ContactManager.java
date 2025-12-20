package Collections.NavigableMap.TreeMap.Controller;
import Collections.NavigableMap.TreeMap.Domain.Contact;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;

public class ContactManager {
    public static void removeContact(NavigableMap<String, Contact> map, String name){
        if (map.containsKey(name)){
            map.remove(name);
            System.out.println("User " + name + "removed");
            return;
        }
        System.out.println("User dont exists in map");
    }

    public static void updateContact(NavigableMap<String, Contact> map, Scanner input){
        System.out.println("Chose one contact for edit:");
        map.forEach((key, value) -> System.out.println(key + ": " + value));
        String name = input.nextLine();
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
                    for (Map.Entry<String, Contact> entry : map.entrySet()){
                        if (entry.getKey().contains(name)){
                            name = entry.getKey();
                        }
                    }
                    if (map.containsKey(name)) {
                        Contact c = map.remove(name);
                        c.setName(nAtribute);
                        map.put(nAtribute, c);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter a new Phone Number");
                    nAtribute = input.nextLine();
                    for (Map.Entry<String, Contact> entry : map.entrySet()){
                        if (entry.getKey().contains(name)){
                            map.get(entry.getKey()).setPhoneNumber(nAtribute);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter a new email");
                    nAtribute = input.nextLine();
                    for (Map.Entry<String, Contact> entry : map.entrySet()){
                        if (entry.getKey().contains(name)){
                            entry.getValue().setPhoneNumber(nAtribute);
                        }
                    }
                    break;
                default:
                    System.out.println("Incorrect Option");
            }
        } catch (InputMismatchException e){
            System.out.println("Input type incorrect");
        }
    }
}