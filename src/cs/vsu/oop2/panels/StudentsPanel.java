package cs.vsu.oop2.panels;
import cs.vsu.oop2.FacultyDatabase;
import cs.vsu.oop2.FacultyScheduleGUI;
import cs.vsu.oop2.models.Student;

import javax.swing.*;
import java.awt.*;

public class StudentsPanel extends JPanel {
    public StudentsPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<String> studentListModel = new DefaultListModel<>();
        JList<String> studentList = new JList<>(studentListModel);
        add(new JScrollPane(studentList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JButton addButton = new JButton("Add Student");

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            if (id.matches("\\d+") && name.matches("[a-zA-Z\\s]+")) {
                FacultyDatabase.addStudent(new Student(id, name)); // Добавляем студента в базу
                studentListModel.addElement(id + ": " + name);
                idField.setText("");
                nameField.setText("");

                // Обновляем список студентов в `GroupsPanel`
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    if (frame instanceof FacultyScheduleGUI) {
                        FacultyScheduleGUI gui = (FacultyScheduleGUI) frame;
                        ((GroupsPanel) gui.getGroupsPanel()).refreshStudentComboBox();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. ID must be numeric and name must contain only letters.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
