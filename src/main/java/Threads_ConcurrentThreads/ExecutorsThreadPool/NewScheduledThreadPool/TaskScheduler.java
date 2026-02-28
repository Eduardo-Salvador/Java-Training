package Threads_ConcurrentThreads.ExecutorsThreadPool.NewScheduledThreadPool;
import java.time.LocalTime;
import java.util.concurrent.*;

public class TaskScheduler{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        Runnable scheduler1 = () -> {
            System.out.println("Task ONE executed by "
                    + Thread.currentThread().getName()
                    + " at " + LocalTime.now() + "\n");
        };

        Runnable scheduler2 = () -> {
            System.out.println("Task TWO executed by "
                    + Thread.currentThread().getName()
                    + " at " + LocalTime.now());
            System.out.println("Working...\n");
        };

        Runnable scheduler3 = () -> {
            System.out.println("Task THREE executed by "
                    + Thread.currentThread().getName()
                    + " at " + LocalTime.now());
            System.out.println("Working...\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };

        service.execute(scheduler1);

        ScheduledFuture<?> result2 =
                service.scheduleAtFixedRate(scheduler2, 0, 1, TimeUnit.SECONDS);

        ScheduledFuture<?> result3 =
                service.scheduleWithFixedDelay(scheduler3, 0, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(5);
        result2.cancel(true);

        TimeUnit.SECONDS.sleep(25);
        result3.cancel(true);

        System.out.println("Finished tasks...");
        service.shutdown();
    }
}