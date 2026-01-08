package Collections.Maps.Map_HashMap_LinkedHashMap.Domain;

public class Product {
    private String code;
    private String name;
    private Double price;

    public Product(String code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + " - $" + getPrice();
    }
}
