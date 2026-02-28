package Threads_ConcurrentThreads.ExecutorsThreadPool.NewWorkStrealingPool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DataAnalyzer implements Callable<String> {
    private String chunkName;

    public DataAnalyzer(String chunkName){
        this.chunkName = chunkName;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " is working on " + chunkName + "...");
        long start = System.currentTimeMillis();
        int sleepTime = ThreadLocalRandom.current().nextInt(200, 1001);
        Thread.sleep(sleepTime);
        long end = System.currentTimeMillis();
        return chunkName + " analyzed by " + Thread.currentThread().getName()+ " in " + (end - start) + "ms";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newWorkStealingPool();
        List<Future<String>> futureList = new ArrayList<>();

        System.out.println("Work Stealing Pool using: " + Runtime.getRuntime().availableProcessors() + " processors");

        long start = System.currentTimeMillis();
        for (int i = 1; i <= 10 ; i++) {
            futureList.add(service.submit(new DataAnalyzer("chunk-" + i)));
        }

        for (Future<String> f : futureList){
            System.out.println(f.get());
        }
        long end = System.currentTimeMillis();


        service.shutdown();

        ExecutorService service2 = Executors.newSingleThreadExecutor();
        List<Future<String>> futureList2 = new ArrayList<>();

        System.out.println("Single Thread Executor:");

        long start2 = System.currentTimeMillis();
        for (int i = 1; i <= 10 ; i++) {
            futureList2.add(service2.submit(new DataAnalyzer("chunk-" + i)));
        }

        for (Future<String> f : futureList2){
            System.out.println(f.get());
        }
        long end2 = System.currentTimeMillis();
        service2.shutdown();

        System.out.println("Work Stealing Pool result: " + (end - start) + "ms");
        System.out.println("Single Thread Executor result: " + (end2 - start2) + "ms");
    }
}