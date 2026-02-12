package UtilityClasses.Period_ChronoUnit;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDate admission = LocalDate.of(2020, 1, 1);
        LocalDate now = LocalDate.now();

        System.out.println("Admission: " + admission);
        System.out.println("Today: " + now);

        Period diference = Period.between(admission, now);
        long totalDays = ChronoUnit.DAYS.between(admission, now);
        System.out.println(
                "Diference: " +
                        diference.getYears() + " years, " +
                        diference.getMonths() + " months and " +
                        diference.getDays() + " days (" +
                        totalDays + " total days)"
        );
    }
}