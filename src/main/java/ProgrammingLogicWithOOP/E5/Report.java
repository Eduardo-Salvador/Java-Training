package ProgrammingLogicWithOOP.E5;

import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;

import java.util.Locale;
import java.util.Scanner;

public class Report {
    private final double MIN_SALARY = 1293.2;

    private void salaryCounter(double salary){
        double salaryCounter = salary / MIN_SALARY;
        System.out.printf("You receive: %.2f minimum wages.%n", salaryCounter);
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        Value value = new CustomNumber();
        System.out.println("Algorithm 5:");
        System.out.print("Enter your salary: ");
        value.setValueDouble(input.nextDouble());
        input.close();
        salaryCounter(value.getValueDouble());
    }
}