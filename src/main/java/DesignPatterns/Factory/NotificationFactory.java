package DesignPatterns.Factory;

public class NotificationFactory {
    public static Notification create(String type){
        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "PUSH" -> new PushNotification();
            case "SMS" -> new SmsNotification();
            default -> throw new IllegalArgumentException("Unknown type.");
        };
    }
}