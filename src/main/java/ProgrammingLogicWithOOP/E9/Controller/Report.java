package ProgrammingLogicWithOOP.E9.Controller;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private void algorithmBMI(double weight, double height){
        double resultBMI = weight / (height * height);
        System.out.println("BMI Result:");
        if (resultBMI < 18.5){
            System.out.println("Underweight");
        } else if (resultBMI >= 18.5 && resultBMI <= 24.9) {
            System.out.println("Ideal weight (congratulations)");
        } else if (resultBMI >= 25 && resultBMI <= 29.9) {
            System.out.println("Slightly overweight");
        } else if (resultBMI >= 30 && resultBMI <= 34.9) {
            System.out.println("Grade I obesity");
        } else if (resultBMI >= 35 && resultBMI <= 39.9) {
            System.out.println("Grade II (severe) obesity");
        } else {
            System.out.println("Grade III (morbid) obesity");
        }
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        Value weight = new CustomNumber();
        Value height = new CustomNumber();

        System.out.println("Algorithm 9:");
        System.out.print("Enter your weight: ");
        weight.setValueDouble(input.nextDouble());
        System.out.print("Enter your height: ");
        height.setValueDouble(input.nextDouble());
        input.close();
        algorithmBMI(weight.getValueDouble(), height.getValueDouble());
    }
}