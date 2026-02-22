package Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Services;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Domain.Product;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class StoreC implements Callable<Product> {
    @Override
    public Product call() throws Exception {
        if (!Main.running) return null;
        System.out.println(Thread.currentThread().getName() + " Importing List");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
        List<Product> products = new ArrayList<>();
        products.add(new Product("Gaming Mouse 12000 DPI", 175.00));
        products.add(new Product("7.1 Surround Headset", 310.00));
        products.add(new Product("24' 144Hz Monitor", 1199.90));
        products.add(new Product("Gaming Chair", 1799.90));
        products.add(new Product("XL Mousepad", 75.90));
        products.sort(Comparator.comparing(Product::getPrice));
        System.out.println(Thread.currentThread().getName() + " Searching the cheapest product");
        Thread.sleep((long) (Math.random() * 2000 + 1000));
        System.out.println(Thread.currentThread().getName() + " Complete the cheapest product");
        return products.getFirst();
    }
}