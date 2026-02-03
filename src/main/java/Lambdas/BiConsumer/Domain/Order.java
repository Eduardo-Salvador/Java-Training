package Lambdas.BiConsumer.Domain;
import java.util.List;
import java.util.function.BiConsumer;

public class Order {
    private String orderId;
    private Double totalAmount;
    private Double discount;

    public Order(Double totalAmount) {
        this.orderId = String.format("%.0f", Math.random() * 100);
        this.totalAmount = totalAmount;
        this.discount = 0D;
    }

    public String getOrderId() {
        return orderId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public static void action(List<Order> orderList, Double percentDiscount , BiConsumer<Order, Double> consumer){
        for (Order o : orderList){
            System.out.printf("Original price: $%.2f%n", o.getTotalAmount());
            consumer.accept(o, percentDiscount);
            System.out.println("Discount: " + (o.getDiscount() * 100) + "%");
            System.out.printf("ID: " + o.getOrderId() + " - $%.2f%n", o.getTotalAmount());
            o.setTotalAmount(o.getTotalAmount() * 1.02);
            System.out.println("Applying Transfer tax: 2%");
            System.out.printf("$%.2f%n", o.getTotalAmount());
            System.out.println("---------");
        }
    }
}