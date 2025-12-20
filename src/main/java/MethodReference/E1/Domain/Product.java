package MethodReference.E1.Domain;

public class Product{
    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }

    public static int compareByPrice(Product p1, Product p2){
        return p1.getPrice().compareTo(p2.getPrice());
    }
}