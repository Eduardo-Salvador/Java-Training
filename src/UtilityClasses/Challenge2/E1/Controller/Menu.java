package UtilityClasses.Challenge2.E1.Controller;
import UtilityClasses.Challenge2.E1.Domain.Invoice;
import UtilityClasses.Challenge2.E1.Domain.PaymentException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
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
        if (dateVerification[2] > 3){
            throw new IOException("Invalid Input Date, try again");
        }
        return true;
    }

    public void menu(){
        int number = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate issueDate = null;
        LocalDate dueDate = null;
        String dateString;
        int option;
        double totalValue;
        Invoice[] invoices = new Invoice[100];
        Scanner inp = new Scanner(System.in);

        do {
            System.out.println("Invoice Menu:");
            System.out.println("1. Create a new invoice");
            System.out.println("2. View Invoices");
            System.out.println("3. Pay invoices");
            System.out.println("4. Calculate late fee");
            System.out.println("5. Exit");
            option = inp.nextInt();

            switch (option){
                case 1:
                    System.out.println("What is the invoice issue date?\nFormat: mm-dd-yyyy");
                    inp.nextLine();
                    dateString = inp.nextLine();
                    try {
                        if(isValid(dateString)){
                            issueDate = LocalDate.parse(dateString, formatter);
                        }
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                        break;
                    } catch (DateTimeParseException e){
                        System.out.println("Invalid format. Please use mm-dd-yyyy.");
                        break;
                    }
                    System.out.println("What is the invoice value?");
                    totalValue = inp.nextDouble();
                    inp.nextLine();
                    System.out.println("What is the due date of the invoice?\nFormat: mm-dd-yyyy");
                    dateString = inp.nextLine();
                    try {
                        if(isValid(dateString)){
                            dueDate = LocalDate.parse(dateString, formatter);
                        }
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                        break;
                    } catch (DateTimeParseException e){
                        System.out.println("Invalid format. Please use mm-dd-yyyy.");
                        break;
                    }
                    Invoice invoice = new Invoice(++number, issueDate, totalValue, dueDate);
                    for (int i = 0; i < invoices.length; i++){
                        if (invoices[99] != null){
                            System.out.println("Full invoices list");
                            break;
                        }
                        if (invoices[i] == null){
                            invoices[i] = invoice;
                            System.out.println("Invoice registered!");
                            System.out.println("Number: " + invoice.getNumber() + "\nDate of Issue: " + invoice.getDateOfIssue() + "\nValue: $" + invoice.getTotalValue() + "\nDue Date: " + invoice.getDueDate());
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Invoices: ");
                    if (invoices[0] == null){
                        System.out.println("No invoices registered, try again");
                    } else {
                        for (Invoice value : invoices) {
                            if (value != null) {
                                System.out.println("Number: " + value.getNumber() + " - Value: $" + value.getTotalValue() + " - Due Date: " + value.getDueDate());
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Select one invoice for pay:");
                    if (invoices[0] == null){
                        System.out.println("No invoices registered, try again");
                    } else {
                        for (Invoice value : invoices) {
                            if (value != null) {
                                System.out.println("Number: " + value.getNumber() + " - Value: $" + value.getTotalValue() + " - Due Date: " + value.getDueDate());
                            }
                        }
                        try {
                            int optionInv = inp.nextInt()-1;
                            invoices[optionInv].payInvoice();
                            invoices[optionInv] = null;
                        } catch (PaymentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    System.out.println("What is the percentage of the daily fine?");
                    double percDay = inp.nextDouble();
                    System.out.println("Select one invoice for calculate:");
                    if (invoices[0] == null){
                        System.out.println("No invoices registered, try again");
                    } else {
                        for (Invoice value : invoices) {
                            if (value != null) {
                                System.out.println("Number: " + value.getNumber() + " - Value: $" + value.getTotalValue() + " - Due Date: " + value.getDueDate());
                            }
                        }
                        try {
                            int optionInv = inp.nextInt()-1;
                            double payValue = invoices[optionInv].calculateFine(percDay);
                            System.out.println("Corrected value: $" + payValue);
                            invoices[optionInv].setDueDate(LocalDate.now());
                            invoices[optionInv].setTotalValue(payValue);
                        } catch (NumberFormatException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (option != 5);

    }
}