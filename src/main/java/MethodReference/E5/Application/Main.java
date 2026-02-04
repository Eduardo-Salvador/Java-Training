package MethodReference.E5.Application;
import MethodReference.E5.Domain.UserFactory;
import MethodReference.E5.Domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        List<String> names = List.of("Eduardo", "Jonas", "Gabriel", "Heloa", "Laura");
        List<Integer> ages = List.of(26, 25, 32, 22, 23);
        List<User> users = new ArrayList<>(5);

        BiFunction<String, Integer, User> createUser = User::new;
        for (int i = 0; i < 5; i++) {
            users.add(createUser.apply(names.get(i), ages.get(i)));
        }
        System.out.println(users);
        System.out.println("--------------------");

        UserFactory createUser2 = User::new;
        users.clear();
        for (int i = 0; i < 5; i++) {
            users.add(createUser2.create(names.get(i), ages.get(i)));
        }
        System.out.println(users);
        System.out.println("--------------------");
    }
}