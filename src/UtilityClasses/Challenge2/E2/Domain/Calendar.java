package UtilityClasses.Challenge2.E2.Domain;
import java.time.LocalDate;

public class Calendar {
    private final Event[] calendar = new Event[100];

    public void addToCalendar(Event event){
        boolean added = false;
        for (int i = 0; i < calendar.length; i++) {
            if (calendar[i] == null) {
                calendar[i] = event;
                added = true;
                break;
            }
        }
        if (!added) {
            System.out.println("List is full, try again");
        }
    }


    public void listEventsOfTheDay(){
        LocalDate now = LocalDate.now();
        Event[] eventsToday = new Event[100];
        if (calendar[0] == null) {
            System.out.println("Calendar is empty, try again.");
            return;
        }
        for (Event value : calendar) {
            if(value != null){
                LocalDate eventDate = value.getDateTimeEvent().toLocalDate();
                if (eventDate.isEqual(now)) {
                    for (int j = 0; j < eventsToday.length; j++) {
                        if (eventsToday[j] == null) {
                            eventsToday[j] = value;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Events of the Day:");
        for (Event event: eventsToday){
            if (event != null) {
                System.out.println("Name: " + event.getName());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Date: " + event.getDateTimeEvent().getDayOfWeek() + " - " + event.getDateTimeEvent().getMonthValue() + "/" + event.getDateTimeEvent().getDayOfMonth() + "/" + event.getDateTimeEvent().getYear());
                System.out.println("Location: " + event.getLocation());
                System.out.println("---------------------------");
            }
        }
    }

    public void listEventsOfTheWeek() {
        LocalDate now = LocalDate.now();
        LocalDate sixDaysLater = now.plusDays(6);
        Event[] eventsWeek = new Event[100];
        if (calendar[0] == null) {
            System.out.println("Calendar is empty, try again.");
            return;
        }
        for (Event value : calendar) {
            if(value != null){
                LocalDate eventDate = value.getDateTimeEvent().toLocalDate();
                if (!eventDate.isBefore(now) && !eventDate.isAfter(sixDaysLater)) {
                    for (int j = 0; j < eventsWeek.length; j++) {
                        if (eventsWeek[j] == null) {
                            eventsWeek[j] = value;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Events of the Week:");
        for (Event event : eventsWeek) {
            if (event != null){
                System.out.println("Name: " + event.getName());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Date: " + event.getDateTimeEvent().getDayOfWeek() + " - " + event.getDateTimeEvent().getMonthValue() + "/" + event.getDateTimeEvent().getDayOfMonth() + "/" + event.getDateTimeEvent().getYear());
                System.out.println("Location: " + event.getLocation());
                System.out.println("---------------------------");
            }
        }
    }

    public void listNextEvents() {
        LocalDate now = LocalDate.now();
        Event[] nextEvents = new Event[100];
        if (calendar[0] == null) {
            System.out.println("Calendar is empty, try again.");
            return;
        }
        for (Event value : calendar) {
            if (value != null){
                LocalDate eventDate = value.getDateTimeEvent().toLocalDate();
                if (!eventDate.isBefore(now)) {
                    for (int j = 0; j < nextEvents.length; j++) {
                        if (nextEvents[j] == null) {
                            nextEvents[j] = value;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Next Events:");
        for (Event event : nextEvents) {
            if (event != null){
                System.out.println("Name: " + event.getName());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Date: " + event.getDateTimeEvent().getDayOfWeek() + " - " + event.getDateTimeEvent().getMonthValue() + "/" + event.getDateTimeEvent().getDayOfMonth() + "/" + event.getDateTimeEvent().getYear());
                System.out.println("Location: " + event.getLocation());
                System.out.println("---------------------------");
            }
        }
    }
}