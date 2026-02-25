package Threads_ConcurrencyThreads.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(new Workers(counter));
        Thread t2 = new Thread(new Workers(counter));
        Thread t3 = new Thread(new Workers(counter));
        Thread t4 = new Thread(new Workers(counter));
        Thread t5 = new Thread(new Workers(counter));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println("Final lock result: " + counter.getValue());
        System.out.println("Final tryLock result: " + counter.getValueTryLock());
    }
}