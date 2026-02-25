package Threads_ConcurrencyThreads.Conditions;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();

        Thread p1 = new Thread(new Producer(buffer), "Producer 1");
        Thread p2 = new Thread(new Producer(buffer), "Producer 2");

        Thread c1 = new Thread(new Consumer(buffer), "Consumer 1");
        Thread c2 = new Thread(new Consumer(buffer), "Consumer 2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();
        c1.join();
        c2.join();
    }
}