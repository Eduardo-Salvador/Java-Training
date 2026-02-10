package Streams.BasicStream.Application;
import Streams.BasicStream.Domain.Product;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Samsung Galaxy Smartphone", 899.99, "Electronics"));
        products.add(new Product("Dell Inspiron Laptop", 1299.00, "Electronics"));
        products.add(new Product("Bluetooth Headphones", 149.90, "Electronics"));
        products.add(new Product("Clean Code - Robert Martin", 45.90, "Books"));
        products.add(new Product("The Hobbit - J.R.R. Tolkien", 25.50, "Books"));
        products.add(new Product("1984 - George Orwell", 18.00, "Books"));
        products.add(new Product("Basic T-Shirt", 29.90, "Clothing"));
        products.add(new Product("Jeans Pants", 189.90, "Clothing"));
        products.add(new Product("Sports Sneakers", 149.00, "Clothing"));

        List<String> filterByPrice =
                products.stream()
                .filter(product -> product.getPrice() > 100)
                        .map(Product::getName)
                        .toList();

        System.out.println(filterByPrice);
    }
}