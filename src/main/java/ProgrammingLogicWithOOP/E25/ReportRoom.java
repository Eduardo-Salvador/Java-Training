package ProgrammingLogicWithOOP.E25;
import java.util.Locale;
import java.util.Scanner;

public class ReportRoom {
    private static void checkerRoom(Room room){
        if(room.getActualTemp() > room.getMaxTemp()){
            System.out.println("Needs cooling (AC on)");
        } else if (room.getActualTemp() < room.getMinTemp()) {
            System.out.println("Needs heating (Heater on)");
        } else {
            System.out.println("Stable");
        }
    }

    private boolean checker(Room[] rooms){
        if(rooms[0] == null){
            System.out.println("No rooms registered, please register rooms and try again.");
            return true;
        }
        return false;
    }

    private static void countHeatedRooms(Room[] rooms){
        int counter = 0;
        for (Room value : rooms) {
            if (value != null) {
                if (value.getActualTemp() < value.getMinTemp()) {
                    counter++;
                }
            }
        }
        System.out.println("We have " + counter + " rooms with heaters on.");

    }

    public int menu(Room[] rooms) {
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Algorithm 25:");
        System.out.println("Temperature Control System");
        System.out.println("What do you want to do?");
        System.out.println("1. Register a room");
        System.out.println("2. Change room settings");
        System.out.println("3. Check rooms");
        System.out.println("4. Check rooms with heater on");
        System.out.println("5. Exit");
        int optionMenu = input.nextInt();
        int option = 0;
        switch (optionMenu) {
            case 1:
                Room room = new Room();
                System.out.print("Enter the room name: ");
                input.nextLine();
                room.setName(input.nextLine());
                System.out.print("What is the minimum temperature? ");
                room.setMinTemp(input.nextDouble());
                System.out.print("What is the maximum temperature? ");
                room.setMaxTemp(input.nextDouble());
                System.out.print("What is the current room temperature? ");
                room.setActualTemp(input.nextDouble());
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i] == null) {
                        rooms[i] = room;
                        break;
                    }
                }
                break;
            case 2:
                if (checker(rooms)){
                    break;
                }
                System.out.println("Which room do you want to change?");
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i] != null) {
                        System.out.println(i + 1 + ". " + rooms[i].getName());
                    }
                }
                option = input.nextInt();
                option--;
                System.out.println("What do you want to change?");
                System.out.println("1. Room Name");
                System.out.println("2. Minimum temperature");
                System.out.println("3. Maximum temperature");
                System.out.println("4. Room actual temperature");
                System.out.println("5. Press 5 to exit.");
                switch (input.nextInt()) {
                    case 1:
                        System.out.print("What new name? ");
                        rooms[option].setName(input.nextLine());
                        break;
                    case 2:
                        System.out.print("What new min temperature? ");
                        rooms[option].setMinTemp(input.nextDouble());
                        break;
                    case 3:
                        System.out.print("What new max temperature? ");
                        rooms[option].setMaxTemp(input.nextDouble());
                        break;
                    case 4:
                        System.out.println("What new room temperature?");
                        rooms[option].setActualTemp(input.nextDouble());
                        break;
                    default:
                        System.out.println("Incorrect option, back to menu");
                        runReport();
                        break;

                }
                break;
            case 3:
                if (checker(rooms)){
                    break;
                }
                System.out.println("=== Climate Control ===");
                for (Room value : rooms) {
                    if (value != null) {
                        System.out.println("Room: " + value.getName());
                        System.out.printf("Current temperature: %.2f°C%n", value.getActualTemp());
                        checkerRoom(value);
                        System.out.println();
                    }
                }
                break;
            case 4:
                if (checker(rooms)){
                    break;
                }
                countHeatedRooms(rooms);
                break;
            case 5:
                System.out.println("Ending...");
                break;
            default:
                System.out.println("Invalid option");
                runReport();
                break;
        }
        return optionMenu;
    }

    public void runReport(){
        Room[] rooms = new Room[100];
        int optionMenu;
        do {
            optionMenu = menu(rooms);
        } while (optionMenu != 5);
        System.out.println("Program ended.");
    }
}
