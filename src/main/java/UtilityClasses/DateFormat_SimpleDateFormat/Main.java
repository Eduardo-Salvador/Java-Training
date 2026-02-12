package UtilityClasses.DateFormat_SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String dateEN_US = "12/25/2025";
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(dateEN_US);
        } catch (ParseException e) {
            System.out.println("Invalid Date");
            return;
        }

        String formatted = outputFormat.format(date);
        System.out.println(formatted);
        String pattern = "dd 'de' MMMM 'de' yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
        System.out.println(sdf.format(date));
    }
}
