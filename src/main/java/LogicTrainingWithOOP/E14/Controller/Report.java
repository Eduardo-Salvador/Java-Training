package LogicTrainingWithOOP.E14.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber n1 = new CustomNumber();
        CustomNumber n2 = new CustomNumber();
        System.out.println("Algorithm 14:");
        System.out.print("Enter number 1 (A): ");
        n2.setValueInt(input.nextInt());
        System.out.print("Enter number 2 (B): ");
        n1.setValueInt(input.nextInt());
        System.out.println("Number 1: " + n1.getValueInt() + " - Number 2: " + n2.getValueInt());
        input.close();
    }
}