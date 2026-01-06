package Collections.Equals_HashCode.Application;
import Collections.Equals_HashCode.Domain.Employee;
import Collections.Equals_HashCode.Services.EmployeeService;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        EmployeeService.addEmployeeToList(employeeList, new Employee("Ana Cordeiro", "111.222.333-44", 5500));
        EmployeeService.addEmployeeToList(employeeList, new Employee("Eduardo Souza", "234.567.890-11", 12300));
        EmployeeService.addEmployeeToList(employeeList, new Employee("Jose Silva", "234.567.890-11", 2300));
        EmployeeService.addEmployeeToList(employeeList, new Employee("Victor Lima", "232.123.431-23", 9000));
        EmployeeService.addEmployeeToList(employeeList, new Employee("Laura Santiago", "111.222.333-44", 7800));
        EmployeeService.printEmployees(employeeList);
    }
}