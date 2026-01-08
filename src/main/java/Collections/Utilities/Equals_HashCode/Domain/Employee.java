package Collections.Utilities.Equals_HashCode.Domain;

public class Employee {
    private String name;
    private String cpf;
    private double salary;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || this.getCpf() == null || this.getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return this.getCpf().equals(employee.getCpf());
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