package UtilityClasses.Challenge1.E7;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        Instant now = Instant.now();
        LocalDateTime laterL = LocalDateTime.of(2025, 10, 10, 19, 49, 12);
        ZoneId zone = ZoneId.systemDefault();
        Instant later = laterL.atZone(zone).toInstant();
        Duration d1 = Duration.between(now, later);
        System.out.println("Duration: " + d1);
        System.out.println("Days: " + d1.toDays());
        System.out.println("Hours: " + d1.toHours());
        System.out.println("Minutes: " + d1.toMinutes());
        System.out.println("Seconds: " + d1.toSeconds());
        System.out.println("Milliseconds: " + d1.toMillis());
        System.out.println("Nanoseconds: " + d1.toNanos());
    }
}
