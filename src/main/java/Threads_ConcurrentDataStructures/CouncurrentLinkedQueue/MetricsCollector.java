package Threads_ConcurrentDataStructures.CouncurrentLinkedQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MetricsCollector {
    private final ConcurrentLinkedQueue<String> logEvents = new ConcurrentLinkedQueue<>();
    volatile boolean run = true;
    AtomicInteger activeProducers = new AtomicInteger(4);

    public ConcurrentLinkedQueue<String> getLogEvents() {
        return logEvents;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public static void main(String[] args) {
        MetricsCollector metricsCollector = new MetricsCollector();

        Runnable producer = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("event-" + (i + 1) + " successfully inserted by: " + Thread.currentThread().getName());
                metricsCollector.getLogEvents().offer("event-" + (i + 1));
            }
            if (metricsCollector.activeProducers.decrementAndGet() == 0) {
                metricsCollector.setRun(false);
            }
        };

        Runnable consumer = () -> {
            while (metricsCollector.isRun() || !metricsCollector.getLogEvents().isEmpty()){
                String log = metricsCollector.getLogEvents().poll();
                if (log == null) {
                    System.out.println("Queue empty, awaiting...");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(Thread.currentThread().getName() + " processed: " + log);
            }
        };

        for (int i = 0; i < 4; i++) {
            Thread p = new Thread(producer, "producer-" + (i + 1));
            p.start();
            if (i < 2) {
                Thread c = new Thread(consumer, "consumer-" + (i + 1));
                c.start();
            }
        }
    }
}