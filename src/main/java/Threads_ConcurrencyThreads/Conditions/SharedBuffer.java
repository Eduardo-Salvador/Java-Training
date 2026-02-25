package Threads_ConcurrencyThreads.Conditions;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedBuffer {
    public final int[] buffer = new int[10];
    ReentrantLock lock = new ReentrantLock();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    private int count;

    public void produce(int value) throws InterruptedException{
        lock.lock();
        try {
            while (buffer.length == count){
                notFull.await();
            }
            buffer[count++] = value;
            System.out.println("Produced: " + value + " by " + Thread.currentThread().getName());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }
            int value = buffer[--count];
            System.out.println("Consumed: " + value + " by " + Thread.currentThread().getName());
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}