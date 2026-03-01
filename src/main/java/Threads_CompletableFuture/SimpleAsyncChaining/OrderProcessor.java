package Threads_CompletableFuture.SimpleAsyncChaining;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OrderProcessor {
    private static CompletableFuture<Void> searchAsyncOrderByID(int id, List<Order> orderList) {
        String searchID = "Order#" + id;

        return CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("supplyAsync running on: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for (Order o : orderList) {
                        if (o.getOrderID().equals(searchID)) {
                            return o;
                        }
                    }
                    return null;
                })
                .thenApply(order -> {
                    System.out.println("thenApply running on: " + Thread.currentThread().getName());
                    if (order == null) {
                        return "Order not found";
                    }
                    double discountedPrice = order.getPrice() * 0.9;
                    return String.format("%s - %s - $%.2f (10%% discount applied)",
                            order.getOrderID(),
                            order.getOrderName(),
                            discountedPrice
                    );
                })
                .thenAccept(result -> {
                    System.out.println("thenAccept running on: " + Thread.currentThread().getName());
                    System.out.println(result);
                });
    }

    public static void main(String[] args) {
        System.out.println("Main thread: " + Thread.currentThread().getName());
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Laptop", 999.00));
        orders.add(new Order("Gaming Mouse", 250.00));
        orders.add(new Order("Mechanical Keyboard", 600.00));
        orders.add(new Order("27-inch Monitor", 1800.00));
        orders.add(new Order("Headset", 350.00));
        orders.add(new Order("Gaming Chair", 2200.00));
        orders.add(new Order("Webcam", 400.00));
        orders.add(new Order("1TB SSD", 750.00));
        orders.add(new Order("Graphics Card", 3200.00));
        orders.add(new Order("Processor", 2100.00));

        CompletableFuture<Void> f1 = searchAsyncOrderByID(3, orders);
        CompletableFuture<Void> f2 = searchAsyncOrderByID(7, orders);
        CompletableFuture<Void> f3 = searchAsyncOrderByID(1, orders);

        CompletableFuture.allOf(f1, f2, f3).join();
        System.out.println("All orders processed!");
    }
}