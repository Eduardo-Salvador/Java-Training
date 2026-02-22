package Threads.E1_Thread_Synchronized_ThreadSafe_Deadlock;
import Threads.E1_Thread_Synchronized_ThreadSafe_Deadlock.Domain.Members;
import Threads.E1_Thread_Synchronized_ThreadSafe_Deadlock.Services.EmailDeliveryService;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Members members = new Members();
        Thread t1 = new Thread(new EmailDeliveryService(members), "Thread Mercedes");
        Thread t2 = new Thread(new EmailDeliveryService(members), "Thread Ferrari");
        t1.start();
        t2.start();

        while(true) {
            String email = JOptionPane.showInputDialog("Enter an email address");
            if (email == null || email.isEmpty()) {
                members.close();
                break;
            }
            members.addMemberEmail(email);
        }
    }
}