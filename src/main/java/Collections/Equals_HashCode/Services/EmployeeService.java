package Collections.Equals_HashCode.Services;
import Collections.Equals_HashCode.Domain.Employee;
import java.util.List;

public class EmployeeService {
    public static void addEmployeeToList(List<Employee> employees, Employee employee) {
        if(employees.contains(employee)){
            System.out.println("Employee with already registered CPF (Brazilian tax identification number): " + employee.getCpf());
            return;
        }
        employees.add(employee);
    }

    public static void printEmployees(List<Employee> employees){
        System.out.println("Registered employees:");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}