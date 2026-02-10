package Streams.MultilevelGrouping.Application;
import Streams.MultilevelGrouping.Domain.Sale;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Sale> sales = new ArrayList<>();
        sales.add(new Sale("North", "Laptop", 3, 3500.0));
        sales.add(new Sale("South", "Mouse", 10, 80.0));
        sales.add(new Sale("East", "Keyboard", 5, 150.0));
        sales.add(new Sale("West", "Monitor", 4, 900.0));
        sales.add(new Sale("North", "Headset", 6, 200.0));
        sales.add(new Sale("South", "Chair", 2, 1200.0));
        sales.add(new Sale("East", "Webcam", 7, 300.0));
        sales.add(new Sale("West", "Desk", 1, 1500.0));
        sales.add(new Sale("North", "Notebook", 8, 20.0));
        sales.add(new Sale("South", "Pen", 50, 2.0));
        sales.add(new Sale("East", "Tablet", 3, 1800.0));
        sales.add(new Sale("West", "Printer", 2, 700.0));
        sales.add(new Sale("North", "Phone", 5, 2200.0));
        sales.add(new Sale("South", "Backpack", 4, 250.0));
        sales.add(new Sale("East", "Router", 6, 350.0));
        sales.add(new Sale("West", "SSD", 9, 400.0));
        sales.add(new Sale("North", "Camera", 2, 2500.0));
        sales.add(new Sale("South", "Microphone", 3, 600.0));
        sales.add(new Sale("East", "TV", 1, 4000.0));
        sales.add(new Sale("West", "Speakers", 5, 500.0));

        Map<String, Map<String, List<Sale>>> regionProduct = sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::getRegion,
                        Collectors.groupingBy(
                                Sale::getProduct
                        )));

        regionProduct.forEach((region, products) -> {
            System.out.println("Region: " + region.toUpperCase());
            products.forEach((product, salesList) -> {
                System.out.print("Product: " + product);
                salesList.forEach(sale -> {
                    System.out.print(" - Qty: " + sale.getQuantity() +
                            " - Value: $" + sale.getValue() + "\n");
                });
            });
            System.out.println("---------");
        });

        Map<String, Map<String, Long>> counterRegionSales = sales.stream()
                .collect(Collectors.groupingBy(Sale::getRegion,
                        Collectors.groupingBy(
                                Sale::getProduct,
                                Collectors.summingLong(
                                        Sale::getQuantity
                                ))));

        counterRegionSales.forEach((region, products) -> {
            System.out.println("Region: " + region.toUpperCase());
            products.forEach((product, s) -> {
                System.out.print("Product: " + product);
                System.out.print(" - Sales: " + s + "\n");
            });
            System.out.println("---------");
        });

        Map<String, Map<String, Double>> sumValuesPerRegion = sales.stream()
                .collect(Collectors.groupingBy(Sale::getRegion,
                        Collectors.groupingBy(
                                Sale::getProduct,
                                Collectors.summingDouble(
                                        s -> s.getValue() * s.getQuantity()
                                ))));

        sumValuesPerRegion.forEach((region, products) -> {
            System.out.println("Region: " + region.toUpperCase());
            products.forEach((product, s) -> {
                System.out.print("Product: " + product);
                System.out.printf(" - Total Value: $%.2f\n", s);
            });
            System.out.println("---------");
        });

    }
}