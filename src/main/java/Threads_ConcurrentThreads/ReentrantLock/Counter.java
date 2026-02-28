package Threads_ConcurrentThreads.ReentrantLock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int value = 0;
    private int valueTryLock = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            value++;
        } finally {
            lock.unlock();
        }
    }

    public void tryIncrement(){
        boolean isLocked = lock.tryLock();
        if (isLocked) {
            try {
                valueTryLock++;
            } finally {
                lock.unlock();
            }
        }
    }

    public int getValue() {
        return value;
    }

    public int getValueTryLock() {
        return valueTryLock;
    }
}