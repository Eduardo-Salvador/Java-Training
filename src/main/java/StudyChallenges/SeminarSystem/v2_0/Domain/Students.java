package StudyChallenges.SeminarSystem.v2_0.Domain;

public class Students {
    private long id;
    private static long counter = 1L;
    private String name;
    private int age;
    private Seminar seminar;

    public Students(String name, int age) {
        this.id = counter;
        counter++;
        this.name = name;
        this.age = age;
    }

    public Students(int id, String name, int age, Seminar seminar) {
        this(name, age);
        this.seminar = seminar;
    }

    public long getId() {
        return id;
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