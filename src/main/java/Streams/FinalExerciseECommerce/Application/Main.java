package Streams.FinalExerciseECommerce.Application;
import Streams.FinalExerciseECommerce.Domain.Customer;
import Streams.FinalExerciseECommerce.Domain.Order;
import Streams.FinalExerciseECommerce.Domain.OrderStatus;
import java.util.*;
import java.util.stream.Collectors;
import static Streams.FinalExerciseECommerce.Domain.OrderStatus.*;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Eduardo", "São Paulo"));
        customers.add(new Customer(2, "Ana", "Campinas"));
        customers.add(new Customer(3, "Carlos", "Rio de Janeiro"));
        customers.add(new Customer(4, "Marina", "Curitiba"));
        customers.add(new Customer(5, "João", "Belo Horizonte"));
        customers.add(new Customer(6, "Fernanda", "Salvador"));
        customers.add(new Customer(7, "Lucas", "Fortaleza"));
        customers.add(new Customer(8, "Patricia", "Recife"));
        customers.add(new Customer(9, "Bruno", "Porto Alegre"));
        customers.add(new Customer(10, "Juliana", "Florianópolis"));

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, 1, List.of("Mouse", "Keyboard"), 250.0, PENDING));
        orders.add(new Order(2, 2, List.of("Monitor"), 900.0, PROCESSING));
        orders.add(new Order(3, 3, List.of("Notebook"), 3500.0, SHIPPED));
        orders.add(new Order(4, 1, List.of("Headset"), 300.0, DELIVERED));
        orders.add(new Order(5, 4, List.of("Webcam"), 200.0, PENDING));
        orders.add(new Order(6, 5, List.of("Mousepad"), 80.0, DELIVERED));
        orders.add(new Order(7, 6, List.of("SSD 1TB"), 450.0, PROCESSING));
        orders.add(new Order(8, 7, List.of("GPU"), 2500.0, SHIPPED));
        orders.add(new Order(9, 8, List.of("RAM 16GB"), 320.0, DELIVERED));
        orders.add(new Order(10, 9, List.of("Chair"), 1200.0, PENDING));
        orders.add(new Order(11, 10, List.of("Desk"), 1500.0, PROCESSING));
        orders.add(new Order(12, 2, List.of("Keyboard", "Mouse"), 400.0, SHIPPED));
        orders.add(new Order(13, 3, List.of("Notebook Bag"), 180.0, DELIVERED));
        orders.add(new Order(14, 4, List.of("HD 2TB"), 500.0, PENDING));
        orders.add(new Order(15, 5, List.of("Router"), 350.0, PROCESSING));
        orders.add(new Order(16, 6, List.of("Switch"), 600.0, SHIPPED));
        orders.add(new Order(17, 7, List.of("Microphone"), 700.0, DELIVERED));
        orders.add(new Order(18, 8, List.of("Camera"), 2200.0, PENDING));
        orders.add(new Order(19, 9, List.of("Tripod"), 150.0, PROCESSING));
        orders.add(new Order(20, 10, List.of("Lighting Kit"), 800.0, SHIPPED));
        orders.add(new Order(21, 1, List.of("USB Hub"), 120.0, DELIVERED));
        orders.add(new Order(22, 2, List.of("Tablet"), 1300.0, PENDING));
        orders.add(new Order(23, 3, List.of("Smartphone"), 2800.0, PROCESSING));
        orders.add(new Order(24, 4, List.of("Smartwatch"), 900.0, SHIPPED));
        orders.add(new Order(25, 5, List.of("Powerbank"), 200.0, DELIVERED));

        List<String> products = orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .distinct()
                .toList();

        System.out.println("=== PRODUCTS ===");
        products.forEach(System.out::println);
        System.out.println("-------------");

        Map<OrderStatus, Long> statusOrders = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.counting()
                ));
        System.out.println("=== STATUS ORDERS ===");
        System.out.println(statusOrders);
        System.out.println("-------------");

        Map<Integer, Customer> customerMap = customers.stream()
                .collect(Collectors.toMap(Customer::getId, c -> c));

        Map<String, Double> totalSalesByCity = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> customerMap.get(order.getCustomerId()).getCity(),
                        Collectors.summingDouble(Order::getTotalValue)
                ));

        System.out.println("=== SALES BY CITIES ===");
        System.out.println(totalSalesByCity);
        System.out.println("-------------");

        Optional<Double> sumDeliveredValue = orders.stream()
                .filter(s -> s.getStatus().equals(DELIVERED))
                .map(Order::getTotalValue)
                .reduce(Double::sum);

        System.out.println("=== TOTAL VALUE DELIVERED ===");
        sumDeliveredValue.ifPresent(s -> System.out.println("$" + s));
        System.out.println("-------------");

        Optional<Order> isAbove1000 = orders.stream()
                .filter(s -> s.getTotalValue() > 1000)
                .findAny();

        boolean hasOneProduct = orders.stream()
                .noneMatch(s -> s.getProducts().isEmpty());

        Map<Integer, Customer> customerMap2 = customers.stream()
                .collect(Collectors.toMap(Customer::getId, c -> c));

        boolean hasEmptyName = orders.stream()
                .map(order -> customerMap2.get(order.getCustomerId()))
                .anyMatch(c -> c.getName() == null || c.getName().isBlank());

        System.out.println("=== MATCHING ===");
        isAbove1000.ifPresent(s -> System.out.println("Any order greater than 1000? " + s));
        System.out.println("All orders must contain at least 1 product? " + hasOneProduct);
        System.out.println("There are customers with blank names? " + hasEmptyName);
        System.out.println("-------------");

        DoubleSummaryStatistics stats = orders.stream()
                        .collect(Collectors.summarizingDouble(Order::getTotalValue));

        System.out.println("=== STATISTICS BY PRICE ===");
        System.out.println("Min: $" + stats.getMin());
        System.out.println("Max: $" + stats.getMax());
        System.out.println("Avg: $" + stats.getAverage());
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: $" + stats.getSum());
        System.out.println("-------------");

        Map<Integer, Customer> customerMap3 = customers.stream()
                .collect(Collectors.toMap(Customer::getId, c -> c));

        Map<Integer, String> idClient = orders.stream()
                .collect(Collectors.toMap(
                        o -> customerMap3.get(o.getCustomerId()).getId(),
                        c -> customerMap3.get(c.getCustomerId()).getName(),
                        (old, next) -> old
                ));

        System.out.println("=== MAP CUSTOMER ID -> CLIENT NAME ===");
        System.out.println(idClient);
        System.out.println("-------------");

        Map<Integer, Customer> customerMap4 = customers.stream()
                .collect(Collectors.toMap(Customer::getId, c -> c));

        Map<String, Map<OrderStatus, List<Order>>> multilevel = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> customerMap4.get(o.getCustomerId()).getCity(),
                        Collectors.groupingBy(Order::getStatus)
                ));

        System.out.println("=== MULTILEVEL CITY -> STATUS: ORDER ===");
        multilevel.forEach((city, status) -> {
            System.out.println(city.toUpperCase());
            status.forEach((stat, listOrders) -> {
                System.out.println(stat);
                listOrders.forEach(o -> System.out.println("- OrderId: " + o.getOrderId()));
            });
            System.out.println("-------------");
        });
    }
}