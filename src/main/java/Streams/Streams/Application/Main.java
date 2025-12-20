package Streams.Streams.Application;
import Streams.Streams.Domain.Employee;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Ana", 25, 3500.0),
                new Employee("Carlos", 40, 8200.0),
                new Employee("Mariana", 30, 5100.0),
                new Employee("João", 22, 2500.0),
                new Employee("Fernanda", 45, 12000.0),
                new Employee("Rafael", 33, 4800.0),
                new Employee("Beatriz", 29, 7600.0)
        );

        List<String> result = employees.stream().filter(s -> s.getSalary() > 5000)
                .map(e -> e.getName() + " - " + e.getSalary())
                .sorted(String::compareToIgnoreCase)
                .distinct()
                .toList();

        System.out.println("New List:");
        result.forEach(System.out::println);
        System.out.println("---------------------------------");

        System.out.println("Number of employees above 5000: " + result.stream().count());
        System.out.println("---------------------------------");

        System.out.println("Highest paid employee: " + employees.stream()
                .max(Comparator.comparing(Employee::getSalary, Double::compareTo))
                .get());
        System.out.println("---------------------------------");

        System.out.println("Lowest paid employee: " + employees.stream()
                .min(Comparator.comparing(Employee::getSalary, Double::compareTo))
                .get());
        System.out.println("---------------------------------");

        System.out.println("Is there anyone over 60 years old?: " + employees.stream()
                .anyMatch(s -> s.getAge() > 60));
        System.out.println("---------------------------------");

        System.out.println("Does everyone earn more than 2000?: " + employees.stream()
                .allMatch(s -> s.getSalary() > 2000));
        System.out.println("---------------------------------");

        System.out.println("Are there any employees with empty names?: " + employees.stream()
                .noneMatch(s -> s.getName() == null));
        System.out.println("---------------------------------");
    }
}