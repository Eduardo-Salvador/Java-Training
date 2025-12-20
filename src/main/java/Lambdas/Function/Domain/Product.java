package Lambdas.Function.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Product {
    private final String name;
    private final Double price;

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

    public Product(String name, Double price) {
            this.name = name;
            this.price = price;
    }

    public static List<Product> filterProducts(List<Product> listProduct, Predicate<Product> condition){
        List<Product> filterProducts = new ArrayList<>();
        for(Product p : listProduct){
            if(condition.test(p)){
                filterProducts.add(p);
            }
        }
        if (!filterProducts.isEmpty()){
            System.out.println("Success filter!");
            return filterProducts;
        }
        System.out.println("No elements in criteria.");
        return filterProducts;
    }

    public static <R> List<R> mapProducts(List<Product> list, Function<String, R> function){
        List<R> result = new ArrayList<>();
        for (Product e : list){
            result.add(function.apply(e.getName()));
        }
        return result;
    }
}