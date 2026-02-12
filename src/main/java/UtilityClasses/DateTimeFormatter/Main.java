package UtilityClasses.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        String log = "2026-02-11 14:30:45 - System Error";

        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateHourString = log.substring(0, 19);

        LocalDateTime dateHourLog = LocalDateTime.parse(dateHourString, originalFormat);

        String formattedDate = dateHourLog.format(originalFormat);

        System.out.println("Original Log: " + log);
        System.out.println("Formatted date: " + formattedDate);

        LocalDateTime now = LocalDateTime.now();

        long seconds = ChronoUnit.SECONDS.between(dateHourLog, now);
        long minutes = ChronoUnit.MINUTES.between(dateHourLog, now);
        long hours = ChronoUnit.HOURS.between(dateHourLog, now);
        long days = ChronoUnit.DAYS.between(dateHourLog, now);

        System.out.println("\nTime elapsed since the log:");
        System.out.println("- " + days + " days");
        System.out.println("- " + hours + " hours");
        System.out.println("- " + minutes + " minutes");
        System.out.println("- " + seconds + " seconds");

        System.out.println("\nTime elapsed (formatted):");
        if (days > 0) {
            long hoursRest = hours % 24;
            long minutesRest = minutes % 60;
            System.out.println(days + " day(s), " + hoursRest + " hour(s) and " + minutesRest + " minute(s)");
        } else if (hours > 0) {
            long minutesRest = minutes % 60;
            System.out.println(hours + " hora(s) e " + minutesRest + " minuto(s)");
        } else if (minutes > 0) {
            System.out.println(minutes + " minuto(s)");
        } else {
            System.out.println(seconds + " segundo(s)");
        }
    }
}