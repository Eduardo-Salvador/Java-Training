package Lambdas.BiPredicate.Domain;
import java.util.List;
import java.util.function.BiPredicate;

public class Product {
    private final String name;
    private final Double price;
    private final Integer stock;

    public Product(String name, Double price, Integer stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public static <T> void  compareLimitProducts(List<Product> products, T threshold, BiPredicate<Product, T> condition){
        for (Product p : products){
            if(condition.test(p, threshold)){
                System.out.println(p.getName() + " - $" + p.getPrice() + " - " + p.getStock() + " Un");
            }
        }
    }

    public static <T> void compareProducts(Product compared, Product comparator, BiPredicate<Product, Product> condition){
        if (condition.test(compared, comparator)){
            System.out.println("True");
            return;
        }
        System.out.println("False");
    }
}