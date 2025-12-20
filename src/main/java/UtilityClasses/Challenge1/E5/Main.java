package UtilityClasses.Challenge1.E5;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static void checkExpiration(LocalDate date) {
        LocalDate now = LocalDate.now();
        if (now.isAfter(date)) {
            System.out.println("Account with expired date");
            System.out.println("Due Date: " + date);
            System.out.println("Current Date: " + now);
        } else if (now.isEqual(date)) {
            System.out.println("Account due today");
            System.out.println("Due Date: " + date);
            System.out.println("Current Date: " + now);
        } else {
            System.out.println("Account due on time");
            System.out.println("Due Date: " + date);
            System.out.println("Current Date: " + now);
        }
    }

    private static boolean isValid(String dateString) throws IOException {
        int[] dateVerification = new int[8];
        int index = 0;
            for (int i = 0; i < dateString.length(); i++) {
                char c = dateString.charAt(i);
                if (Character.isDigit(c)) {
                    dateVerification[index++] = Character.getNumericValue(c);
                }
            }
            if (dateVerification[0] > 1) {
                throw new IOException("Invalid Input Date, try again");
            }
            if (dateVerification[2] > 3){
                throw new IOException("Invalid Input Date, try again");
            }
            return true;
    }

    private static String menu(){
        Scanner inputDate = new Scanner(System.in);
        System.out.println("Enter your account expiration date:\nFormat: mm-dd-yyyy");
        return inputDate.nextLine();
    }

    public static void main(String[] args) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = null;
        String dateString;
        boolean valid = false;
        while (!valid){
            try {
                dateString = menu();
                isValid(dateString);
                date = LocalDate.parse(dateString, formatter);
                valid = true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e){
                System.out.println("Invalid format. Please use MM-dd-yyyy.");
            }
        }
        checkExpiration(date);
    }
}