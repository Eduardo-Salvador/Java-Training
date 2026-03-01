package Threads_CompletableFuture.ThreadFactoryAndPool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ReportService {
    private static int reportCounter = 0;

    private static String generateReport(){
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(300, 801));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        int id = ++reportCounter;
        return "Report-"
                + id
                + " by: "
                + Thread.currentThread().getName()
                + "\nHave a good day";
    };

    public static CompletableFuture<Void> runReport(ExecutorService pool){
        return CompletableFuture.supplyAsync(ReportService::generateReport, pool)
                .thenAccept(System.out::println);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ThreadFactory factory = new ThreadFactory() {
            private int count = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("report-worker-" + count++);
                t.setDaemon(true);
                return t;
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(3, factory);
        List<CompletableFuture<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            tasks.add(runReport(pool));
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
        all.join();
        long endTime = System.currentTimeMillis();

        System.out.println("\nTotal execution time: " + (endTime - startTime) + " ms");
        pool.shutdown();
    }
}