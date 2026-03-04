package DesignPatterns.Factory;

public class SmsNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("SMS Received...");
        System.out.println(message);
    }
}