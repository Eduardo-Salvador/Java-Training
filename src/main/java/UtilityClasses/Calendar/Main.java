package UtilityClasses.Calendar;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(2001, Calendar.JANUARY, 17);

        int years = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
        int months = now.get(Calendar.MONTH) - birthdate.get(Calendar.MONTH);

        if (months < 0) {
            years--;
            months += 12;
        }

        System.out.println("Age: " + years + " years and " + months + " months");

        birthdate.add(Calendar.YEAR, 10);
        System.out.println("10 Years+: " + birthdate.getTime());

        Calendar nextBirthday = (Calendar) birthdate.clone();
        nextBirthday.set(Calendar.YEAR, now.get(Calendar.YEAR));

        if (nextBirthday.before(now)) {
            nextBirthday.add(Calendar.YEAR, 1);
        }

        long diffMs = nextBirthday.getTimeInMillis() - now.getTimeInMillis();
        long diffDays = diffMs / (1000 * 60 * 60 * 24);

        System.out.println("Days until next birthday: " + diffDays);
    }
}