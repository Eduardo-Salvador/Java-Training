package GUI.SimpleTests;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimpleGui {
    private JFrame frame;
    private JLabel label;
    private Image image;

    public SimpleGui(){
        image = new ImageIcon(
                getClass().getResource("/GUI/dobermann.jpg")
        ).getImage();
    }

    public static void main(String[] args) {
        SimpleGui gui = new SimpleGui();
        gui.go();
    }

    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("I'm a label");

        JButton labelButton = new JButton("Change label");
        labelButton.addActionListener(event -> label.setText("Ouch!"));

        JButton button = new JButton("Change circle");
        button.addActionListener(event -> frame.repaint());

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(2,1));

        eastPanel.add(new ImagePainel());
        eastPanel.add(labelButton);

        frame.add(BorderLayout.EAST, eastPanel);

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, new MyDrawPainel());
        frame.getContentPane().add(BorderLayout.EAST, eastPanel);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.setSize(1500, 750);
        frame.setVisible(true);
    }

    /* class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evet){
            label.setText("Ouch!");
        }
    }

    class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            frame.repaint();
        }
    } */

    static class MyDrawPainel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            Graphics2D g2d = (Graphics2D) g;
            Random random = new Random();

            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor1 = new Color(red, green, blue);

            red = random.nextInt(256);
            green = random.nextInt(256);
            blue = random.nextInt(256);
            Color randomColor2 = new Color(red, green, blue);

            GradientPaint gradientPaint =
                    new GradientPaint(70, 70, randomColor1, 150, 150, randomColor2);

            g2d.setPaint(gradientPaint);
            g2d.fillOval(70,70,100,100);
        }
    }

    class ImagePainel extends JPanel {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 500);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}