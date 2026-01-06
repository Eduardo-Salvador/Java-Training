package Collections.Lists.Application;
import Collections.Lists.Services.InventoryManager;
import Collections.Lists.Domains.Product;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        Product mouse = new Product("Gaming Mouse", 120.0, 15);
        InventoryManager.addProduct(products, mouse);
        System.out.println("-------------------");

        InventoryManager.showAllProducts(products);
        System.out.println("-------------------");

        InventoryManager.updatePrice(products, "Mouse", 150D);
        InventoryManager.showAllProducts(products);
        System.out.println("-------------------");

        Product keyboard = new Product("Mechanical Keyboard", 350.0, 10);
        InventoryManager.addProduct(products, keyboard);

        Product monitor = new Product("27-inch Monitor", 1450.0, 7);
        InventoryManager.addProduct(products, monitor);

        Product laptop = new Product("Dell Laptop", 4800.0, 4);
        InventoryManager.addProduct(products, laptop);

        Product headset = new Product("Wireless Headset", 220.0, 12);
        InventoryManager.addProduct(products, headset);
        System.out.println("-------------------");

        InventoryManager.showAllProducts(products);
        System.out.println("-------------------");

        InventoryManager.removeProduct(products, "Headset");
        System.out.println("-------------------");

        InventoryManager.removeProduct(products, "Wireless Headset");
        System.out.println("-------------------");

        InventoryManager.removeProduct(products, "Dell Laptop");
        System.out.println("-------------------");

        InventoryManager.sortByPrice(products);
    }
}
