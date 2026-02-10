package Streams.ParallelStreams.Application;
import Streams.ParallelStreams.Domain.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 100; i++) {
            int time = random.nextInt(41) + 10;
            tasks.add(new Task(i, time));
        }

        long startSequential = System.currentTimeMillis();
        tasks.stream()
                .forEach(Task::execute);
        long endSequential = System.currentTimeMillis();
        long sequentialTime = endSequential - startSequential;

        long startParallel = System.currentTimeMillis();
        tasks.parallelStream()
                .forEach(Task::execute);
        long endParallel = System.currentTimeMillis();
        long parallelTime = endParallel - startParallel;

        System.out.println("Sequential time: " + sequentialTime + " ms");
        System.out.println("Parallel time: " + parallelTime + " ms");
        double speedup = (double) sequentialTime / parallelTime;
        System.out.println("Speedup: " + speedup);
    }
}