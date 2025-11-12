package InnerClasses.LocalClasses.Application;
import InnerClasses.LocalClasses.Domain.Event;

public class Main {
    public static void main(String[] args) {
        Event e1 = new Event("GitHub California", 100);
        e1.generateTickets();
    }
}