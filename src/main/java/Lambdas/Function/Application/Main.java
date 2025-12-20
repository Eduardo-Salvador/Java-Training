package Lambdas.Function.Application;

import Lambdas.Function.Domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("Dell XPS 13 Laptop", 30D);
        Product p2 = new Product("LG UltraWide 34\" Monitor", 31D);
        Product p3 = new Product("HP ProLiant DL380 Server", 180D);
        Product p4 = new Product("Microsoft Office 365 License", 12D);
        Product p5 = new Product("Cisco Wi-Fi 6 Router", 250D);

        List<Product> productList = new ArrayList<>(List.of(p1, p2, p3, p4, p5));

        List<Product> filter50List = Product.filterProducts(productList, p -> p.getPrice() > 50);
        System.out.println("Price: > $50:");
        filter50List.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        List<Product> filter50List2 = Product.filterProducts(productList, p -> p.getPrice() < 50);
        System.out.println("Price: < $50:");
        filter50List2.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        List<String> changedType = Product.mapProducts(productList, String::toUpperCase);
        changedType.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        System.out.println("Price: > $50:");
        List<String> changedTypeAndFilter = Product.mapProducts(filter50List, String::toUpperCase);
        changedTypeAndFilter.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

    }
}
