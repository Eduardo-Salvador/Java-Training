package Exceptions.E1.Services;
import java.util.Scanner;

public class Calculator {
    private static int calculator(int dividend, int divider) throws ArithmeticException{
        if (divider == 0){
            throw new ArithmeticException("Divisor cannot be 0");
        }
        return dividend / divider;
    }

    public void Menu(){
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Division: ");
            System.out.print("Enter the first number dividend: ");
            int dividend = input.nextInt();
            System.out.print("Enter the second number divider: ");
            int divider = input.nextInt();
            System.out.println("Result: " + calculator(dividend, divider));
        } catch (ArithmeticException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}