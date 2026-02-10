package Streams.SortedDistinct.Application;
import Streams.SortedDistinct.Domain.Student;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creating the student list
        List<Student> students = new ArrayList<>();

        students.add(new Student("Alice Johnson", 8.5));
        students.add(new Student("Bob Smith", 7.2));
        students.add(new Student("Charlie Brown", 9.1));
        students.add(new Student("Alice Johnson", 6.8));
        students.add(new Student("David Wilson", 8.9));
        students.add(new Student("Emma Davis", 7.5));
        students.add(new Student("Bob Smith", 9.3));
        students.add(new Student("Fiona Miller", 8.0));
        students.add(new Student("Charlie Brown", 7.8));
        students.add(new Student("George Taylor", 9.5));

        List<Student> sortedNoDuplicate =
                students.stream()
                        .distinct()
                        .sorted(Comparator.comparing(Student::getGrade))
                        .toList();

        System.out.println(sortedNoDuplicate);
    }
}