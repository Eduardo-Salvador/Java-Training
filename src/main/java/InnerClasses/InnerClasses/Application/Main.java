package InnerClasses.InnerClasses.Application;
import InnerClasses.InnerClasses.Domain.University;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        University u1 = new University("Harvard", "Cambridge");
        University.Student s1 = u1.new Student("Eduardo", "A11C3", "Computer Engineering");
        University.Student s2 = u1.new Student("Paulo", "A21B5", "Civil Engineering");
        University.Student s3 = u1.new Student("Hector", "C11F7", "Mechanical Engineering");
        University.Student s4 = u1.new Student("Laura", "H63A3", "Computer Engineering");
        University.Student s5 = u1.new Student("Lara", "K1V32", "Civil Engineering");
        System.out.println("-------------------------------");

        System.out.println(u1);
        List<University.Student> list = new ArrayList<>(List.of(s1,s2,s3,s4,s5));
        list.forEach(System.out::println);
        System.out.println("-------------------------------");

        u1.searchStudent("A11C3", list);
        System.out.println("-------------------------------");

        u1.deleteStudent("A21B5", list);
        System.out.println("-------------------------------");

        u1.totalStudents(list);
    }
}