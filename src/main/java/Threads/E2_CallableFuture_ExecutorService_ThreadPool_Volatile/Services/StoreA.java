package Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Services;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Domain.Product;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class StoreA implements Callable<Product> {
    @Override
    public Product call() throws Exception {
        if (!Main.running) return null;
        System.out.println(Thread.currentThread().getName() + " Importing List");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
        List<Product> products = new ArrayList<>();
        products.add(new Product("RGB Mechanical Keyboard", 349.90));
        products.add(new Product("Mouse Gamer 12000 DPI", 189.90));
        products.add(new Product("Headset 7.1 Surround", 299.90));
        products.add(new Product("Mousepad XL", 89.90));
        products.add(new Product("Full HD Webcam", 259.90));
        products.sort(Comparator.comparing(Product::getPrice));
        System.out.println(Thread.currentThread().getName() + " Searching the cheapest product");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
        System.out.println(Thread.currentThread().getName() + " Complete the cheapest product");
        return products.getFirst();
    }
}