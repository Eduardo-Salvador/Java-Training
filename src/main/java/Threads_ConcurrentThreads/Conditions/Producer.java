package Threads_ConcurrentThreads.Conditions;
import java.util.Random;

public class Producer implements Runnable{
    private static Random random = new Random();
    private SharedBuffer buffer;

    public Producer(SharedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        try {
            for (int i = 0; i < 10; i++) {
                buffer.produce(random.nextInt(101));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}