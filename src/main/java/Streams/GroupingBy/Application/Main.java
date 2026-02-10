package Streams.GroupingBy.Application;
import Streams.GroupingBy.Domain.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Lucas", "IT", 5500.0));
        employees.add(new Employee("Mariana", "IT", 6200.0));
        employees.add(new Employee("Rafael", "IT", 4800.0));
        employees.add(new Employee("Ana", "HR", 4000.0));
        employees.add(new Employee("Bruno", "HR", 4200.0));
        employees.add(new Employee("Carla", "HR", 3900.0));
        employees.add(new Employee("Pedro", "Sales", 4500.0));
        employees.add(new Employee("Juliana", "Sales", 4700.0));
        employees.add(new Employee("Fernando", "Sales", 5200.0));
        employees.add(new Employee("Roberta", "Finance", 6000.0));
        employees.add(new Employee("Gustavo", "Finance", 5800.0));
        employees.add(new Employee("Patricia", "Finance", 6100.0));

        Map<String, List<Employee>> departments = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departments.forEach((department, list) -> {
            System.out.println("Department: " + department);
            list.forEach(emp -> System.out.println("- " + emp.getName()));
        });

        Map<String, Long> departmentCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        System.out.println("Count departments:");
        System.out.println(departmentCount);
    }
}