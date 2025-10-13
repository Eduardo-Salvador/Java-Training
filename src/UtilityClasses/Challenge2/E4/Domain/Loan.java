package UtilityClasses.Challenge2.E4.Domain;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private double value;
    private final LocalDate contractDate;
    private final int installments;
    private final double monthlyInterestRate;

    public void setValue(double value) {
        this.value = value;
    }

    public Loan(double value, LocalDate contractDate, int installments, double monthlyInterestRate) throws IOException {
        if (value <= 0){
            throw new IOException("Loan amount cannot be less than or equal to zero");
        }
        if (contractDate.isBefore(LocalDate.now())){
            throw new IOException("Loan date cannot be less than today");
        }
        if (installments <= 0){
            throw new IOException("Installments cannot be 0 or negative");
        }
        if (monthlyInterestRate <= 0){
            throw new IOException("Monthly interest rates cannot be less than or equal to 0");
        }
        this.value = value;
        this.contractDate = contractDate;
        this.installments = installments;
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public double calculateCompoundInterest(double value, int installments, double monthlyInterestRate){
        return value * Math.pow(1 + monthlyInterestRate, installments);
    }

    public double simulateEarlyPayment(LocalDate earlyPaymentDate) {
        LocalDate dueDate = contractDate.plusMonths(installments);
        if (earlyPaymentDate.isAfter(dueDate)) {
            System.out.println("Payment after due date. No discount.");
            return 0.0;
        }
        long monthsRemaining = ChronoUnit.MONTHS.between(earlyPaymentDate, dueDate);
        double presentValue = value * Math.pow(1 + monthlyInterestRate, -monthsRemaining);
        double discount = value - presentValue;
        System.out.println("Months remaining: " + monthsRemaining);
        System.out.println("Original total value: $" + String.format("%.2f", value));
        System.out.println("Early payment amount: $" + String.format("%.2f", presentValue));
        System.out.println("Discount obtained: $" + String.format("%.2f", discount));
        return discount;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public int getInstallments() {
        return installments;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }
}