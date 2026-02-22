package Threads.E1_Thread_Synchronized_ThreadSafe_Deadlock.Domain;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Members {
    private final Queue<String> emails = new ArrayBlockingQueue<>(10); //Thread Safe!
    private boolean open = true;

    public boolean isOpen() {
        return open;
    }

    public int pendingEmails() {
        synchronized (emails) {
            return emails.size();
        }
    }

    public void addMemberEmail(String email) {
        synchronized (this.emails) {
            System.out.println(Thread.currentThread().getName() + " Added the email: " + email);
            this.emails.add(email);
            this.emails.notifyAll();
        }
    }

    public String retrieveEmail() {
        System.out.println(Thread.currentThread().getName() + " Checking if there are emails");
        synchronized (this.emails) {
            while (this.emails.isEmpty()) {
                if (!open) return null;
                System.out.println(Thread.currentThread().getName() + " No emails, waiting mode");
                try {
                    this.emails.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            return this.emails.poll();
        }
    }

    public void close () {
        open = false;
        synchronized (this.emails) {
            System.out.println("Sleeping all Threads");
        }
    }
}