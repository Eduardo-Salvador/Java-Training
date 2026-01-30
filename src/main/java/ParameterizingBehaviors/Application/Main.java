package ParameterizingBehaviors.Application;
import ParameterizingBehaviors.Services.PaymentProcessor;
import ParameterizingBehaviors.Domain.PaymentStrategy;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PaymentStrategy creditCard = new PaymentStrategy() {
            @Override
            public void pay(double amount) {
                if (amount > 3000) {
                    System.out.println("Applying 2% discount");
                    amount *= 0.98;
                }
                System.out.println("Payment of <$" + amount + "> by credit card");
            }
        };

        PaymentStrategy pix = new PaymentStrategy() {
            @Override
            public void pay(double amount) {
                if (amount > 3000) {
                    System.out.println("Applying 10% discount");
                    amount *= 0.9;
                }
                System.out.println("Payment of <$" + amount + "> by pix");
            }
        };
        PaymentStrategy ticket = new PaymentStrategy() {
            @Override
            public void pay(double amount) {
                if (amount > 3000) {
                    System.out.println("Applying 5% discount");
                    amount *= 0.95;
                }
                System.out.println("Payment of <$" + amount + "> by ticket");
            }
        };

        PaymentProcessor p1 = new PaymentProcessor();
        //p1.processPayment(3500, pix);
        //or
        p1.processPayment(3500, x -> {
            if (x > 3000) {
                System.out.println("Applying 10% discount");
                x *= 0.9;
            }
            System.out.println("Payment of <$" + x + "> by pix");
        });

        p1.processPayment(3500, x -> {
        if (x > 3000) {
            System.out.println("Applying 2% discount");
            x *= 0.98;
        }
        System.out.println("Payment of <$" + x + "> by credit card");
        });

        p1.processPayment(3500, x -> {
            if (x > 3000) {
                System.out.println("Applying 5% discount");
                x *= 0.95;
            }
            System.out.println("Payment of <$" + x + "> by ticket");
        });

        System.out.println("----------------------------------------");

        List<PaymentStrategy> paymentStrategyList = new ArrayList<>(List.of(creditCard, pix, ticket));
        for (PaymentStrategy p : paymentStrategyList){
            p.pay(5000);
        }
    }
}