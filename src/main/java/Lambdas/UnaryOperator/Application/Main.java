package Lambdas.UnaryOperator.Application;
import Lambdas.UnaryOperator.Domain.Product;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 2500.00),
                new Product("Mouse", 45.50),
                new Product("Keyboard", 150.75),
                new Product("Monitor", 899.99),
                new Product("Webcam", 220.30)
        );

        System.out.println("+ 10% Tax:");
        Product.modifyPrice(products, p -> {
            p.setPrice(p.getPrice() * 1.1);
            return p;
        });
        System.out.println("---------------");

        System.out.println("+ $5 Tax:");
        Product.modifyPrice(products, p -> {
            p.setPrice(p.getPrice() + 5.0);
            return p;
        });
        System.out.println("---------------");

        System.out.println("Integer value:");
        Product.modifyPrice(products, p -> {
            p.setPrice((double) Math.round(p.getPrice()));
            return p;
        });
        System.out.println("---------------");

        UnaryOperator<Product> tax10 = (p -> {
            p.setPrice(p.getPrice() * 1.1);
            return p;
        });

        UnaryOperator<Product> integer = (p -> {
            p.setPrice((double) Math.round(p.getPrice()));
            return p;
        });

        Product p6 = new Product("Dell Laptop", 3423.22);
        p6 = tax10.andThen(integer).apply(p6);
        System.out.println("Result: $" + p6.getPrice());

        Product p7 = new Product("Dell Laptop", 3423.22);
        p7 = tax10.compose(integer).apply(p7);
        System.out.println("Result: $" + p7.getPrice());

    }
}