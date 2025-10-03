package LogicTrainingWithOOP.E12.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import LogicTrainingWithOOP.CustomNumberDomain.Value;
import LogicTrainingWithOOP.E12.Domain.PaymentTerms;
import java.util.Locale;
import java.util.Scanner;

public class Report {

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        Value p1 = new CustomNumber();
        int optionPayment;
        System.out.println("Algorithm 12:");
        System.out.print("Enter the product value: ");
        p1.setValueDouble(input.nextDouble());
        System.out.println("Enter the payment option");
        System.out.println("1 - Cash or Pix, receive a 15% discount\r\n" + //
                        "2 - Cash by credit card, receive a 10% discount\r\n" + //
                        "3 - Two installments on credit card, regular product price, interest-free\r\n" + //
                        "4 - Three or more installments on credit card, regular product price plus 10% interest");
        optionPayment = input.nextInt();
    }
}