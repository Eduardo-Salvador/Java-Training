package StudyChallenges.SeminarSystem.v2_0.Controller;
import StudyChallenges.SeminarSystem.v2_0.Service.SeminarService;
import StudyChallenges.SeminarSystem.v2_0.Service.StudentService;
import StudyChallenges.SeminarSystem.v2_0.Service.TeacherService;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    SeminarService seminarBaseService = new SeminarService(new ArrayList<>());
    StudentService studentsBaseService = new StudentService(new ArrayList<>(), seminarBaseService);
    TeacherService teacherBaseService = new TeacherService(new ArrayList<>(), seminarBaseService);

    public static void displayMenu(){
        System.out.println("\n=== Seminar System ===");
        System.out.println("1. Register Seminar");
        System.out.println("2. Change Seminar");
        System.out.println("3. List Seminars");
        System.out.println("4. Register Student");
        System.out.println("5. Change Student");
        System.out.println("6. List Students");
        System.out.println("7. Register Teacher");
        System.out.println("8. Change Teacher");
        System.out.println("9. List Teachers");
        System.out.println("10. Exit");
    }

    public void runApp(){
        Scanner newScanner = new Scanner(System.in);
        int option = 0;

        while (option != 10){
            if(option < 0 || option > 10){
                System.out.println("Invalid Option, try again.");
            }
            displayMenu();
            option = newScanner.nextInt();
            switch (option){
                case 1:
                    seminarBaseService.register(newScanner);
                    break;
                case 2:
                    seminarBaseService.change(newScanner);
                    break;
                case 3:
                    seminarBaseService.list();
                    break;
                case 4:
                    studentsBaseService.register(newScanner);
                    break;
                case 5:
                    studentsBaseService.change(newScanner);
                    break;
                case 6:
                    studentsBaseService.list();
                    break;
                case 7:
                    teacherBaseService.register(newScanner);
                    break;
                case 8:
                    teacherBaseService.change(newScanner);
                    break;
                case 9:
                    teacherBaseService.list();
                    break;
                case 10:
                    System.out.println("Exit...");
                    newScanner.close();
                    break;
            }
        }
    }
}