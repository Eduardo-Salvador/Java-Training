package UtilityClasses.Challenge2.E4.Controller;
import UtilityClasses.Challenge2.E4.Domain.Loan;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Menu {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static LocalDate date;

    private static boolean isValid(String dateString) throws IOException {
        int[] dateVerification = new int[8];
        int index = 0;
        for (int i = 0; i < dateString.length(); i++) {
            char c = dateString.charAt(i);
            if (Character.isDigit(c)) {
                dateVerification[index++] = Character.getNumericValue(c);
            }
        }
        if (dateVerification[0] > 1) {
            throw new IOException("Invalid Input Date, try again");
        }
        if (dateVerification[2] > 3) {
            throw new IOException("Invalid Input Date, try again");
        }
        if (dateVerification[7] != 5){
            throw new IOException("Invalid Input Date, try again");
        }
        return true;
    }

    public void menu(){
        Scanner i = new Scanner(System.in);
        Loan loan = null;
        System.out.println("Loan System");
        double originalValue = 0;
        int option = 0;
        do {
            System.out.println("1. Request a Loan");
            System.out.println("2. View current loan");
            System.out.println("3. Simulate discharge");
            System.out.println("4. Exit");
            option = i.nextInt();

            switch (option){
                case 1:
                    System.out.println("-----------------");
                    if (loan != null){
                        System.out.println("Loan in progress, please pay it off to continue");
                        break;
                    }
                    System.out.print("What value is needed? $");
                    double value = i.nextDouble();
                    originalValue = value;
                    i.nextLine();
                    System.out.println("What date should I start the contract and receive the amount? (Limit 2025)\nUse mm-dd-yyyy.");
                    String dateHourString = i.nextLine();
                    try {
                        if (isValid(dateHourString)) {
                            date = LocalDate.parse(dateHourString, formatter);
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        System.out.println("-----------------");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid format. Please use mm-dd-yyyy.");
                        System.out.println("-----------------");
                    }
                    System.out.print("How many installments? ");
                    int installments = i.nextInt();
                    System.out.print("What is the desired interest rate? ");
                    double rate = i.nextDouble()/100;
                    try {
                        System.out.println("-----------------");
                        loan = new Loan(value, date, installments, rate);
                        double loanValue = loan.calculateCompoundInterest(value, installments, rate);
                        System.out.println("Value Loan: $" + originalValue);
                        System.out.printf("Debit balance: $%.2f%n", loanValue);
                        System.out.printf("Installment is %dx of $%.2f%n", installments, (loanValue/installments));
                        System.out.println("Confirm? (1 Yes, 2 No)");
                        if (i.nextInt() == 1) {
                            i.nextLine();
                            loan.setValue(loanValue);
                            System.out.println("Employer successfully requested!");
                            System.out.println("-----------------");
                            break;
                        }
                        else {
                            i.nextLine();
                            loan = null;
                            System.out.println("Canceling operation");
                            System.out.println("-----------------");
                            break;
                        }
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                        System.out.println("-----------------");
                        break;
                    }
                case 2:
                    System.out.println("-----------------");
                    if (loan != null){
                        System.out.println("Current loan:");
                        System.out.println("Contract Date: " + loan.getContractDate());
                        if (loan.getContractDate().isAfter(LocalDate.now())){
                            long month = ChronoUnit.MONTHS.between(loan.getContractDate(), LocalDate.now());
                            if (month != 0){
                                double valuePaid = (loan.getValue()/loan.getInstallments()) * month;
                                loan.setValue(loan.getValue() - valuePaid);
                            }
                        }
                        System.out.println("Value Loan: $" + originalValue);
                        System.out.printf("Debit balance: $%.2f%n", loan.getValue());
                        System.out.printf("Installment is %dx of $%.2f%n", loan.getInstallments(), (loan.getValue() / loan.getInstallments()));
                        System.out.println("Rate: " + (loan.getMonthlyInterestRate()*100) + "%");
                    } else {
                        System.out.println("No current loan");
                    }
                    System.out.println("-----------------");
                    break;
                case 3:
                    System.out.println("-----------------");
                    if (loan != null){
                        loan.simulateEarlyPayment(LocalDate.now());
                        System.out.println("Proceed with payment?");
                        System.out.println("Confirm? (1 Yes, 2 No)");
                        if (i.nextInt() == 1){
                            i.nextLine();
                            loan = null;
                            System.out.println("Payment completed successfully");
                            System.out.println("-----------------");
                            break;
                        } else if (i.nextInt() == 2){
                            i.nextLine();
                            System.out.println("Canceling operation");
                            System.out.println("-----------------");
                            break;
                        } else {
                            i.nextLine();
                            System.out.println("Invalid option, canceling operation");
                            System.out.println("-----------------");
                            break;
                        }
                    } else {
                        System.out.println("No current loan");
                        System.out.println("-----------------");
                        break;
                    }
                case 4:
                    System.out.println("Exit...");
                    i.close();
                    break;
                default:
                    System.out.println("Invalid option.");
                    i.close();
                    break;
            }
        } while (option != 4);
    }
}