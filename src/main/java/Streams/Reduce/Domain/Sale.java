package Streams.Reduce.Domain;

public class Sale {
    private String product;
    private Integer quantity;
    private Double unitPrice;

    public Sale(String product, Integer quantity, Double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getTotalValue(){
        return this.quantity * this.unitPrice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return String.format("%s (Qty: %d, Unit: $%.2f, Total: $%.2f)",
                product, quantity, unitPrice, getTotalValue());
    }
}