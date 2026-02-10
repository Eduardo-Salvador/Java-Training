package Streams.MultilevelGrouping.Domain;

public class Sale {
    private String region;
    private String product;
    private Integer quantity;
    private Double value;

    public Sale(String region, String product, Integer quantity, Double value) {
        this.region = region;
        this.product = product;
        this.quantity = quantity;
        this.value = value;
    }

    public String getRegion() {
        return region;
    }

    public String getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return region + " - " + product + " - qty: " + quantity + " - $" + value;
    }
}