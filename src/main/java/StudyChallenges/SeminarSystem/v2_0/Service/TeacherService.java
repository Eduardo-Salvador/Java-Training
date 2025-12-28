package StudyChallenges.SeminarSystem.v2_0.Service;
import StudyChallenges.SeminarSystem.v2_0.Domain.Seminar;
import StudyChallenges.SeminarSystem.v2_0.Domain.Teacher;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherService extends BaseService<Teacher> {

    private final SeminarService seminarService;

    public TeacherService(ArrayList<Teacher> arrayList, SeminarService seminarService) {
        super(arrayList);
        this.seminarService = seminarService;
    }

    @Override
    public void register(Scanner newScanner) {
        System.out.println("=== Teacher Register ===");

        System.out.print("Name: ");
        newScanner.nextLine();
        String name = newScanner.nextLine();

        System.out.print("Speciality: ");
        String specialty = newScanner.nextLine();

        System.out.println("Is the Teacher in a seminar? (Y/N)");
        String option = String.valueOf(newScanner.next().charAt(0));

        Teacher teacher;
        ArrayList<Seminar> seminarsArrayList;
        if (option.equalsIgnoreCase("n")) {
            teacher = new Teacher(name, specialty);
            System.out.println("Teacher successfully registered.\nName: " + teacher.getName() + "\nSpeciality: " + teacher.getSpecialty());
            data.add(teacher);
            return;
        }
        if (!seminarService.data.isEmpty()) {
            seminarsArrayList = new ArrayList<>();
            int x = 0;
            int choose = -1;
            do {
                System.out.println("\nPress 0 to Exit");
                System.out.print("Choose one or more seminars:\n");
                for (Seminar s : seminarService.data) {
                    System.out.println(s.getId() + ". " + s.getTitle());
                    x++;
                }
                choose = newScanner.nextInt();
                if (choose > 0 && choose <= x) {
                    Seminar seminarOption = seminarService.data.get(choose - 1);
                    seminarsArrayList.add(seminarOption);
                }
            } while (choose != 0);
            teacher = new Teacher(name, specialty, seminarsArrayList);
            data.add(teacher);
            System.out.println("Teacher successfully registered.\nName: " + teacher.getName() + "\nSpeciality: " + teacher.getSpecialty() + "\nSeminars: " + teacher.getSeminars());
            return;
        }
        System.out.println("No seminars for register");
    }

    @Override
    public void change(Scanner newScanner) {
        System.out.println("Choose a Teacher:");
        int teacherOption = -1;
        if (!data.isEmpty()) {
            for (Teacher t : data) {
                System.out.println(t.getId() + ". " + t.getName());
            }
            teacherOption = newScanner.nextInt();
        }
        System.out.println("Press 0 to Exit");

        if(teacherOption == 0){
            return;
        }

        System.out.println("What would you like to change?");
        System.out.println("1. Name");
        System.out.println("2. Specialty");
        System.out.println("3. Seminars");
        System.out.println("4. Name and Specialty");
        System.out.println("5. All");
        System.out.println("6. Cancel and Exit to Menu");
        int optionChange = newScanner.nextInt();

        String newName;
        String newSpecialty;
        ArrayList<Seminar> newSeminarArray;
        teacherOption--;
        switch (optionChange) {
            case 1:
                System.out.println("What new name?");
                newScanner.nextLine();
                newName = newScanner.nextLine();
                data.get(teacherOption).setName(newName);
                break;
            case 2:
                System.out.println("What new specialty?");
                newScanner.nextLine();
                newSpecialty = newScanner.nextLine();
                data.get(teacherOption).setSpecialty(newSpecialty);
                break;
            case 3:
                System.out.println("What are the new seminars?");
                if (!seminarService.data.isEmpty()) {
                    newSeminarArray = new ArrayList<>();
                    int x = 0;
                    int choose = -1;
                    do {
                        System.out.println("\nPress 0 to Exit");
                        System.out.print("Choose one or more seminars:\n");
                        for (Seminar s : seminarService.data) {
                            System.out.println(s.getId() + ". " + s.getTitle());
                            x++;
                        }
                        choose = newScanner.nextInt();
                        if (choose > 0 && choose <= x) {
                            Seminar seminarOption = seminarService.data.get(choose - 1);
                            newSeminarArray.add(seminarOption);
                        }
                    } while (choose != 0);
                    data.get(teacherOption).setSeminars(newSeminarArray);
                    return;
                }
                System.out.println("No seminars for register");
                break;
            case 4:
                System.out.println("What new name?");
                newScanner.nextLine();
                newName = newScanner.nextLine();
                System.out.println("What new specialty?");
                newSpecialty = newScanner.nextLine();
                data.get(teacherOption).setName(newName);
                data.get(teacherOption).setSpecialty(newSpecialty);
                break;
            case 5:
                System.out.println("What new name?");
                newScanner.nextLine();
                newName = newScanner.nextLine();
                System.out.println("What new specialty?");
                newSpecialty = newScanner.nextLine();
                System.out.println("What are the new seminars?");
                if (!seminarService.data.isEmpty()) {
                    newSeminarArray = new ArrayList<>();
                    int x = 0;
                    int choose = -1;
                    do {
                        System.out.println("\nPress 0 to Exit");
                        System.out.print("Choose one or more seminars: ");
                        for (Seminar s : seminarService.data) {
                            System.out.println(s.getId() + ". " + s.getTitle());
                            x++;
                        }
                        choose = newScanner.nextInt();
                        if (choose > 0 && choose <= x) {
                            Seminar seminarOption = seminarService.data.get(choose - 1);
                            newSeminarArray.add(seminarOption);
                        }
                    } while (choose != 0);
                    data.get(teacherOption).setName(newName);
                    data.get(teacherOption).setSpecialty(newSpecialty);
                    data.get(teacherOption).setSeminars(newSeminarArray);
                    return;
                }
                System.out.println("No seminars for register");
                break;
            case 6:
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + optionChange);
        }
    }
}