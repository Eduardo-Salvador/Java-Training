package Exceptions.E1.Controller;
import Exceptions.E1.Domain.CreateNumber;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    private static int calculator(int dividend, int divider) throws ArithmeticException, InputMismatchException{
        if (divider == 0){
            throw new ArithmeticException("Divisor cannot be 0");
        }
        return dividend / divider;
    }

    public void Menu(){
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Division: ");
            System.out.print("Enter the first number dividend: ");
            CreateNumber dividender = new CreateNumber(input.nextInt());
            System.out.print("Enter the second number divider: ");
            CreateNumber divider = new CreateNumber(input.nextInt());
            System.out.println("Result: " + calculator(dividender.getNumber(), divider.getNumber()));
        } catch (ArithmeticException | InputMismatchException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}