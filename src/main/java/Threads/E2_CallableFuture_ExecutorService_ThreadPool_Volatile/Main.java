package Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Domain.*;
import Threads.E2_CallableFuture_ExecutorService_ThreadPool_Volatile.Services.*;
import java.util.concurrent.*;

public class Main {
    public static volatile boolean running = true;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future<Product> searchA = pool.submit(new StoreA());
        Future<Product> searchB = pool.submit(new StoreB());
        Future<Product> searchC = pool.submit(new StoreC());
        try {
            double a = searchA.get(5, TimeUnit.SECONDS).getPrice();
            double b = searchB.get(5, TimeUnit.SECONDS).getPrice();
            double c = searchC.get(5, TimeUnit.SECONDS).getPrice();
            double lowest = Math.min(a, Math.min(b, c));
            final double EPS = 0.0001;

            int count = 0;
            if (Math.abs(a - lowest) < EPS) count++;
            if (Math.abs(b - lowest) < EPS) count++;
            if (Math.abs(c - lowest) < EPS) count++;

            if (count > 1) {
                System.out.println("There are " + count + " lists with the same lowest price: " + lowest);
            } else {
                System.out.println(
                        Math.abs(a - lowest) < EPS ? "The lowest price is on list A: " + searchA.get() :
                                Math.abs(b - lowest) < EPS ? "The lowest price is on list B: " + searchB.get() :
                                        "The lowest price is on list C: " + searchC.get()
                );
            }

        } catch (TimeoutException e) {
            if (!searchA.isDone()) System.out.println("StoreA timed out");
            if (!searchB.isDone()) System.out.println("StoreB timed out");
            if (!searchC.isDone()) System.out.println("StoreC timed out");
            running = false;
        }
    }
}