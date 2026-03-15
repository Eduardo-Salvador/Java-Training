package FinalProject.GUI;
import FinalProject.Service.PetService;
import javax.swing.*;

public class MainFrame extends JFrame {
    private final PetService service;
    private final JTextArea logArea = new JTextArea();

    public MainFrame(PetService service) {
        this.service = service;
        setTitle("Pet Adoption System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        ReportPanel reportPanel = new ReportPanel(service);

        tabbedPane.addTab("Register",      new RegisterPanel(service, reportPanel));
        tabbedPane.addTab("Search",        new SearchPanel(service, reportPanel));
        tabbedPane.addTab("Edit",          new EditPanel(service, reportPanel));
        tabbedPane.addTab("Summary",       reportPanel);

        logArea.setEditable(false);
        tabbedPane.addTab("Notifications", new JScrollPane(logArea));

        add(tabbedPane);
        setVisible(true);
    }

    public void appendLog(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}