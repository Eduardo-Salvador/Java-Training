package ProgrammingLogicWithOOP.E23.Controller;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private double salaryCalculation(double classTimeMoney, int classesTaught, double percentINSS){
        return (classTimeMoney * classesTaught) * (1 - percentINSS);
    }

    private int validValue(int num, Scanner input){
        while (num <= 0){
            System.out.print("Invalid input, please input again: ");
            num = input.nextInt();
        }
        return num;
    }

    private double validValue(double num, Scanner input){
        while (num <= 0){
            System.out.print("Invalid input, please input again: ");
            num = input.nextDouble();
        }
        return num;
    }

    private double validValueINSS(double num, Scanner input){
        while (num <= 0){
            System.out.print("Invalid input, please input again: ");
            num = input.nextDouble()/100;
        }
        return num;
    }
    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Algorithm 23:");
        System.out.print("Enter the class time value: $");
        double classTime = validValue(input.nextDouble(), input);
        System.out.print("Enter the classes taught in the month: ");
        int classesTaught = validValue(input.nextInt(), input);
        System.out.print("Enter the percent of INSS: ");
        double percentINSS = validValueINSS(input.nextDouble()/100, input);
        System.out.printf("Your month salary is = $%.2f", salaryCalculation(classTime, classesTaught, percentINSS));
        input.close();
    }
}