package Optionals.Application;
import Optionals.Controller.UserRepository;
import Optionals.Domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        User u1 = new User(1, "Ana Souza", "ana.souza@gmail.com");
        User u2 = new User(2, "Bruno Lima", "bruno.lima@yahoo.com");
        User u3 = new User(3, "Carla Mendes", "carla.mendes@gmail.com");
        User u4 = new User(4, "Diego Rocha", "diego.rocha@hotmail.com");
        User u5 = new User(5, "Eduarda Silva", "eduarda.silva@gmail.com");
        User u6 = new User(6, "Fernando Alves", "fernando.alves@live.com");
        User u7 = new User(7, "Gabriela Costa", "gabriela.costa@gmail.com");
        List<User> userList = new ArrayList<>(List.of(u1, u2, u3, u4, u5));
        UserRepository userRepository = new UserRepository(userList);

        userRepository.add(u6);
        userRepository.add(u7);
        System.out.println("-------------------------");

        Optional<User> email = userRepository.findByEmail("eduarda.silva@gmail.com");
        System.out.println(email);
        System.out.println("-------------------------");

        userRepository.findByEmail("fernando.alves@live.com")
                .ifPresent(System.out::println);
        System.out.println("-------------------------");

        userRepository.findByEmail("heitor.alves@live.com")
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("User not found"));
        System.out.println("-------------------------");

        User e1 = userRepository.findByEmail("fernando.bairro@live.com").
                orElse(new User(99, "User Standard", "userstandard@standard.com"));
        System.out.println(e1);
        System.out.println("-------------------------");

        User fallback = userRepository.findByEmail("ericka@live.com")
                .orElseGet(() -> new User(100, "Fallback", "fallback@mail.com"));
        System.out.println(fallback);
        System.out.println("-------------------------");

        userRepository.findByEmail("12345").orElseThrow();
        System.out.println("-------------------------");
    }
}