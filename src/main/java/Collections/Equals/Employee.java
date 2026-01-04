package Collections.Equals;

public class Employee {
    private final String name;
    private final String cpf;
    private final double salary;

    public Employee(String name, String cpf, double salary) {
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(this.getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return getCpf() != null && getCpf().equals(employee.getCpf());
    }

    @Override
    public int hashCode() {
       return getCpf() == null ? 0 : this.getCpf().hashCode();
    }

    @Override
    public String toString() {
        return getName() + " - CPF: " + getCpf() + " - Salary: " + getSalary() + " - ID: " + hashCode();
    }
}