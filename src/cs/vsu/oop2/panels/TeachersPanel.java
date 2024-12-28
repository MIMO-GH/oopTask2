package cs.vsu.oop2.panels;
import cs.vsu.oop2.FacultyDatabase;
import cs.vsu.oop2.models.Teacher;

import javax.swing.*;
import java.awt.*;

public class TeachersPanel extends JPanel {
    public TeachersPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<String> teacherListModel = new DefaultListModel<>();
        JList<String> teacherList = new JList<>(teacherListModel);
        add(new JScrollPane(teacherList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JButton addButton = new JButton("Add Teacher");

        inputPanel.add(new JLabel("Teacher ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Teacher Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            if (id.matches("\\d+") && name.matches("[a-zA-Z\\s]+")) {
                FacultyDatabase.addTeacher(new Teacher(id, name));
                teacherListModel.addElement(id + ": " + name);
                idField.setText("");
                nameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. ID must be numeric and name must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}


