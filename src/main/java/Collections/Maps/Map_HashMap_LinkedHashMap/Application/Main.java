package Collections.Maps.Map_HashMap_LinkedHashMap.Application;
import Collections.Maps.Map_HashMap_LinkedHashMap.Domain.Product;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("A1001", "Wireless Mouse", 89.90);
        Product p2 = new Product("B2002", "Mechanical Keyboard", 249.50);
        Product p3 = new Product("C3003", "HD Monitor 24\"", 799.00);
        Product p4 = new Product("D4004", "USB-C Docking Station", 459.99);
        Product p5 = new Product("E5005", "Noise Cancelling Headphones", 1299.00);

        Map<String, Product> productMap = new HashMap<>();
        productMap.putIfAbsent(p1.getCode(), p1);
        productMap.putIfAbsent(p2.getCode(), p2);
        productMap.putIfAbsent(p3.getCode(), p3);
        productMap.putIfAbsent(p4.getCode(), p4);
        productMap.putIfAbsent(p5.getCode(), p5);
        productMap.putIfAbsent(p1.getCode(), p3);
        for (String key : productMap.keySet()){
            System.out.println(key + ": " + productMap.get(key));
        }
        System.out.println("------------------------");

        System.out.println("Updated P1: " + p1.getName());
        p1.setPrice(129.90);
        for (String key : productMap.keySet()){
            System.out.println(key + ": " + productMap.get(key));
        }
        System.out.println("------------------------");

        productMap.remove(p1.getCode());
        for (String key : productMap.keySet()){
            System.out.println(key + ": " + productMap.get(key));
        }
        System.out.println("------------------------");

        Map<String, Product> productMapSorted = new LinkedHashMap<>();
        productMapSorted.putIfAbsent(p1.getCode(), p1);
        productMapSorted.putIfAbsent(p2.getCode(), p2);
        productMapSorted.putIfAbsent(p3.getCode(), p3);
        productMapSorted.putIfAbsent(p4.getCode(), p4);
        productMapSorted.putIfAbsent(p5.getCode(), p5);
        productMapSorted.putIfAbsent(p1.getCode(), p3);
        System.out.println("Products in list sorted by insertion: " + productMapSorted.size());
        productMapSorted.forEach((key, value) -> System.out.println(key + ": $" + value));
        System.out.println("------------------------");
    }
}
