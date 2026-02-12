package UtilityClasses.LocalDate_LocalTime_LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        LocalDate appointmentDate = LocalDate.of(2026, 3, 15);
        LocalTime appointmentTime = LocalTime.of(14, 30);
        LocalDateTime appointment = LocalDateTime.of(appointmentDate, appointmentTime);
        System.out.println("Original Date: " + appointment);
        System.out.println("New date: " + appointment.plusWeeks(1));
        System.out.println("Is a weekend date? " + appointment.getDayOfWeek());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm a", Locale.US);
        System.out.println(appointment.format(pattern));
    }
}