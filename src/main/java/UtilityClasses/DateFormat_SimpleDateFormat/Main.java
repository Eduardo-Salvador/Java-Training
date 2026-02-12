package UtilityClasses.DateFormat_SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a date:");
        String dateEN_US = input.nextLine();

        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        inputFormat.setLenient(false);

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
        SimpleDateFormat extendOutput = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
        System.out.println(extendOutput.format(date));
    }
}