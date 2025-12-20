package UtilityClasses.Challenge2.E1.Domain;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Invoice {
    private int number;
    private LocalDate dateOfIssue;
    private double totalValue;
    private LocalDate dueDate;

    public Invoice(){
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    private final LocalDate now = LocalDate.now();

    public Invoice(int number, LocalDate dateOfIssue, double totalValue, LocalDate dueDate) {
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.totalValue = totalValue;
        this.dueDate = dueDate;
    }

     public void payInvoice() throws PaymentException{
        if (isOverdue()){
            throw new PaymentException("It is not possible to pay an overdue invoice, please calculate fine.");
        } else {
            System.out.println("Invoice paid successfully!");
        }
    }

    public boolean isOverdue(){
        return now.isAfter(dueDate);
    }

    public double calculateFine(double percentagePerDay) throws NumberFormatException{
        if (percentagePerDay > 100 || percentagePerDay < 0){
            throw new NumberFormatException("Invalid percentage, try again");
        }
        if (isOverdue()){
            long diff = ChronoUnit.DAYS.between(dueDate, now);
            percentagePerDay = (percentagePerDay/100);
            return ((percentagePerDay * diff) + 1) * totalValue;
        }
        System.out.println("Invoice on time");
        return 0;
    }

    public int getNumber() {
        return number;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}