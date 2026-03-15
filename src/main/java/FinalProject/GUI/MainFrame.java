package FinalProject.GUI;
import FinalProject.Service.PetService;
import lombok.extern.log4j.Log4j2;
import javax.swing.*;

@Log4j2
public class MainFrame extends JFrame {
    private final PetService service;
    private final JTextArea logArea = new JTextArea();

    public MainFrame(PetService service) {
        this.service = service;
        setTitle("Pet Adoption System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Register", new RegisterPanel(service));
        tabbedPane.addTab("Search", new SearchPanel(service));
        tabbedPane.addTab("Summary", new ReportPanel(service));

        logArea.setEditable(false);
        tabbedPane.addTab("Notifications", new JScrollPane(logArea));

        add(tabbedPane);
        setVisible(true);
    }

    public void appendLog(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
