package UtilityClasses.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate nextMonday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate firstDayOfTheNextMonth = now.with(TemporalAdjusters.firstDayOfNextMonth());

        LocalDate lastBusinessDay = lastDayOfMonth;
        if (lastDayOfMonth.getDayOfWeek() == DayOfWeek.SATURDAY) {
            lastBusinessDay = lastDayOfMonth.minusDays(1);
        } else if (lastDayOfMonth.getDayOfWeek() == DayOfWeek.SUNDAY) {
            lastBusinessDay = lastDayOfMonth.minusDays(2);
        }

        System.out.println("Now: " + now);
        System.out.println("Last day of Month: " + lastDayOfMonth);
        System.out.println("Last business day of Month: " + lastBusinessDay);
        System.out.println("Next Monday: " + nextMonday);
        System.out.println("First dat of the next Month: " + firstDayOfTheNextMonth);
    }
}