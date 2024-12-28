package cs.vsu.oop2.panels;
import cs.vsu.oop2.FacultyDatabase;
import cs.vsu.oop2.models.Subject;

import javax.swing.*;
import java.awt.*;

public class SubjectsPanel extends JPanel {
    public SubjectsPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<String> subjectListModel = new DefaultListModel<>();
        JList<String> subjectList = new JList<>(subjectListModel);
        add(new JScrollPane(subjectList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        JTextField subjectNameField = new JTextField();
        JButton addButton = new JButton("Add Subject");

        inputPanel.add(new JLabel("Subject Name:"));
        inputPanel.add(subjectNameField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String subjectName = subjectNameField.getText().trim();
            if (subjectName.matches("[a-zA-Z\\s]+")) {
                FacultyDatabase.addSubject(new Subject(subjectName));
                subjectListModel.addElement(subjectName);
                subjectNameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Subject name must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
