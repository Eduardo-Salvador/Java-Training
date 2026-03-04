package DesignPatterns.Factory;

public class PushNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("New social network notifications...");
        System.out.println(message);
    }
}