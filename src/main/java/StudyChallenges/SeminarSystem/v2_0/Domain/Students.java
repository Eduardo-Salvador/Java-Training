package StudyChallenges.SeminarSystem.v2_0.Domain;

public class Students {
    private static long NEXT_ID = 1L;
    private long id;
    private String name;
    private int age;
    private Seminar seminar;

    public Students(String name, int age) {
        this.id = NEXT_ID++;
        this.name = name;
        this.age = age;
    }

    public Students(String name, int age, Seminar seminar) {
        this(name, age);
        this.seminar = seminar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return  "Name: " + name +
                " | Age: " + age +
                " | " + seminar;
    }
}