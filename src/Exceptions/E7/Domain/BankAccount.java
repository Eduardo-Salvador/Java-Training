package Exceptions.E7.Domain;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) throws BalanceInsufficientException{
        if (balance < 0) {
            throw new BalanceInsufficientException("Initial deposit cannot be less than 0");
        } else {
            this.balance = balance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw (double value) throws BalanceInsufficientException {
        if (value > balance) {
            throw new BalanceInsufficientException("Balance less than requested withdrawal");
        } else {
            balance -= value;
            System.out.printf("Withdraw successful, value: $%.2f", value);
        }
    }

    public void deposit(double value){
        balance += value;
        System.out.printf("Deposit successful, value: $%.2f", value);
    }
}