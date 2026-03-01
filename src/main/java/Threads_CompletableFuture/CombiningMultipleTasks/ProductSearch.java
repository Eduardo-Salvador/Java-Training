package Threads_CompletableFuture.CombiningMultipleTasks;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class ProductSearch {
    private static Integer generatePrice(String storeName) {
        try {
            int delay = ThreadLocalRandom.current().nextInt(200, 1001);
            Thread.sleep(delay);
            int price = ThreadLocalRandom.current().nextInt(50, 201);
            System.out.println(storeName + " return price: $" + price + " after " + delay + "ms");
            return price;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runAllOfExample() {
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> simulateTask("Task 1"));
        CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> simulateTask("Task 2"));
        CompletableFuture<Void> t3 = CompletableFuture.runAsync(() -> simulateTask("Task 3"));
        CompletableFuture<Void> t4 = CompletableFuture.runAsync(() -> simulateTask("Task 4"));
        CompletableFuture<Void> t5 = CompletableFuture.runAsync(() -> simulateTask("Task 5"));
        CompletableFuture<Void> all = CompletableFuture.allOf(t1, t2, t3, t4, t5);
        all.thenRun(() -> System.out.println("All 5 tasks have been completed.")).join();
    }

    private static void simulateTask(String name) {
        try {
            int delay = ThreadLocalRandom.current().nextInt(200, 1001);
            Thread.sleep(delay);
            System.out.println(name + " finished after " + delay + "ms");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> store1 = CompletableFuture.supplyAsync(() -> generatePrice("Store 1"));
        CompletableFuture<Integer> store2 = CompletableFuture.supplyAsync(() -> generatePrice("Store 2"));
        CompletableFuture<Integer> store3 = CompletableFuture.supplyAsync(() -> generatePrice("Store 3"));
        CompletableFuture<Integer> firstComparison = store1.thenCombine(store2, Integer::min);
        CompletableFuture<Integer> finalResult = firstComparison.thenCombine(store3, Integer::min);

        finalResult.thenAccept(minPrice -> System.out.println("Lowest price found: $" + minPrice));
        finalResult.join();

        ProductSearch.runAllOfExample();
    }
}