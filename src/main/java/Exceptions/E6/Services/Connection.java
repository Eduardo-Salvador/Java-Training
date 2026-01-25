package Exceptions.E6.Services;
import Exceptions.E6.Domain.MockConnection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Connection {
    public void run() {
        try(MockConnection connection = new MockConnection();
        Scanner input = new Scanner(System.in);){
            System.out.println("Enter one int number: ");
            int newNumber = input.nextInt();
            System.out.println("Number: " + newNumber);
        } catch (InputMismatchException e){
            System.out.println("Input invalid!");
        }
    }
}