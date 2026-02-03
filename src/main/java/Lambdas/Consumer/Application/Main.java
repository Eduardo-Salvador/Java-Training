package Lambdas.Consumer.Application;
import Lambdas.Consumer.Domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("Dell XPS 13 Laptop", 8500D);
        Product p2 = new Product("LG UltraWide 34\" Monitor", 3200D);
        Product p3 = new Product("HP ProLiant DL380 Server", 18000D);
        Product p4 = new Product("Microsoft Office 365 License", 1200D);
        Product p5 = new Product("Cisco Wi-Fi 6 Router", 2500D);
        List<Product> productList = new ArrayList<>(List.of(p1, p2, p3, p4, p5));
        System.out.println("--------------------------------------------");

        Consumer<Product> normal = System.out::println;
        Product.processProducts(productList, normal);
        System.out.println("--------------------------------------------");

        Consumer<Product> discount = p -> System.out.println("10% OFF: " + p.getName() + " - $" + (p.getPrice() * 0.9));
        Product.processProducts(productList, discount);
        System.out.println("--------------------------------------------");

        Consumer<Product> upperName = p -> System.out.println(p.getName().toUpperCase() + " - $" + p.getPrice());
        Product.processProducts(productList, upperName);
        System.out.println("--------------------------------------------");

        List<Consumer<Product>> consumerList = new ArrayList<>(List.of(normal, discount, upperName));
        for (Consumer<Product> p : consumerList){
            Product.processProducts(productList, p);
            System.out.println("--------------------------------------------");
        }

        Consumer<Product> duoConsumer = upperName.andThen(discount);
        Product.processProducts(productList, duoConsumer);
        System.out.println("--------------------------------------------");
    }
}