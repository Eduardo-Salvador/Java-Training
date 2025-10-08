package Exceptions.E7.Controller;
import Exceptions.E7.Domain.BalanceInsufficientException;
import Exceptions.E7.Domain.BankAccount;
import java.util.Scanner;

public class Menu {
    public int menu(Scanner scanner){
        System.out.println("\nWhat do you want to do?\n1.Deposit\n2.Withdraw\n3.View balance\n4.Exit");
        return scanner.nextInt();
    }

    public void displayMenu(Scanner scanner, BankAccount account) throws BalanceInsufficientException {
        int option = menu(scanner);
        while (option != 4) {
            switch (option){
                case 1:
                    System.out.print("What is the deposit amount? $");
                    account.deposit(scanner.nextDouble());
                    option = menu(scanner);
                    break;
                case 2:
                    System.out.print("What is the withdraw amount? $");
                    account.withdraw(scanner.nextDouble());
                    option = menu(scanner);
                    break;
                case 3:
                    System.out.println("Actual Balance: $" + account.getBalance());
                    option = menu(scanner);
                    break;
                default:
                    System.out.println("Invalid Option");
                    option = menu(scanner);
                    break;
            }
        }
        System.out.println("Exit...");
    }
}