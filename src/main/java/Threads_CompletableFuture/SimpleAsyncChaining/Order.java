package Threads_CompletableFuture.SimpleAsyncChaining;

import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private final String orderID;
    private final String orderName;
    private final Double price;
    private static final AtomicInteger id = new AtomicInteger(1);

    public Order(String orderName, Double price) {
        this.orderID = "Order#" + id.getAndIncrement();
        this.orderName = orderName;
        this.price = price;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderName() {
        return orderName;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "OrderProcessor{" +
                "orderID='" + orderID + '\'' +
                ", orderName='" + orderName + '\'' +
                ", price=" + price +
                '}';
    }
}
