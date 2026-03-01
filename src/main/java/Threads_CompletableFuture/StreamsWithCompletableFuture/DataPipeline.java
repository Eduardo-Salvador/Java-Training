package Threads_CompletableFuture.StreamsWithCompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class DataPipeline {
    private static CompletableFuture<Integer> getIntegerList(){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " is searching...");
            List<Integer> integerList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                integerList.add(ThreadLocalRandom.current().nextInt(-50, 101));
            }
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " return this list: " + integerList);
            System.out.println(Thread.currentThread().getName() + " is filtering and sum...");
            return integerList;
        }).thenApply(list -> list.stream()
                .filter(x -> x > 0)
                .map(x -> x * 3)
                .collect(Collectors.toList())
        ).thenApply(list -> list.stream()
                .reduce(0, Integer::sum)
        ).exceptionally(e -> {
            System.out.println("Error: " + e.getMessage());
            return 0;
        });
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CompletableFuture<Integer> c1 = DataPipeline.getIntegerList();
        CompletableFuture<Integer> c2 = DataPipeline.getIntegerList();
        CompletableFuture<Integer> result = c1.thenCombine(c2, Integer::sum);
        System.out.println("List 1 result: " + c1.join());
        System.out.println("List 2 result: " + c2.join());
        System.out.println("Sum parallel list result: " + result.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
    }
}