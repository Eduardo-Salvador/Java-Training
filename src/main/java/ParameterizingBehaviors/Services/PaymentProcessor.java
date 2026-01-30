package ParameterizingBehaviors.Services;
import ParameterizingBehaviors.Domain.PaymentStrategy;

public class PaymentProcessor {
    public void processPayment(double amount, PaymentStrategy strategy){
        strategy.pay(amount);
    }
}