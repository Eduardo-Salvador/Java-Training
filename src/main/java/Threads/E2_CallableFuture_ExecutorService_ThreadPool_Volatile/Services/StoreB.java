package Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Services;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Domain.Product;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class StoreB implements Callable<Product> {
    @Override
    public Product call() throws Exception {
        if (!Main.running) return null;
        System.out.println(Thread.currentThread().getName() + " Importing List");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
        List<Product> products = new ArrayList<>();
        products.add(new Product("RGB Mechanical Keyboard", 319.90));
        products.add(new Product("12000 DPI Gaming Mouse", 210.00));
        products.add(new Product("7.1 Surround Headset", 279.90));
        products.add(new Product("24' 144Hz Monitor", 1299.90));
        products.add(new Product("Gaming Chair", 1899.90));
        products.sort(Comparator.comparing(Product::getPrice));
        System.out.println(Thread.currentThread().getName() + " Searching the cheapest product");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
        System.out.println(Thread.currentThread().getName() + " Complete the cheapest product");
        return products.getFirst();
    }
}