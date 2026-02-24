package Threads.E1_Thread_Synchronized_ThreadSafe_Deadlock.Services;
import Threads.E1_Thread_Synchronized_ThreadSafe_Deadlock.Domain.Members;

public class EmailDeliveryService implements Runnable {
    private final Members members;

    public EmailDeliveryService(Members members) {
        this.members = members;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Starting deliver emails");
        while(members.isOpen() || members.pendingEmails() > 0) {
            try {
                String email = members.retrieveEmail();
                if(email == null) continue;
                System.out.println(Thread.currentThread().getName() + " sending email: " + email);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}