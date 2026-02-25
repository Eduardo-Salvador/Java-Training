package Threads_ConcurrencyThreads.ReentrantLock;

public class Workers implements Runnable{
    private final Counter counter;

    public Workers(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
        for (int i = 0; i < 1000; i++) {
            counter.tryIncrement();
        }
    }
}