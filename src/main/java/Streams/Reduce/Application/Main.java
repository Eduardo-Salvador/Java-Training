package Streams.Reduce.Application;
import Streams.Reduce.Domain.Sale;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        List<Sale> sales = new ArrayList<>();
        sales.add(new Sale("Laptop", 2, 3500.00));
        sales.add(new Sale("Mouse", 5, 80.00));
        sales.add(new Sale("Keyboard", 3, 150.00));
        sales.add(new Sale("Monitor", 4, 900.00));
        sales.add(new Sale("Headset", 6, 200.00));
        sales.add(new Sale("Gaming Chair", 1, 1200.00));
        sales.add(new Sale("Webcam", 2, 300.00));
        sales.add(new Sale("External Hard Drive", 3, 450.00));

        double sumResult = sales.stream()
                .map(Sale::getTotalValue)
                .reduce(0D, Double::sum);

        Optional<Sale> maxSale = sales.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparing(Sale::getTotalValue)));

        Optional<Integer> quantitySum = sales.stream()
                .map(Sale::getQuantity)
                .reduce((a, b) -> a * b);

        System.out.printf("Revenue: $%.2f\n", sumResult);
        maxSale.ifPresent(m -> System.out.println("Max Sale: " + m));
        quantitySum.ifPresent(s -> System.out.println("Product of quantity items in Stock: " + s));
    }
}