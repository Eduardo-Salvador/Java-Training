package Threads_ConcurrentThreads.ExecutorsThreadPool.NewSingleThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DatabaseWriter implements Callable<String>{
    private String register;

    public DatabaseWriter(String register){
        this.register = register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " - Writing " + register + "...");
        Thread.sleep(300);
        return register + " completed successfully!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        System.out.println("Single Thread Executor:");
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            futureList.add(service.submit(new DatabaseWriter("register-" + i)));
        }

        for (Future<String> f : futureList) {
            System.out.println(f.get());
        }

        service.shutdown();

        System.out.println("\nFixed Thread Pool (3Un):");
        ExecutorService service2 = Executors.newFixedThreadPool(3);
        System.out.println("Single Thread Executor:");
        List<Future<String>> futureList2 = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            futureList2.add(service2.submit(new DatabaseWriter("register-" + i)));
        }

        for (Future<String> f : futureList2) {
            System.out.println(f.get());
        }

        service2.shutdown();
    }
}