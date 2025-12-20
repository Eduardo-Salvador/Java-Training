package Exceptions.E7.Application;
import Exceptions.E7.Controller.Menu;
import Exceptions.E7.Domain.BalanceInsufficientException;
import Exceptions.E7.Domain.BankAccount;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            Menu newMenu = new Menu();
            System.out.print("Welcome, enter the initial deposit: $");
            BankAccount b1 = new BankAccount(input.nextDouble());
            newMenu.displayMenu(input, b1);
        } catch (BalanceInsufficientException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}