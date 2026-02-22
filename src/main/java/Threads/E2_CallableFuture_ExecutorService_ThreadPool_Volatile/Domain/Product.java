package Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Domain;

public class Product {
    private final String name;
    private final Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " — R$ " + price;
    }
}