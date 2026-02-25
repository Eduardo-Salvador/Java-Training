package Threads_ConcurrencyThreads.ReentrantReadWriteLock;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConfigCache configCache = new ConfigCache(new ReentrantReadWriteLock());
        AtomicInteger counter = new AtomicInteger(1);

        Runnable read = () -> {
            int i = 1;
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("Read Thread " + Thread.currentThread().getName() + " he picked up: " + configCache.get(String.valueOf(i)));
                System.out.println("Map Size by: " + Thread.currentThread().getName() + " -> " + configCache.size());
                i++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        };

        Runnable write = () -> {
            while (!Thread.currentThread().isInterrupted()){
                int i = counter.getAndIncrement();
                System.out.println("Write Thread " + Thread.currentThread().getName() + " put: key=" + i + " value=" + i);
                configCache.put(String.valueOf(i), String.valueOf(i));
                System.out.println("Write Thread " + Thread.currentThread().getName() + " Sleeping 5 seconds");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                    break;
                }
            }
        };

        Thread w1 = new Thread(write, "Write 1");
        Thread w2 = new Thread(write, "Write 2");
        Thread r1 = new Thread(read, "Reader 1");
        Thread r2 = new Thread(read, "Reader 2");
        Thread r3 = new Thread(read, "Reader 3");
        Thread r4 = new Thread(read, "Reader 4");
        Thread r5 = new Thread(read, "Reader 5");
        Thread r6 = new Thread(read, "Reader 6");
        Thread r7 = new Thread(read, "Reader 7");
        Thread r8 = new Thread(read, "Reader 8");
        Thread r9 = new Thread(read, "Reader 9");

        List<Thread> threads = List.of(w1, w2, r1, r2, r3, r4, r5, r6, r7, r8, r9);
        threads.forEach(Thread::start);

        Thread.sleep(5000);
        threads.forEach(Thread::interrupt);
    }
}