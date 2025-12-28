package StudyChallenges.SeminarSystem.v2_0.Service;
import StudyChallenges.SeminarSystem.v2_0.Domain.Location;
import StudyChallenges.SeminarSystem.v2_0.Domain.Seminar;
import java.util.ArrayList;
import java.util.Scanner;

public class SeminarService extends BaseService<Seminar>{

    public SeminarService(ArrayList<Seminar> arrayList) {
        super(arrayList);
    }

    @Override
    public void register(Scanner newScanner) {
        System.out.println("=== Seminar Register ===");
        System.out.print("Title: ");
        newScanner.nextLine();
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
    public void change(Scanner newScanner) {
        System.out.print("\nChoose a Seminar:");
        System.out.println();
        for (Seminar s : data) {
            System.out.println(s.getId() + ". " + s.getTitle());
        }
        System.out.println("\nPress 0 to Exit");
        int id = newScanner.nextInt();
        if (id == 0) {
            return;
        }
        Seminar newSeminar = data.get(--id);

        System.out.println("What would you like to change?");
        System.out.println("1. Title");
        System.out.println("2. Location");
        System.out.println("3. All");
        System.out.println("4. Cancel and Exit to Menu");
        int changeOption = newScanner.nextInt();

        switch (changeOption) {
            case 1:
                System.out.println("What a new title?");
                newScanner.nextLine();
                newSeminar.setTitle(newScanner.nextLine());
                break;
            case 2:
                System.out.println("What a new Location?");
                newScanner.nextLine();
                newSeminar.setLocation(new Location(newScanner.nextLine()));
                break;
            case 3:
                System.out.println("What a new title?");
                newScanner.nextLine();
                newSeminar.setTitle(newScanner.nextLine());
                System.out.println("What a new Location?");
                newSeminar.setLocation(new Location(newScanner.nextLine()));
                break;
            case 4:
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
        data.set(id, newSeminar);
    }
}