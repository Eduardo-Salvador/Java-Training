package UtilityClasses.Challenge1.E2;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String date = "25/12/2025";
        DateTimeFormatter formatterBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parseDate = LocalDate.parse(date, formatterBR);
        System.out.println(parseDate);
    }
}