package Lambdas.BiPredicate.Application;
import Lambdas.BiPredicate.Domain.Product;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("Dell XPS 13 Laptop", 8500D, 12);
        Product p2 = new Product("LG UltraWide 34\" Monitor", 3200D,73);
        Product p3 = new Product("HP ProLiant DL380 Server", 18000D,3);
        Product p4 = new Product("Microsoft Office 365 License", 1200D,25);
        Product p5 = new Product("Cisco Wi-Fi 6 Router", 2500D, 54);

        List<Product> productList = List.of(p1, p2, p3, p4, p5);

        System.out.println("Price lower than limit: $4000.00");
        Product.compareLimitProducts(productList, 4000D, (p, limit) -> p.getPrice() < limit);
        System.out.println("------------------------------");

        System.out.println("Products with stock exceeding the limit: 30Un");
        Product.compareLimitProducts(productList, 30, (p, limit) -> p.getStock() > limit);
        System.out.println("------------------------------");

        System.out.println("Products priced at the limit: $8500.00");
        Product.compareLimitProducts(productList, 8500D, (p, limit) -> p.getPrice().equals(limit));
        System.out.println("------------------------------");

        System.out.println("Higher price?\n" + p1.getName() + " > " + p2.getName());
        Product.compareProducts(p1, p2, (x, y) -> x.getPrice() > y.getPrice());
        System.out.println("------------------------------");

        System.out.println("Higher price?\n" + p1.getName() + " > " + p4.getName());
        Product.compareProducts(p1, p4, (x, y) -> x.getPrice() > y.getPrice());
        System.out.println("------------------------------");

        System.out.println("Higher price?\n" + p5.getName() + " > " + p3.getName());
        Product.compareProducts(p5, p3, (x, y) -> x.getPrice() > y.getPrice());
        System.out.println("------------------------------");

        System.out.println("Equal stock?\n" + p1.getName() + " == " + p2.getName());
        Product.compareProducts(p1, p2, (x, y) -> x.getStock().equals(y.getStock()));
        System.out.println("------------------------------");

        System.out.println("Equal stock?\n" + p1.getName() + " == " + p4.getName());
        Product.compareProducts(p1, p4, (x, y) -> x.getStock().equals(y.getStock()));
        System.out.println("------------------------------");

        System.out.println("Equal stock?\n" + p3.getName() + " == " + p5.getName());
        Product.compareProducts(p3, p5, (x, y) -> x.getStock().equals(y.getStock()));
    }
}