package InnerClasses.AnonymousClasses.Application;
import InnerClasses.AnonymousClasses.Domain.Alert;
import InnerClasses.AnonymousClasses.Domain.Event;
import InnerClasses.AnonymousClasses.Domain.Notification;
import InnerClasses.AnonymousClasses.Domain.Notifier;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Tech Conference", "25/11/2025"));
        events.add(new Event("Hackathon ", "30/11/2025"));
        events.add(new Event("Dev Summit", "10/12/2025"));
        events.add(new Event("AI Workshop", "15/12/2025"));
        events.add(new Event("Cloud Expo", "20/12/2025"));

        Notifier emailNotifier = new Notifier() {
            @Override
            public void notifyUser(String message) {
                System.out.println("[Email] " + message);
            }
        };

        Notifier smsNotifier = new Notifier() {
            @Override
            public void notifyUser(String message) {
                System.out.println("[SMS] " + message);
            }
        };

        Notifier pushNotifier = new Notifier() {
            @Override
            public void notifyUser(String message) {
                System.out.println("[Push] " + message);
            }
        };

        for (Event e : events) {
            emailNotifier.notifyUser("New event registered: " + e.getName() + " - " + e.getDate());
            smsNotifier.notifyUser("Reminder: " + e.getName() + " on " + e.getDate());
            pushNotifier.notifyUser("Upcoming event: " + e.getName());
            System.out.println("---------------------------------");
        }

        Notification n1 = new Notification("Hello, please call me", 5){
            @Override
            public void send(){
                System.out.println("Sending notification: <" + getMessage().toUpperCase() + "> Priority: <" + getPriority() + ">");
            }
        };
        Notification n2 = new Notification("Hello, how are you?", 1){
            @Override
            public void send(){
                class User {
                    private String name;

                    public User(String name) {
                        this.name = name;
                    }

                    public String getName() {
                        return name;
                    }
                }
                User u1 = new User("John");
                System.out.println("Sending notification: <" + getMessage() + "> Priority: <" + getPriority() + "> - User: " + u1.getName());
            }
        };

        List<Notification> notificationList = new ArrayList<>(List.of(n1, n2));
        for (Notification n : notificationList){
            n.send();
            System.out.println("----------------------");
        }

        Alert a1 = new Alert() {
            @Override
            public void execute() {
                System.out.println("Java is the best!");
            }
        };

        Alert a2 = new Alert() {
            @Override
            public void execute() {
                System.out.println("I love you Java!");
            }
        };

        a1.execute();
        a2.execute();
    }
}