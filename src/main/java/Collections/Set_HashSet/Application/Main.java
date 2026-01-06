package Collections.Set_HashSet.Application;
import Collections.Set_HashSet.Domain.User;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("Alice Johnson", "alice@gmail.com");
        User u2 = new User("Bruno Silva", "bruno@hotmail.com");
        User u3 = new User("Carla Mendes", "carla@yahoo.com");
        User u4 = new User("Daniel Souza", "daniel@gmail.com");
        User u5 = new User("Alice Johnson", "alice@gmail.com");
        Set<User> userSet = new HashSet<>();
        userSet.add(u1);
        userSet.add(u2);
        userSet.add(u3);
        userSet.add(u4);
        userSet.add(u5);
        userSet.add(u1);

        System.out.println("Set Size: " + userSet.size());
        userSet.forEach(System.out::println);
        Scanner s = new Scanner(System.in);
        System.out.println("-----------------");

        System.out.println("Enter any e-mail: ");
        String email = s.nextLine();
        userSet.removeIf(e -> e.getEmail().equals(email));

        System.out.println("-----------------");
        System.out.println("Set Size: " + userSet.size());
        userSet.forEach(System.out::println);
    }
}