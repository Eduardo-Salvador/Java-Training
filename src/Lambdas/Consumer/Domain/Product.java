package Lambdas.Consumer.Domain;

import java.util.List;
import java.util.function.Consumer;

public class Product {
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }

    public static void processProducts(List<Product> productList, Consumer<Product> action){
        for(Product p: productList){
            action.accept(p);
        }
    }
}
