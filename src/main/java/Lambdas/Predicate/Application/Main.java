package Lambdas.Predicate.Application;
import Lambdas.Predicate.Domain.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("John", 32, 2500D);
        Employee e2 = new Employee("Ana", 28, 7499.99);
        Employee e3 = new Employee("Eduardo", 26, 12430D);
        Employee e4 = new Employee("Paula", 27, 4000D);
        Employee e5 = new Employee("Nathalia", 27, 5000D);
        List<Employee> list = new ArrayList<>(List.of(e1, e2, e3, e4, e5));

        System.out.println("Salary > 5000:");
        Employee.filterEmployees(list, e -> e.getSalary() > 5000D);
        System.out.println("----------------------------");

        System.out.println("Age > 30:");
        Employee.filterEmployees(list, e -> e.getAge() > 30);
        System.out.println("----------------------------");

        System.out.println("Name starts A:");
        Employee.filterEmployees(list, e -> e.getName().charAt(0) == 'A');
        System.out.println("----------------------------");

        System.out.println("Name starts E and Salary > 10.000:");
        Employee.filterEmployees(list, (e -> e.getName().charAt(0) == 'E' && e.getSalary() > 10000));
        System.out.println("----------------------------");

        Predicate<Employee> highSalary = e -> e.getSalary() > 10000;
        System.out.println("Salary < 10.000:");
        Employee.filterEmployees(list, highSalary.negate());
        System.out.println("----------------------------");
    }
}