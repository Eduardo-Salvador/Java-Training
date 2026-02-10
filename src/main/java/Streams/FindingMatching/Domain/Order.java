package Streams.FindingMatching.Domain;

public class Order {
    private Type status;
    private Integer id;
    private String customerName;
    private Double totalValue;

    public Order(Integer id, String customerName, Double totalValue, Type status){
        this.id = id;
        this.customerName = customerName;
        this.totalValue = totalValue;
        this.status = status;
    }

    public Type getStatus() {
        return status;
    }

    public void setStatus(Type status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "Order{" +
                "status=" + status +
                ", id=" + id +
                ", customerName='" + customerName + '\'' +
                ", totalValue=" + totalValue +
                '}';
    }
}