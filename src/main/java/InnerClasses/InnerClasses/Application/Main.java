package InnerClasses.InnerClasses.Application;
import InnerClasses.InnerClasses.Domain.University;
import InnerClasses.InnerClasses.Domain.University.Student;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        University u1 = new University("Harvard", "Cambridge");
        Student s1 = u1.new Student("Eduardo", "A11C3", "Computer Engineering");
        Student s2 = u1.new Student("Paulo", "A21B5", "Civil Engineering");
        Student s3 = u1.new Student("Hector", "C11F7", "Mechanical Engineering");
        Student s4 = u1.new Student("Laura", "H63A3", "Computer Engineering");
        Student s5 = u1.new Student("Lara", "K1V32", "Civil Engineering");
        System.out.println("-------------------------------");

        System.out.println(u1);
        List<Student> studentList = new ArrayList<>(List.of(s1,s2,s3,s4,s5));
        studentList.forEach(System.out::println);
        System.out.println("-------------------------------");

        u1.searchStudent("A11C3", studentList);
        System.out.println("-------------------------------");

        u1.deleteStudent("A21B5", studentList);
        System.out.println("-------------------------------");

        u1.totalStudents(studentList);
    }
}