package DesignPatterns.Factory;

public class Main {
    public static void main(String[] args) {
        Notification  sms = NotificationFactory.create("Sms");
        Notification  push = NotificationFactory.create("Push");
        Notification  email = NotificationFactory.create("Email");

        sms.send("Mom");
        System.out.println();
        push.send("Friends");
        System.out.println();
        email.send("Job");
        System.out.println();

        try {
            Notification error = NotificationFactory.create("Error");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}