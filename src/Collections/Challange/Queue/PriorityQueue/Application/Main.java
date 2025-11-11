package Collections.Challange.Queue.PriorityQueue.Application;
import Collections.Challange.Queue.PriorityQueue.Controller.PatientManager;
import Collections.Challange.Queue.PriorityQueue.Domain.Patient;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Patient> normalQueue = new LinkedList<>();
        PriorityQueue<Patient> urgentQueue = new PriorityQueue<>();

        Patient p1 = new Patient("João Silva", 0);
        Patient p2 = new Patient("Maria Oliveira", 1);
        Patient p3 = new Patient("Carlos Souza", 0);
        Patient p4 = new Patient("Ana Costa", 3);
        Patient p5 = new Patient("Pedro Santos", 2);

        PatientManager.addPatient(normalQueue, urgentQueue, p1);
        PatientManager.addPatient(normalQueue, urgentQueue, p2);
        PatientManager.addPatient(normalQueue, urgentQueue, p3);
        PatientManager.addPatient(normalQueue, urgentQueue, p4);
        PatientManager.addPatient(normalQueue, urgentQueue, p5);
        System.out.println("-----------------------------------");

        PatientManager.service(normalQueue, urgentQueue);
        PatientManager.service(normalQueue, urgentQueue);
        PatientManager.service(normalQueue, urgentQueue);
        PatientManager.service(normalQueue, urgentQueue);
        PatientManager.service(normalQueue, urgentQueue);
    }
}