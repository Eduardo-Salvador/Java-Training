package UtilityClasses.Date;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        long oneDay = 86_400_000L;

        System.out.println("Now: " + date);
        System.out.println("Now in ms: " + date.getTime());

        long nowMs = date.getTime();
        Date now = new Date(nowMs);
        date.setTime(date.getTime() + oneDay); //24h

        System.out.println("Tomorrow: " + date);
        System.out.println("Tomorrow before now? " + date.before(now));
        System.out.println("Tomorrow after now? " + date.after(now));

        long diffMs = date.getTime() - now.getTime();
        long diffDays = diffMs / oneDay;
        System.out.println("Difference in days: " + diffDays);
    }
}