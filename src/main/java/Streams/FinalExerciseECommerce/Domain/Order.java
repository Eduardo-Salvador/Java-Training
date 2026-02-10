package Streams.FinalExerciseECommerce.Domain;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private List<String> products;
    private Double totalValue;
    private OrderStatus status;

    public Order(Integer orderId, Integer customerId, List<String> products, Double totalValue, OrderStatus status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = products;
        this.totalValue = totalValue;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public List<String> getProducts() {
        return products;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", products=" + products +
                ", totalValue=" + totalValue +
                ", status=" + status +
                '}';
    }
}