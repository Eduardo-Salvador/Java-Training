package Collections.Lists.Domains;

public class Product {
    private String name;
    private Double price;
    private Integer quantity;

    public Product(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getName() != null && getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return getName() == null ? 0 : this.getName().hashCode();
    }

    @Override
    public String toString() {
        return getName() + " - $" + getPrice() + " - Quantity: " + getQuantity() + " - Code: " + hashCode();
    }
}