package GUI.Challenge.View;
import GUI.Challenge.Controller.CheckDatas;
import javax.swing.*;
import java.awt.*;

public class RegistrationForm {
    public void go(){
        JFrame jFrame = new JFrame("Registration Form");
        JPanel jPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name:");
        JTextField name = new JTextField(40);
        JPanel jPanelName = new JPanel();

        JLabel emailLabel = new JLabel("Email:");
        JTextField email = new JTextField(40);
        JPanel jPanelEmail = new JPanel();

        JLabel ageLabel = new JLabel("Age:");
        JTextField age = new JTextField(40);
        JPanel jPanelAge = new JPanel();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField(40);
        JPanel jPanelPassword = new JPanel();

        JButton register = new JButton("Register");;

        register.addActionListener(e -> {
            String nameText = name.getText().trim();
            String emailText = email.getText().trim();
            String ageText = age.getText().trim();
            String passwordText = new String(password.getPassword()).trim();
            String error = CheckDatas.validate(nameText, emailText, ageText, passwordText);

            if (error != null) {
                JOptionPane.showMessageDialog(jFrame, error, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int ageInt = Integer.parseInt(ageText);
            jFrame.dispose();
            new Confirmation(nameText, emailText, ageInt);
        });

        jPanel.setLayout(new GridLayout(4, 1, 10, 10));

        jPanelName.add(nameLabel);
        jPanelName.add(name);
        jPanel.add(jPanelName);

        jPanelEmail.add(emailLabel);
        jPanelEmail.add(email);
        jPanel.add(jPanelEmail);

        jPanelAge.add(ageLabel);
        jPanelAge.add(age);
        jPanel.add(jPanelAge);

        jPanelPassword.add(passwordLabel);
        jPanelPassword.add(password);
        jPanel.add(jPanelPassword);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600, 600);
        jFrame.getContentPane().add(BorderLayout.CENTER, jPanel);
        jFrame.getContentPane().add(BorderLayout.SOUTH, register);
        jFrame.setVisible(true);
    }
}