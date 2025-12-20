package UtilityClasses.Challenge1.E8;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        System.out.println("Enter any value");
        double value = i.nextDouble();
        Locale localeBR = new Locale("pt", "BR");
        Locale localeUSA = new Locale("en", "US");
        Locale localeJP = new Locale("ja", "JP");
        NumberFormat[] arrayNF = new NumberFormat[3];
        arrayNF[0] = NumberFormat.getCurrencyInstance(localeBR);
        arrayNF[1] = NumberFormat.getCurrencyInstance(localeUSA);
        arrayNF[2] = NumberFormat.getCurrencyInstance(localeJP);
        for(NumberFormat n: arrayNF){
            System.out.println(n.getCurrency() + ": " + n.format(value));
        }
    }
}