package LogicTrainingWithOOP.E17.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private double temperatureConverter(double num){
        return 5 * (num-32) / 9;
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber temperature = new CustomNumber();
        System.out.println("Algorithm 17:");
        System.out.print("What the temperature in Fahrenheit?: ");
        temperature.setValueDouble(input.nextDouble());
        System.out.printf("Temperature in Fahrenheit: %.2f°%n", temperature.getValueDouble());
        temperature.setValueDouble(temperatureConverter(temperature.getValueDouble()));
        System.out.printf("Temperature in Celsius: %.2f°%n", temperature.getValueDouble());
        input.close();
    }
}