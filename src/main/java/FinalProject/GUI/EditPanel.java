package FinalProject.GUI;
import FinalProject.Domain.*;
import FinalProject.Exception.PetNotFoundException;
import FinalProject.Exception.PetValidationException;
import FinalProject.Service.PetService;
import lombok.extern.log4j.Log4j2;
import javax.swing.*;
import java.awt.*;

@Log4j2
public class EditPanel extends JPanel {
    private final PetService service;
    private final ReportPanel reportPanel;
    private final JTextField idField     = new JTextField(20);
    private final JTextField nameField   = new JTextField(20);
    private final JTextField breedField  = new JTextField(20);
    private final JTextField addressField = new JTextField(20);
    private final JTextField ageField    = new JTextField(20);
    private final JTextField weightField = new JTextField(20);
    private final JComboBox<PetType>   typeCombo   = new JComboBox<>(PetType.values());
    private final JComboBox<PetSex>    sexCombo    = new JComboBox<>(PetSex.values());
    private final JComboBox<PetStatus> statusCombo = new JComboBox<>(PetStatus.values());

    public EditPanel(PetService service, ReportPanel reportPanel) {
        this.service = service;
        this.reportPanel = reportPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        idField.setEditable(true);

        addRow(gbc, 0, "ID:",      idField);
        addRow(gbc, 1, "Name:",    nameField);
        addRow(gbc, 2, "Breed:",   breedField);
        addRow(gbc, 3, "Address:", addressField);
        addRow(gbc, 4, "Age:",     ageField);
        addRow(gbc, 5, "Weight:",  weightField);
        addRow(gbc, 6, "Type:",    typeCombo);
        addRow(gbc, 7, "Sex:",     sexCombo);
        addRow(gbc, 8, "Status:",  statusCombo);

        JButton loadButton   = new JButton("Load by ID");
        JButton updateButton = new JButton("Update");
        JPanel buttons = new JPanel();
        buttons.add(loadButton);
        buttons.add(updateButton);
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(buttons, gbc);
        loadButton.addActionListener(e -> load());
        updateButton.addActionListener(e -> update());
    }

    private void addRow(GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    private void load() {
        SwingUtilities.invokeLater(() -> {
            try {
                Long id = Long.parseLong(idField.getText());
                service.findById(id).ifPresentOrElse(
                        this::populateForm,
                        () -> JOptionPane.showMessageDialog(this, "Pet not found.", "Not Found", JOptionPane.WARNING_MESSAGE)
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                log.error("Unexpected error on load: {}", e.getMessage());
                JOptionPane.showMessageDialog(this, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void populateForm(PetResponseDTO dto) {
        idField.setText(String.valueOf(dto.id()));
        nameField.setText(dto.name());
        breedField.setText(dto.breed());
        addressField.setText(dto.address());
        ageField.setText(String.valueOf(dto.age()));
        weightField.setText(String.valueOf(dto.weight()));
        typeCombo.setSelectedItem(dto.type());
        sexCombo.setSelectedItem(dto.sex());
        statusCombo.setSelectedItem(dto.status());
    }

    private void update() {
        SwingUtilities.invokeLater(() -> {
            try {
                Pet pet = Pet.PetBuilder.aPet()
                        .withName(nameField.getText())
                        .withType((PetType) typeCombo.getSelectedItem())
                        .withSex((PetSex) sexCombo.getSelectedItem())
                        .withAge(Double.parseDouble(ageField.getText()))
                        .withWeight(Double.parseDouble(weightField.getText()))
                        .withBreed(breedField.getText())
                        .withAddress(addressField.getText())
                        .withStatus((PetStatus) statusCombo.getSelectedItem())
                        .build();
                pet.setId(Long.parseLong(idField.getText()));

                service.update(pet);
                JOptionPane.showMessageDialog(this, "Pet updated successfully!");
                reportPanel.refresh();
            } catch (PetNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Not Found", JOptionPane.WARNING_MESSAGE);
            } catch (PetValidationException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                log.error("Unexpected error on update: {}", e.getMessage());
                JOptionPane.showMessageDialog(this, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}