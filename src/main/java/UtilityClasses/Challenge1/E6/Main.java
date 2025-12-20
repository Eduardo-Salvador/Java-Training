package UtilityClasses.Challenge1.E6;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        Instant now = Instant.now();

        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime newYork = now.atZone(newYorkZone);
        String newYorkOutput = newYork.format(formatterOutput);
        System.out.println(newYorkZone + " " + newYorkOutput);

        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyo = now.atZone(tokyoZone);
        String tokyoOutput = tokyo.format(formatterOutput);
        System.out.println(tokyoZone + " " + tokyoOutput);
    }
}