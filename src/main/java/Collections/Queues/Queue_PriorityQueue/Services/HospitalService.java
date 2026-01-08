package Collections.Queues.Queue_PriorityQueue.Services;
import Collections.Queues.Queue_PriorityQueue.Domain.Patient;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HospitalService {
    private final PriorityQueue<Patient> priorityQueue;
    private final LinkedList<Patient> normalQueue;

    public HospitalService() {
        this.priorityQueue = new PriorityQueue<>();
        this.normalQueue = new LinkedList<>();
    }

    public void add(Patient p) {
        if (p.getPriority() <= 3) {
            priorityQueue.add(p);
        } else {
            normalQueue.add(p);
        }
    }

    public void service() {
        if (!priorityQueue.isEmpty()) {
            System.out.println("Priority service: ");
            System.out.println(priorityQueue.peek());
            priorityQueue.poll();
            System.out.println("\nNext in Queue: ");
            if (!priorityQueue.isEmpty()) {
                System.out.println(priorityQueue.peek());
                System.out.println("------------------");
                return;
            }
            System.out.println(normalQueue.peek());
            System.out.println("------------------");
            return;
        }
        if (!normalQueue.isEmpty()) {
            System.out.println("Normal service: ");
            System.out.println(normalQueue.peek());
            normalQueue.poll();
            System.out.println("\nNext in Queue: ");
            System.out.println(normalQueue.peek());
            System.out.println("------------------");
            return;
        }
        System.out.println("No patients in queue.");
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty() && normalQueue.isEmpty();
    }
}