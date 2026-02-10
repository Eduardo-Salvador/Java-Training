package Streams.Summarizing.Application;
import Streams.Summarizing.Domain.Transaction;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import static Streams.Summarizing.Domain.Type.*;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(DEPOSIT, 500.0));
        transactions.add(new Transaction(WITHDRAWAL, 120.0));
        transactions.add(new Transaction(DEPOSIT, 1000.0));
        transactions.add(new Transaction(WITHDRAWAL, 300.0));
        transactions.add(new Transaction(DEPOSIT, 250.0));
        transactions.add(new Transaction(WITHDRAWAL, 80.0));
        transactions.add(new Transaction(DEPOSIT, 700.0));
        transactions.add(new Transaction(WITHDRAWAL, 150.0));
        transactions.add(new Transaction(DEPOSIT, 400.0));
        transactions.add(new Transaction(WITHDRAWAL, 50.0));
        transactions.add(new Transaction(DEPOSIT, 900.0));
        transactions.add(new Transaction(WITHDRAWAL, 200.0));
        transactions.add(new Transaction(DEPOSIT, 300.0));
        transactions.add(new Transaction(WITHDRAWAL, 60.0));
        transactions.add(new Transaction(DEPOSIT, 1100.0));

        DoubleSummaryStatistics generalStats = transactions.stream()
                .collect(Collectors.summarizingDouble(Transaction::getAmount));

        System.out.println("=== GENERAL STATS ===");
        System.out.println("Count: " + generalStats.getCount());
        System.out.printf("Sum: $%.2f\n", generalStats.getSum());
        System.out.println("Min: $" + generalStats.getMin());
        System.out.println("Max: $" + generalStats.getMax());
        System.out.printf("Average: $%.2f\n", generalStats.getAverage());
        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics depositStats = transactions.stream()
                .filter(x -> x.getType() == DEPOSIT)
                .collect(Collectors.summarizingDouble(Transaction::getAmount));

        System.out.println("=== DEPOSIT STATS ===");
        System.out.println("Count: " + depositStats.getCount());
        System.out.printf("Sum: $%.2f\n", depositStats.getSum());
        System.out.println("Min: $" + depositStats.getMin());
        System.out.println("Max: $" + depositStats.getMax());
        System.out.printf("Average: $%.2f\n", depositStats.getAverage());
        System.out.println("--------------------------------------------");

        DoubleSummaryStatistics withdrawalStats = transactions.stream()
                .filter(x -> x.getType() == WITHDRAWAL)
                .collect(Collectors.summarizingDouble(Transaction::getAmount));

        System.out.println("=== WITHDRAWAL STATS ===");
        System.out.println("Count: " + withdrawalStats.getCount());
        System.out.printf("Sum: $%.2f\n", withdrawalStats.getSum());
        System.out.println("Min: $" + withdrawalStats.getMin());
        System.out.println("Max: $" + withdrawalStats.getMax());
        System.out.printf("Average: $%.2f\n", withdrawalStats.getAverage());
        System.out.println("--------------------------------------------");
    }
}