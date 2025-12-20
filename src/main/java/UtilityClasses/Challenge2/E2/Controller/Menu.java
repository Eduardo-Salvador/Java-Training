package UtilityClasses.Challenge2.E2.Controller;
import UtilityClasses.Challenge2.E2.Domain.Calendar;
import UtilityClasses.Challenge2.E2.Domain.Event;
import java.util.Scanner;

public class Menu {
    public void menu(){
        Scanner inp = new Scanner(System.in);
        int option;
        Event event = new Event();
        Calendar calendar = new Calendar();
        do {
            System.out.println("Appointments Agenda:");
            System.out.println("1. Create a event");
            System.out.println("2. List events of the day");
            System.out.println("3. List events of the week");
            System.out.println("4. List next events");
            System.out.println("5. Exit");
            option = inp.nextInt();

            switch (option){
                case 1:
                    try {
                        event.createEvent(calendar);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    calendar.listEventsOfTheDay();
                    break;
                case 3:
                    calendar.listEventsOfTheWeek();
                    break;
                case 4:
                    calendar.listNextEvents();
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (option != 5);
    }
}