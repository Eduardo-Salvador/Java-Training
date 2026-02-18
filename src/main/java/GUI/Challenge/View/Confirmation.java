package GUI.Challenge.View;
import javax.swing.*;

public class Confirmation {
    public Confirmation(String name, String emailText, int age){
        JFrame summaryFrame = new JFrame("Summary");
        summaryFrame.setSize(400, 300);
        summaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea summary = new JTextArea();
        summary.setEditable(false);

        summary.setText(
                "Registration successful!\n\n" +
                        "Name: " + name + "\n" +
                        "Email: " + emailText + "\n" +
                        "Age: " + age
        );
        summaryFrame.add(summary);
        summaryFrame.setVisible(true);
    }
}