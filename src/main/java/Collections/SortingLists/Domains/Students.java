package Collections.SortingLists.Domains;

public class Students implements Comparable<Students> {
    private String name;
    private Integer age;
    private Double grade;

    public Students(String name, Integer age, Double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Students o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Name: " + getName() + " Age: " + getAge() + " Grade: " + getGrade();
    }
}