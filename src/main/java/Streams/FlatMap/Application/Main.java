package Streams.FlatMap.Application;
import Streams.FlatMap.Domain.Classroom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Classroom> classrooms = new ArrayList<>();

        classrooms.add(new Classroom("Math 101", Arrays.asList("Alice", "Brian", "Carla", "Daniel")));
        classrooms.add(new Classroom("Physics 201", Arrays.asList("Ethan", "Fiona", "George", "Hannah", "Ian")));
        classrooms.add(new Classroom("History 102", Arrays.asList("Julia", "Kevin", "Laura")));
        classrooms.add(new Classroom("Computer Science 301", Arrays.asList("Michael", "Nina", "Oliver", "Paula")));

        List<String> students = classrooms.stream()
                .flatMap(list -> list.getStudents().stream())
                .sorted()
                .toList();

        List<String> studentsStartA = classrooms.stream()
                .flatMap(list -> list.getStudents().stream())
                .filter(a -> a.charAt(0) == 'A')
                .sorted()
                .toList();

        System.out.println("All students: " + students);
        System.out.println("Students filter A: " + studentsStartA);
    }
}