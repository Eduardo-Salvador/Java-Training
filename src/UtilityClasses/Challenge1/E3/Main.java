package UtilityClasses.Challenge1.E3;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate nowPlus2 = now.plusDays(2);
        System.out.println(ChronoUnit.DAYS.between(now, nowPlus2) + " Days");
    }
}