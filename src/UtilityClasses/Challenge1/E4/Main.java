package UtilityClasses.Challenge1.E4;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        now = now.plusDays(30);
        if (DayOfWeek.from(now) == DayOfWeek.SUNDAY || DayOfWeek.from(now) == DayOfWeek.SATURDAY){
            System.out.println("In 30 days it will be: " + DayOfWeek.from(now) + ", a weekend");
        }
    }
}