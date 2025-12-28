package StudyChallenges.SeminarSystem.v2_0.Service;
import StudyChallenges.SeminarSystem.v2_0.Domain.Seminar;
import StudyChallenges.SeminarSystem.v2_0.Domain.Students;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentService extends BaseService<Students> {

    private final SeminarService seminarService;

    public StudentService(ArrayList<Students> arrayList, SeminarService seminarService) {
        super(arrayList);
        this.seminarService = seminarService;
    }

    @Override
    public void register(Scanner newScanner) {
        System.out.println("=== Student Register ===");
        System.out.print("Name: ");
        newScanner.nextLine();
        String name = newScanner.nextLine();
        System.out.print("Age: ");
        int age = newScanner.nextInt();
        System.out.println("Is the student in a seminar? (Y/N)");
        String option = String.valueOf(newScanner.next().charAt(0));
        Students student;
        if (option.equalsIgnoreCase("n")) {
            student = new Students(name, age);
            System.out.println("Student successfully registered.\nName: " + student.getName() + "\nAge: " + student.getAge());
            data.add(student);
            return;
        }

        if (!seminarService.data.isEmpty()) {
            int x = 0;
            int choose = -1;
            do {
                System.out.println("\nPress 0 to Exit");
                System.out.print("Choose a seminar:\n");
                for (Seminar s : seminarService.data) {
                    System.out.println(s.getId() + ". " + s.getTitle());
                    x++;
                }
                choose = newScanner.nextInt();
                if (choose > 0 && choose <= x) {
                    Seminar seminarOption = seminarService.data.get(--choose);
                    student = new Students(name, age, seminarOption);
                    data.add(student);
                    System.out.println("Student successfully registered.\nName: " + student.getName() + "\nAge: " + student.getAge() + "\nSeminar: " + student.getSeminar());
                    break;
                }
            } while (choose != 0);
            return;
        }
        System.out.println("No seminars for register");
    }

    @Override
    public void change(Scanner newScanner) {
        System.out.println("\nChoose a Student:");
        int studentOption = -1;
        if (!data.isEmpty()) {
            for (Students s : data) {
                System.out.println(s.getId() + ". " + s.getName());
            }
            studentOption = newScanner.nextInt();
        }
        System.out.println("Press 0 to Exit");

        if(studentOption == 0){
            return;
        }

        System.out.println("What would you like to change?");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Seminar");
        System.out.println("4. Name and Age");
        System.out.println("5. All");
        System.out.println("6. Cancel and Exit to Menu");
        int changeOption = newScanner.nextInt();

        String newName;
        int newAge;
        Seminar newSeminar;
        studentOption--;
        switch (changeOption){
            case 1:
                System.out.println("What new name?");
                newScanner.nextLine();
                newName = newScanner.nextLine();
                data.get(studentOption).setName(newName);
                break;
            case 2:
                System.out.println("What new age?");
                newAge = newScanner.nextInt();
                data.get(studentOption).setAge(newAge);
                break;
            case 3:
                System.out.println("What new Seminar?");
                if (!seminarService.data.isEmpty()) {
                    int x = 0;
                    int choose = -1;
                    while (choose != 0) {
                        System.out.println("\nPress 0 to Exit");
                        System.out.print("Choose a seminar:\n");
                        for (Seminar s : seminarService.data) {
                            System.out.println(s.getId() + ". " + s.getTitle());
                            x++;
                        }
                        choose = newScanner.nextInt();
                        if (choose > 0 && choose <= x) {
                            newSeminar = seminarService.data.get(--choose);
                            data.get(studentOption).setSeminar(newSeminar);
                            System.out.println("Successfully change!");
                            break;
                        }
                    }
                }
                break;
            case 4:
                System.out.println("What new name?");
                newScanner.nextLine();
                newName = newScanner.nextLine();
                System.out.println("What new age?");
                newAge = newScanner.nextInt();
                data.get(studentOption).setName(newName);
                data.get(studentOption).setAge(newAge);
                break;
            case 5:
                System.out.println("What new name?");
                newScanner.nextLine();
                newName = newScanner.nextLine();
                System.out.println("What new age?");
                newAge = newScanner.nextInt();
                System.out.println("What new Seminar?");
                if (!seminarService.data.isEmpty()) {
                    int x = 0;
                    int choose = -1;
                    while (choose != 0) {
                        System.out.println("\nPress 0 to Exit");
                        System.out.print("Choose a seminar:\n");
                        for (Seminar s : seminarService.data) {
                            System.out.println(s.getId() + ". " + s.getTitle());
                            x++;
                        }
                        choose = newScanner.nextInt();
                        if (choose > 0 && choose <= x) {
                            newSeminar = seminarService.data.get(--choose);
                            data.get(studentOption).setName(newName);
                            data.get(studentOption).setAge(newAge);
                            data.get(studentOption).setSeminar(newSeminar);
                            System.out.println("Successfully change!");
                            break;
                        }
                    }
                }
                break;
            case 6:
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
    }
}