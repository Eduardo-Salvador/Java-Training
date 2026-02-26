package Threads_ConcurrentDataStructures.ArrayBlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DownloadManager {
    private BlockingQueue<String> downloadSlot = new ArrayBlockingQueue<>(3);

    public BlockingQueue<String> getDownloadSlot() {
        return downloadSlot;
    }

    public static void main(String[] args) {
        DownloadManager manager = new DownloadManager();
        Runnable producer = () -> {
            for (int j = 0; j < 5; j++) {
                try {
                    manager.getDownloadSlot().put("Requisition " + j);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + " queued: " + "Requisition " + j);
            }
        };

        Thread p1 = new Thread(producer, "Producer 1");
        Thread p2 = new Thread(producer, "Producer 2");
        Thread c1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String item = manager.getDownloadSlot().take();
                    System.out.println("Downloaded: " + item);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        p1.start();
        p2.start();
        c1.start();
    }
}