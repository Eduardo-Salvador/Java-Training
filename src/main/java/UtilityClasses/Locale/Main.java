package UtilityClasses.Locale;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Double value = 1234.56;
        Date now = new Date();

        NumberFormat currencyBRL = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
        NumberFormat currencyUSDT = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat currencyJPL = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        NumberFormat currencyGER = NumberFormat.getCurrencyInstance(Locale.GERMANY);

        DateFormat dateBRL = DateFormat.getDateInstance(DateFormat.SHORT, Locale.of("pt", "BR"));
        DateFormat dateUSD = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        DateFormat dateJPL = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
        DateFormat dateGER = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);

        System.out.println("Currency Brazil: " + currencyBRL.format(value));
        System.out.println("Date Brazil: " + dateBRL.format(now));

        System.out.println("Currency United States: " + currencyUSDT.format(value));
        System.out.println("Date United States: " + dateUSD.format(now));

        System.out.println("Currency Japan: " + currencyJPL.format(value));
        System.out.println("Date Japan: " + dateJPL.format(now));

        System.out.println("Currency Germany: " + currencyGER.format(value));
        System.out.println("Date Germany: " + dateGER.format(now));
    }
}