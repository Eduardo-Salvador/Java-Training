package Threads_ConcurrentDataStructures.CopyOnWriteArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class EventManager {
    //private final List<String> copyOnWriteArrayList = new ArrayList<>();
    private final List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public void add(String listener){
        copyOnWriteArrayList.add(listener);
    }

    public List<String> getCopyOnWriteArrayList() {
        return copyOnWriteArrayList;
    }

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        eventManager.add("Listener 1");
        eventManager.add("Listener 2");
        eventManager.add("Listener 3");
        eventManager.add("Listener 4");
        eventManager.add("Listener 5");

        Runnable iterate = () -> {
            for (String s : eventManager.getCopyOnWriteArrayList()) {
                System.out.println(Thread.currentThread().getName() + " notifying: " + s);
            }
        };

        Thread t1 = new Thread(iterate, "Thread 1");
        Thread t2 = new Thread(iterate, "Thread 2");
        Thread t3 = new Thread(iterate, "Thread 3");

        t1.start();
        t2.start();
        t3.start();

        AtomicInteger i = new AtomicInteger(5);
        Thread t4 = new Thread(() -> {
            eventManager.getCopyOnWriteArrayList().add("Listener " + i.getAndIncrement());
            System.out.println("Added new listener: Listener " + i.get());
            System.out.println("Sleeping for 100ms");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        t4.start();
    }
}