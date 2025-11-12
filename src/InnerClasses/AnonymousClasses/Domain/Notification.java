package InnerClasses.AnonymousClasses.Domain;

public class Notification {
    private String message;
    private Integer priority;

    public Notification(String message, Integer priority) {
        this.message = message;
        this.priority = priority;
    }

    public void send(){
        System.out.println("Sending default notification: <" + message + "> Priority: <" + priority + ">");
    }

    public String getMessage() {
        return message;
    }

    public Integer getPriority() {
        return priority;
    }
}