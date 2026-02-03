package Lambdas.UnaryOperator.Domain;
import java.util.List;
import java.util.function.UnaryOperator;

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

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static void modifyPrice(List<Product> products, UnaryOperator<Product> operator){
        for (Product p : products){
            System.out.printf("Previous price: $%.2f%n", p.getPrice());
            operator.apply(p);
            System.out.printf("New price: $%.2f%n", p.getPrice());
            System.out.println("-");
        }
    }
}