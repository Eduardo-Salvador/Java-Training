package Seminar.System.Domain;

public class Students {
    private String name;
    private int age;
    private Seminar seminar;

    public Students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Students(String name, int age, Seminar seminar) {
        this(name, age);
        this.seminar = seminar;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
