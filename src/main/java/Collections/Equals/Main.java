package Collections.Equals;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee anaCordeiro = new Employee("Ana Cordeiro", "111.222.333-44", 5500);
        Employee eduardoSouza = new Employee("Eduardo Souza", "234.567.890-11", 12300);
        Employee joseSilva = new Employee("Jose Silva", "234.567.890-11", 2300);
        Employee lauraSantiago = new Employee("Laura Santiago", "111.222.333-44", 7800);
        Employee victorLima = new Employee("Victor Lima", "232.123.431-23", 9000);

        List<Employee> listEmployees = new ArrayList<>(List.of(anaCordeiro, eduardoSouza, joseSilva, lauraSantiago, victorLima));
        listEmployees = EmployeeList.addEmployeeToList(listEmployees);
        System.out.println(listEmployees);

    }
}