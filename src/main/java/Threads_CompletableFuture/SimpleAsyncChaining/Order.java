package Threads_CompletableFuture.SimpleAsyncChaining;

public class Order {
    private final String orderID;
    private final String orderName;
    private final Double price;
    private static int id = 1;

    public Order(String orderName, Double price) {
        this.orderID = "Order#" + id;
        this.orderName = orderName;
        this.price = price;
        id++;
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
