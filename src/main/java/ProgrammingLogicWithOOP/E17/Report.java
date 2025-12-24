package ProgrammingLogicWithOOP.E17;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private double temperatureConverter(double num){
        return 5 * (num-32) / 9;
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber temperatureC = new CustomNumber();
        CustomNumber temperatureF = new CustomNumber();
        System.out.println("Algorithm 17:");
        System.out.print("What the temperature in Fahrenheit?: ");
        temperatureF.setValueDouble(input.nextDouble());
        System.out.printf("Temperature in Fahrenheit: %.2f°%n", temperatureF.getValueDouble());
        temperatureC.setValueDouble(temperatureConverter(temperatureF.getValueDouble()));
        System.out.printf("Temperature in Celsius: %.2f°%n", temperatureC.getValueDouble());
        input.close();
    }
}