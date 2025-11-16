package Streams.Reduce.Application;
import Streams.Reduce.Domain.Item;
import Streams.Reduce.Domain.Order;
import Streams.Reduce.Domain.Product;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 5500.0);
        Product p2 = new Product("Mouse", 150.0);
        Product p3 = new Product("Keyboard", 320.0);
        Product p4 = new Product("Monitor", 1200.0);
        Product p5 = new Product("Headset", 500.0);

        Item i1 = new Item(p1, 1);
        Item i2 = new Item(p2, 2);
        Item i3 = new Item(p3, 1);
        Item i4 = new Item(p4, 2);
        Item i5 = new Item(p5, 3);
        Item i6 = new Item(p2, 5);

        Order o1 = new Order(1, new ArrayList<>(List.of(i1, i2)));
        Order o2 = new Order(2, new ArrayList<>(List.of(i3, i5)));
        Order o3 = new Order(3, new ArrayList<>(List.of(i4)));
        Order o4 = new Order(4, new ArrayList<>(List.of(i6, i1, i5)));

        List<Order> orders = new ArrayList<>(List.of(o1, o2, o3, o4));

        Optional<Double> totalValues = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .map(v -> v.getProduct().getPrice() * v.getQuantity())
                .reduce(Double::sum);

        System.out.println("Total value in orders is: $" + totalValues.get());
        System.out.println("--------------------------------------");

        Optional<Double> maxValue = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .map(v -> v.getProduct().getPrice() * v.getQuantity())
                .reduce(Double::max);

        System.out.println("The biggest request is: $" + maxValue.get());
        System.out.println("--------------------------------------");

        String moreOrder = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getName(),
                        Collectors.reducing(0, Item::getQuantity, Integer::sum)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nothing");
        System.out.println(moreOrder);
    }
}