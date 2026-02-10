package Streams.FindingMatching.Application;
import Streams.FindingMatching.Domain.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static Streams.FindingMatching.Domain.Type.*;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(1, "John Anderson", 250.50, PENDING));
        orders.add(new Order(2, "Sarah Mitchell", 180.00, SHIPPED));
        orders.add(new Order(3, "Michael Brown", 520.75, DELIVERED));
        orders.add(new Order(4, "Emily Davis", 95.30, PENDING));
        orders.add(new Order(5, "Robert Wilson", 340.00, SHIPPED));
        orders.add(new Order(6, "Jessica Moore", 450.25, DELIVERED));
        orders.add(new Order(7, "David Taylor", 125.90, PENDING));
        orders.add(new Order(8, "Linda Martinez", 680.50, SHIPPED));
        orders.add(new Order(9, "James Johnson", 210.00, PENDING));
        orders.add(new Order(10, "Patricia Garcia", 390.75, DELIVERED));
        orders.add(new Order(11, "Christopher Lee", 155.40, SHIPPED));
        orders.add(new Order(12, "Maria Rodriguez", 540.00, DELIVERED));

        Optional<Order> firstPending = orders.stream()
                .filter(s -> s.getStatus() == PENDING)
                .findFirst();

        firstPending.ifPresent(System.out::println);
    }
}