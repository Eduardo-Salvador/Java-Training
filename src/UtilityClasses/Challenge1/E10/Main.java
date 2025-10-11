package UtilityClasses.Challenge1.E10;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale localeIT = new Locale("it", "IT");
        Instant now = Instant.now();

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, localeIT);
        String dateIT = dateFormat.format(Date.from(now));
        System.out.println(dateIT);

        double value = 5342.21;
        NumberFormat number = NumberFormat.getNumberInstance(localeIT);
        System.out.println(number.getCurrency() + ": " + number.format(value));

    }
}
