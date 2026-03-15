package FinalProject.GUI;
import FinalProject.Domain.*;
import FinalProject.Exception.PetValidationException;
import FinalProject.Service.PetService;
import lombok.extern.log4j.Log4j2;
import javax.swing.*;
import java.awt.*;

@Log4j2
public class RegisterPanel extends JPanel {
    private final PetService service;
    private final ReportPanel reportPanel;
    private final JTextField nameField    = new JTextField(20);
    private final JTextField breedField   = new JTextField(20);
    private final JTextField addressField = new JTextField(20);
    private final JTextField ageField     = new JTextField(20);
    private final JTextField weightField  = new JTextField(20);
    private final JComboBox<PetType>   typeCombo   = new JComboBox<>(PetType.values());
    private final JComboBox<PetSex>    sexCombo    = new JComboBox<>(PetSex.values());
    private final JComboBox<PetStatus> statusCombo = new JComboBox<>(PetStatus.values());

    public RegisterPanel(PetService service, ReportPanel reportPanel) {
        this.service = service;
        this.reportPanel = reportPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addRow(gbc, 0, "Name:",     nameField);
        addRow(gbc, 1, "Breed:",     breedField);
        addRow(gbc, 2, "Address:", addressField);
        addRow(gbc, 3, "Age:",    ageField);
        addRow(gbc, 4, "Weight:",     weightField);
        addRow(gbc, 5, "Type:",     typeCombo);
        addRow(gbc, 6, "Sex:",     sexCombo);
        addRow(gbc, 7, "Stats:",   statusCombo);

        JButton submitButton = new JButton("Register");
        gbc.gridx = 1; gbc.gridy = 8;
        add(submitButton, gbc);

        submitButton.addActionListener(e -> register());
    }

    private void addRow(GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    private void register() {
        SwingUtilities.invokeLater(() -> {
            try {
                PetRequestDTO dto = new PetRequestDTO(
                        nameField.getText(),
                        (PetType)   typeCombo.getSelectedItem(),
                        (PetSex)    sexCombo.getSelectedItem(),
                        Double.parseDouble(ageField.getText()),
                        Double.parseDouble(weightField.getText()),
                        breedField.getText(),
                        addressField.getText(),
                        (PetStatus) statusCombo.getSelectedItem()
                );
                service.register(dto);
                JOptionPane.showMessageDialog(this, "Pet successfully registered.!");
                reportPanel.refresh();
                clearForm();
            } catch (PetValidationException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                log.error("Unexpected error on register: {}", e.getMessage());
                JOptionPane.showMessageDialog(this, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void clearForm() {
        nameField.setText("");
        breedField.setText("");
        addressField.setText("");
        ageField.setText("");
        weightField.setText("");
        typeCombo.setSelectedIndex(0);
        sexCombo.setSelectedIndex(0);
        statusCombo.setSelectedIndex(0);
    }
}