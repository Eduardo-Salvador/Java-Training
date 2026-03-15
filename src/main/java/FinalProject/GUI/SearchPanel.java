package FinalProject.GUI;
import FinalProject.Domain.*;
import FinalProject.Exception.PetNotFoundException;
import FinalProject.Service.PetService;
import lombok.extern.log4j.Log4j2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Log4j2
public class SearchPanel extends JPanel {

    private final PetService service;

    private final JTextField nameField  = new JTextField(15);
    private final JTextField breedField = new JTextField(15);
    private final JComboBox<PetType> typeCombo = new JComboBox<>(PetType.values());

    private final String[] columns = {"ID", "Name", "Type", "Sex", "Age", "Weight", "Breed", "Address", "Status"};
    private final DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
    };
    private final JTable table = new JTable(tableModel);

    public SearchPanel(PetService service) {
        this.service = service;
        setLayout(new BorderLayout(5, 5));

        JPanel searchBar = new JPanel();
        searchBar.add(new JLabel("Name:"));
        searchBar.add(nameField);
        searchBar.add(new JLabel("Breed:"));
        searchBar.add(breedField);
        searchBar.add(new JLabel("Type:"));
        searchBar.add(typeCombo);

        JButton searchButton = new JButton("Search");
        JButton adoptButton  = new JButton("Adopt");
        JButton deleteButton = new JButton("Delete");

        searchBar.add(searchButton);
        searchBar.add(adoptButton);
        searchBar.add(deleteButton);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(searchBar, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        searchButton.addActionListener(e -> search());
        adoptButton.addActionListener(e  -> adopt());
        deleteButton.addActionListener(e -> delete());
    }

    private void search() {
        SwingUtilities.invokeLater(() -> {
            try {
                String name  = nameField.getText();
                String breed = breedField.getText();
                List<PetResponseDTO> results;

                if (!name.isBlank() || !breed.isBlank()) {
                    results = service.findByNameAndBreed(name, breed);
                } else {
                    results = service.findByType((PetType) typeCombo.getSelectedItem());
                }

                populateTable(results);
            } catch (PetNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Not found", JOptionPane.WARNING_MESSAGE);
            } catch (Exception e) {
                log.error("Unexpected error on search: {}", e.getMessage());
                JOptionPane.showMessageDialog(this, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void adopt() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a pet to adopt..");
            return;
        }
        SwingUtilities.invokeLater(() -> {
            try {
                Long id = (Long) tableModel.getValueAt(row, 0);
                service.adopt(id);
                JOptionPane.showMessageDialog(this, "Pet successfully adopted.!");
                search();
            } catch (PetNotFoundException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Not found", JOptionPane.WARNING_MESSAGE);
            } catch (Exception e) {
                log.error("Unexpected error on adopt: {}", e.getMessage());
                JOptionPane.showMessageDialog(this, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void delete() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a pet to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to delete this pet?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        SwingUtilities.invokeLater(() -> {
            try {
                Long id = (Long) tableModel.getValueAt(row, 0);
                service.delete(id);
                search();
            } catch (Exception e) {
                log.error("Unexpected error on delete: {}", e.getMessage());
                JOptionPane.showMessageDialog(this, "Unexpected error.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void populateTable(List<PetResponseDTO> list) {
        tableModel.setRowCount(0);
        list.forEach(p -> tableModel.addRow(new Object[]{
                p.id(), p.name(), p.type(), p.sex(),
                p.age(), p.weight(), p.breed(), p.address(), p.status()
        }));
    }
}