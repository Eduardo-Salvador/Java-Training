package FinalProject.GUI;
import FinalProject.Service.PetService;
import lombok.extern.log4j.Log4j2;
import javax.swing.*;
import java.awt.*;

@Log4j2
public class ReportPanel extends JPanel {

    private final PetService service;
    private final JTextArea reportArea = new JTextArea(20, 50);

    public ReportPanel(PetService service) {
        this.service = service;
        setLayout(new BorderLayout(5, 5));

        reportArea.setEditable(false);

        JButton refreshButton = new JButton("Update");
        refreshButton.addActionListener(e -> refresh());

        add(new JScrollPane(reportArea), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        refresh();
    }

    private void refresh() {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();

            sb.append("=== Total per Type ===\n");
            service.countByType()
                    .forEach((type, count) -> sb.append(type).append(": ").append(count).append("\n"));

            sb.append("\n=== Average Weight by Type ===\n");
            service.averageWeightByType()
                    .forEach((type, avg) -> sb.append(type).append(": ").append(String.format("%.2f lb", avg)).append("\n"));

            sb.append("\n=== Oldest Pet ===\n");
            service.findOldest().ifPresentOrElse(
                    p -> sb.append(p.name()).append(" | ").append(p.type()).append(" | ").append(p.age()).append(" year\n"),
                    () -> sb.append("No pets registered..\n")
            );

            reportArea.setText(sb.toString());
        });
    }
}