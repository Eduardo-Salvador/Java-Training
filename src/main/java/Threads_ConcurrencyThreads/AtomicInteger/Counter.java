package Threads_ConcurrencyThreads.AtomicInteger;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger count;

    public Counter(){
        count = new AtomicInteger(0);
    }

    void increment(){
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}