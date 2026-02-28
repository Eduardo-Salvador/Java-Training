package Threads_ConcurrentThreads.Conditions;

public class Consumer implements Runnable{
    SharedBuffer buffer;

    public Consumer(SharedBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        try {
            for (int i = 0; i < 10; i++) {
               buffer.consume();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}