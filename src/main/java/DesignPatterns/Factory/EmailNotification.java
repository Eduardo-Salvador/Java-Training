package DesignPatterns.Factory;

public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email Notification...");
        System.out.println(message);
    }
}