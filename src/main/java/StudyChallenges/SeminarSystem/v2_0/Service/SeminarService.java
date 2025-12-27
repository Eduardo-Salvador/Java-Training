package StudyChallenges.SeminarSystem.v2_0.Service;
import StudyChallenges.SeminarSystem.v2_0.Domain.Location;
import StudyChallenges.SeminarSystem.v2_0.Domain.Seminar;
import java.util.ArrayList;
import java.util.Scanner;

public class SeminarService extends BaseService<Seminar>{

    @Override
    public void register(Scanner newScanner) {
        System.out.println("Seminar Register:");
        System.out.print("Title: ");
        String title = newScanner.nextLine();
        System.out.print("Address: ");
        String address = newScanner.nextLine();
        Seminar seminar = new Seminar(title, new Location(address));
        data.add(seminar);
        System.out.println(
                "\nSeminar successfully registered." +
                        "\nTitle: " + seminar.getTitle() +
                        "\nLocation: " + address
        );
    }

    @Override
    public void change(Seminar seminar, Scanner newScanner) {
        System.out.print("\nChoose a Seminar:");
        System.out.println();
        int id = newScanner.nextInt();
        System.out.println("\nPress 0 to Exit");
        int seminarOption = newScanner.nextInt();
        if (seminarOption == 0) {
            return;
        }
        System.out.println("What would you like to change?");
        System.out.println("1. Title");
        System.out.println("2. Location");
        System.out.println("3. All");
        System.out.println("4. Cancel and Exit to Menu");
        int changeOption = newScanner.nextInt();

        Location newLocation;
        String newTitle;
        switch (changeOption) {
            case 1:
                System.out.println("What a new title?");
                newScanner.nextLine();
                newTitle = newScanner.nextLine();
                break;
            case 2:
                System.out.println("What a new Location?");
                newScanner.nextLine();
                newLocation = newScanner.nextLine();
                break;
            case 3:
                System.out.println("What a new title?");
                newScanner.nextLine();
                newTitle = newScanner.nextLine();
                System.out.println("What a new Location?");
                newLocation = new Location(newScanner.nextLine();)
                break;
            case 4:
                return;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
        Seminar newSeminar = new Seminar(newTitle, newLocation);
        data.set(id, newSeminar);
    }

    public SeminarService(ArrayList<Seminar> arrayList) {
        super(arrayList);
    }
}
