package Streams.GroupingByDownstream.Application;
import Streams.GroupingByDownstream.Domain.Product;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product("Laptop", "Electronics", 3500.0, 10));
        products.add(new Product("Smartphone", "Electronics", 2200.0, 25));
        products.add(new Product("Headphones", "Electronics", 300.0, 40));
        products.add(new Product("Monitor", "Electronics", 900.0, 15));
        products.add(new Product("Keyboard", "Electronics", 150.0, 50));
        products.add(new Product("Rice", "Food", 25.0, 100));
        products.add(new Product("Beans", "Food", 18.0, 80));
        products.add(new Product("Milk", "Food", 6.5, 60));
        products.add(new Product("Bread", "Food", 7.0, 70));
        products.add(new Product("Cheese", "Food", 30.0, 35));
        products.add(new Product("T-Shirt", "Clothing", 50.0, 45));
        products.add(new Product("Jeans", "Clothing", 120.0, 30));
        products.add(new Product("Jacket", "Clothing", 200.0, 20));
        products.add(new Product("Sneakers", "Clothing", 250.0, 25));
        products.add(new Product("Cap", "Clothing", 35.0, 40));

        Map<String, Double> summingCategoryPrices = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(p -> p.getPrice() * p.getStock())));

        System.out.println("=== SUMMING STOCK PER CATEGORY ===");
        System.out.println(summingCategoryPrices);

        Map<String, Double> averageCategoryPrices = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        System.out.println("=== AVERAGE PRICE PER CATEGORY ===");
        System.out.println(averageCategoryPrices);

        Map<String, DoubleSummaryStatistics> statsCategoryPrices =
                products.stream()
                        .collect(Collectors.groupingBy(Product::getCategory, Collectors.summarizingDouble(Product::getPrice)));

        System.out.println("=== STATS PER CATEGORY ===");
        statsCategoryPrices.forEach((category, stats) -> {
            System.out.println(category.toUpperCase());
            System.out.println("Count: " + stats.getCount());
            System.out.println("Sum: $" + stats.getSum());
            System.out.println("Max: $" + stats.getMax());
            System.out.println("Min: $" + stats.getMin());
            System.out.println("Average: $" + stats.getAverage());
            System.out.println("-----");
        });
    }
}