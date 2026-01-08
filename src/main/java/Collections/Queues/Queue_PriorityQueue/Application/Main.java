package Collections.Queues.Queue_PriorityQueue.Application;
import Collections.Queues.Queue_PriorityQueue.Domain.Patient;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Patient p1 = new Patient("Eduardo", 5);
        Patient p2 = new Patient("Jose", 3);
        Patient p3 = new Patient("Helena", 7);
        Patient p4 = new Patient("Nathalia", 2);
        Patient p5 = new Patient("Maria", 1);

        Queue<Patient> patientQueue = new PriorityQueue<>();
        patientQueue.add(p1);
        patientQueue.add(p2);
        patientQueue.add(p3);
        patientQueue.add(p4);
        patientQueue.add(p5);

        System.out.println("Serving by priority: ");
        System.out.println(patientQueue.poll());
        System.out.println(patientQueue.poll());
        System.out.println(patientQueue.poll());
        System.out.println(patientQueue.poll());
        System.out.println(patientQueue.poll());

        System.out.println("-----------------------");
        System.out.println("Actual Queue:");
        patientQueue.forEach(System.out::println);
    }
}