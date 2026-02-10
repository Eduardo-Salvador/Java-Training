package Streams.toMap.Application;
import Streams.toMap.Domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Lucas", "Computer Science"));
        students.add(new Student(2, "Mariana", "Engineering"));
        students.add(new Student(3, "Rafael", "Mathematics"));
        students.add(new Student(4, "Ana", "Physics"));
        students.add(new Student(5, "Bruno", "Biology"));
        students.add(new Student(6, "Carla", "Chemistry"));
        students.add(new Student(7, "Pedro", "Economics"));
        students.add(new Student(8, "Juliana", "Law"));
        students.add(new Student(9, "Fernando", "History"));
        students.add(new Student(10, "Patricia", "Design"));

        Map<Integer, String> idName = students.stream()
                .collect(Collectors.toMap(
                        Student::getId,
                        Student::getName
                ));
        System.out.println(idName);

        Map<Integer, Student> idStudent = students.stream()
                .collect(Collectors.toMap(
                        Student::getId,
                        student -> student
                ));
        System.out.println(idStudent);

        List<Student> students2 = new ArrayList<>();

        students2.add(new Student(1, "Lucas", "Computer Science"));
        students2.add(new Student(2, "Mariana", "Engineering"));
        students2.add(new Student(3, "Rafael", "Computer Science"));
        students2.add(new Student(4, "Ana", "Law"));
        students2.add(new Student(5, "Bruno", "Engineering"));
        students2.add(new Student(6, "Carla", "Medicine"));
        students2.add(new Student(7, "Pedro", "Law"));
        students2.add(new Student(8, "Juliana", "Computer Science"));
        students2.add(new Student(9, "Fernando", "Design"));
        students2.add(new Student(10, "Patricia", "Medicine"));


        Map<String, String> courseName = students2.stream()
                .collect(Collectors.toMap(
                        Student::getCourse,
                        Student::getName,
                        (old, next) -> old
                ));
        System.out.println(courseName);
    }
}