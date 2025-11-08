package Collections.Equals.Controller;
import Collections.Equals.Domains.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    public static List<Employee> addEmployeeToList(List<Employee> employees){
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (!isEmployeeDuplicate(employee, list)) {
                list.add(employee);
                System.out.println("Employee successfully registered:");
                System.out.println(employee);
            } else {
                System.out.println("Employee with already registered CPF: " + employee.getCpf());
            }
        }
        return list;
    }

    private static boolean isEmployeeDuplicate(Employee employee, List<Employee> list){
        for (Employee e : list) {
            if (e.getCpf().equals(employee.getCpf())) {
                return true;
            }
        } return false;
    }
}