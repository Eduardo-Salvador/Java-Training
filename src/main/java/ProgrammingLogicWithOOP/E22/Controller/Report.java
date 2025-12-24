package ProgrammingLogicWithOOP.E22.Controller;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {

    private void division(int num, int num2){
        System.out.println("Division: " + num + " / " + num2);
        System.out.println("Quotient = " + (num / num2));
        System.out.println("Remainder = " + (num % num2));
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber n1 = new CustomNumber();
        CustomNumber n2 = new CustomNumber();
        System.out.println("Algorithm 22:");
        System.out.print("Enter the first number of the division: ");
        n1.setValueInt(input.nextInt());
        System.out.print("Enter the second number of the division: ");
        n2.setValueInt(input.nextInt());
        division(n1.getValueInt(), n2.getValueInt());
        input.close();
    }
}