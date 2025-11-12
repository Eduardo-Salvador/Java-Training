package InnerClasses.InnerClasses.Domain;
import java.util.List;

public class University {
    private static String name;
    private static String city;

    public University(String name, String city) {
        University.name = name;
        University.city = city;
    }

    public String getName() {
        return name;
    }

    public static void totalStudents(List<University.Student> list){
        System.out.println("Total Students in: " + name);
        System.out.println(list.size());
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public static class Student {
        private final String name;
        private final String registrationNumber;
        private final String course;

        public Student(String name, String registrationNumber, String course) {
            this.name = name;
            this.registrationNumber = registrationNumber;
            this.course = course;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public static void searchStudent(String rN, List<University.Student> list){
            for (University.Student c : list){
                if(c.getRegistrationNumber().equals(rN)){
                    System.out.println(c);
                    return;
                }
            }
            System.out.println("Student not found");
        }

        public static void deleteStudent(String rN, List<University.Student> list){
            System.out.println("Student remove!");
            list.removeIf(c -> c.registrationNumber.equals(rN));
            list.forEach(System.out::println);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", registrationNumber='" + registrationNumber + '\'' +
                    ", course='" + course + '\'' +
                    '}';
        }
    }
}