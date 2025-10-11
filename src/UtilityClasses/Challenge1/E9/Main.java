package UtilityClasses.Challenge1.E9;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale localeBR = new Locale("pt", "BR");
        Locale localeUSA = new Locale("en", "US");
        NumberFormat[] arrayNF = new NumberFormat[2];
        arrayNF[0] = NumberFormat.getCurrencyInstance(localeBR);
        arrayNF[1] = NumberFormat.getCurrencyInstance(localeUSA);
        boolean valid = false;
        do{
            try {
                Scanner i = new Scanner(System.in);
                System.out.println("Enter a number: ");
                double number = i.nextDouble();
                for (NumberFormat nf : arrayNF){
                    System.out.println(nf.getCurrency() + ": " + nf.format(number));
                }
                valid = true;
                i.close();
            } catch (Exception e){
                System.out.println("Invalid type number in input, please try again");
            }
        } while (!valid);
    }
}
