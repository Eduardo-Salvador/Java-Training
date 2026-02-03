package Lambdas.Predicate.Domain;
import java.util.List;
import java.util.function.Predicate;

public class Employee {
    private final String name;
    private final Integer age;
    private final Double salary;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - Age: " + age + " - Salary: $" + salary;
    }

    public static void filterEmployees(List<Employee> list, Predicate<Employee> condition){
        for(Employee e : list){
            if(condition.test(e)){
                System.out.println(e);
            }
        }
    }
}