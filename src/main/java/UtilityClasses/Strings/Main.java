package UtilityClasses.Strings;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a CPF: ");
        String cpf = input.nextLine();

        cpf = cpf.replace(".", "")
                .replace("-", "");

        if (cpf.length() != 11) {
            System.out.println("Invalid CPF: must have 11 digits");
            return;
        }
        String base = cpf.substring(0, 9);
        String dv = cpf.substring(9);

        System.out.println("CPF cleaned: " + cpf);
        System.out.println("Base (9 digits): " + base);
        System.out.println("Check digits: " + dv);

        System.out.println("Enter a check digits: ");
        String checkDigits = input.nextLine();
        if (dv.equals(checkDigits)) {
            System.out.println("Verifier equals " + dv);
        }
    }
}