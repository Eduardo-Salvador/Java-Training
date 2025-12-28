package StudyChallenges.SeminarSystem.v2_0.Domain;
import java.util.ArrayList;

public class Teacher {
    private static long NEXT_ID = 1L;
    private long id = 1L;
    private String name;
    private String specialty;
    private ArrayList<Seminar> seminars;

    public Teacher(String name, String specialty) {
        this.id = NEXT_ID++;
        this.name = name;
        this.specialty = specialty;
    }

    public Teacher(String name, String specialty, ArrayList<Seminar> seminars) {
        this(name, specialty);
        this.seminars = seminars;
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

    public String getSpecialty() {
        return specialty;
    }

    public ArrayList<Seminar> getSeminars() {
        return seminars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setSeminars(ArrayList<Seminar> seminars) {
        this.seminars = seminars;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Specialty: " + specialty +
                " | " + seminars;
    }
}