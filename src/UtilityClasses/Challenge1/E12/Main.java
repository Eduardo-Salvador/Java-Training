package UtilityClasses.Challenge1.E12;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Scanner;

public class Main {

    public static String clearText(String text) throws IOException {
        if (text == null) {
            throw new IOException("String Null, try again");
        }
        String normalizer = Normalizer.normalize(text, Normalizer.Form.NFD);
        String noAccents = normalizer.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String clear = noAccents.replaceAll("[\\p{Punct}]", "");
        return clear.trim();
    }

    public static boolean palindromeCheck(String word){
        try {
            word = clearText(word).toLowerCase();

            StringBuilder sb = new StringBuilder(word);
            StringBuilder reversed = new StringBuilder(word).reverse();

            return sb.toString().contentEquals(reversed);

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        boolean pCheck = false;
        String palindrome;

        do {
            System.out.print("Enter any word: ");
            palindrome = i.nextLine();
            pCheck = palindromeCheck(palindrome);

            if (!pCheck) {
                System.out.println("Not a palindrome, try again.\n");
            }
        } while (!pCheck);
        System.out.println("The word: " + palindrome + " is palindrome.");
    }
}
