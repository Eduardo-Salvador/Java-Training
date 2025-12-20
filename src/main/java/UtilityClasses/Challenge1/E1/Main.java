package UtilityClasses.Challenge1.E1;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatterBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultDateBR = now.format(formatterBR);
        System.out.println(resultDateBR);
    }
}