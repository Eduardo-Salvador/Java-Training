package Collections.Lists.Services;
import Collections.Lists.Domains.Product;
import java.util.Comparator;
import java.util.List;

public class InventoryManager {
    public static void addProduct(List<Product> products, Product product) {
        if (products.contains(product)) {
            System.out.println("This product is already in stock.");
            return;
        }
        products.add(product);
        System.out.printf("Product added: %s - $%.2f%n", product.getName(), product.getPrice());
    }

    public static void removeProduct(List<Product> products, String name) {
        if(products.removeIf(p -> p.getName().equalsIgnoreCase(name))){
            System.out.println("Product removed: " + name);
            return;
        }
        System.out.println("This product is not in stock.");
    }

    public static void updatePrice(List<Product> products, String name, Double newPrice) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setPrice(newPrice);
                System.out.printf("Price updated for %s to $%.2f%n", product.getName(), newPrice);
                return;
            }
        }
        System.out.println("This product is not in stock.");
    }

    public static void sortByPrice(List<Product> products) {
        Comparator<Product> orderByPrice = Comparator.comparing(Product::getPrice);
        products.sort(orderByPrice);
        System.out.println("Products in stock (sorted by price):");
        products.forEach(System.out::println);
    }

    public static void showAllProducts(List<Product> products) {
        int index = 1;
        System.out.println("Products in stock:");
        for (Product product : products){
            System.out.println(index++ + " - " + product);
        }
    }
}