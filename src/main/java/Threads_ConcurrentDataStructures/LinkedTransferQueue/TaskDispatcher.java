package Threads_ConcurrentDataStructures.LinkedTransferQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class TaskDispatcher {
    private final LinkedTransferQueue<String> tasks = new LinkedTransferQueue<>();
    volatile boolean run = true;

    public LinkedTransferQueue<String> getTasks() {
        return tasks;
    }

    public boolean getRun() {
        return run;
    }

    public void setRun(boolean rodando) {
        this.run = rodando;
    }

    public static void main(String[] args) throws InterruptedException {
        TaskDispatcher dispatcher = new TaskDispatcher();
        for (int i = 0; i < 20; i++) {
            System.out.println("Dispatching: task-" + i + " by: " + Thread.currentThread().getName());
            dispatcher.getTasks().put("task-" + i);
        }

        Runnable consumer = () -> {
            try {
                while (dispatcher.getRun() || !dispatcher.getTasks().isEmpty()) {
                    String task = dispatcher.getTasks().poll(500, TimeUnit.MILLISECONDS);
                    if (task == null) continue;
                    System.out.println(Thread.currentThread().getName() + " received: " + task);
                    Thread.sleep(400);
                    System.out.println(Thread.currentThread().getName() + " finished: " + task);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable producer = () -> {
            for (int i = 0; i < 6; i++) {
                try {
                    System.out.println("Dispatching: task-" + i + " by " + Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + " task-" + i + " await consumer");
                    dispatcher.getTasks().transfer("task-" + i);
                    System.out.println("Producer task-" + i + " confirmed delivered");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            dispatcher.setRun(false);
        };

        Thread c1 = new Thread(consumer, "Consumer-1");
        Thread c2 = new Thread(consumer, "Consumer-2");
        Thread p1 = new Thread(producer, "Producer-1");

        c1.start();
        c2.start();
        p1.start();
    }
}