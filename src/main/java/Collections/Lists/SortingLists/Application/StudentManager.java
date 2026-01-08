package Collections.Lists.SortingLists.Application;
import Collections.Lists.SortingLists.Domains.Students;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StudendsByAge implements Comparator<Students> {
    @Override
    public int compare(Students o1, Students o2) {
        return o1.getAge().compareTo(o2.getAge());
    }
}

class StudendsByGrade implements Comparator<Students> {
    @Override
    public int compare(Students o1, Students o2) {
        return o1.getGrade().compareTo(o2.getGrade());
    }
}

public class StudentManager {
    public static void main(String[] args) {
        Students brian = new Students("Brian Smith", 19, 7.8);
        Students alice = new Students("Alice Johnson", 22, 8.5);
        Students emily = new Students("Emily Davis", 23, 8.9);
        Students david = new Students("David Brown", 20, 6.9);
        Students carol = new Students("Carol Evans", 21, 9.3);

        List<Students> students = new ArrayList<>(List.of(brian, alice, emily, david, carol));

        System.out.println("Original:");
        students.forEach(System.out::println);
        System.out.println("----------------------");

        Collections.sort(students);
        System.out.println("By Name:");
        students.forEach(System.out::println);
        System.out.println("----------------------");

        students.sort(new StudendsByAge());
        System.out.println("By Age:");
        students.forEach(System.out::println);
        System.out.println("----------------------");

        students.sort(new StudendsByGrade().reversed());
        System.out.println("By Grade descending:");
        students.forEach(System.out::println);
        System.out.println("----------------------");
    }
}
