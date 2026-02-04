package MethodReference.E1.Domain;

public class Product {
    private final String NAME;
    private final Double PRICE;

    public Product(String NAME, Double PRICE) {
        this.NAME = NAME;
        this.PRICE = PRICE;
    }

    public String getName() {
        return NAME;
    }

    public Double getPrice() {
        return PRICE;
    }

    @Override
    public String toString() {
        return NAME + " - $" + PRICE;
    }

    public static int compareByPrice(Product p1, Product p2){
        return p1.getPrice().compareTo(p2.getPrice());
    }
}