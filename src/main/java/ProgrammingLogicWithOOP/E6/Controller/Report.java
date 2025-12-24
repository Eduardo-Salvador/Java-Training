package ProgrammingLogicWithOOP.E6.Controller;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private void value5percent(double value){
        System.out.printf("5%% percent adjustment in value: %.2f = %.2f%n", value, (value * 1.05));
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        Value value = new CustomNumber();
        System.out.println("Algorithm 6:");
        System.out.print("Enter any value: ");
        value.setValueDouble(input.nextDouble());
        input.close();
        value5percent(value.getValueDouble());
    }
}