package UtilityClasses.Challenge1.E15;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NumberFormatException {
        Scanner i = new Scanner(System.in);
        System.out.print("Enter any value in String: ");
        String s1 = i.nextLine();
        try {
            Double d1 = Double.parseDouble(s1);
            Integer i1 = d1.intValue();
            System.out.println("String converted successfully: ");
            System.out.println("→ As Integer: " + i1);
            System.out.println("→ As Double:  " + d1);
        } catch (NumberFormatException e){
            System.out.println("Value incompatible with conversion.");
        }
        i.close();
    }
}