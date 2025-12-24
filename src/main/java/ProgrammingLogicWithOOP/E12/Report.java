package ProgrammingLogicWithOOP.E12;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;

import java.util.Locale;
import java.util.Scanner;

public class Report {
    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        int optionPayment;
        Value p1 = new CustomNumber();
        System.out.println("Algorithm 12:");
        System.out.print("Enter the product value: ");
        p1.setValueDouble(input.nextDouble());
        System.out.println("Enter the payment option");
        System.out.println("1 - Cash or Pix, receive a 15% discount\r\n" + //
                        "2 - Cash by credit card, receive a 10% discount\r\n" + //
                        "3 - Two installments on credit card, regular product price, interest-free\r\n" + //
                        "4 - Three or more installments on credit card, regular product price plus 10% interest");
        optionPayment = input.nextInt();
        switch (optionPayment) {
            case 1:
                System.out.printf("Final value in Cash or Pix option %d = $%.2f%n",
                        PaymentTerms.CASH_PIX.getValue(),
                        PaymentTerms.CASH_PIX.calculateValue(p1.getValueDouble()));
                break;
            case 2:
                System.out.printf("Final value in Cash by credit card option %d = $%.2f%n",
                        PaymentTerms.CASH_CREDIT_CARD.getValue(),
                        PaymentTerms.CASH_CREDIT_CARD.calculateValue(p1.getValueDouble()));
                break;
            case 3:
                System.out.printf("Final value in Two installments on credit card option %d = $%.2f%n",
                        PaymentTerms.TWO_INSTALLMENTS.getValue(),
                        PaymentTerms.TWO_INSTALLMENTS.calculateValue(p1.getValueDouble()));
                break;
            case 4:
                System.out.printf("Final value in Three or more installments on credit card option %d = $%.2f%n",
                        PaymentTerms.THREE_OR_MORE_INSTALLMENTS.getValue(),
                        PaymentTerms.THREE_OR_MORE_INSTALLMENTS.calculateValue(p1.getValueDouble()));
                break;
            default:
                System.out.println("Invalid Option, please try again!");
        }
    }
}