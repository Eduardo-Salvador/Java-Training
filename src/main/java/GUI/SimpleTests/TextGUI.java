package GUI.SimpleTests;
import javax.swing.*;
import java.awt.*;

public class TextGUI {
    public static void main(String[] args) {
        TextGUI textGUI = new TextGUI();
        textGUI.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("Just Click It");
        JTextArea textArea = new JTextArea(10,20);

        textArea.setLineWrap(true);
        button.addActionListener(e -> textArea.append("button clicked \n"));

        JScrollPane pane = new JScrollPane(textArea);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(pane);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}