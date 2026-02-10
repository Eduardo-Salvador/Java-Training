package Streams.FlatMap.Domain;
import java.util.List;

public class Classroom {
    private String className;
    private List<String> students;

    public Classroom(String className, List<String> students) {
        this.className = className;
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}