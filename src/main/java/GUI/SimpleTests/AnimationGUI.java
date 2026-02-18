package GUI.SimpleTests;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class AnimationGUI {
    private int xPos = 70;
    private int yPos = 70;
    private JFrame frame;

    public static void main(String[] args) {
        AnimationGUI gui = new AnimationGUI();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPainel myDrawPainel = new MyDrawPainel();

        frame.getContentPane().add(myDrawPainel);
        frame.setSize(1500, 750);
        frame.setVisible(true);

        for (int i = 0; i < 542; i++) {
            xPos++;
            yPos++;

            myDrawPainel.repaint();

            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class MyDrawPainel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.ORANGE);
            g.fillOval(xPos, yPos, 100, 100);
        }
    }
}