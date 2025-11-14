package MethodReference.E1.Application;
import MethodReference.E1.Domain.Product;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>(List.of(new Product("A", 400D),
                new Product("C", 500D),
                new Product("B", 600D)));

        productList.sort(Comparator.comparing(Product::getName, String::compareToIgnoreCase));
        productList.forEach(System.out::println);
        System.out.println("-----------------------------------------");
        productList.sort(Product::compareByPrice);
        productList.forEach(System.out::println);
    }
}