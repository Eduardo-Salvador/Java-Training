package UtilityClasses.Challenge2.E2.Domain;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Event {
    private String name;
    private String description;
    private LocalDateTime dateTimeEvent;
    private String location;
    private static String dateHourString;
    private static LocalDate date;
    private static LocalTime time;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private static boolean isValid(String dateString) throws IOException {
        int[] dateVerification = new int[8];
        int index = 0;
        for (int i = 0; i < dateString.length(); i++) {
            char c = dateString.charAt(i);
            if (Character.isDigit(c)) {
                dateVerification[index++] = Character.getNumericValue(c);
            }
        }
        if (dateVerification[0] > 1) {
            throw new IOException("Invalid Input Date, try again");
        }
        if (dateVerification[2] > 3) {
            throw new IOException("Invalid Input Date, try again");
        }
        return true;
    }

    public Event() {
    }

    public Event(String name, String description, LocalDateTime dateTimeEvent, String location) {
        this.name = name;
        this.description = description;
        this.dateTimeEvent = dateTimeEvent;
        this.location = location;
    }

    public void createEvent(Calendar calendar) throws Exception{
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter the event name:");
        name = inp.nextLine();
        System.out.println("Enter the description:");
        description = inp.nextLine();
        System.out.println("What is the event date?\nFormat: mm-dd-yyyy");
        dateHourString = inp.nextLine();

        try {
            if (isValid(dateHourString)) {
                date = LocalDate.parse(dateHourString, formatter);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid format. Please use mm-dd-yyyy.");
        }

        System.out.println("What time is the event??\nFormat: 00h for 23h59");
        dateHourString = inp.nextLine();

        if(dateHourString.length() < 5){
            throw new Exception("Invalid format. Please use 00h00/01h00 for 23h59");
        }

        if (dateHourString.charAt(2) != 'h') {
            System.out.println("Incorrect input. Please try again: 00h for 23h59");
            return;
        }

        String[] parts = dateHourString.split("h");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        time = LocalTime.of(hour, minute);
        dateTimeEvent = LocalDateTime.of(date, time);

        System.out.println("What is the location?");
        location = inp.nextLine();
        Event event = new Event(name, description, dateTimeEvent, location);
        calendar.addToCalendar(event);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTimeEvent() {
        return dateTimeEvent;
    }

    public String getLocation() {
        return location;
    }
}