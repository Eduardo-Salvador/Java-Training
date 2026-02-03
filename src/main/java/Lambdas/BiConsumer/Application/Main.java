package Lambdas.BiConsumer.Application;
import Lambdas.BiConsumer.Domain.Order;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Order o1 = new Order(3204D);
        Order o2 = new Order(4783.32);
        Order o3 = new Order(321D);
        Order o4 = new Order(3543.59);
        Order o5 = new Order(43270.43);
        List<Order> orderList = List.of(o1, o2, o3, o4, o5);

        Order.action(orderList, 0.1, (o, d) -> {
            o.setDiscount(d);
            o.setTotalAmount(o.getTotalAmount() - (o.getTotalAmount() * d));
        });
    }
}